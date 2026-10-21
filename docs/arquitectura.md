# ARQUITECTURA DEL SISTEMA — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. PRINCIPIO DE SEPARACIÓN LIMPIA
La arquitectura desacopla estrictamente:
- **MOTOR:** Lógica de orquestación de agentes, protocolo MCP y runtime de ejecución.
- **CONTEXTO:** Datos, memorias y proyectos provistos por el usuario en tiempo real.
- **DOCUMENTOS:** Archivos académicos y código fuente analizados.
- **CONFIGURACIÓN:** Preferencias, temas y tokens configurables.

## 2. PILA TECNOLÓGICA
- **Cliente Android:** Kotlin 2.4.10, Jetpack Compose (BOM 2026.08.00), Material Design 3, Coroutines/Flow, Hilt 2.60.1.
- **Renderizado de Animaciones:** Lottie Compose / AnimatedVectorDrawable.
- **Servidor Local Móvil:** Ktor embebido en Android (`localhost:8080/mcp`) con autenticación Bearer Token local soberana.
- **Diseño & Maquetado:** Penpot MCP + Streamable HTTP JSON-RPC 2.0.

---

## 3. SUBSISTEMA DE CONSOLA LINUX NATIVA & SANDBOX ROOTLESS
Para permitir la ejecución de agentes CLI (OpenCode, Google Antigravity `agy`, Claude Code, Node.js 22, Bun, Python 3.11) sin root y eludiendo las restricciones $W \wedge X$ en Android 14–16:
- **Motor de Emulación de Kernel:** `termux/proot` + `libtalloc` compilados con Android NDK r29 (alineación de páginas ELF a 16 KB) empaquetados en `app/src/main/jniLibs/arm64-v8a/libproot.so` declarando `useLegacyPackaging = true`.
- **Ejecución Legal bajo SELinux:** El sistema Android extrae las librerías nativas en `applicationInfo.nativeLibraryDir`, un directorio de solo lectura con contexto ejecutable permitido (`apk_data_file`), validando llamadas `execve()` bajo `targetSdk = 34` y `37`.
- **Distribución Base (Rootfs):** Debian 12 Bookworm arm64 estándar con `glibc 2.36` (descargado desde Linux Containers con validación SHA-256), almacenado en `context.noBackupFilesDir + "/sandbox/debian-12"` para prevenir subidas masivas a Google Drive.
- **Sub-sistema PTY & Renderizado ANSI:** Apertura directa del maestro de pseudo-terminal `/dev/ptmx` mediante la biblioteca JNI nativa Bionic `libtermux.so` (~24 KB C). El frontend en Compose renderiza secuencias de escape ANSI a 60 FPS con tipografía monospace y una barra de teclas rápidas móviles (`ESC`, `TAB`, `CTRL`, `ALT`, `|`, `/`, `-`, `^C`).
- **Bridge MCP Local:** Inyección automática en `~/.config/opencode/opencode.json` de la URL `http://127.0.0.1:8080/mcp` con token Bearer, conectando los agentes CLI a las 94 herramientas MCP de Fénix Móvil.

