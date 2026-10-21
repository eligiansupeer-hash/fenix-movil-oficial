# INFORME DE PROSPECCIÓN TÉCNICA: ECOSISTEMA GOOGLE PLAY (IA Y ENTORNOS DE DESARROLLO)

- **Fecha de Auditoría:** 17 de Octubre de 2026
- **Criterio de Selección:** Aplicaciones de Inteligencia Artificial y Entornos de Programación Móvil con valoración de usuarios $\ge 4.2$ estrellas en Google Play Store.
- **Principio Rector:** **COSTO = 0 ABSOLUTO**. No se realizan compras de aplicaciones, microtransacciones ni pruebas con tarjetas de pago. En aplicaciones freemium o comerciales, se analiza la factibilidad de ejecución de herramientas libres, APIs comunitarias o descarga de paquetes de código abierto sin intermediación de pago.

---

## 1. MATRIZ DE PROSPECCIÓN DE APLICACIONES SELECCIONADAS

| Aplicación | Categoría | Valoración | Modelo de Costo | Framework / Motor Técnico | Conectividad MCP / Protocolos |
| :--- | :--- | :---: | :--- | :--- | :--- |
| **Termux** | Entorno Linux / POSIX | 4.6 ★ | 100% Gratuito y Open Source | CLI nativa C / Bionic libc / Bash / Node / Python | **Máxima (Nativa)**: Capaz de alojar servidores y clientes MCP (HTTP SSE / stdio) |
| **Acode Code Editor** | IDE Móvil | 4.5 ★ | Freemium (Core Open Source) | WebView acelerado + Monaco / Ace Editor + Plugins JS | **Alta**: Soporta plugins JS para conectar con endpoints MCP / REST locales |
| **Spck Editor** | IDE Móvil & Git | 4.4 ★ | Freemium (Básico gratis) | Monaco Editor + Servidor HTTP local embebido | **Media/Alta**: Permite ejecutar clientes web y hacer peticiones fetch a MCP |
| **DroidScript** | IDE & Framework JS | 4.4 ★ | Freemium | Runtime V8/Rhino + Bridge JNI nativo Android | **Alta**: Servidor WebSocket y HTTP integrado para interoperar con herramientas MCP |
| **Mimo: Learn to Code** | Educación / Sandbox | 4.7 ★ | Freemium (Básico gratis) | React Native + Hermes + Sandbox Cloud | **Nula**: Entorno de ejecución en la nube cerrado para lecciones curriculares |
| **Genspark AI** | Motor Multi-Agente | 4.5 ★ | Gratuito con cuotas | Flutter + Web Engine + Multi-agentes paralelos | **Media (Indirecta)**: Búsqueda autónoma profunda, exportación a web abierta |
| **Kimi AI (Moonshot)** | Asistente Contexto Extenso | 4.6 ★ | Gratuito con límites API | React Native / H5 híbrido | **Media**: Soporte de RAG documental avanzado y APIs compatibles con OpenAI |
| **Qwen (Tongyi Qianwen)** | Asistente & Código | 4.5 ★ | Gratuito / API abierta | Android Nativo / WebView H5 | **Alta (vía API)**: Soporte completo de Function Calling y Tool Use en la familia Qwen 2.5 |

---

## 2. FICHAS TÉCNICAS DETALLADAS

### 2.1 Termux (`com.termux`)
- **Valoración:** 4.6 ★
- **Rol en la Arquitectura Fénix:** **Pilar de Infraestructura Móvil a Costo = 0**.
- **Análisis Técnico:**
  - Termux no es un emulador; es un entorno Linux completo que corre directamente sobre el kernel de Android utilizando la biblioteca C de Android (Bionic).
  - Admite la instalación de paquetes estándar mediante `pkg`: Node.js 22+, Python 3.12+, Git, OpenSSH, Rust, SQLite3, cURL.
  - **Compatibilidad MCP:** Puede ejecutar directamente el SDK de Model Context Protocol (`@modelcontextprotocol/sdk` en Node.js o `mcp` en Python) tanto en modo servidor local (`http://127.0.0.1:port`) como en modo stdio, sirviendo de puente para que clientes en el dispositivo o en la red local consuman herramientas y recursos sin costo de servidor cloud.
