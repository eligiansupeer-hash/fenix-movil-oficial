# PLAN MAESTRO DE IMPLEMENTACIÓN PARA JULES — FÉNIX MÓVIL (FASE A FASE)

> **Destinatario de Ejecución:** Agente Autónomo Jules  
> **Estándar Rector:** ISO/IEC/IEEE 29148  
> **Protocolo Operativo:** Protocolo GUARDIAN  
> **Autor y Titular:** Elí Gian Cortez  
> **Licencia:** Licencia MIT (Copyright © 2026 Elí Gian Cortez)  
> **Repositorio Fuente de Verdad:** `https://github.com/eligiansupeer-hash/fenix-movil-oficial`  

---

## 🎯 1. MISIÓN DE JULES Y DECLARACIÓN DE PRINCIPIOS

Jules: Tu misión es construir la base de código limpia, modular, verificada y 100% funcional de **Fénix Móvil**, tomando como fuente de verdad la suite viva de requisitos ISO 29148 y el dossier de los 28 tableros maquetados.

### Directivas Inviolables de Construcción:
1. **La misión NO es crear la app desde cero inventando arquitecturas:** Todo el diseño de interfaz, las cotas, los colores, los estados y los contratos de datos ya han sido diseñados y sellados en los 28 tableros de Penpot y en la documentación viva.
2. **La misión TAMPOCO es usar código sucio, roto o dependencias huérfanas de terceros:** Debes inspeccionar, limpiar quirúrgicamente y refactorizar.
3. **Eliminación Total y Definitiva de Termux:** Está terminantemente prohibido mantener paquetes, scripts o referencias a `com.termux`. El entorno Linux se ejecuta como sandbox nativa en user-space mediante `libproot.so` empaquetado legalmente en `jniLibs` con `glibc 2.36`.
4. **Eliminación y Sustitución del MCP de Daniele (`remote-control-mcp-android`):** Debes extirpar completamente el repositorio y el código sucio de Daniele que hoy reside en el proyecto. En su lugar, debes implementar **nuestro propio servicio nativo** en Kotlin (`FenixAccessibilityBridge` y `FenixMcpServer` en Ktor) que cumpla todas las funciones de accesibilidad, dispatch táctil e inspección de UI con código limpio, seguro y soberano.
5. **Costo Cero Absoluto:** Ninguna implementación debe introducir servicios de pago, dependencias comerciales ni límites que obliguen al usuario a pagar.
6. **Límites Éticos Inviolables (Regla 25):** La IA no es psicóloga ni médico personal. La lógica de los agentes debe incorporar los filtros éticos para derivar amablemente a profesionales humanos ante crisis y mantener el estudio médico en el marco estrictamente pedagógico.
7. **Atribución y Licencias en el Centro de Ayuda:** En la parte inferior del Tablero 27 (Ayuda) debe figurar la cláusula de eximición jurídica y el crédito:  
   *`Creado por Elí Gian Cortez. Todos los derechos reservados. Licencia MIT.`*

---

## 📚 2. REPOSITORIOS DE REFERENCIA Y ANÁLISIS

Jules dispondrá de los siguientes 3 repositorios como material de análisis y referencia técnica comparativa:

1. **`https://github.com/eligiansupeer-hash/fenix-ia-movil-con-mcp-integrado`**
   - *Propósito de análisis:* Extraer la lógica de integración existente de modelos y UI base de Fénix, descartando código comentado y dependencias obsoletas.
2. **`https://github.com/simonschubert/kai-9000` (Kai 9000 de Simon Schubert)**
   - *Propósito de análisis:* Estudiar la orquestación local de agentes, bus de eventos reactivos y arquitectura de bridge en Kotlin/Android.
3. **`https://github.com/daniele/remote-control-mcp-android` (Daniele MCP)**
   - *Propósito de análisis:* Analizar las llamadas a `AccessibilityNodeInfo` y eventos de inyección de gestos (`GestureDescription`), **con el único fin de reprogramar nuestra versión propia limpia** y descartar el repositorio externo.

---

## 🛠️ 3. PLAN DE IMPLEMENTACIÓN FASE A FASE

---

