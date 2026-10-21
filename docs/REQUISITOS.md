# REQUISITOS DEL SISTEMA — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  
> **Última Actualización:** 2026-10-20T21:55:00-03:00

---

## 1. INTRODUCCIÓN Y ALCANCE
Fénix Móvil es una plataforma de desarrollo y orquestación de agentes de IA locales y remotos para dispositivos Android, basada en el Model Context Protocol (MCP), entornos aislados de terminal Linux y asistentes con memoria persistente, diseñada con principio de costo 0 absoluto y cero hardcodeo institucional.

---

## 2. REQUISITOS FUNCIONALES (RF)

- **[RF-01] Identidad y Logotipo Dinámico:**
  - El sistema debe presentar un Ave Fénix de fuego en formato vectorial animado (Lottie/AVD) en pantalla de bienvenida (110×110 px), barra superior (34×34 px) y como indicador de pensamiento en streaming (22×22 px).
- **[RF-02] Barra Superior (TopAppBar):**
  - Debe mostrar el logotipo oficial, el título `"Sesión Global"` y 5 iconos de estado: Nube (Sync), Chat (Sesión), Ajustes, Ayuda `?` y Avatar/Login en el extremo derecho (con Ayuda situado a su izquierda inmediata).
- **[RF-03] Hub de Bienvenida (Bento Grid):**
  - Debe proveer 6 accesos directos a paneles dedicados: *Sandbox Linux*, *Protocolo MCP*, *Interfaces UI*, *Terminal CLI*, *Editores & Ext.*, *Crear Apps*, más una tarjeta destacada de *Asistente con Memoria Persistente*.
- **[RF-04] Barra de Entrada Reactiva (Input Pill):**
  - Botón `+` en color dorado que despliega panel modal de herramientas.
  - Placeholder `"| Mensaje para Fénix..."` que desaparece al ingresar contenido.
  - Micrófono reactivo: azul claro en reposo, verde radiante con contador al grabar audio.
  - Enviar reactivo: gris inactivo en reposo, amarillo dorado activo al existir contenido en la caja.
- **[RF-05] Navegación Inferior (BottomBar):**
  - 5 pestañas fijas: *Fénix IA* (activa con píldora dorada), *Terminal* (`>_`), *F-Code*, *F-Docs* y *Mis Creaciones* (`🎨`, panel de UIs personalizadas en el extremo derecho).
- **[RF-06] Marco Perimetral HUD Cyberpunk:**
  - Borde con efecto de fuego térmico y acentos angulares en esquinas exteriores con cambio de tonalidades.
- **[RF-07] Selectores Dinámicos sobre el Input (`Modelos` y `Modo`):**
  - Dos chips translúcidos compactos situados a 8 dp sobre el campo de texto. Su etiqueta conmuta dinámicamente al modelo o modo seleccionado.
- **[RF-08] Gestor Jerárquico de Modelos (CLI, Edge LLM, API):**
  - Entornos CLI ordenados alfabéticamente con semáforo de 4 estados (Verde = Conexión OK, Rojo = Falla conexión, Azul = Revise cuota/login, Gris = "Instalar").
  - LLMs Locales con diagnóstico previo de hardware RAM (4, 8, 12, 16 GB) y semáforo de viabilidad (Verde = Apto, Amarillo = Lento, Rojo = Imposible).
  - Autodescubrimiento de modelos de API mediante consulta en tiempo real a endpoints (`/v1/models`), sin modelos hardcodeados. Submenú acordeón desplegable al tocar el proveedor conectado.
- **[RF-09] Asistente Guiado de Proveedores de IA:**
  - Panel interactivo con 3 campos (Nombre, URL, API Key), botón de soporte asistido, botón especial *"No sé el proveedor ni tengo API (Guiarme)"* y botón *"Enviar"* reactivo en verde.
- **[RF-10] Bóveda Segura de Credenciales (`secrets`):**
  - Las claves de API se guardan exclusivamente en almacenamiento seguro local y nunca se comparten en los prompts con los agentes de asistencia.
- **[RF-11] Relevo Automático y Bienvenida del Modelo:**
  - Tras verificar la conexión de un nuevo proveedor, el sistema conmuta la selección activa a este y la IA emite un saludo de bienvenida alegre con emojis.
- **[RF-12] Modos Axiom y Control de Razonamiento:**
  - 5 Modos Axiom (`General`, `Build`, `Plan`, `Deep Research`, `Amigable`) con selector de esfuerzo cognitivo (`Bajo`, `Medio`, `Fuerte`).
