# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: POE (QUORA)

- **Fecha de Auditoría:** 17 de Octubre de 2026
- **Dispositivo de Prueba:** Motorola Moto G77 (`ZY22MK7B2P`, Android 14)
- **Nombre de la Aplicación:** Poe - Fast AI Chat / Chat rápido con IA
- **Package Name:** `com.poe.android`
- **Versión Analizada:** `a3.77.2` (Build `3077006`, Target SDK 36)
- **Valoración en Play Store:** 4.3 ★ (451K opiniones)
- **Arquitectura de Ejecución:** ARM64-v8a

---

## 1. RESUMEN EJECUTIVO Y ARQUITECTURA DE SOFTWARE

Poe (desarrollado por Quora) es una plataforma multi-modelo y centro de orquestación de bots de inteligencia artificial. Su aplicación móvil destaca por su arquitectura orientada a la extensibilidad, renderizado científico y protocolos de servidores externos.

### 1.1 Stack Tecnológico y Librerías Nativas
- **Framework UI:** Android Nativo (Jetpack Compose / Android Views con aceleración de trazado).
- **Librerías Nativas (.so):**
  - `libpairipcore.so` (609.4 KB): Motor nativo de protección de integridad criptográfica y seguridad contra tampering (PairIP / Google Play Integrity).
  - `libandroidx.graphics.path.so` (10.1 KB): Renderizado acelerado de curvas y trazado vectorial.
- **Renderizado Matemático Científico Offline:**
  - Incorpora la biblioteca nativa **JLatexMath** (`org/scilab/forge/jlatexmath`), empaquetando fuentes tipográficas LaTeX completas (`jlm_cmsy10.ttf`, `jlm_eufb10`, Computer Modern, Euler, St. Mary) y diccionarios de símbolos griegos y matemáticos.
  - Esto le permite renderizar fórmulas complejas offline sin depender de MathJax en la nube ni sobrecargar el WebView.

---

## 2. MECANISMOS DE CONECTIVIDAD, BOT CREATOR Y PROTOCOLO EXTERNO

### 2.1 Bot de Servidor (Poe Server Bot Protocol)
Poe implementa de forma nativa la funcionalidad **"Bot de Servidor"** (*"Crea un bot basado en un servidor que gestionas"*):
- Es conceptual y técnicamente análogo a **Model Context Protocol (MCP)**: el desarrollador o usuario especifica una URL de servidor HTTP/SSE con autenticación por clave API.
- El servidor remoto recibe los eventos del chat, ejecuta llamadas a herramientas locales, bases de datos o pipelines personalizados y devuelve respuestas en streaming (*Server-Sent Events*).
- Esto permite conectar arquitecturas locales soberanas (como Fénix o servidores en Termux) directamente al cliente de Poe.

### 2.2 Bot de Scripts y Lienzo (Canvas / Artifacts)
- **Bot de Scripts:** Permite encadenar flujos de trabajo multi-modelo (ej. un modelo para búsqueda + otro modelo para síntesis y verificación).
- **Lienzo (Canvas):** Capacidad de renderizar experiencias web enriquecidas y código interactivo (*Generative UI / Artifacts*) más allá del chat textual plano.

### 2.3 Catálogo de Modelos de Frontera Soportados
Poe expone acceso unificado a los modelos más avanzados de la industria:
- `GPT-6-Astra` (OpenAI)
- `Claude-Opus-5.5` y `Claude-Fable-5.1` (Anthropic)
- `Gemini-4-Argon` (Google)
- `DeepSeek-R1`

---

## 3. POLÍTICA DE COSTO Y CONCLUSIÓN DE INGENIERÍA
- **Costo de Descarga:** 100% Gratuito en Google Play.
- **Uso:** El acceso a ciertos modelos y funcionalidades de chat requiere cuota de puntos o suscripción, pero la estructura de **"Bot de Servidor"** representa un caso de estudio fundamental para Fénix: demuestra que un cliente móvil puede actuar como frontend universal para cualquier backend de herramientas soberano mediante protocolos HTTP abiertos.