### FASE 1: Inicialización del Proyecto Android Limpio & NDK r29
- **Objetivo:** Crear el andamiaje del proyecto Android Studio limpio en Kotlin con Jetpack Compose.
- **Acciones Técnicas:**
  1. Configurar `build.gradle.kts` con:
     - `minSdk = 26` (Android 8.0 Oreo).
     - `targetSdk = 34` (Android 14) con compatibilidad hacia Android 15/16 (`targetSdk = 37`).
     - `useLegacyPackaging = true` en `android.packagingOptions.jniLibs`.
  2. Compilar/incorporar `libproot.so` en `app/src/main/jniLibs/arm64-v8a/` utilizando Android NDK r29 con alineación ELF estricta a 16 KB (`max-page-size=16384`) para eludir $W \wedge X$ legalmente sin root.
  3. Configurar dependencias esenciales a costo $0: Room DB (`sqlite-vec`), Ktor Server CIO con soporte SSE, Coil para imágenes, Lottie Compose y Material 3.
- **Validación y Auditoría:**
  - Compilación exitosa de APK Debug.
  - Verificación con `readelf -l libproot.so` de que el alineamiento de segmento `LOAD` sea `0x4000` (16 KB).
  - Comprobación de instalación en Android 14/15 sin alertas de Google Play Protect.

---

### FASE 2: Consola Linux Nativa & PTY Interactiva Bionic (Tablero 23)
- **Objetivo:** Proveer la terminal Debian 12 Bookworm arm64 estándar con compatibilidad `glibc 2.36`.
- **Acciones Técnicas:**
  1. Extirpar cualquier vestigio de Termux o llamadas a procesos externos.
  2. Implementar `libtermux.so` (~24 KB de C nativo compilado con Bionic) para abrir descriptores de pseudoterminal `/dev/ptmx` y llamadas `ioctl(TIOCSWINSZ)`.
  3. Crear el servicio de descarga y extracción atómica del rootfs Debian 12 arm64 mínimo (~45 MB comprimido) en `context.noBackupFilesDir`.
  4. Implementar el composable `LinuxTerminalView` con renderizado ANSI a 60 FPS acelerado por GPU y la barra de teclas de control flotante (`ESC`, `TAB`, `CTRL`, `ALT`, `|`, `/`, `-`, `^C`, `▲`, `▼`).
  5. Configurar chips táctiles de inicio rápido para agentes CLI: `[ opencode ]`, `[ agy ]`, `[ htop ]`, `[ clear ]`.
- **Validación y Auditoría:**
  - Ejecución de comandos `uname -a` y `ldd --version` verificando `glibc 2.36`.
  - Instalación y arranque de Bun (`bun --version`) y OpenCode dentro de la sandbox sin privilegios de root.

---

### FASE 3: Servidor MCP Ktor Propio & Puente de Accesibilidad Soberano (Tableros 12, 13, 15, 16)
- **Objetivo:** Sustituir definitivamente el repositorio de Daniele por nuestro propio servicio MCP nativo en Android.
- **Acciones Técnicas:**
  1. Implementar `FenixMcpServer` en Kotlin utilizando Ktor Server CIO sobre `http://127.0.0.1:8080/mcp` con transporte HTTP Streamable (SSE) y autenticación Bearer token dinámica.
  2. Implementar `FenixAccessibilityBridge`: servicio de accesibilidad nativo que expone las herramientas:
     - `mcp_get_screen_state`: Inspección del árbol de nodos activos (`AccessibilityNodeInfo`).
     - `mcp_dispatch_click`: Inyección de toque mediante `GestureDescription` en coordenadas x, y.
     - `mcp_dispatch_scroll`: Gestos de deslizamiento programático.
     - `mcp_launch_app`: Despacho de intents explícitos a paquetes permitidos.
  3. Incorporar modal de exención legal obligatoria (Tablero 15) con checkbox de consentimiento y el banner flotante de autorización de conexión de agentes con match-code (Tablero 13).
- **Validación y Auditoría:**
  - El escaneo estático de seguridad debe certificar cero dependencias de código externo de Daniele.
  - Ejecución de un tool-call JSON-RPC 2.0 desde la terminal Debian hacia `http://127.0.0.1:8080/mcp` obteniendo respuesta en < 50 ms.

---

