# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: CHATGPT ANDROID

**Fecha de Auditoría:** 17 de octubre de 2026  
**Dispositivo:** Motorola Moto G77 (Motorola penangf / `ZY22MK7B2P`), Android 14 (API 34)  
**Aplicación:** ChatGPT (`com.openai.chatgpt`)  
**Versión de App:** `1.2026.279`  
**Arquitectura Binaria:** ARM64-v8a  
**Directorio de Auditoría en Dispositivo:** `/sdcard/fenix_auditorias/04_chatgpt/`

---

## 1. RESUMEN EJECUTIVO & PERFIL DE LA APLICACIÓN

ChatGPT para Android representa un cliente móvil híbrido de alta complejidad que combina interfaces nativas en Jetpack Compose con un runtime nativo masivo en C++/Rust y pilas de streaming multimedia WebRTC para interacción de voz bidireccional en tiempo real (Advanced Voice Mode).

A diferencia de clientes más desacoplados o basados puramente en WebView, la app de OpenAI integra directamente binarios nativos dedicados a procesamiento de medios (`liblkjingle_peerconnection_so.so` de 108.4 MB) y su capa de exportación cruzada (`libopenai_xplat_export.so` de 17.2 MB).

---

## 2. INGENIERÍA INVERSA ESTÁTICA DEL PAQUETE (APK & SO)

### 2.1. Librerías Nativas Relevantes (`/lib/arm64-v8a`)
* **`liblkjingle_peerconnection_so.so` (108.4 MB):**
  - Implementación completa de la pila WebRTC (PeerConnection API de Google/Chromium/libjingle) compilada para Android ARM64.
  - Soporte de transporte full-duplex de baja latencia con códec Opus para Advanced Voice Mode, soporte de cancelación de eco acústico (AEC), supresión de ruido (NS) y negociación de canales de datos bidireccionales en tiempo real.
* **`libopenai_xplat_export.so` (17.2 MB):**
  - Núcleo multiplataforma C++/Rust de OpenAI. Encapsula la lógica de sincronización de estado, serialización de eventos de streaming, gestión de tokens locales, colas de mensajería y cifrado de transporte.
* **`libcapture.so` (2.6 MB):**
  - Capa de captura de vídeo y cámara en tiempo real para visión computacional interactiva en sesiones multimodales.
* **`libimagepipeline.so` (793 KB):**
  - Pipeline optimizado de procesamiento y compresión de imágenes antes de la subida al clúster de OpenAI.

### 2.2. Permisos y Capacidades Declaradas
* `RECORD_AUDIO` & `CAMERA`: Entrada continua para Voice Mode y Vision.
* `INTERNET` & `ACCESS_NETWORK_STATE`: Comunicación con endpoints de OpenAI (`chatgpt.com`, `api.openai.com`).
* `BLUETOOTH` / `BLUETOOTH_CONNECT`: Enrutamiento de audio a auriculares manos libres/BT LE Audio.

---

## 3. AUDITORÍA DE INTERFAZ, FLUJOS Y UXL

### 3.1. Navegación Principal y Drawer Lateral
* **Selector de Contexto:**
  - `Chat nuevo`
  - `Imágenes` (Modo DALL-E / Generación visual)
  - `Biblioteca` (Almacenamiento persistente de documentos y archivos subidos por el usuario)
  - `Proyectos` (Workspaces con instrucciones personalizadas y contexto compartido de documentos)
  - `Remoto` / `Se programó` (Automatizaciones y tareas programadas)
  - `Complementos` (Ecosistema de integraciones y GPT Actions)

### 3.2. Mecanismos de Seguridad y Permisos de Conectores
En la sección de gestión de complementos y GPT Actions, ChatGPT implementa tres políticas de seguridad granulares:
1. **"Preguntar siempre"**: Cada invocación de API externa requiere aprobación explícita del usuario.
2. **"Permitir acciones de lectura"**: Habilita endpoints GET/Safe automáticamente.
3. **"Permitir acciones de bajo riesgo"**: Ejecución fluida para consultas sin mutación de estado.

