# ESPECIFICACIONES DE DISEÑO EN PENPOT — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  
> **Lienzo Penpot ID:** `331ebe47-5d89-804d-8008-d227b4c61a4f`  
> **Resolución Base Móvil:** 412 × 915 dp (Aspect Ratio 20:9)

---

## 1. PALETA DE COLORES Y DESIGN TOKENS
- **Superficie Base (Fondo de Pantalla):** `#0A0B10` (Negro carbón profundo)
- **TopAppBar / BottomBar Surface:** `#0E0F17` (Vidrio ahumado oscuro)
- **Tarjetas Bento / Input Pill:** `#161722` (Dark Glassmorphism)
- **Acento Fénix Fuego Núcleo:** `#FF3D00` (Rojo magma incandescente)
- **Acento Fénix Fuego Plasma:** `#FF9100` (Naranja fuego)
- **Acento Solar Activo / Indicadores:** `#FFD54F` / `#FFD700` (Oro solar)
- **Estado Grabación Audio:** `#00E676` (Verde esmeralda reactivo)
- **Estado Micrófono Reposo:** `#40C4FF` (Azul celeste)
- **Texto Principal:** `#FFFFFF` / `#F0F0F5`
- **Texto Secundario / Placeholders:** `#8C8C9E` / `#9E9EB2`

---