- **[RF-13] Resiliencia y Detección de Falla con Proxy Pool:**
  - Si un modelo no responde, se ofrece selección automática vía proxy pool (comprueba disponibilidad de otros modelos del proveedor) o selección manual en acordeón.
- **[RF-14] Fallback Crítico Multi-Key y Proveedor Automático:**
  - Si ningún modelo del proveedor responde, el sistema ofrece registrar API key secundaria, API key nueva o conmutar al proveedor automático más capacitado disponible.
- **[RF-15] Activación Soberana de Accesibilidad con Checkpoint Legal:**
  - Ficha dedicada para solicitar accesibilidad sin forzarla en el manifiesto. Panel informativo de pros/contras, exención de responsabilidad y checkbox obligatorio para desbloquear el botón `"Permitir"`.
- **[RF-16] Orquestación de Agentes para Configuración de Accesibilidad:**
  - Despacho automático de instrucciones de configuración a OpenCode. Cascada a Antigravity u otros CLIs en caso de indisponibilidad. Temporizador de 5 minutos para reintento directo sin re-solicitar exención.
- **[RF-17] Notificación Push e Intent Directo a Ajustes de Accesibilidad:**
  - Al completar la extensión, el botón conmuta a verde esmeralda y se envía una notificación local Android (`POST_NOTIFICATIONS`) que al pulsar abre directamente `Settings.ACTION_ACCESSIBILITY_SETTINGS`.
- **[RF-18] Consola Linux Nativa en Pestaña `>_ Terminal`:**
  - El sistema debe incorporar una terminal interactiva completa ejecutada sobre un sandbox Linux nativo, con emulación de códigos de escape ANSI a 60 FPS, soporte de colores, cursor titilante y redimensionamiento dinámico (SIGWINCH).
  - Incluye barra de teclas de control móviles sobre el teclado: `[ ESC ]` `[ TAB ]` `[ CTRL ]` `[ ALT ]` `[ | ]` `[ / ]` `[ - ]` `[ ^C ]`.
- **[RF-19] Ejecución de Agentes CLI de IA (OpenCode, Antigravity, Claude Code, Aider):**
  - La consola debe permitir la ejecución nativa de binarios de agentes CLI (empaquetados en Bun, Node.js o binarios Go/Rust) sin cuelgues, soportando modo headless interactivo o streaming.
- **[RF-20] Inyección Automática del Servidor MCP Local de Fénix en Agentes CLI:**
  - Al iniciar la sandbox, la aplicación inyecta automáticamente en `~/.config/opencode/opencode.json` (y configs equivalentes) el endpoint local del servidor Ktor MCP (`http://127.0.0.1:8080/mcp`) con su Bearer Token soberano, habilitando el uso inmediato de las 94 herramientas MCP de Fénix.
- **[RF-21] Pestañas y Sesiones Dedicadas por CLI en la Consola:**
  - Los agentes CLI instalados en la sandbox (OpenCode, Antigravity, Claude Code, Aider, etc.) deben aparecer como opciones clickeables (chips/tabs) en la consola. Al presionar un CLI, se lanza un script de inicialización específico en una terminal dedicada, entregando al usuario el chat interactivo de ese agente listo para usar o autenticarse.
- **[RF-22] Pool Router Multi-Key Específico por Proveedor (Failover Automático):**
  - El sistema permite registrar múltiples API keys para un mismo proveedor. Estas claves se agrupan en un Pool Router dedicado. Si una clave agota su cuota, entra en rate limit (`429`) o falla (`5xx`), el router conmuta de forma automática e imperceptible a la siguiente API key disponible del mismo proveedor sin interrumpir la operación del usuario.
- **[RF-23] Gestión de Logins y Flujo OAuth en Navegador Móvil:**
  - Si el usuario decide cambiar de cuenta en Antigravity u otro entorno CLI, el botón de login dispara el proceso en el CLI generando automáticamente el lanzamiento del navegador web nativo de Android para completar el flujo OAuth/login de forma transparente.
- **[RF-24] Disponibilidad Universal de APIs sin Exposición de Credenciales:**
  - Las API keys y credenciales se custodian cifradas en la bóveda segura del dispositivo (`EncryptedSharedPreferences` / KeyStore) sin exponerse en texto plano a los LLMs ni en prompts, pero permanecen accesibles de forma transparente para cualquier agente activo y para el sistema en todas sus interfaces (F-Chat, F-Code, F-Docs, Terminal).
