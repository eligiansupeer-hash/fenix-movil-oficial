# REGLAS DE NEGOCIO — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. REGLAS DE ESTADO DEL INPUT DE MENSAJES
- **[RN-01] Activación del Botón Enviar:** El botón de envío debe mantenerse deshabilitado y visualmente en color gris inactivo (`#757575`) mientras la caja de texto contenga 0 caracteres o solo espacios en blanco. Debe activarse y cambiar a amarillo dorado (`#FFD700`) tan pronto exista al menos 1 caracter válido o un archivo adjunto.
- **[RN-02] Disparo del Panel Adicional (`+`):** El botón `+` dorado está reservado para abrir el selector de herramientas y adjuntos. Nunca debe insertar caracteres directos en el campo de texto.
- **[RN-03] Grabación de Audio Excluyente:** Al iniciar la grabación de audio (micrófono verde), la entrada de texto por teclado se suspende temporalmente y se muestra el contador de tiempo en vivo.

## 2. REGLAS DE NAVEGACIÓN Y ACCESO A HERRAMIENTAS
- **[RN-04] Distribución de Pestañas en la Barra Inferior:** La barra inferior (BottomBar) consta de 5 pestañas principales:
  1. `🔥 Fénix IA`: Asistente conversacional principal.
  2. `>_ Terminal`: Shell Linux aislado.
  3. `⚡ F-Code`: Entorno de código y scripts.
  4. `📚 F-Docs`: Explorador de documentación y especificaciones.
  5. `🎨 Mis Creaciones`: Galería e inspección en vivo de interfaces de usuario (UI) personalizadas y componentes generados por el usuario.
- **[RN-05] Ubicación de Soporte / Ayuda y Login en TopAppBar:** El botón de `Ayuda` (`?`) se ubica en el extremo derecho superior, inmediatamente a la izquierda del botón de Login/Avatar. Se prescinde definitivamente del icono de la llavecita en la barra superior (las APIs se gestionan desde el selector de Modelos).
- **[RN-06] Independencia Institucional:** Prohibido incorporar nombres de universidades, cátedras o entidades específicas como valores fijos en el código. Toda cátedra o materia es dato de entrada del usuario.

## 3. REGLAS DE SELECTORES DINÁMICOS Y GESTIÓN DE MODELOS
- **[RN-07] Selectores Dinámicos sobre el Input (`Modelos` y `Modo`):** Se ubican a 8 dp sobre la caja de texto en una barra horizontal compacta. Tienen apariencia de cápsulas translúcidas (chips) y muestran dinámicamente el nombre del modelo y modo activos (o el texto por defecto `Modelos` / `Modo` antes de la primera selección). Al seleccionar un elemento, el chip conmuta su etiqueta al nombre seleccionado.
- **[RN-08] Jerarquía y Semáforo de Modelos:**
  1. **Entornos de Programación CLI:** Listados siempre en orden alfabético estricto (`Aider`, `Antigravity`, `Claude Code`, `Codex CLI`, `Goose`, `OpenCode`, etc.). Si el usuario instala otros, se incorporan automáticamente respetando el orden alfabético. Semáforo de 4 estados:
     - **Verde (`#00E676`):** Instalado y conexión verificada.
     - **Rojo (`#FF1744`):** Instalado pero con falla de conexión.
     - **Azul (`#2979FF`):** Instalado y funcionando, pero con advertencia de cuota o login (`"revise su cuota o login"`).
     - **Gris (`#757575`):** No instalado, con badge interactivo `"Instalar"` que deriva al panel de instalación/desinstalación.
  2. **Modelos LLM Locales (Nativo Edge):** Lectura previa del hardware y memoria RAM disponible del dispositivo (categorizado en 4 GB, 8 GB, 12 GB, 16 GB). Cada modelo detalla su peso en almacenamiento. Semáforo de viabilidad:
     - **Verde:** Totalmente apto para la RAM del dispositivo. Botón de descarga habilitado.
     - **Amarillo (`#FFD600`):** Ejecutable pero con rendimiento reducido o latencia alta.
     - **Rojo:** Inviable para la RAM actual (riesgo de Out Of Memory / Crash del sistema). Botón deshabilitado.
     - Al instalar uno o más LLMs locales, se incorporan de inmediato con su nombre al listado activo.
  3. **Modelos por API de Proveedores:** Cero hardcodeo de modelos. Al conectar una API mediante su URL base y credencial, se realiza una llamada ligera de autodescubrimiento (`/v1/models`) para obtener la lista actualizada de modelos reales del proveedor.