### FASE 4: Provider Pool Router Multi-Key & Bóveda Android KeyStore (Tableros 6, 7, 9, 10, 26)
- **Objetivo:** Garantizar failover automático ante agotamiento de cuota (error 429) y seguridad de credenciales.
- **Acciones Técnicas:**
  1. Implementar `ProviderPoolRouter`: gestor de rotación de claves por proveedor (Gemini, Groq, Mistral, OpenAI) con conmutación automática e imperceptible ante `429 Too Many Requests`, `5xx` o timeout.
  2. Implementar `KeystoreVault`: almacenamiento cifrado en `EncryptedSharedPreferences` con AES-256-GCM respaldado por hardware en Android KeyStore.
  3. Implementar el asistente de conexión de Gmail con guía de "Contraseña de aplicación" de 16 letras (`myaccount.google.com/apppasswords`) y los conectores para GitHub (PAT), Cloudflare Workers/R2, Supabase Postgres y Telegram Bot.
  4. Desplegar los componentes de diálogo reactivo: Cartel modal de fallo de modelo (Tablero 9) y Banner crítico perimetral (Tablero 10).
- **Validación y Auditoría:**
  - Prueba de inyección de error 429 simulado: el router debe rotar a la segunda API key en < 200 ms sin interrumpir la sesión del usuario.
  - Verificación de que ninguna API key se guarde en texto plano en disco ni se envíe en los prompts de los agentes.

---

### FASE 5: Módulos Académicos F-Docs Dual & F-Code (Tableros 19, 20, 21, 24, 25)
- **Objetivo:** Construir el motor de procesamiento curricular, editor de documentos y laboratorio multimedia.
- **Acciones Técnicas:**
  1. **Editor Asistido (Tablero 24):**
     - Parser SAX streaming ultra-liviano para archivos DOCX, PDF, TXT, MD y XLSM a costo $0.
     - Barra de 20 herramientas con auto-formato según Normas APA 7ma edición.
     - Sistema de anclas cognitivas F-Z: negritas táctiles con `ClickableSpan` para invocar al tutor socrático.
     - Pestaña deslizable inferior para desplegar al Maestro a media pantalla (Split Sheet), garantizando que el contexto inyectado a la IA sea **única y exclusivamente el del documento abierto**.
  2. **Fénix Studio (Tablero 25):**
     - Generador y reproductor de sonido ambiente con las 10 opciones acústicas de concentración on-device (Lluvia, Tormenta, Bosque, Olas, Ondas Alfa 40Hz, etc.) a costo $0 y sin consumo de datos.
     - Acceso al Documento Maestro compilado (Partes A-F) y exportador local instantáneo en PDF y DOCX.
     - Generador de diapositivas 16:9, tablas relacionales con sellos de trazabilidad (`[FUENTE]`, `[INFERENCIA]`) y simulador de examen oral en vivo por voz.
  3. **F-Code (Tableros 19, 20, 21):**
     - Editor multi-pestaña con telemetría de hardware (RAM/Temperatura/Git), split view con diffs interactivos y gestor de dependencias Zero-Bloat.
- **Validación y Auditoría:**
  - Apertura de un documento DOCX de 200 páginas con consumo de memoria < 15 MB de RAM.
  - Exportación de documento a PDF y DOCX generada 100% de manera local en el teléfono.

---

### FASE 6: Marketplace de Creaciones, Filtro Criticón & Auditoría Final (Tableros 27, 28)
- **Objetivo:** Desplegar el hub comunitario seguro y sellar la calidad del sistema.
- **Acciones Técnicas:**
  1. **Marketplace Comunitario (Tablero 28):**
     - Catálogo de 10 categorías taxonómicas (MCP, Instant Apps, Agentes, Modelos IA, Runtime, Skills, Conectores, Nube, Productividad, Mis Creaciones).
     - Mecanismo criptográfico de verificación para la marca protegida `Fénix Oficial ✓`.
     - Pasarela de publicación obligatoria: autenticación contra base de datos, nombre real, correo, permisos explícitos y ficha detallada de más de 5 renglones y 500 palabras.
     - Módulo de auditoría "Criticón": escaneo estático y dinámico de extensiones; los paquetes sospechosos se proyectan con una tarjeta de alerta perimétrica en rojo carmesí (`#FF1744`), bloqueando su ejecución.
     - Semillero inicial poblado con al menos 5 elementos verificados por categoría.
  2. **Centro de Ayuda Oficial (Tablero 27):**
     - Buscador temático de ayuda y tarjetas extensas (> 5 renglones, > 300 palabras normativas) por interfaz con enlaces a sitios oficiales.
     - Inserción en el pie de página de la atribución a los proyectos Open Source, eximición jurídica y el crédito:  
       *`Creado por Elí Gian Cortez. Todos los derechos reservados. Licencia MIT.`*
  3. **Auditoría de Rendimiento y Empaquetado Release:**
     - Auditoría de los 11 puntos de la Constitución Operativa.
     - Compilación del APK Release firmado con ProGuard/R8 optimizado.