- **[RF-25] Arquitectura Dual en `📚 F-Docs` (Editor Asistido vs. Fénix Studio):**
  - La sección `F-Docs` se estructura en dos sub-módulos conmutables:
    1. *Editor de Texto Asistido:* Renderizado y edición paginada de documentos (`DOCX`, `PDF`, `TXT`, `MD`, `XLSM`) con 20 herramientas de formato, ajuste automático Normas APA 7ma, OCR on-device y anclas táctiles F-Z en negritas para invocar al tutor.
    2. *Fénix Studio:* Espacio de generación y orquestación multimodal académica.
- **[RF-26] Generación Multimodal Académica en Fénix Studio:**
  - *Diapositivas Académicas (Slide Decks):* Generación determinística de presentaciones en relación de aspecto 16:9 con jerarquía sobria, exportables a PDF/PPTX.
  - *Infografías & Diagramas Conceptuales:* Generación de mapas de flujo, árboles de decisión y grafos SVG / Mermaid.
  - *Video-Lecciones y Simulaciones Animadas:* Ensamble de animaciones pedagógicas estilo pizarra y locución socrática mediante `FFmpeg` / `MediaCodec` local a costo $0.
  - *Tribunal de Examen Oral en Vivo:* Simulador socrático con micrófono interactivo y repreguntas en tiempo real.
- **[RF-27] Jerarquía Académica Estricta de 4 Niveles (Aislamiento de Árbol):**
  - La arquitectura organiza los proyectos en 4 niveles jerárquicos: `Carrera` -> `Año de Cursada` -> `Cátedra` -> `Unidad / Bolilla / Módulo`.
  - Queda prohibido mezclar unidades entre sí o cruzar materias en una misma caja de contexto; cada unidad opera como un espacio de estudio estanco y limpio.
- **[RF-28] Documento Visual Maestro y Exportación Soberana (PDF & DOCX):**
  - Además de las tarjetas en el chat, Fénix Studio compila en tiempo real un documento navegable con las Partes A a F del Protocolo Fénix v2.1/v3.0. El estudiante puede estudiar directamente en pantalla o descargarlo en un toque en formatos `.pdf` y `.docx`.
- **[RF-29] Flexibilidad Dinámica de Formato Visual a Pedido:**
  - Si bien cada sección A–F posee colores y estructuras predeterminadas (Blanco, Gris, Verde Feynman, Rojo Recall, Violeta Tablas, Celeste Glosario), la IA debe poder reensamblar y proyectar cualquier contenido en el formato que el usuario solicite (ej. transformar un caso en cuestionario interactivo o una tabla en prosa).
- **[RF-30] Reproductor de Ruidos Blancos y Ondas de Estudio (Ambiente Acústico):**
  - La sala de Fénix Studio integra un generador/reproductor de sonido ambiente a costo $0 con un mínimo de 10 opciones acústicas (Lluvia, Tormenta, Bosque, Olas, Fuego de chimenea, Murmullo de cafetería universitaria, Ruido blanco, Ruido rosa, Ruido marrón y Ondas Alfa 40 Hz).
- **[RF-31] Interfaz Paginada del Editor Asistido (Tablero 24):**
  - Renderizado tipo página A4 con barra de 20 herramientas de acceso rápido (Normas APA 7ma, Negrita táctil F-Z, Citas, Tablas, OCR, Guardado, PDF, DOCX), telemetría de palabras y callout emergente contextual para invocar al tutor socrático sin abandonar la posición de lectura.
- **[RF-32] Lienzo Multimodal Fénix Studio (Tablero 25):**
  - Interfaz visual en Azul Noche Institucional con reproductor acústico integrado en cabecera, acceso directo al Documento Maestro compilado, renderizado de tarjetas interactivas (Diapositivas 16:9, Tablas relacionales Parte B con badges `[FUENTE]`/`[INFERENCIA]` y Simulador de Examen Oral por voz).
- **[RF-33] Exportador On-Device de Documento Maestro en Un Clic:**
  - Generación y exportación de archivos binarios `.pdf` y `.docx` procesados 100% de manera local en el teléfono sin intermediación de servidores externos de pago, garantizando costo cero absoluto y privacidad de datos.
- **[RF-34] Panel Central de Conectores & Cloud MCP (Tablero 26):**
  - Espacio unificado accesible desde la TopAppBar principal para administrar servicios externos y de nube sin costo (GitHub PAT, Gmail SMTP, Cloudflare Workers/R2, Supabase Postgres/Storage, Telegram Bot y servidores Context7 MCP en la nube).
  - Cada conector dispone de interruptor de encendido/apagado manual e indicadores de estado reactivos.