## 2. TABLEROS Y VARIANTES EN LIENZO (MATRIZ SECUENCIAL)
- **Tablero 1 (`[1. Home Bienvenida]`):** x = 0, y = 0. Portada oficial con Ave Fénix Hero (110 px), Bento Grid de herramientas, Memoria Persistente y Barra de Entrada con selectores dinámicos `[ ⚡ Modelos ▾ ]` y `[ 🎯 Modo ▾ ]`.
- **Tablero 2 (`[2. Chat Reposo]`):** x = 460, y = 0. Conversación activa con burbujas de diálogo, selectores dinámicos y campo de texto en reposo.
- **Tablero 3 (`[3. Grabando Audio]`):** x = 920, y = 0. Micrófono verde con halo y contador de grabación `"● Grabando audio (0:04)..."`.
- **Tablero 4 (`[4. Texto Escrito]`):** x = 1380, y = 0. Texto ingresado por el usuario y botón de enviar en amarillo activo.
- **Tablero 5 (`[5. Panel '+' Abierto]`):** x = 1840, y = 0. Velo oscuro y panel modal inferior de 240 dp con 4 tarjetas de acción.
- **Tablero 6 (`[6. Selector de Modelos]`):** x = 2300, y = 0. Modal bento con Entornos CLI (alfabético + semáforo 4 colores), LLM Locales (hardware RAM) y APIs con botón naranja `"Agregar Proveedor"`.
- **Tablero 7 (`[7. Agregar Proveedor & Asistente]`):** x = 2760, y = 0. Formulario de conexión (Nombre, URL, API Key), botón especial de guía y botón `"Enviar"` reactivo en verde.
- **Tablero 8 (`[8. Modo & Razonamiento]`):** x = 3220, y = 0. Selector de 5 modos Axiom (General, Build, Plan, Deep Research, Amigable) y selector de esfuerzo (`Bajo`, `Medio`, `Fuerte`).
- **Tablero 9 (`[9. Cartel: Modelo no responde]`):** x = 3680, y = 0. Diálogo emergente con opciones de Selección automática (Proxy Pool) y Selección manual de modelos.
- **Tablero 10 (`[10. Banner: Fallo Total de Proveedor]`):** x = 4140, y = 0. Banner perimetral rojo carmesí con acciones directas para API secundaria, API nueva y conmutación a Proveedor automático.
- **Tablero 11 (`[11. Modal '+': Archivos y Carpetas]`):** x = 4600, y = 0. Gestor de adjuntos multi-formato (PDF, TXT, DOCX, XLSM, imágenes, videos, MD), selector de directorios vía `OpenDocumentTree` y creador de carpetas locales.
- **Tablero 12 (`[12. Panel de Red y Servicios]`):** x = 5060, y = 0. Switch en vivo del Servidor MCP (Ktor), configuración de túneles HTTPS fijos (Cloudflare / Ngrok) con asistente guiado, lista de agentes conectados con matriz granular de permisos del móvil y skills dinámicas apagadas por defecto.
- **Tablero 13 (`[13. Banner: Solicitud Conexión MCP]`):** x = 5520, y = 0. Alerta flotante superior de autorización Bearer/OAuth (`[Agente] quiere conectarse a tu MCP`) con match-code, timer y botones de concesión/rechazo.
- **Tablero 14 (`[14. Ajustes: 4 Esenciales]`):** x = 5980, y = 0. Menú limpio con las 4 únicas opciones visibles para el usuario (Herramientas MCP, Permisos, Almacenamiento, Linux Sandbox) con delegación de opciones técnicas a OpenCode.
- **Tablero 15 (`[15. Accesibilidad: Exención Legal]`):** x = 6440, y = 0. Modal de consentimiento informado con pros/contras, cláusula legal de exención, checkbox obligatorio y botón reactivo condicional `Permitir`.
- **Tablero 16 (`[16. Estados: Construcción Accesibilidad]`):** x = 6900, y = 0. Evolución reactiva de la ficha (construcción con validaciones, cooldown de 5 min, botón verde con Intent directo a `Settings.ACTION_ACCESSIBILITY_SETTINGS` y notificación local push).
- **Tablero 17 (`[17. Registro Institucional]`):** x = 7360, y = 0. Formulario de registro con callout de privacidad y consentimiento legal.
- **Tablero 18 (`[18. Login & Sesión]`):** x = 7820, y = 0. Pantalla de acceso con avatar de Fénix y enlace a registro.
- **Tablero 19 (`[19. F-Code Editor]`):** x = 0, y = 1000. Editor de código on-device con telemetría RAM/Temp, tabs y números de línea.
- **Tablero 20 (`[20. F-Code Terminal & Split IA]`):** x = 460, y = 1000. Terminal interactiva y split chat IA con diffs en tiempo real.
- **Tablero 21 (`[21. F-Code Gestor de Librerías]`):** x = 920, y = 1000. Gestor de paquetes y dependencias con política Zero-Bloat.
- **Tablero 22 (`[22. F-Code GitHub & Sync]`):** x = 1380, y = 1000. Sincronización git y repositorios.
- **Tablero 23 (`[23. Consola Linux Nativa]`):** x = 1840, y = 1000. Terminal Debian 12 Bookworm con PTY interactiva y barra de teclas especiales.
- **Tablero 24 (`[24. F-Docs: Editor Asistido & Jerarquía]`):** x = 2300, y = 1000. Editor paginado con árbol de 4 niveles estancos, 20 herramientas APA 7ma, negrita F-Z táctil, botón deslizable para abrir al Maestro a mitad de pantalla (Split View) con contexto exclusivo del documento abierto y botón opcional de consulta global.
- **Tablero 25 (`[25. Fénix Studio: Chat Multimodal & Ruido Blanco]`):** x = 2760, y = 1000. Laboratorio multimodal en azul noche, reproductor de 10 ruidos blancos, documento maestro (PDF/DOCX) y tarjetas de diapositivas 16:9, tablas Parte B y simulador de examen oral.
- **Tablero 26 (`[26. Conectores & Cloud MCP]`):** x = 3220, y = 1000. Gestor de servicios externos y cloud con switches ON/OFF individuales: GitHub (PAT), Gmail (Contraseña de Aplicación de 16 letras con guía paso a paso), Cloudflare (Workers & R2), Supabase (Postgres & Auth), Telegram Bot (Alertas proactivas de examen) y Context7 MCP (recuperación documental profunda). Incluye asistente IA para generación de skills personalizadas e inputs directos a Android KeyStore sin exponer credenciales.
- **Tablero 27 (`[27. Centro de Ayuda & Guías Oficiales]`):** x = 3680, y = 1000. Centro de documentación integral con buscador y fichas técnicas sustanciales (> 5 renglones, > 300 palabras normativas) por cada módulo (Consola Debian 12 PTY, F-Docs Split Maestro, Conexión Gmail 16 letras, Cloudflare/Supabase Costo $0, y Soberanía Local-First con límites éticos de la Regla 25), con enlaces directos a documentación oficial.
- **Tablero 28 (`[28. Creaciones: Marketplace & Instant Apps]`):** x = 4140, y = 1000. Hub comunitario y catálogo estilo VS Code con filtro de 10 categorías (MCP, Instant Apps, Agentes, Modelos IA, Runtime, Skills, Conectores, Nube, Productividad, Mis Creaciones). Incorpora sello verificado de seguridad `Fénix Oficial ✓`, auditoría automática `Aprobado por Criticón`, alerta roja carmesí para paquetes riesgosos, puntuación comunitaria de 5 estrellas y panel de publicación autenticado con base de datos.


