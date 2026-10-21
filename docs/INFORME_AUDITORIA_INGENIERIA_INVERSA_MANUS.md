# INFORME MAESTRO DE AUDITORÍA E INGENIERÍA INVERSA: MANUS APP
**Fecha del Informe:** 17 de octubre de 2026  
**Objetivo:** Análisis integral de arquitectura, UX/UI, conectividad MCP, sandbox de ejecución, inventario de paquetes y capacidades operativas de la aplicación móvil Manus (`tech.butterfly.app` v26.18.3) para extraer aprendizajes aplicables a **Fénix Mobile MCP**.  
**Dispositivo Auditado:** Motorola Moto G77 (`ZY22MK7B2P`), Android 14.  
**Conexión MCP Verificada:** Servidor MCP Móvil Fénix conectado exitosamente a través de túnel seguro Cloudflare HTTPS (`mobile_mcp_bearer` con 97 herramientas).

---

## 1. RESUMEN EJECUTIVO

Mediante inspección estática del APK de Android, exploración interactiva de accesibilidad y un interrogatorio técnico directo en profundidad contra el modelo y sandbox de Manus, se obtuvo la radiografía técnica completa del ecosistema Manus:

1. **Cliente Móvil Liviano (Flutter/Dart AOT):** La aplicación móvil no procesa cómputo pesado localmente. Es una UI reactiva construida en Flutter con librerías C++ nativas para WebRTC (`libjingle_peerconnection`), renderizado de PDFs (`libpdfium`), sockets en tiempo real (`liblivekit_ffi`) y base de datos local SQLite (`libsqlite3`).
2. **Segregación Operativa (Tareas vs. Asistente "Cue"):** Manus ha separado su arquitectura: la app de Manus se especializa en **Tareas autónomas pesadas** (sandbox, terminal, scraping, código), mientras que las funciones conversacionales livianas y proactivas se han trasladado a una nueva app satélite denominada **Cue**.
3. **Infraestructura Cloud del Sandbox (KVM / 24 GB RAM / 6 vCPUs):** Cada tarea dispone de una máquina virtual/microVM en la nube basada en **KVM**, corriendo **Ubuntu 24.04.5 LTS (Kernel Linux 6.18.38+ x86_64)**, con **6 vCPUs Intel Xeon @ 2.10 GHz**, **24 GB de memoria RAM** (24,418 MB) y **50 GB de almacenamiento SSD**.
4. **Tooling de Desarrollo e IDE Headless (LSP):** El sandbox no solo tiene intérpretes básicos; cuenta con un stack de 15 servidores de lenguaje (LSPs para TypeScript, Pyright, Prisma, Vue, Astro, Svelte, Intelephense, YAML, Dockerfile) que permiten análisis semántico y corrección de código en tiempo real.
5. **Stack Científico y de Documentación Forense (102 librerías Python):** Dispone de una suite exhaustiva para generación y manipulación de documentos PDF (`weasyprint`, `reportlab`, `xhtml2pdf`, `pypdf`, `fpdf2`, `pyHanko`), automatización de navegador (`playwright 1.63.0`), APIs locales (`fastapi`, `uvicorn`, `flask`) y supervisor de procesos (`supervisor 4.2.5`).

---

## 2. ESPECIFICACIONES DE INFRAESTRUCTURA DEL SANDBOX (VERIFICADAS)

Los siguientes datos fueron medidos y volcados directamente desde la terminal del sandbox de Manus:

| Recurso / Parámetro | Valor Verificado en Sandbox | Comportamiento Observado |
|---|---|---|
| **Sistema Operativo** | `Ubuntu 24.04.5 LTS` | Distribución Linux oficial LTS de 64 bits. |
| **Kernel Linux** | `6.18.38+ x86_64` | Kernel reciente optimizado para virtualización. |
| **Virtualización / Aislamiento** | `KVM` (Kernel-based Virtual Machine) | No se detectaron contenedores Docker ni LXC visibles; microVM aislada. |
| **Procesador (CPU)** | `Intel(R) Xeon(R) Processor @ 2.10GHz` | 6 procesadores/vCPUs disponibles reportados por `nproc`. |
| **Memoria RAM** | Total: `24,418 MB` (~24 GB)<br>Libre: `22,870 MB`<br>Disponible: `21,885 MB` | Amplio margen de memoria para compilar código, correr navegadores y procesar PDFs grandes. |
| **Almacenamiento (Disco)** | Partición raíz `/`: `49 GB` SSD | 50 GB de almacenamiento efímero por tarea. |
| **Usuario y Directorio** | `ubuntu` en `/home/ubuntu` | Entorno de usuario sin root restrictivo por defecto. |
| **Conectividad de Red** | HTTPS abierto hacia el exterior | `curl -I https://example.com` retornó HTTP status 200 en 3.22 s. |

---

## 3. INVENTARIO COMPLETO DE RUNTIMES Y PAQUETES PREINSTALADOS

### 3.1 Paquetes Globales de Node.js (`npm list -g --depth=0`) — 15 Paquetes
El entorno cuenta con Node.js 22.13.0, npm 10.9.2 y pnpm 11.25.0, con un conjunto de herramientas LSP profesionales:

1. `@astrojs/ts-plugin` (v1.10.10) — Plugin de TypeScript para proyectos Astro.
2. `@prisma/language-server` (v31.11.0) — Language Server para modelos de bases de datos Prisma ORM.
3. `@vue/typescript-plugin` (v3.3.8) — Soporte de tipos para Vue.
4. `corepack` (v0.30.0) — Gestor de paquetes alternativos (Yarn/pnpm).
5. `dockerfile-language-server-nodejs` (v0.15.0) — Servidor de lenguaje para validación sintáctica de Dockerfiles.
6. `graphql-language-service-cli` (v3.5.0) — Autocompletado y validación de esquemas GraphQL.
7. `intelephense` (v1.18.5) — Language Server completo para PHP.
8. `npm` (v10.9.2) — Gestor de paquetes estándar de Node.js.
9. `pnpm` (v11.25.0) — Gestor de paquetes de alto rendimiento con symlinks.
10. `pyright` (v1.1.411) — Type checker estático de Microsoft para Python.
11. `svelte-language-server` (v0.18.3) — Language Server para componentes Svelte.
12. `typescript-language-server` (v5.3.0) — Servidor de lenguaje oficial para JavaScript/TypeScript.
13. `typescript` (v6.0.3) — Compilador TypeScript de última generación.
14. `vscode-langservers-extracted` (v4.10.0) — Lenguajes web de VSCode (HTML, CSS, JSON, ESLint).
15. `yaml-language-server` (v1.24.0) — Validación y autocompletado de manifiestos YAML y Kubernetes.

### 3.2 Distribuciones de Python (`pip list`) — 102 Librerías Verificadas
El entorno corre Python 3.12.3 y pip 24.0 con las siguientes 102 librerías organizadas por especialidad técnica:

- **Generación y Manipulación Forense de Documentos:**
  `weasyprint` (70.0), `reportlab` (5.0.1), `xhtml2pdf` (0.2.21), `pypdf` (6.19.0), `fpdf` (1.7.2), `fpdf2` (2.8.8), `pdf2image` (1.17.0), `pyHanko` (0.37.0), `pyhanko-certvalidator` (0.32.1), `svglib` (2.2.0), `pydyf` (0.12.1), `pyphen` (0.18.1), `fonttools` (4.66.0).
- **Automatización de Navegador Web y Scraping:**
  `playwright` (1.63.0), `beautifulsoup4` (4.15.0), `lxml` (6.1.3), `html5lib` (1.1), `tinyhtml5` (2.1.0), `cssselect2` (0.10.1), `tinycss2` (1.5.1), `soupsieve` (2.10), `webencodings` (0.6.1).