- **[RN-09] Panel de Alta de Proveedores y Bóveda Segura:**
  - Formulario con 3 entradas: Nombre del Proveedor, URL Base y API Key.
  - Botón naranja de acción principal `"Agregar Proveedor"`.
  - Las credenciales nunca se exponen en el chat ni se transmiten en prompts a los agentes de IA. Se custodian en la bóveda cifrada local `secrets`.
- **[RN-10] Asistencia Guiada para Conexión de APIs:**
  - Disparo mediante botón interactivo de `"Ayuda"` con identidad Fénix.
  - La caja dinámica de chat guiado incluye: campo para nombre del proveedor, campo seguro para la clave de API y un botón especial dedicado: `"No sé el proveedor ni tengo API (Guiarme)"`.
  - El botón `"Enviar"` de la caja de ayuda permanece deshabilitado (gris `#757575`) y se activa en color **verde** (`#00E676`) de forma reactiva en dos únicas condiciones: cuando el usuario completa datos válidos o cuando pulsa el botón especial de guía.
- **[RN-11] Relevo Automático y Bienvenida del Nuevo Proveedor:**
  - Cuando un agente (ej. OpenCode o Antigravity) completa con éxito la configuración y prueba de la API, cede el turno automáticamente.
  - El selector principal conmuta de inmediato al nuevo proveedor configurado como modelo activo.
  - La IA del nuevo proveedor emite un primer mensaje de bienvenida cálido y entusiasta con emojis (`"Hola, un gusto estar aquí, soy [Modelo] del proveedor [Nombre]. ¡Gracias por agregarme en Fénix! Por dónde comenzamos? 🔥🚀"`).
- **[RN-12] Modos de Operación y Esfuerzo de Razonamiento:**
  - 5 Modos Axiom: `General`, `Build`, `Plan`, `Deep Research`, `Amigable`.
  - Selector lateral de esfuerzo cognitivo: `Bajo`, `Medio`, `Fuerte`.
  - El modo `Amigable` tiene la misión prioritaria de empatía, calidez y trato cordial.
  - La selección de modo y esfuerzo modula las directivas de sistema del motor Axiom y la selección inteligente del modelo.

## 4. REGLAS DE RESILIENCIA Y FAILOVER EN MODELOS Y PROVEEDORES
- **[RN-13] Acordeón Desplegable de Modelos por Proveedor:** En el selector de modelos, al presionar un proveedor que se encuentra conectado (semáforo verde), se despliega un submenú acordeón con la lista de modelos arrojados dinámicamente por la API. Cada modelo muestra su denominación técnica y un badge de estado.
- **[RN-14] Gestión de Falla Indiviual ("El modelo no responde"):** Si el modelo activo seleccionado no emite respuesta durante la inferencia o supera el timeout de espera, se dispara de inmediato un cartel en pantalla con dos acciones:
  - **`Selección automática`:** La aplicación activa el módulo interno de `proxy pool` para verificar la disponibilidad real de los demás modelos del proveedor y asigna automáticamente el primero operativo sin bloquear al usuario.
  - **`Selección manual`:** Despliega de inmediato la lista de modelos de ese proveedor para que el usuario escoja manualmente el reemplazo.
- **[RN-15] Protocolo de Falla Crítica ("Ningún modelo del proveedor responde"):** Cuando el proxy pool detecta que la totalidad de los modelos de un proveedor se encuentran inoperativos (cuota de cuenta agotada o caída total del servicio), se despliega un banner de emergencia con 3 opciones:
  - **`Agregar API key secundaria`:** Abre el panel directo para registrar una clave de respaldo para failover.
  - **`Agregar API key nueva`:** Abre el panel para sustituir la clave actual del proveedor.
  - **`Proveedor automático`:** Conmuta de forma inmediata al siguiente proveedor o entorno CLI con mayor solvencia y disponibilidad en el sistema.

## 5. REGLAS DE ACCESIBILIDAD DIFERIDA Y CONSTRUCCIÓN AUTÓNOMA
- **[RN-16] Política de No Intrusión y Blindaje Play Protect:** La aplicación base no solicita ni declara permisos de accesibilidad de manera invasiva en su flujo regular, evitando alertas de Google Play Protect y garantizando una instalación limpia. La accesibilidad es una decisión 100% voluntaria y soberana del usuario.
- **[RN-17] Exención de Responsabilidad y Checkpoint de Consentimiento:**
  - En el panel de permisos existe la ficha `"Conceder permisos de accesibilidad"`.
  - Al presionarla, se despliega un panel con pros y contras técnicos, advertencia de riesgos y cláusula legal de exención de responsabilidad hacia los creadores del sistema.
  - Requiere marcar un checkbox obligatorio: `"Acepto los términos y asumo la total responsabilidad de la activación"`.
  - El botón `"Permitir"` permanece apagado e inactivo mientras el checkbox no esté tildado (el botón `"Denegar"` permanece siempre habilitado).
