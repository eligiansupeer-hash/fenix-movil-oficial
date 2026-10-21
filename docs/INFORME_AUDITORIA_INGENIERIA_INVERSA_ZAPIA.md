# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: ZAPIA AI

**Fecha:** 17 de octubre de 2026  
**Dispositivo de Prueba:** Motorola Moto G77 (Modelo `ZY22MK7B2P`, Android 14)  
**Paquete Auditado:** `com.brainlogic.zapia`  
**Versión de la App:** 1.50.0 (Split APK arm64-v8a)  
**Clasificación:** Asistente Personal de Productividad y Mensajería Multicanal  

---

## 1. RESUMEN EJECUTIVO
Zapia es un asistente de inteligencia artificial orientado a la automatización de productividad personal y mensajería en Latinoamérica. A diferencia de clientes LLM puros como Claude o ChatGPT, Zapia está estructurado como un concentrador de comunicaciones (hub de mensajería) con agentes de fondo que se conectan a cuentas de usuario de WhatsApp, Gmail, Notion, Outlook, Slack, Telegram, Google Calendar y Google Tasks.

Su arquitectura móvil está construida íntegramente sobre **Flutter AOT (Dart)** con librerías nativas de alto rendimiento para audio (`libflutter_soloud_plugin.so`) y puente FFI (`libdartjni.so`). En cuanto a extensibilidad externa, la aplicación opera bajo un modelo de plataforma cerrada: no cuenta con soporte ni conector para el protocolo MCP (Model Context Protocol), no ofrece sandbox de ejecución de código para el usuario, y toda la inferencia y conectores residen en sus microservicios propietarios en la nube.

---

## 2. ARQUITECTURA DE SOFTWARE Y BINARIOS NATIVOS

### 2.1 Inspección de Paquetes y Splits
* **APK Base:** `/data/app/~~XPDDujc083FjbZRJH6AMxQ==/com.brainlogic.zapia-Zl5KhwY-fNDp199LfpHj_Q==/base.apk`
* **Splits:**
  * `split_config.arm64_v8a.apk`
  * `split_config.es.apk`
  * `split_config.xxhdpi.apk`
* **Directorio de Datos:** `/data/user/0/com.brainlogic.zapia`

### 2.2 Binarios Nativos Extraídos (`arm64-v8a`)
| Binario | Tamaño | Propósito Técnico |
| :--- | :--- | :--- |
| `libapp.so` | **22.61 MB** | Código de la aplicación compilado AOT (Ahead-of-Time) desde Dart (widgets, lógica de negocio y state management). |
| `libflutter.so` | **14.01 MB** | Motor del framework Flutter (renderizado gráfico Skia/Impeller, event loop). |
| `libflutter_soloud_plugin.so` | **1.92 MB** | Motor de audio nativo C++ de baja latencia SoLoud para reproducción y captura fluida de notas de voz. |
| `libsuper_native_extensions.so` | **1.10 MB** | Manejo nativo avanzado de portapapeles, arrastrar y soltar (drag & drop) y enriquecimiento del portapapeles del sistema. |
| `libsentry.so` | **800 KB** | Núcleo C nativo del SDK de Sentry para captura de caídas (crash reporting) a nivel NDK. |
| `libdartjni.so` | **123 KB** | Puente de interoperabilidad directa JNI (Java Native Interface) desde Dart sin intermediación lenta de MethodChannels. |
| `libsentry-android.so` | **19.8 KB** | Conector JNI de Sentry con el runtime de Android. |
| `libandroidx.graphics.path.so` | **10.0 KB** | Renderizado acelerado por hardware de trazados vectoriales. |
| `libdatastore_shared_counter.so` | **7.1 KB** | Sincronización atómica de contadores en el almacenamiento de preferencias de Android. |
| `libirondash_engine_context_native.so` | **4.4 KB** | Utilidad nativa de soporte para motores de contexto Flutter. |

---

## 3. AUDITORÍA DE INTERFAZ DE USUARIO (UX/UI) Y FLUJOS OPERATIVOS