- **Validación y Auditoría:**
  - Test de 60 FPS estables en transiciones UI bajo Jetpack Compose.
  - Consumo de RAM en reposo < 45 MB para la aplicación base.

---

## 📋 4. ARQUITECTURA DE VALIDACIÓN REMOTA EN GITHUB ACTIONS CI (PROHIBICIÓN ESTRICTA DE COMPILACIÓN LOCAL EN JULES)

> [!CRITICAL]
> **REGLA DE ORO DE HIGIENE: PROHIBICIÓN TOTAL DE `./gradlew build` O `assemble` DENTRO DEL SANDBOX DE JULES**  
> Jules tiene **terminantemente prohibido** ejecutar `./gradlew build`, `./gradlew assembleDebug` o cualquier comando de compilación local que genere carpetas de caché `.gradle/` o artefactos binarios `.dex`/`.jar`.  
> Intentar compilar localmente genera más de 80 MB de binarios que saturan el límite de tamaño de la API de Jules y abortan la sesión. Jules **SOLO escribe código fuente de texto puro** (`.kt`, `.kts`, `.xml`, `.c`, `.h`, `.md`).

### Flujo de Verificación Continua en la Nube (GitHub Actions CI):
1. **Compilación Asistida en la Nube:**  
   - Cada commit o push de Jules a su rama de trabajo dispara de forma automática el pipeline `.github/workflows/android-ci.yml` en los servidores de GitHub (runners Ubuntu con 16 GB de RAM dedicadas).
   - GitHub Actions se encarga de descargar el código, configurar JDK 17, compilar el proyecto (`assembleDebug`), ejecutar los tests y generar el APK sin consumir 1 solo byte de RAM en el entorno local de Jules.

2. **Retroalimentación en Tiempo Real para Jules:**  
   - Jules debe observar el estado del **Check de GitHub Actions** asociado a su rama o Pull Request.
   - **Si el Check falla (`FAILED`):** Jules inspecciona el log de error que GitHub Actions arroja en el check (por ejemplo, errores de tipado de Kotlin, imports faltantes, dependencias de Gradle desalineadas) y aplica quirúrgicamente la corrección en el archivo `.kt` o `.kts` correspondiente.
   - **Si el Check pasa (`SUCCESS` / Verde):** Significa que el código compila de forma 100% limpia y el APK ha sido generado con éxito en los servidores de GitHub.

3. **Pruebas de Binarios NDK y 16 KB ELF (Inspección Estática):**  
   - Para verificar la compatibilidad de `libproot.so` con alineación de 16 KB, realizar únicamente inspección estática de encabezados sin compilar:
     ```bash
     readelf -l app/src/main/jniLibs/arm64-v8a/libproot.so | grep -i "LOAD"
     ```
   - Verificar que `max-page-size` sea 16384 (16 KB) para garantizar compatibilidad con Android 14, 15 y 16 sin W^X crashes.

---

## 📋 5. ENTREGABLES EXIGIDOS A JULES EN CADA FASE

Al culminar cada fase, Jules debe:
1. Presentar el log de pruebas automatizadas y comandos ejecutados sin errores.
2. Confirmar la ausencia total de código de Termux y del MCP de Daniele.
3. Verificar que no se hayan introducido APIs ni servicios de pago (Costo $0).
4. Realizar commit atómico documentando los cambios en el nuevo repositorio `fenix-movil-oficial`.


---

*Fin del Plan Maestro. Aprobado para ejecución inmediata por Jules.*