- **[RN-18] Despacho Autónomo al Sandbox y Estado de Construcción:**
  - Al pulsar `"Permitir"`, se cierra el panel modal y el consentimiento queda registrado.
  - Se despacha de forma desatendida a OpenCode en el sandbox un prompt estructurado con los comandos e instrucciones necesarias para orquestar la accesibilidad en el dispositivo.
  - En la interfaz principal, el botón transmuta de inmediato al estado `"OpenCode construyendo..."` con feedback de carga.
- **[RN-19] Cascada de Resiliencia entre Agentes y Cooldown de 5 Minutos:**
  - Si OpenCode sufre falta de cuota o falla de conexión, el trabajo rota automáticamente a **Antigravity**.
  - Si Antigravity no está disponible, rota al siguiente entorno CLI instalado en el sandbox (Claude Code, Codex CLI, Aider).
  - Si ningún agente está disponible, el botón conmuta al mensaje: `"Ningún agente se encuentra disponible en este momento, vuelva a intentar más tarde"`.
  - Pasados 5 minutos, el botón pasa a `"Conectar"`.
  - Al presionar `"Conectar"` post-cooldown, el sistema reintenta la construcción directamente con los agentes sin solicitar nuevamente el consentimiento legal ya otorgado, volviendo al estado `"OpenCode construyendo..."`.
- **[RN-20] Validación Rigurosa Obligatoria por el CLI Constructor:**
  El prompt despachado exige al agente constructor (OpenCode / Antigravity / CLI) la ejecución de pruebas de verificación automatizadas (smoke test, comprobación de socket y chequeo de integridad). El agente no puede dar por finalizada la tarea sin validar que todo el mecanismo funcione correctamente.
- **[RN-21] Dinamismo de Identidad en el Botón de Progreso:**
  El botón refleja de forma reactiva la identidad del CLI asignado en tiempo real (`"[Nombre_Agente] construyendo..."`, ej. `"OpenCode construyendo..."`, `"Antigravity construyendo..."`).
- **[RN-22] Transición a Verde, Intent Directo y Notificación Local:**
  - Tras superar las pruebas de validación, el botón conmuta a **color verde brillante (`#00E676`)** con el texto: `"Ya puedes conceder los permisos de accesibilidad"`.
  - El sistema dispara de forma autónoma una notificación nativa de Android (apoyada en el permiso estándar `POST_NOTIFICATIONS`) informando al usuario que la extensión fue creada con éxito.
  - Al pulsar tanto el botón verde como la notificación, se lanza el Intent `Settings.ACTION_ACCESSIBILITY_SETTINGS` que deposita al usuario exactamente en la pantalla de ajustes de accesibilidad de Android para conceder el permiso sin navegación manual.
- **[RN-23] Política Zero-Bloat en el Sandbox Linux:**
  - La instalación del rootfs de Debian 12 Bookworm es bajo demanda. La imagen base contiene únicamente coreutils, bash, glibc y apt (~35 MB comprimido). No se preinstalan librerías pesadas hasta que el usuario o un script lo solicite explícitamente.
- **[RN-24] Autonomía de Agentes CLI con Inyección Ktor MCP:**
  - Todo agente CLI iniciado en la sandbox (OpenCode, Antigravity, Claude Code) recibe en su inicialización la configuración automática del servidor MCP local (`http://127.0.0.1:8080/mcp`) con el token local, permitiéndole operar de forma simbiótica con el almacenamiento y hardware del móvil.
- **[RN-25] Gestión de Persistencia y Descarga Reanudable:**
  - Los datos de usuario, paquetes instalados y repositorios se almacenan en `noBackupFilesDir`. La descarga del rootfs es transaccional y reanudable por rangos HTTP (`Range: bytes=...`), validada con hash SHA-256 antes de la extracción.
- **[RN-26] Proactividad Académica y Notificaciones en Segundo Plano:**
  - El subsistema de Tareas Programadas registra las fechas de examen. El agente educativo puede programar crons nativos de Android (AlarmManager/WorkManager) a costo 0 para emitir recordatorios de Active Recall espaciados con la app cerrada, sujeto a la configuración de intensidad elegida por el usuario.
