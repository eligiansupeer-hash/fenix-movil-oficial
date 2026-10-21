# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: GOOGLE AI EDGE GALLERY

**Fecha:** 17 de octubre de 2026  
**Dispositivo de Prueba:** Motorola Moto G77 (Modelo `ZY22MK7B2P`, Android 14)  
**Paquete Auditado:** `com.google.ai.edge.gallery`  
**Versión de la App:** 1.0.20 (Split APK arm64-v8a)  
**Clasificación:** Plataforma de Inferencia On-Device de Modelos Fundacionales Abiertos, Agent Skills y RAG Local  

---

## 1. RESUMEN EJECUTIVO
Google AI Edge Gallery es la implementación oficial de referencia de Google para la ejecución de modelos de lenguaje e inteligencia artificial generativa **directamente en el hardware del dispositivo móvil (On-Device)** sin depender de servidores remotos ni conexión a internet.

Esta auditoría descubrió que Google ha implementado soporte nativo para **"Agent Skills"** on-device, permitiendo que modelos locales (como la familia **Gemma 4**, p. ej. `Gemma-4-E2B-it`) ejecuten **Function Calling y uso de herramientas** en el propio teléfono. Además, integra **SQLite Vector Store** y **MediaPipe Tasks**, habilitando arquitecturas RAG (Retrieval-Augmented Generation) 100% locales, privadas y de **COSTO = 0 ABSOLUTO**.

---

## 2. ARQUITECTURA DE SOFTWARE Y BINARIOS NATIVOS

### 2.1 Inspección de Paquetes y Splits
* **APK Base:** `/data/app/~~Se10xdIbLow3wVBGC61ybg==/com.google.ai.edge.gallery-Fo_WOQTZDEm5du6XB6QdXg==/base.apk`
* **Splits:**
  * `split_config.arm64_v8a.apk`
  * `split_config.es.apk`
  * `split_config.xxhdpi.apk`
* **Directorio de Datos:** `/data/user/0/com.google.ai.edge.gallery`

### 2.2 Binarios Nativos Extraídos (`arm64-v8a`)
| Binario | Tamaño | Propósito Técnico |
| :--- | :--- | :--- |
| `liblitertlm_jni.so` | **21.87 MB** | LiteRT-LM (LiteRT Language Models): Motor C++ nativo de Google para inferencia acelerada por GPU/NPU de modelos fundacionales locales. |
| `libmediapipe_tasks_jni.so` | **12.81 MB** | Google MediaPipe GenAI Tasks: Orquestación nativa de flujos multimodales (texto, audio, imagen). |
| `libsqlite_vector_store_jni.so` | **5.78 MB** | SQLite Vector Store: Extensión nativa de base de datos vectorial para SQLite en Android. Permite almacenar embeddings y realizar búsquedas por distancia coseno directamente en el chip móvil. |
| `libLiteRt.so` | **5.39 MB** | Runtime oficial de Google LiteRT (evolución de TensorFlow Lite) para ejecución optimizada de tensores en Android. |
| `libicing.so` | **3.79 MB** | Google Icing Search Engine: Motor nativo de búsqueda indexada semántica y recuperación rápida de texto. |

---

## 3. AUDITORÍA DE CASOS DE USO Y MODELOS LOCALES

### 3.1 Módulos Operativos Integrados
1. **Agent Skills (2 modelos):**
   * Definición oficial: *"Chatea con modelos de lenguaje grandes integrados en el dispositivo con habilidades y herramientas"*.
   * Permite que el LLM local emita llamadas estructuradas a funciones del sistema Android para automatizar tareas sin enviar datos a la nube.
2. **AI Chat (7 modelos):**
   * Interacción conversacional pura con LLMs locales sin latencia de red.
3. **Mobile Actions (1 modelo):**
   * Control directo de funciones del dispositivo móvil mediante comandos en lenguaje natural.
4. **Ask Image (4 modelos):**
   * Modelos de visión multimodal locales (VLM) para análisis de documentos, gráficos y capturas de pantalla.
5. **Audio Scribe (4 modelos):**
   * Transcripción y traducción de voz a texto on-device en tiempo real.
6. **Búsqueda Instantánea con `EmbeddingGemma 2`:**
   * Indexación y búsqueda semántica de documentos e imágenes locales mediante embeddings vectoriales calculados en el dispositivo.

### 3.2 Modelos Fundacionales On-Device Disponibles
* **Gemma-4-E2B-it (2.6 GB):** Variante de Gemma 4 optimizada para despliegue en Android mediante LiteRT-LM. Soporta entrada multimodal con una ventana de contexto de **hasta 32K tokens**.
* **Gemma-4-E4B-it:** Variante de 4 mil millones de parámetros para mayor profundidad en razonamiento y generación técnica.

---

## 4. CONECTIVIDAD MCP Y COMPARACIÓN CON EL ECOSISTEMA FÉNIX

* **La Pieza Clave para Costo = 0:** Google AI Edge Gallery demuestra de forma contundente que la visión de una PWA o aplicación móvil de procesamiento académico con costo = 0 es técnicamente viable en Android 14. 
* **Integración con MCP:** Las "Agent Skills" locales de LiteRT-LM pueden mapearse directamente a las herramientas de un servidor MCP local (como el servidor MCP de Fénix en el móvil), permitiendo que un modelo como `Gemma-4-E2B-it` consuma herramientas MCP locales sin necesidad de pagar APIs comerciales ni transmitir datos fuera del teléfono.