- **Estrategia Costo 0:** 100% libre de costo y código abierto (licencia GPLv3).

### 2.2 Acode - Powerful Code Editor (`com.foxdebug.acode`)
- **Valoración:** 4.5 ★
- **Rol en la Arquitectura:** Entorno de edición y orquestación de código local.
- **Análisis Técnico:**
  - Desarrollado como aplicación híbrida optimizada con Monaco Editor (el mismo editor de VS Code).
  - Incluye soporte para el protocolo Language Server Protocol (LSP) a través de complementos, resaltado de sintaxis, gestión de proyectos con carpetas de Android Storage Access Framework (SAF).
  - Arquitectura de plugins basada en JavaScript: un desarrollador puede escribir un plugin de Acode en pocas líneas que consulte un servidor MCP local (por ejemplo corriendo en Termux o Fénix) para autocompletar código, generar refactorizaciones o consultar documentación.
- **Estrategia Costo 0:** Versión comunitaria open-source en GitHub / F-Droid libre de pago.

### 2.3 Spck Editor / Git Client (`io.spck`)
- **Valoración:** 4.4 ★
- **Rol en la Arquitectura:** Desarrollo web y frontend reactivo móvil.
- **Análisis Técnico:**
  - Integra cliente Git completo (clone, commit, push, merge) implementado en JavaScript puro.
  - Dispone de un servidor web local integrado que sirve páginas HTML/JS directamente en un puerto localhost, lo que permite probar clientes de interfaz de usuario (generative UI / artifacts) en tiempo real en la pantalla del smartphone.
- **Estrategia Costo 0:** El editor principal, sincronización Git y servidor local no requieren pago.

### 2.4 DroidScript (`com.smartphoneremote.androidscript`)
- **Valoración:** 4.4 ★
- **Rol en la Arquitectura:** Plataforma de prototipado rápido de utilidades Android y micro-servicios locales.
- **Análisis Técnico:**
  - Permite escribir aplicaciones Android completas usando JavaScript estándar.
  - Ofrece APIs para controlar sensores, cámara, almacenamiento, red y Bluetooth.
  - Incluye un servidor web IDE accesible por Wi-Fi desde el navegador de la PC, facilitando la programación en el móvil desde la computadora.
  - **Conectividad:** Su capacidad para instanciar sockets TCP y servidores HTTP ligeros le permite actuar como puente cliente para herramientas MCP.
- **Estrategia Costo 0:** Versión gratuita plenamente operativa para scripts y servidores locales.

### 2.5 Genspark AI (`ai.mainfunc.genspark`)
- **Valoración:** 4.5 ★
- **Rol en la Arquitectura:** Agente autónomo de investigación paralela.
- **Análisis Técnico:**
  - Aplica un pipeline multi-agente donde varios LLMs navegan simultáneamente la web, contrastan fuentes, sintetizan resultados y construyen "Sparkpages" interactivas.
  - Excelente referencia de arquitectura de agregación y síntesis documental para el motor de la PWA académica.

### 2.6 Kimi AI (`com.moonshot.kimichat`)
- **Valoración:** 4.6 ★
- **Rol en la Arquitectura:** Procesamiento de documentos académicos extensos.
- **Análisis Técnico:**
  - Modelo Moonshot con capacidad probada de procesar libros enteros y papers de cientos de páginas con alta fidelidad de recuperación y anclaje a fuentes.
  - Arquitectura orientada a RAG de precisión para fuentes no estructuradas.

---

## 3. CONCLUSIÓN DE PROSPECCIÓN Y DECISIÓN DE INGENIERÍA

1. **Infraestructura Local:** **Termux** se consolida como el entorno de cómputo local soberano para Android. Permite correr agentes, servidores MCP y herramientas sin depender de servidores de pago externos.
2. **Entornos de Desarrollo:** **Acode** y **Spck** demuestran que es completamente viable portar experiencias de edición con LSP y previsualización HTTP a dispositivos móviles sin costo de licenciamiento.
3. **Agentes y Multimodalidad:** Aplicaciones como **Genspark** y **Kimi** reafirman que la investigación académica automatizada se basa en multi-agentes paralelos y anclaje estricto a documentos provistos por el usuario, principio central de la Constitución Operativa del Proyecto Fénix.