- **[RN-27] Pool Router Multi-Key por Proveedor (Conmutación Invisible):**
  - Cada proveedor de IA (ej. Gemini, Groq, Cerebras, Mistral, OpenAI) puede tener registradas `N` API keys.
  - El Pool Router mantiene un circuito de balanceo y failover: ante una respuesta `429 (Rate Limit)`, `402/403 (Quota Exceeded)` o `5xx (Service Unavailable)`, el router marca temporalmente la clave afectada en cooldown (según cabecera `Retry-After` o backoff exponencial) y reenvía la petición a la siguiente clave del pool sin alertar al usuario ni romper la conversación.
- **[RN-28] Sesiones Dedicadas de Terminal por CLI:**
  - Cada CLI instalado en la sandbox (`opencode`, `agy`, `claude`, etc.) dispone de un descriptor de sesión PTY independiente.
  - Al hacer click en el chip del CLI, el sistema ejecuta su script de arranque (`entrypoint.sh`) que prepara las variables de entorno, autenticación y modo headless, entregando al usuario la interfaz interactiva directa de ese CLI.
- **[RN-29] Flujo de Re-Autenticación OAuth en Navegador:**
  - Si el usuario requiere cambiar de cuenta o reautenticarse en Google Antigravity o cualquier CLI, la acción dispara el comando de login del CLI y abre automáticamente el navegador web predeterminado de Android (`Intent.ACTION_VIEW` con la URL de autorización). Al completar el login, el callback redirige de vuelta a Fénix Móvil.
- **[RN-30] Disponibilidad Universal sin Fuga de Secretos:**
  - La bóveda de credenciales (`AndroidKeyStore`) expone un proveedor interno seguro para el sistema y los agentes activos. Ningún prompt enviado a LLMs contiene las API keys en claro. Los conectores inyectan los headers de autorización a nivel de red (HTTP/gRPC) en la capa de transporte local.
- **[RN-31] Interacción Táctil F-Z (Llamada al Tutor por Anclas Cognitivas):**
  - En la Sección A, todo término en negrita generado por la IA es un enlace activo (`ClickableSpan`). Al ser presionado por el estudiante, invoca de inmediato al tutor pedagógico con el concepto anclado en su contexto para desglose, analogías o autoevaluación.
- **[RN-32] Prioridad de Modelos y Nivel Máximo de Razonamiento:**
  - Para tareas de procesamiento conceptual denso, auditoría de cátedra y generación de materiales, el sistema prioriza en orden: **Claude 3.5/3.7 Sonnet**, **Google Antigravity (`agy`)**, y **OpenCode** (DeepSeek R1 / o1 / o3-mini), configurados siempre en su **nivel de razonamiento más alto (extended thinking)**.
- **[RN-33] Corpus Académico como Material de Consumo Global:**
  - Todo documento o apunte procesado se vectoriza e indexa automáticamente en la base local (`sqlite-vec`). Queda disponible de inmediato como material de consumo y consulta contextual para cualquier sala de chat de la aplicación.
- **[RN-34] Límites Éticos Inviolables de la IA (No Psicología, No Medicina Personal):**
  - Los agentes tienen terminantemente prohibido asumir roles de psicólogo o médico personal. Ante angustia o peligro, derivan con empatía a ayuda profesional humana. En carreras de medicina, el conocimiento médico es estrictamente académico-curricular y nunca aplicado al usuario.
- **[RN-35] Gobernanza y Soberanía en Fénix Studio:**
  - Todo artefacto generado en Fénix Studio (diapositivas, infografías vectoriales, video-lecciones) debe mantener trazabilidad rigurosa contra el corpus de la cátedra (`[FUENTE]`, `[INFERENCIA]`). Queda prohibida la generación de diapositivas o videos con contenido ficticio no respaldado en el material del usuario.
- **[RN-36] Eficiencia de Renderizado Multimedia y Costo $0 en Dispositivo:**
  - El renderizado de video-lecciones se ejecuta localmente mediante encoders de hardware de Android (`MediaCodec`) o binarios `FFmpeg` compilados para arm64 en la sandbox Linux. Se priorizan secuencias de diapositivas dinámicas con síntesis de audio para no superar los 50 MB de almacenamiento por lección y mantener costo $0 absoluto.