- **Networking, Clientes HTTP y APIs:**
  `requests` (2.34.2), `urllib3` (2.8.0), `httpx2` (2.13.1), `httpcore2` (2.13.1), `aiohttp` (3.14.3), `aiohappyeyeballs` (2.7.1), `aiosignal` (1.4.0), `yarl` (1.25.1), `multidict` (6.9.1), `frozenlist` (1.8.0), `fastapi` (0.141.1), `uvicorn` (0.54.0), `starlette` (1.7.0), `Flask` (3.1.3), `Werkzeug` (3.1.9).
- **Procesamiento de Datos y Visualización:**
  `numpy` (2.5.3), `pandas` (3.0.6), `matplotlib` (3.11.2), `seaborn` (0.13.2), `plotly` (7.1.0), `openpyxl` (3.1.5), `contourpy` (1.4.0), `cycler` (0.12.1), `kiwisolver` (1.5.1), `narwhals` (2.26.0), `tabulate` (0.10.0).
- **Imágenes y Multimedia:**
  `pillow` (12.3.0), `zopfli` (0.4.3), `arabic-reshaper` (3.0.1), `python-bidi` (0.6.11).
- **Almacenamiento Cloud y S3:**
  `boto3` (1.43.103), `botocore` (1.43.103), `s3transfer` (0.19.2), `git-remote-s3` (0.4.2), `jmespath` (1.1.0).
- **IA y Modelos:**
  `openai` (3.19.2), `jiter` (0.17.0), `pydantic` (2.13.5), `pydantic_core` (2.46.5).
- **Infraestructura y Procesos del Sistema:**
  `supervisor` (4.2.5), `cryptography` (50.0.1), `cffi` (2.1.1), `pycparser` (3.0), `greenlet` (3.5.6), `anyio` (4.15.1), `sniffio` (1.3.1), `tqdm` (4.70.1), `click` (8.5.0), `Jinja2` (3.1.6).

---

## 4. AUDITORÍA DE UX/UI Y ESTRUCTURA DE LA APP ANDROID

### 4.1 Pantalla Principal (`Home`)
- **Tabs Superiores:** Alternador entre `Tareas` (operaciones pesadas de sandbox) y `Agent` (derivación a `Cue`).
- **Carrusel Horizontal Superior:**
  1. `Actualizar`: Gestión de créditos y planes.
  2. `Manus 2.0`: Selector de modelo (`Lite` vs `Pro`).
  3. `Ordenadores`: Conexión remota por WebRTC para control de mouse, teclado y pantalla de PC de escritorio.
  4. `Proyectos`: Contextos compartidos para múltiples tareas.
  5. `Biblioteca`: Recursos estáticos.
  6. `Creaciones`: Navegador de archivos generados por el agente con buscador en tiempo real.
  7. `Habilidades`: Marketplace de Skills.
  8. `Programado`: Tareas cron programadas.
  9. `Favoritos`: Elementos guardados.
- **Composer Inferior:**
  - Selector de modelo `M` / `Lite`.
  - Botón de adjuntos (archivos e imágenes).
  - Caja de texto expandible con soporte multilínea.

### 4.2 Menú de Ajustes y Perfil
- **Créditos:** Monedero en tiempo real (`1,261 créditos` observados).
- **Tareas Programadas:** Pestañas `Ejecuciones` (historial) y `Programado` (cron de agentes).
- **Conocimiento (Knowledge Base / RAG):** Sistema de 3 campos:
  - *Nombre*: Etiqueta del bloque de conocimiento.
  - *Usar cuando \** (Trigger semántico): Regla contextual que dispara la inyección en el prompt.
  - *Contenido \** (Snippet): Información específica que se concatena al contexto.
- **Mail Manus:**
  - Dirección única: `usuario@manus.bot`.
  - Workflow Emails para enrutar correos a tareas autónomas con instrucciones fijas.
  - Lista de remitentes autorizados (whitelist de seguridad).
