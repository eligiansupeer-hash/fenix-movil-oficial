# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: BYTEDANCE TRAE AI

**Fecha:** 17 de octubre de 2026  
**Dispositivo de Prueba:** Motorola Moto G77 (Modelo `ZY22MK7B2P`, Android 14)  
**Paquete Auditado:** `com.bytedance.trae.overseas`  
**Versión de la App:** 0.0.24 (Split APK arm64-v8a)  
**Clasificación:** Asistente Móvil de Ingeniería de Software, Control Remoto de IDE y Orquestación Multi-Modelo  

---

## 1. RESUMEN EJECUTIVO
ByteDance Trae AI es un entorno de desarrollo asistido por inteligencia artificial que actúa tanto como agente autónomo en la nube como control remoto y cliente conversacional para el IDE de escritorio (`TraeWork`).

La aplicación móvil destaca por su arquitectura nativa basada en **Lynx** (el framework de alto rendimiento desarrollado por ByteDance alternativo a React Native), junto con motores de inferencia neural en el dispositivo (**ByteNN**), intérprete **WebAssembly (Wasm)** embebido y un motor WebRTC de baja latencia (**Volcano Engine RTC** de 15.7 MB). Soporta una matriz de modelos de frontera incluyendo GPT-6-Sol, GPT-5.4, Gemini-3.1-Pro-Preview, DeepSeek-V4-Pro y MiniMax-M3.

---

## 2. ARQUITECTURA DE SOFTWARE Y BINARIOS NATIVOS

### 2.1 Inspección de Paquetes y Splits
* **APK Base:** `/data/app/~~nfO9pKF7HZWzXZroAoIFFg==/com.bytedance.trae.overseas-er5T-ZZQkNUZ0Ajw7lYeKA==/base.apk`
* **Splits:**
  * `split_config.arm64_v8a.apk`
  * `split_config.es.apk`
  * `split_config.xxhdpi.apk`
* **Directorio de Datos:** `/data/user/0/com.bytedance.trae.overseas`

### 2.2 Binarios Nativos Extraídos (`arm64-v8a`)
| Binario | Tamaño | Propósito Técnico |
| :--- | :--- | :--- |
| `libvolcenginertc.so` | **15.69 MB** | Motor WebRTC de Volcano Engine para streaming de audio dúplex completo y telemetría de baja latencia. |
| `libsscronet.so` | **6.35 MB** | Cronet (Chromium Network Stack) para llamadas HTTP/3, QUIC y gRPC con multiplexación avanzada. |
| `liblynx.so` / `liblynxbase.so` | **4.16 MB** | Núcleo del framework Lynx para renderizado de componentes nativos ultra-fluidos sin sobrecarga de V8. |
| `libttffmpeg.so` / `libRTCFFmpeg.so` | **5.39 MB** | Procesamiento y compresión multimedia y streaming en tiempo real. |
| `libbytenn.so` | **3.11 MB** | ByteNN (ByteDance Neural Network Inference Engine) para procesamiento de IA en el dispositivo móvil sobre NPU/GPU/CPU. |
| `libttmplayer.so` | **3.07 MB** | Reproductor de video y animaciones técnicas optimizado para Android. |
| `libkrypton.so` | **1.59 MB** | Motor de renderizado 2D/3D Canvas acelerado por hardware para visualización de esquemas técnicos. |
| `libskity.so` | **1.38 MB** | Motor gráfico 2D ligero en C++ alternativo a Skia. |
| `libwasm.so` | **1.06 MB** | Intérprete y runtime WebAssembly para ejecución de módulos de cálculo seguros en el dispositivo. |
| `libcmark_gfm_flow.so` | **577 KB** | Parser C nativo para renderizado instantáneo de GitHub Flavored Markdown con soporte de sintaxis de código y tablas. |
| `libbdzstd.so` | **231 KB** | Compresión Zstandard propietaria de ByteDance para transferencias de diffs de código. |

---

## 3. AUDITORÍA DE MODELOS Y CONECTORES

### 3.1 Catálogo Oficial de Modelos Integrados
La aplicación expone un selector nativo multimodelo de vanguardia:
1. **TRAE Auto Model:** Enrutador heurístico de inferencia.
2. **Gemini-3.1-Pro-Preview & Gemini-3-Flash-Preview:** Modelos multimodales de Google.
3. **DeepSeek-V4-Pro & DeepSeek-V4.1-Flash:** Modelos de razonamiento y codificación profunda.
4. **MiniMax-M3 & MiniMax-M2.7:** Modelos orientados a síntesis y generación técnica.
5. **Kimi-K3 & Kimi-K2.5:** Modelos de contexto masivo de Moonshot AI.
6. **GPT-6.1-Sol, GPT-6-Sol, GPT-6-Luna, GPT-5.4 y GPT-5.2:** Familia de modelos avanzados de OpenAI.

### 3.2 Integración y Control
* **Control my computer (`trae.ai`):** Protocolo de emparejamiento seguro por token/cuenta para dirigir el IDE de escritorio (`TraeWork`) desde el teléfono inteligente.
* **Integración Git:** Conector nativo con GitHub (`Connect Github`) y selector de ramas de desarrollo (`Select Branch`).
* **Entorno de Ejecución:** Selector de contexto entre `Cloud` y `Local/Workstation`.

---

## 4. CONECTIVIDAD MCP Y COMPARACIÓN CON EL ECOSISTEMA FÉNIX

* **Soporte MCP Directo:** Al igual que Replit, Trae no expone un conector MCP genérico en la app cliente de Android, pero su protocolo de enlace remoto `trae.ai` cumple un rol conceptualmente idéntico al de un agente puente (Gateway Agent).
* **Innovación en Renderizado:** Su uso de Lynx en lugar de WebView o React Native pesado otorga tiempos de arranque inferiores a 200 ms y consumo de RAM mínimo (< 85 MB).