- **[RN-37] Aislamiento Estricto del Árbol Jerárquico de 4 Niveles:**
  - La indexación en `sqlite-vec` y Room particiona estrictamente el almacenamiento con filtros de dominio: `carrera_id > anio_id > catedra_id > unidad_id`. Queda estrictamente prohibido mezclar documentos de distintas unidades en la misma ventana de contexto del LLM para evitar alucinaciones por mezcla de temas.
- **[RN-38] Reproducción Acústica Soberana y Cero-Consumo:**
  - Los 10 paisajes sonoros para concentración se ejecutan mediante loops OGG optimizados (< 500 KB por pista) o generadores de ruido blanco matemáticos en C/Kotlin vía `AudioTrack`. No dependen de conexión a Internet, APIs externas de pago ni streaming de terceros.
- **[RN-39] Conmutabilidad de Formatos Visuales en Fénix Studio:**
  - El estudiante tiene la soberanía de solicitar la visualización de cualquier parte del Protocolo Fénix (A-F) en el formato que prefiera (tarjetas flash, tabla comparativa, diapositiva 16:9, mapa conceptual o diálogo socrático), conservando siempre la etiqueta de trazabilidad (`[FUENTE]` o `[INFERENCIA]`).
- **[RN-40] Gobernanza y Soberanía de Conectores Cloud a Costo $0:**
  - Solo se admiten conectores con niveles de uso gratuitos permanentes o cuotas sin cargo para el creador y usuario (GitHub Free, Gmail SMTP propio, Cloudflare Workers/R2 Free Tier, Supabase Free Tier, Telegram Bot API y servidores MCP de acceso libre). Cada conector cuenta con un interruptor manual que revoca el acceso de los agentes de forma inmediata al estar apagado.
- **[RN-41] Protocolo Anti-Filtración de Credenciales en Chat:**
  - Cuando un agente IA asiste en la configuración de un conector o la creación de una skill, tiene prohibido solicitar al usuario que pegue claves o tokens directamente en la caja de texto del chat. En su lugar, el sistema despliega un componente nativo de entrada enmascarada (`SecureCredentialInput`) que almacena el secreto directamente en Android KeyStore (`EncryptedSharedPreferences`), transfiriendo únicamente el identificador opaco al agente.
- **[RN-42] Aislamiento Epistémico Estricto en F-Docs (Document-First Context):**
  - Al activar el Maestro Socrático en F-Docs mediante el botón deslizable a media pantalla (Split Sheet), la ventana de contexto de la IA se inicializa exclusivamente con el documento abierto en el editor. Prohibido inyectar materiales de otras unidades o materias en el prompt salvo instrucción explícita del usuario (`🌐 Todo el Repo`).
- **[RN-43] Política de Documentación Viva y Ayuda Enriquecida:**
  - Las fichas del Centro de Ayuda deben contener descripciones técnicas rigurosas (mínimo 5 renglones y más de 300 palabras normativas de especificación) y enlaces verificables a la documentación oficial de los proyectos de software libre y estándares involucrados.
- **[RN-44] Publicación Comunitaria con Autenticación Verificada:**
  - Todo usuario que desee publicar extensiones, instant apps o skills en el catálogo comunitario debe autenticarse previamente contra la base de datos de usuarios autorizados. Se exige la consignación obligatoria de nombre real, correo electrónico verificado, categoría taxonómica, matriz de permisos explícitos y descripción exhaustiva de más de 5 renglones y 500 palabras.
- **[RN-45] Protección Criptográfica de Marca Oficial ("Fénix Oficial"):**
  - Queda prohibido a los usuarios de la comunidad utilizar la denominación "Fénix", "Fénix Oficial" o prefijos asociados en el nombre de sus creaciones. Dicha etiqueta está reservada en exclusiva para los módulos centrales verificados mediante firma criptográfica de clave pública del proyecto.
- **[RN-46] Auditoría Preventiva Obligatoria por "Criticón":**
  - Ningún paquete puede ser listado en la comunidad sin haber superado el escaneo estático y dinámico del módulo de auditoría "Criticón". Si el paquete solicita permisos abusivos (como acceso promiscuo a red, sockets en bruto o acceso sin restricciones al almacenamiento anfitrión), se proyectará de forma inmediata con una tarjeta de alerta perimétrica en rojo carmesí (`#FF1744`), bloqueando su ejecución en el móvil.
- **[RN-47] Población Mínima de Catálogo (Semillero No Vacío):**
  - Cada una de las 10 categorías taxonómicas del marketplace debe contener al menos 5 elementos iniciales verificados y funcionales para garantizar que el usuario nunca enfrente una interfaz desierta al iniciar la aplicación.




