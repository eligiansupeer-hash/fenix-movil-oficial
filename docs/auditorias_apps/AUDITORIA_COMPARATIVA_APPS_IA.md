# AUDITORÍA TÉCNICA COMPARATIVA GLOBAL: ECOSISTEMA DE APLICACIONES DE IA Y DESARROLLO MÓVIL

- **Fecha de Auditoría:** 17 de Octubre de 2026
- **Entorno de Evaluación:** Dispositivo Físico Motorola Moto G77 (`ZY22MK7B2P`, Android 14, Arquitectura ARM64-v8a)
- **Marco Regulatorio:** Constitución Operativa del Proyecto Fénix (Principio de Costo = 0 Absoluto, Universalidad Disciplinar, Prohibición de Hardcodeo y Preservación Documental).

---

## 1. GRAN MATRIZ COMPARATIVA DE ARQUITECTURA E INGENIERÍA

| Aplicación | Framework UI / Motor Core | Motor JS / Dart / Script | Librerías Nativas Clave (.so) | Tamaño .so Crítico | Protocolo Streaming / Voz | Soporte MCP / Herramientas | Modelo de Costo Operativo |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Manus** (`tech.butterfly.app`) | Flutter AOT | Dart VM AOT | `libapp.so`, `libflutter.so` | 28.5 MB | WebSocket binario (Protobuf) | Sandbox Cloud (MicroVM KVM Ubuntu 24.04, 6 vCPU, 24 GB RAM) | Suscripción comercial en nube |
| **Perplexity** (`ai.perplexity.app.android`) | Nativo Android (Kotlin) | Rust UniFFI Bridge | `libengine.so` (Rust), `libsherpa-onnx-jni.so` | 18.6 MB (Rust) + 4.4 MB (ONNX) | Sherpa-ONNX local + HTTPS SSE | MCP Remoto vía Spaces (HTTP/SSE con Bearer Token) | Freemium (Básico gratuito) |
| **Claude** (`com.anthropic.claude`) | Jetpack Compose (Kotlin) | N/A (Nativo puro) | `libconscrypt_jni.so`, WebRTC | 1.4 MB (BoringSSL) | WebRTC + SSE | **MCP Nativo Oficial**: Conector personalizado con URL Streamable HTTP / SSE | Freemium (Básico gratuito) |
| **ChatGPT** (`com.openai.chatgpt`) | Nativo Android (Kotlin/C++) | N/A (Nativo C++) | `liblkjingle_peerconnection_so.so`, `libopus_c.so` | 108.4 MB (WebRTC C++) | WebRTC bidireccional nativo | GPT Actions (OpenAPI REST con 3 niveles de autorización) | Freemium (Básico gratuito) |
| **Copilot** (`com.microsoft.copilot`) | React Native | V8 Engine JIT | `libv8android.so`, `libmaplibre.so`, `libBabylonNative.so` | 18.6 MB (V8) + 13.1 MB (MapLibre) | WebRTC + V8 JIT | Copilot Studio Plugins, Graph Connectors (Enterprise) | Freemium / Microsoft 365 |
| **Zapia** (`com.brainlogic.zapia`) | Flutter AOT | Dart VM AOT | `libapp.so`, `libflutter_soloud_plugin.so`, `libdartjni.so` | 22.6 MB (App) + 1.92 MB (SoLoud) | SoLoud C++ Audio Engine | Conectores WhatsApp/Notion/Gmail (Human-in-the-Loop obligatorio) | Freemium (Básico gratuito) |
| **Replit** (`com.replit.app`) | React Native Fabric | Hermes Engine | `libhermes.so`, `libzstd-kmp.so` | 2.48 MB (Hermes) + 731 KB (Zstd) | WebSocket + Sincronización Zstd | 3 Modos de Agente (Free, Power, Max) y 8 tipos de output | Freemium (Básico gratuito) |
| **Trae AI** (`com.bytedance.trae.overseas`) | ByteDance Lynx | WebAssembly (Wasm) | `liblynx.so`, `libbytenn.so`, `libwasm.so`, `libvolcenginertc.so` | 3.94 MB (Lynx) + 3.11 MB (ByteNN) + 15.7 MB (RTC) | Volcano Engine RTC | Control Remoto PC (`trae.ai`) + Modelos frontera (GPT-6, Gemini 3.1) | Gratuito con cuenta ByteDance |
| **DeepSeek** (`com.deepseek.chat`) | Nativo Android | N/A (Nativo C++) | `libWCDB.so` (Tencent SQLite), `libmmkv.so`, `libliteavsdk.so` | 4.33 MB (WCDB) + 298 KB (MMKV) + 11.6 MB (LiteAV) | Tencent LiteAV | Modos `Pensar` (R1) y `Buscar`. Cliente cerrado sin MCP | 100% Gratuito en app |
| **Mistral** (`ai.mistral.chat`) | React Native | Hermes Engine | `librnskia.so` (Skia GPU), `libjingle_peerconnection_so.so`, `liboboe.so` | 11.31 MB (Skia) + 12.1 MB (WebRTC) + 296 KB (Oboe) | WebRTC + Oboe C++ | **Sistema de Skills Modulares**: Conmutables por switch, creación personalizada | Freemium (Mistral Medium 3.5 gratis) |
| **Google AI Edge** (`com.google.ai.edge.gallery`) | Android Nativo (Kotlin/C++) | C++ LiteRT Runtime | `liblitertlm_jni.so`, `libsqlite_vector_store_jni.so`, `libmediapipe_tasks_text_jni.so` | 21.87 MB (LiteRT) + 5.78 MB (Vector Store) + 12.8 MB (MediaPipe) | Inferencia local CPU/NPU/GPU | **Agent Skills On-Device**: Function calling local con Gemma 4 a **Costo 0** | **100% Gratuito y Soberano** |
| **NotebookLM** (`com.google.android.apps.labs.language.tailwind`) | Flutter AOT | Dart VM AOT | `libflutter.so`, `libNotebookLM_prod_android_library_flutter_artifacts.so`, `libjingle_peerconnection_so.so`, `libnlm_opus.so` | 20.25 MB (App) + 6.68 MB (WebRTC) + 266 KB (Opus) | WebRTC + Opus Audio Overviews | Anclaje estricto a fuentes (Grounding Gemini 1.5 Pro). Sin conector directo | 100% Gratuito con cuenta Google |