### 3.1 Estructura de Pantallas
1. **Home / Chat Multimodal:**
   * Barra superior con botón de menú lateral (indicador de chats sin leer), título dinámico y botón para nuevo chat.
   * Lista de mensajes con soporte de bloques de pensamiento ("Completados N pasos") desplegables.
   * Barra inferior con entrada de texto (`Pídeme lo que quieras`), botón `+` para adjuntos (imágenes, documentos) y botón de micrófono para notas de voz.
2. **Menú Lateral (Drawer):**
   * Historial cronológico de conversaciones.
   * Acceso directo a "Conectores" y "Configuración".
   * Indicadores de estado de cuenta.
3. **Panel de Conectores:**
   * Lista de servicios soportados con conmutadores (toggles) de conexión individual:
     * **WhatsApp:** Lectura de mensajes, transcripción automática de notas de voz, envío de texto y archivos multimedia.
     * **Gmail:** Búsqueda y lectura de correos, borradores, envío y respuestas.
     * **Notion:** Búsqueda en páginas y bases de datos, creación de registros.
     * **Outlook / Microsoft 365:** Correos y calendario corporativo.
     * **Slack:** Integración de canales y mensajes directos.
     * **Telegram:** Mensajería y alertas.
     * **Google Calendar & Tasks:** Gestión de agenda y tareas pendientes.
     * **Botón "Solicitar conector":** Canal de telemetría de demanda para que los usuarios sugieran nuevas APIs de terceros.

---

## 4. AUDITORÍA DE SEGURIDAD Y EJECUCIÓN (HUMAN-IN-THE-LOOP)

A través de la prueba técnica en vivo sobre el motor conversacional de Zapia, se verificaron los mecanismos de seguridad y autorización aplicados a las herramientas externas:
* **Principio de Aprobación Obligatoria (Human-in-the-Loop):**
  * Para operaciones de solo lectura (buscar correos en Gmail, consultar páginas en Notion, leer chats de WhatsApp), el agente ejecuta la consulta automáticamente sin interrumpir al usuario.
  * Para operaciones con efectos secundarios o mutaciones (enviar un mensaje de WhatsApp a un contacto, enviar un correo desde Gmail, modificar una base de datos en Notion), el agente **obliga al usuario a confirmar explícitamente la acción** en pantalla antes de ejecutar el llamado a la API.
* **Política de Confidencialidad del Modelo:**
  * El asistente tiene instrucciones de sistema estrictas que impiden divulgar el modelo de lenguaje fundacional utilizado (GPT-4o, Claude 3.5 Sonnet, Llama, etc.).

---

## 5. CAPACIDAD DE CONEXIÓN MCP Y HERRAMIENTAS DE DESARROLLO

1. **Soporte MCP (Model Context Protocol):**
   * **Inexistente:** Zapia no dispone de cliente ni servidor MCP en su arquitectura actual. No permite ingresar URLs de endpoints MCP (como sí lo hace Claude for Android), ni exponer sus propios conectores de mensajería para que otros agentes externos los consuman.
2. **Sandbox de Ejecución de Código:**
   * **Inexistente:** No cuenta con entorno aislado de ejecución de scripts en Python, Bash o JavaScript. Su propósito es exclusivamente la interacción en lenguaje natural y la ejecución de herramientas SaaS.

---

## 6. LECCIONES DE ARQUITECTURA PARA LA PWA FÉNIX

1. **Patrón Human-in-the-Loop para Herramientas de Escritura:**
   * Zapia demuestra una excelente implementación de seguridad donde las herramientas de lectura son automáticas pero cualquier mutación (creación de notas, alteración de archivos, llamadas a APIs externas) requiere validación explícita del usuario. Este patrón es fundamental para el motor de herramientas de la PWA Fénix.
2. **Motor de Audio de Baja Latencia (`SoLoud`):**
   * La inclusión de `libflutter_soloud_plugin.so` demuestra que para asistentes móviles multimodales que manejan notas de voz y transcripción en tiempo real, delegar el audio a un motor C++ dedicado evita bloqueos en el hilo de renderizado.
3. **Ausencia de Interoperabilidad Abierta:**
   * Al ser un sistema cerrado, Zapia no puede integrarse en flujos de trabajo de desarrollo avanzados o comunicarse con agentes locales. La PWA Fénix se diferencia radicalmente al adoptar el estándar abierto MCP y permitir la orquestación local y remota con soberanía absoluta de los datos.