- **[RF-35] Conexión de Gmail mediante Contraseña de Aplicación de 16 Caracteres:**
  - Flujo guiado paso a paso para configurar envíos automáticos de resúmenes y notas por SMTP utilizando contraseñas de aplicación generadas en `myaccount.google.com/apppasswords`, protegiendo la contraseña principal del estudiante.
- **[RF-36] Generador de Skills y Bóveda Segura de Credenciales por IA:**
  - La IA en cualquier chat puede asistir en la creación de conectores y redacción de especificaciones MCP, desplegando cajas de entrada seguras en la interfaz para persistir API keys y tokens directamente en Android KeyStore (`secrets`), prohibiendo su inclusión en texto plano o prompts.
- **[RF-37] Centro de Ayuda Integral y Guías Oficiales por Interfaz (Tablero 27):**
  - Módulo accesible desde el icono `?` con buscador temático y tarjetas explicativas detalladas (mínimo 5 renglones y más de 300 palabras normativas de descripción técnica) para cada superficie de la app, acompañadas de enlaces directos a sitios oficiales.
- **[RF-38] Modo Split Sheet en Editor F-Docs con Contexto Estricto de Documento:**
  - Pestaña deslizable inferior que abre al Maestro Socrático a media pantalla sobre el documento en lectura. Por diseño, la IA recibe de manera estricta y exclusiva el contexto del archivo abierto, impidiendo alucinaciones o cruces de cátedra, salvo solicitud expresa del alumno (`🌐 Todo el Repo`).
- **[RF-39] Marketplace Comunitario de Creaciones & Instant Apps (Tablero 28):**
  - Catálogo dinámico organizado en 10 categorías (MCP, Instant Apps, Agentes, Modelos IA, Runtime y Compiladores, Skills, Conectores, Servicio Virtual y Nube, Productividad y Herramientas, Mis Creaciones) con tarjetas colapsables, sistema de valoración comunitaria (estrellas y descargas) y descarga soberana desde repositorios públicos de GitHub.
- **[RF-40] Pasarela de Publicación Segura, Firma Oficial y Filtro Criticón:**
  - Flujo de publicación que exige autenticación contra la base de datos (usuario y contraseña), ficha obligatoria (nombre, correo, categoría, permisos y descripción mayor a 5 renglones y 500 palabras).
  - Reserva criptográfica exclusiva del identificador "Fénix Oficial" con firma del core.
  - Auditoría preventiva obligatoria por el módulo "Criticón", proyectando tarjetas de alerta en rojo carmesí (`#FF1744`) para extensiones que soliciten permisos abusivos o comprometan la sandbox.




---

## 3. REQUISITOS NO FUNCIONALES (RNF)

- **[RNF-01] Costo Cero:** Ningún componente o dependencia debe obligar al usuario a pagar licencias o servicios.
- **[RNF-02] Consumo de Memoria:** El renderizado de animación del Ave Fénix no debe superar 1.5 MB de RAM en tiempo de ejecución en el móvil.
- **[RNF-03] Tasa de Refresco:** Las transiciones de interfaz, terminal y animaciones deben ejecutarse a un mínimo de 60 FPS aceleradas por hardware (GPU/Skia).
- **[RNF-04] Aislamiento y Privacidad:** Prohibido registrar o transmitir datos personales del usuario a servidores no autorizados.
- **[RNF-05] Cumplimiento Play Protect:** Ausencia de permisos de accesibilidad abusivos en el manifiesto principal para asegurar instalación sin advertencias de seguridad de Google.
- **[RNF-06] Bypass de $W \wedge X$ en Android 14–16 con `proot` en `jniLibs`:**
  - El sistema Linux debe ejecutarse sin requerir permisos de Root mediante `proot` compilado con Android NDK r29 (alineación ELF a 16 KB) empaquetado en `app/src/main/jniLibs/arm64-v8a/libproot.so` con `useLegacyPackaging = true`. Esto permite llamadas `execve()` totalmente legales bajo `targetSdk = 34` y `37`.
- **[RNF-07] Compatibilidad Binaria `glibc` con Debian 12 Bookworm (arm64):**
  - La distribución Linux debe ser Debian 12 Bookworm arm64 estándar con `glibc 2.36`, garantizando 100% de compatibilidad con instaladores oficiales de agentes CLI (Bun, Node 22, Python manylinux, Rust), descartando musl libc.

