# PLAN MAESTRO Y PROTOCOLO ESTÁNDAR DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA DE APPS DE IA
**Fecha de Registro:** 17 de octubre de 2026  
**Proyecto:** Biblioteca Fénix v3 / Fénix Mobile MCP  
**Dispositivo de Prueba:** Motorola Moto G77 (`ZY22MK7B2P`), Android 14.  
**Ubicación Persistente en el Móvil:** `/sdcard/fenix_auditorias/PLAN_MAESTRO_AUDITORIA.md`

---

## 1. OBJETIVO DEL PLAN

Establecer un protocolo metodológico estricto, uniforme y reproducible para auditar cada aplicación de Inteligencia Artificial instalada en el dispositivo móvil y las nuevas apps que se descubran en Google Play (con valoración ≥ 4.2).

El propósito es extraer de cada aplicación exactamente el **mismo conjunto sistemático de datos técnicos, arquitectónicos, de infraestructura y de conectividad MCP** que se obtuvo con Manus, permitiendo una comparación profunda y fundamentada para alimentar el roadmap de **Fénix Mobile MCP**.

---

## 2. PLANTILLA OBLIGATORIA DE 7 DIMENSIONES (POR CADA APLICACIÓN)

Cada informe individual (`INFORME_<APP>.md`) debe responder obligatoriamente a los siguientes 7 módulos sin omitir ninguno:

### MÓDULO 1: IDENTIFICACIÓN Y ARQUITECTURA ESTÁTICA DEL APK
- **Paquete oficial (`packageName`) y versión instalada (`versionName`, `versionCode`).**
- **Nivel de API:** `minSdk` y `targetSdk`.
- **Framework de UI:** Nativo Jetpack Compose / ViewBinding / Flutter AOT / React Native (identificado por assets y binarios).
- **Librerías Nativas C++/Rust (`.so` en `/lib/arm64-v8a`):**
  - Motores de inferencia local / Edge AI (ej. ONNX Runtime, TFLite, Sherpa-ONNX).
  - Motores de audio/voz y WebRTC (ej. LiveKit, Jingle, Opus).
  - Motores de documentos y renderizado (ej. PDFium, KaTeX/AndroidMath).
  - Motores de persistencia local (ej. SQLite, DataStore).
  - Telemetría y anti-fraude (ej. Sentry, FingerprintJS).

### MÓDULO 2: TOPOLOGÍA DE UI/UX Y FLUJOS DE NAVEGACIÓN
- **Estructura de Pantallas:** Home, Biblioteca, Configuración, Menú de Perfil.
- **Composer y Controles de Entrada:**
  - Selector de modelos y modos de razonamiento (Thinking / Lite / Pro / Max).
  - Entrada multimodal: Cámara, Galería, Archivos de documentos, Audio.
  - Modos de voz: Transcripción local (STT) vs Conversación bidireccional (Voice-to-Voice WebRTC).
  - Modo incógnito / Sesiones anónimas temporales.
- **Paywalls y Modelo Comercial:**
  - Distinción estricta de funciones gratuitas vs planes de pago (Pro/Plus/Max).
  - **Regla Inviolable:** Nunca activar pruebas pagas ni introducir datos de pago (Costo = 0).

### MÓDULO 3: INFRAESTRUCTURA DE CÓMPUTO Y SANDBOX DE EJECUCIÓN
*(Interrogatorio técnico interactivo y ejecución de scripts donde sea viable)*
- **¿Dispone de Sandbox de Ejecución Remoto?** (Sí / No / Solo motor RAG generativo).
- **Entorno de Virtualización:** KVM, microVMs (Firecracker), Contenedores Docker, LXC o bare metal.
- **Sistema Operativo y Kernel:** Distribución Linux, versión exacta, arquitectura (`x86_64` / `aarch64`).
- **Recursos de Hardware Asignados:**
  - CPU y núcleos (`nproc` / modelo Intel Xeon, AMD EPYC, etc.).
  - Memoria RAM total, libre y disponible (`free -m`).
  - Almacenamiento en disco efímero vs montajes persistentes (`df -h`).
- **Runtimes y Lenguajes Soportados:**
  - Versión de Python, pip y catálogo de librerías científicas, scraping y web.
  - Versión de Node.js, npm, pnpm y servidores de lenguaje (LSP).
  - Binarios CLI nativos (Git, FFmpeg, utilidades de compilación).
- **Límites Operativos:** Timeouts de ejecución, supervisión de procesos en background (`supervisor` / watchdog).