- **Controles de Datos:**
  - Auditoría de Tareas compartidas y archivadas.
  - Auditoría de Archivos compartidos.
  - Auditoría de **Sitios web desplegados** (web apps estáticas alojadas en subdominios de Manus).
  - Auditoría de **Apps generadas**.
- **Navegador en la Nube:** Switch de *"Mantener el estado de inicio de sesión entre tareas"* para conservar cookies y perfiles autenticados de sitios web.
- **Conectores (MCP):** Configuración de servidores MCP en Modo URL o Modo Formulario (cabeceras `Authorization: Bearer`).
- **Integraciones SaaS:** Telegram, Slack, LINE, Google Workspace (Gmail, Drive, Docs, Sheets, Calendar), Notion, Linear, GitHub, HubSpot.

---

## 5. COMPARATIVA TÉCNICA: SKILLS VS CONECTORES MCP

| Criterio | Habilidad (Skill) | Conector MCP |
|---|---|---|
| **Definición** | Procedimiento, playbook y directivas de prompt estructuradas. | Endpoint HTTP/HTTPS remoto que implementa el protocolo MCP. |
| **Cómputo** | Se evalúa e inyecta en el contexto semántico del LLM. | Se ejecuta en el servidor remoto del usuario o del proveedor. |
| **Autenticación** | Ninguna (cero credenciales técnicas). | Tokens Bearer, claves de API o túneles cifrados. |
| **Descubrimiento** | Por trigger semántico ("Usar cuando"). | Por protocolo estándar `tools/list` con esquemas JSON. |
| **Equivalente en Fénix** | Reglas de cátedra, directivas de estudio y prompts de procesamiento. | Las 97 herramientas expuestas por `fenix-mobile-mcp` (ADB, FS, SO, Supabase, Cloudflare, etc.). |

---

## 6. APRENDIZAJES CLAVE PARA LA ARQUITECTURA DE FÉNIX MOBILE MCP

De esta auditoría profunda se desprenden 5 mejoras estratégicas para **Fénix Mobile MCP**:

1. **Pestaña Servidor de Fénix — Selector de Clientes con Instrucciones:**
   - Incorporar botones directos para los clientes MCP líderes: **Manus**, **Claude Desktop**, **Antigravity IDE**, **Cursor**, **Windsurf**.
   - Al seleccionar Manus, mostrar la guía exacta validada en esta auditoría:
     - Tipo de conexión: Modo Formulario.
     - URL: `https://<tunel>.trycloudflare.com/mcp`.
     - Cabecera: `Authorization: Bearer <token>`.
   - Permitir que los propios agentes editen estas guías en pantalla vía MCP mediante una herramienta dedicada (`mcp_actualizar_guia_cliente`).
2. **Generación de Entregables Forenses (Stack PDF):**
   - El gran acierto de Manus es su stack masivo de librerías PDF (`weasyprint`, `reportlab`, `fpdf2`, `pypdf`, `pdf2image`). Fénix debe emular esta capacidad de generar reportes, transcripciones y cuadros sinópticos compilados en PDF nativo de alta calidad.
3. **Persistencia de Sesiones de Navegación:**
   - Implementar la opción de cookies persistentes en las herramientas de scraping de Fénix (`browser_inteligente` / Playwright) para no perder inicios de sesión en portales académicos universitarios.
4. **Patrón de Conocimiento Semántico (Memory Triggers):**
   - Adaptar la estructura de 3 campos (*Nombre*, *Usar cuando*, *Contenido*) para el sistema de apuntes y reglas de cátedra en Fénix, evitando la sobrecarga ciega del contexto.
5. **Mitigación de Salidas Extensas:**
   - Manus compacta las salidas largas truncándolas o paginándolas automáticamente. Fénix debe implementar paginado y buffers efímeros para herramientas de terminal y logs muy extensos.
