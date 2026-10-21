# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: DEEPSEEK CHAT

**Fecha:** 17 de octubre de 2026  
**Dispositivo de Prueba:** Motorola Moto G77 (Modelo `ZY22MK7B2P`, Android 14)  
**Paquete Auditado:** `com.deepseek.chat`  
**Versión de la App:** 2.6.2 (Compilación 282, Split APK arm64-v8a)  
**Clasificación:** Asistente Conversacional de Razonamiento Profundo y Búsqueda Web  

---

## 1. RESUMEN EJECUTIVO
DeepSeek Chat para Android es el cliente móvil oficial para los modelos fundacionales de la familia DeepSeek (DeepSeek-V3 y DeepSeek-R1). Destaca por su velocidad de generación de tokens, su capacidad nativa de mostrar la cadena de razonamiento ("Pensamiento") en tiempo real y su motor de búsqueda web integrado.

En el plano de ingeniería de software, DeepSeek emplea una arquitectura nativa fuertemente apoyada en los componentes de infraestructura de código abierto de Tencent: **WCDB** (base de datos SQLite ultrarrápida cifrada multihilo), **MMKV** (almacenamiento clave-valor de latencia cero mapeado en memoria vía `mmap`) y **LiteAV SDK** para streaming de voz y audio.

---

## 2. ARQUITECTURA DE SOFTWARE Y BINARIOS NATIVOS

### 2.1 Inspección de Paquetes y Splits
* **APK Base:** `/data/app/~~z2xtex9QDHsj1nathHKEMQ==/com.deepseek.chat-0CY7BrFpUN4fezBKTRTQOg==/base.apk`
* **Splits:**
  * `split_config.arm64_v8a.apk`
  * `split_config.es.apk`
  * `split_config.xxhdpi.apk`
* **Directorio de Datos:** `/data/user/0/com.deepseek.chat`

### 2.2 Binarios Nativos Extraídos (`arm64-v8a`)
| Binario | Tamaño | Propósito Técnico |
| :--- | :--- | :--- |
| `libliteavsdk.so` | **11.62 MB** | Motor audiovisual Tencent LiteAV para streaming y comunicación por voz de baja latencia. |
| `libWCDB.so` | **4.33 MB** | Tencent WCDB (WeChat Database): SQLite de alto rendimiento con extensiones para concurrencia masiva y reparación automática de corrupción. |
| `libtxffmpeg.so` | **2.09 MB** | FFmpeg personalizado para decodificación y compresión de formatos de audio. |
| `libc++_shared.so` | **1.25 MB** | Runtime C++ estándar de LLVM NDK. |
| `libsmsdk.so` | **842 KB** | SDK de verificación y telemetría de seguridad. |
| `librscrypto.so` | **337 KB** | Criptografía nativa para autenticación y cifrado de paquetes de red. |
| `libmmkv.so` | **298 KB** | MMKV (Memory-Mapped Key-Value Store) de Tencent para persistencia instantánea sin bloqueo de I/O. |
| `libtxsoundtouch.so` | **194 KB** | Modulación de tono y velocidad de audio para lectura por voz. |
| `libvolc_log.so` | **134 KB** | Sistema de logs binarios de alto rendimiento. |
| `libapminsighta.so` / `libapminsightb.so` | **251 KB** | Monitoreo de rendimiento de aplicaciones (APM) y métricas de latencia de red. |

---

## 3. AUDITORÍA DE INTERFAZ DE USUARIO (UX/UI) Y CAPACIDADES DEL MODELO

### 3.1 Controles Principales
1. **Modo `Pensar` (DeepSeek-R1):**
   * Activa el modo de razonamiento explícito. Las respuestas incluyen una sección desplegable con el proceso de deducción matemática y lógica antes de emitir la respuesta final.
2. **Modo `Buscar`:**
   * Invoca el motor de navegación web en tiempo real para verificar información reciente o fuentes externas citadas con hipervínculos.
3. **Entrada Multimodal:**
   * Soporte de notas de voz con pulsación continua o manos libres (`(((·)))`).
   * Adjunto de archivos y capturas mediante el botón `+`.

---

## 4. CONECTIVIDAD MCP Y HERRAMIENTAS EXTERNAS

* **Cliente Móvil:** La aplicación no cuenta con soporte para el protocolo MCP en Android. Es un cliente orientado exclusivamente a la interacción conversacional texto/voz.
* **Extensibilidad:** Para agentes externos, DeepSeek ofrece una API compatible con el estándar de OpenAI (Function Calling / Tool Calling) disponible en la nube.

---

## 5. LECCIONES DE ARQUITECTURA PARA LA PWA FÉNIX

1. **Almacenamiento Mapeado en Memoria (`MMKV`):**
   * MMKV es una alternativa superior a `SharedPreferences` de Android y a `localStorage` básico, ya que utiliza `mmap` del kernel de Linux, eliminando demoras de escritura en disco al guardar estados de chats y tokens.
2. **Separación de Razonamiento y Búsqueda en Botones Conmutables:**
   * La interfaz de DeepSeek es minimalista y eficiente: dos botones en la barra de entrada (`Pensar` y `Buscar`) le dan al usuario control total sobre el costo de cómputo y la latencia de la respuesta.