---

## 2. ANÁLISIS COMPARATIVO DE EJES ESTRUCTURALES

### 2.1 Eje 1: Conectividad y Protocolo MCP (Model Context Protocol)
1. **Soporte Oficial y Abierto:** **Claude** es el único cliente que implementa nativamente el protocolo MCP mediante configuración visual en la app, aceptando endpoints HTTP/SSE arbitrarios.
2. **Soporte Remoto vía Espacios:** **Perplexity** permite conectar fuentes y herramientas MCP a través de su infraestructura de Spaces y agentes.
3. **Soporte por Plugins y REST OpenAPI:** **ChatGPT** (GPT Actions) y **Copilot** (Copilot Studio) utilizan especificaciones OpenAPI estándar. Requieren que un servidor MCP se exponga mediante un proxy OpenAPI/REST para ser consumido.
4. **Arquitectura Modular de Skills:** **Mistral AI** aplica una abstracción conceptualmente idéntica al sistema de Skills de Antigravity: herramientas atómicas independientes que el usuario activa o desactiva con switches según la tarea.
5. **Agent Skills On-Device (Costo = 0):** **Google AI Edge Gallery** demuestra que la ejecución de herramientas, Function Calling y RAG vectorial con SQLite pueden operar al 100% dentro del procesador del teléfono sin depender de APIs de pago, cuotas ni conexión a internet.

### 2.2 Eje 2: Frameworks de UI y Rendimiento Móvil
- **Dominio de React Native + Hermes:** Elegido por Replit y Mistral, combinando agilidad de despliegue con renderizado de alto rendimiento (Skia en Mistral).
- **Dominio de Flutter AOT:** Elegido por Manus, Zapia y NotebookLM, asegurando consistencia visual multiplataforma e interfaz determinista a 60/120 FPS.
- **Alternativas Especializadas (Lynx de ByteDance):** Trae AI utiliza Lynx, un motor de UI de ByteDance compilado a C++ con soporte WebAssembly para maximizar la velocidad de respuesta.
- **Nativo Puro con Jetpack Compose / C++:** Claude, ChatGPT y DeepSeek priorizan la integración directa con las APIs del sistema operativo Android y bibliotecas C++ de bajo nivel (BoringSSL, WebRTC, MMKV, WCDB).

### 2.3 Eje 3: Tecnologías de Voz y Streaming de Baja Latencia
- **ChatGPT** lidera en peso e inversión nativa con `liblkjingle_peerconnection_so.so` (108.4 MB) para garantizar el modo de voz avanzado bidireccional.
- **Perplexity** destaca por incorporar inferencia acústica offline con `libsherpa-onnx-jni.so` (4.4 MB).
- **NotebookLM** optimiza la transmisión de podcasts generativos con WebRTC y el códec especializado `libnlm_opus.so`.

---

## 3. IMPLICANCIAS DIRECTAS PARA LA ARQUITECTURA FÉNIX (PWA ACADÉMICA)

1. **Principio de Costo = 0 y Soberanía Local:** La combinación de **Termux** (como servidor local de herramientas y scripts) con **Google AI Edge / LiteRT-LM** (para embeddings y categorización on-device) permite que la PWA académica procese apuntes, genere resúmenes y analice bibliografía sin incurrir en costos de tokens de nube para tareas locales.
2. **Desacoplamiento Estricto (No Hardcodeo):** Siguiendo el modelo de **NotebookLM** y **Mistral Skills**, la PWA nunca debe asumir la estructura de una cátedra o disciplina; las unidades académicas, los programas y la bibliografía ingresan como fuentes dinámicas proporcionadas exclusivamente por el estudiante.
3. **Extensibilidad MCP:** La PWA Fénix debe ofrecer un conector cliente/servidor MCP unificado sobre HTTP Streamable (SSE), compatible tanto con el estándar implementado en Claude como con clientes locales que corran en el smartphone o la computadora del usuario.