### MÓDULO 4: ORQUESTACIÓN Y CATÁLOGO DE MODELOS DE IA
- **Catálogo de Modelos Disponibles:** Lista completa de modelos accesibles desde la app (propios y de terceros: Claude, GPT, Gemini, Kimi, GLM, Grok, etc.).
- **Auto-routing y Modos Especiales:** Conmutación automática entre modelos ligeros y modelos de razonamiento profundo.
- **Ventana de Contexto y Gestión de Salidas Largas:** Cómo la plataforma previene la saturación del contexto ante comandos o textos extensos.

### MÓDULO 5: CONECTIVIDAD EXTERNA Y SOPORTE DE PROTOCOLO MCP
- **¿Actúa la app como Cliente MCP?** (Capacidad de conectarse a servidores MCP externos para invocar herramientas).
- **Transportes y Protocolos Admitidos:**
  - HTTP / HTTPS (Streamable HTTP, Server-Sent Events / SSE).
  - Soporte de servidores locales (vía proxies o daemons de puente).
- **Esquemas de Autenticación Soportados:** Bearer tokens, API Keys, OAuth2, o sin autenticación.
- **Integraciones SaaS Alternativas:** Google Workspace, GitHub, Slack, Notion, Linear, Datadog, etc.
- **Directiva Operativa:** **NO conectar las aplicaciones al servidor MCP de Fénix** durante la fase de auditoría.

### MÓDULO 6: PERSISTENCIA, MEMORIA Y GESTIÓN DE ENTREGABLES
- **Almacenamiento de Archivos Generados:** ¿Tiene explorador de archivos creados (creaciones, sitios desplegados, código compilado)?
- **Sistema de Memoria:**
  - Reglas de usuario fijas / Instrucciones personalizadas.
  - RAG contextual dinámico (disparadores tipo trigger-action / Knowledge Base).
  - Persistencia de cookies de navegación entre tareas consecutivas.

### MÓDULO 7: SÍNTESIS COMPARATIVA Y APRENDIZAJES PARA FÉNIX MOBILE MCP
- **Ventajas Competitivas:** Fortalezas arquitectónicas o de UX detectadas.
- **Debilidades / Limitaciones:** Restricciones de aislamiento, cuotas o bloqueos.
- **Lecciones Aplicables para Fénix:** Directivas directas de diseño para el código y la arquitectura de Fénix Mobile MCP.

---

## 3. HOJA DE RUTA Y MATRIZ DE APLICACIONES A AUDITAR

```
[FASE 1: APPS INSTALADAS EN EL DISPOSITIVO]
  ├── 1. Manus (tech.butterfly.app v26.18.3) ──────────────► [AUDITADA Y DOCUMENTADA]
  ├── 2. Perplexity (ai.perplexity.app.android v2.101.0) ──► [EN CURSO: COMPLETANDO MÓDULOS 3-5]
  ├── 3. Claude (com.anthropic.claude v1.261012.20) ──────► [PENDIENTE]
  ├── 4. ChatGPT (com.openai.chatgpt) ────────────────────► [PENDIENTE]
  ├── 5. Microsoft Copilot (com.microsoft.copilot) ────────► [PENDIENTE]
  └── 6. Zapia (com.brainlogic.zapia) ────────────────────► [PENDIENTE]

[FASE 2: DESCUBRIMIENTO E INSTALACIÓN DESDE GOOGLE PLAY]
  ├── Criterio de Selección: Rating ≥ 4.2 estrellas.
  ├── Categorías:
  │     ├── Entornos de programación y edición con IA móvil (ej. Acode, Dcoder, Spck, Termux AI).
  │     └── Clientes y agentes de modelos autónomos (ej. Poe, Chatcraft).
  ├── Regla de Pago: Si la app es de pago, NO instalar. Se crea Ficha de Prospección técnica.
  └── Depuración: Si el almacenamiento libre del móvil baja de 4 GB, desinstalar apps sin MCP.

[FASE 3: SÍNTESIS COMPARATIVA FINAL]
  └── Creación de Matriz Comparativa Exhaustiva en docs/AUDITORIA_COMPARATIVA_APPS_IA.md.
```

---

## 4. REGISTRO Y GESTIÓN DE ALMACENAMIENTO (CERO BASURA EN PC)

- **Ubicación en el Móvil:** `/sdcard/fenix_auditorias/`
  - `01_manus/INFORME_MANUS.md` + capturas
  - `02_perplexity/INFORME_PERPLEXITY.md` + capturas
  - `03_claude/INFORME_CLAUDE.md` + capturas
  - `04_chatgpt/INFORME_CHATGPT.md` + capturas
  - `05_copilot/INFORME_COPILOT.md` + capturas
  - `06_zapia/INFORME_ZAPIA.md` + capturas
  - `07_tienda_google_play/` (fichas técnicas de prospección e instalación)
- **Copia de Respaldo:** Memoria persistente del servidor MCP (`memoria_guardar`) para recuperación instantánea ante pérdida de contexto.