---

## 4. INTERROGATORIO TÉCNICO Y EVALUACIÓN DE ARQUITECTURA

### 4.1. Conectividad MCP (Model Context Protocol) vs Custom Actions
* **Soporte MCP Oficial:** OpenAI ha documentado e implementado soporte de servidores MCP en modalidades de escritorio y enterprise. En la app móvil para Android, las integraciones con servicios externos se gestionan principalmente a través del framework de **GPT Actions** (especificaciones OpenAPI 3.0 REST vía HTTPS) y complementos gestionados por cuenta.
* **Interoperabilidad:** Para integrar servicios compatibles con MCP a ChatGPT móvil, la pasarela recomendada es un puente **MCP-to-OpenAPI Gateway**, o la conexión mediante servidores MCP remotos configurados a nivel de Workspace/Proyecto.

### 4.2. Entorno de Ejecución Backend (Code Interpreter / Advanced Data Analysis)
* **Arquitectura de Sandbox:** Microentorno Linux aislado y efímero basado en contenedores (gVisor/Docker/Kube-based sandboxing).
* **Restricciones de Red:** El sandbox de Python carece de acceso a internet saliente (egress bloqueado) por razones de seguridad. No es posible realizar peticiones `requests` o `urllib` a APIs externas desde el sandbox de ejecución.
* **Persistencia:** Sistema de archivos efímero por sesión. Al expirar el tiempo de cómputo o resetear la sesión, los archivos temporales son purgados.

### 4.3. Cuotas y Límites de Almacenamiento y Documentos
| Parámetro | Límite Documentado / Observado |
| :--- | :--- |
| **Tamaño máximo por archivo individual** | 512 MB |
| **Límite de tokens por archivo de texto** | 2.000.000 tokens por documento |
| **Tamaño máximo por imagen individual** | 20 MB |
| **Archivos por proyecto (Plan Free)** | 5 archivos |
| **Archivos por proyecto (Plan Go / Plus)** | 25 archivos |
| **Archivos por proyecto (Pro / Team / Enterprise)** | 40 archivos |
| **Carga simultánea por operación** | Hasta 10 archivos |
| **Cuota de almacenamiento en Biblioteca (Free)** | 500 MB |
| **Carga de archivos en cuentas Free** | Hasta 3 archivos diarios |

---

## 5. COMPARATIVA Y VALOR PARA EL ECOSISTEMA FÉNIX

1. **Gestión de Medios WebRTC:** El enfoque de empaquetar `liblkjingle_peerconnection_so.so` demuestra cómo lograr latencia ultra baja en streaming de voz e interrupciones, modelo que Fénix puede adoptar vía WebRTC nativo o WebSockets de streaming.
2. **Biblioteca Unificada de Archivos:** La separación entre "Biblioteca" global y "Proyectos" con cuotas diferenciadas es una excelente pauta de diseño UX para organizar los documentos de cátedra y materias en la PWA académica.
3. **Políticas de Autorización de Herramientas:** El modelo de 3 niveles ("Preguntar siempre", "Lectura", "Bajo riesgo") es directamente trasladable a la gestión de llamadas a MCP Tools en Fénix.

---

## 6. REGISTRO DE EVIDENCIA FOTOGRÁFICA EN DISPOSITIVO
Las siguientes capturas han sido archivadas en `/sdcard/fenix_auditorias/04_chatgpt/`:
- `chatgpt_home.png`: Pantalla principal y barra de herramientas inferior.
- `chatgpt_drawer.png`: Menú lateral (Proyectos, Biblioteca, Complementos).
- `chatgpt_plugin_permissions.png`: Modos de seguridad y permisos de complementos.
- `chatgpt_audit_sent.png`: Envío de la batería técnica de preguntas.
- `chatgpt_response_scroll1.png` a `chatgpt_response_scroll7.png`: Despliegue de respuestas de auditoría.
- `chatgpt_final_response.png` a `chatgpt_final_response4.png`: Cuotas de proyectos, tokens y síntesis técnica.