---

## 3. COMPONENTES Y COTAS
- **TopAppBar:** Alto 56 dp, divisor en y = 56. Logo Fénix en x = 12, y = 10 (32×32 px). La botonera derecha se compone de: Sync (nube), Chat, Ajustes, **Ayuda (`?`)** y **Login/Avatar** (en el extremo derecho, con Ayuda inmediatamente a su lado).
- **Hero Fénix:** Diámetro 110 px en y = 80, centrado horizontalmente.
- **Tarjetas Bento:** 2 columnas de 176 dp de ancho, 58 dp de alto, margen lateral 23 dp.
- **Tarjeta Memoria:** Ancho completo 366 dp, alto 64 dp, margen lateral 23 dp.
- **Selectores Dinámicos:** Alto 28 dp, radio de curvatura 14 dp, ubicados a 8 dp sobre el Input Pill (y = 746 dp). Chip Modelo: ancho auto/140 dp, Chip Modo: ancho auto/120 dp.
- **Acordeón Desplegable de Proveedor:** Tarjeta expandible en el Selector de Modelos. Al pulsar el proveedor (ej. Gemini), la tarjeta se expande verticalmente revelando la lista indentada de modelos (`gemini-2.0-flash`, `gemini-1.5-pro`, `gemini-1.5-flash`) con selector radial de selección activa.
- **Cartel Modal "El Modelo No Responde":** Diálogo de alerta con borde ámbar (`#FF9100`), mensaje explicativo y 2 botones de acción: `[ ⚡ Selección automática ]` (activa proxy pool de comprobación) y `[ 👆 Selección manual ]` (abre acordeón de modelos).
- **Banner de Emergencia "Ningún Modelo Responde":** Banner crítico perimetral con acento rojo carmesí (`#FF1744`), mensaje de agotamiento de proveedor y 3 botones táctiles: `[ 🔑 API secundaria ]`, `[ ➕ API nueva ]` y `[ 🔄 Proveedor automático ]`.
- **Input Pill:** Ancho 380 dp, alto 52 dp, x = 16 dp, y = 780 dp.
- **Bottom Navigation Bar:** Alto 72 dp, y = 843 dp a 915 dp. 5 pestañas de 82.4 dp cada una: `🔥 Fénix IA`, `>_ Terminal`, `⚡ F-Code`, `📚 F-Docs`, `🎨 Creaciones`.
- **Thinking Loader:** Micro-escala 24×24 px en burbuja de respuesta estilo Claude.
- **Tablero 23 — Consola Linux Nativa (`>_ Terminal`):** x = 1840, y = 1000. Canvas oscuro `#0A0B10`, barra superior con estado de sandbox (Debian 12 Bookworm, glibc 2.36, PTY activo, RAM y CPU en tiempo real), botón de acciones rápidas CLI (`opencode`, `agy`, `top`, `clear`), terminal interactiva con emulación ANSI a 60 FPS, prompt estilizado `fenix@debian-12:~$` y barra flotante de teclas especiales sobre teclado: `[ ESC ]` `[ TAB ]` `[ CTRL ]` `[ ALT ]` `[ | ]` `[ / ]` `[ - ]` `[ ^C ]`.
- **Tablero 24 — F-Docs: Editor Asistido & Jerarquía Académica (`📚 F-Docs`):** x = 2300, y = 1000. Canvas `#090A0F`, selector dual de modo (`[ 📝 Editor Asistido ]` activo vs `[ 🧪 Fénix Studio ]`), breadcrumb de jerarquía estanca de 4 niveles (`📁 Medicina > 📁 3º Año > 📁 Farmacología > 📁 U2: Farmacocinética`), barra de 20 herramientas de edición asistida (APA 7ma, Negrita F-Z táctil, Citas, Tablas, OCR, Guardado, PDF, DOCX), lienzo paginado A4 con términos hiper-conectados y callout flotante del Tutor Socrático sobre negritas clave, botón interactivo para deslizar al Maestro a mitad de pantalla (Split View) con contexto exclusivo del documento abierto y telemetría local (`sqlite-vec` + Room).
- **Tablero 25 — Fénix Studio: Chat Multimodal & Ruido Blanco (`📚 F-Docs`):** x = 2760, y = 1000. Canvas Azul Noche Institucional `#0B0E1B`, selector dual con `[ 🧪 Fénix Studio ]` activo, reproductor acústico de Ruidos Blancos (10 opciones on-device: Lluvia, Tormenta, Bosque, Olas, Cafetería, 40Hz Alfa, etc.), botón de Documento Maestro (Partes A-F compiladas) con descarga directa en un clic (`[ 📥 PDF ]` y `[ 📥 DOCX ]`), chat interactivo con tarjetas de la IA Maestra (Diapositiva 16:9 con curva gráfica y botón de presentación, Tabla Relacional Parte B con badges `[FUENTE]` e `[INFERENCIA]`, y Simulador de Examen Oral en Vivo con grabación de voz), input pill con selector dinámico de formato de tarjeta y micrófono.
- **Tablero 26 — Conectores & Cloud MCP (`TopAppBar > Conectores`):** x = 3220, y = 1000. Canvas `#090A0F`, TopAppBar con botón volver y `➕ Conector`, banner de seguridad con cifrado en Android KeyStore, tarjetas con interruptores reactivos ON/OFF (GitHub, Gmail App Password con guía de 16 letras, Cloudflare Workers/R2, Supabase Postgres, Telegram Bot para alertas de examen, Context7 MCP Cloud), y generador de skills personalizadas mediante IA con inputs enmascarados hacia `secrets`.
- **Tablero 27 — Centro de Ayuda & Guías Oficiales (`TopAppBar > ?`):** x = 3680, y = 1000. Canvas `#090A0F`, buscador temático de guías por interfaz, tarjetas estructuradas de más de 5 renglones por módulo con explicaciones técnicas de arquitectura y enlaces salientes a documentación oficial (Debian, Apache OpenOffice/PDF, Google Support, Cloudflare Docs, Supabase Docs y Protocolo Fénix).
- **Tablero 28 — Creaciones: Marketplace Comunitario & Instant Apps (`🎨 Creaciones`):** x = 4140, y = 1000. Canvas `#090A0F`, buscador superior, selector de pestañas Mis Creaciones vs Comunidad Global, barra horizontal de chips de categorías, tarjetas con distintivo oficial verificado `Fénix Oficial ✓`, tarjetas de la comunidad aprobadas por Criticón en verde, tarjetas rojas de alerta perimétricas (`#FF1744`) para extensiones bloqueadas por permisos sospechosos y panel inferior de publicación con autenticación y auditoría previa.




