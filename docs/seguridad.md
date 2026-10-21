# POLÍTICAS DE SEGURIDAD Y PRIVACIDAD — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. POLÍTICAS DE PRIVACIDAD ESTRICTA
- **[SEG-01] Prohibición de Captura de Pantalla No Autorizada:** Prohibido realizar `adb screencap` o capturas silenciosas de la pantalla del móvil sin autorización explícita del usuario.
- **[SEG-02] Almacenamiento Seguro de Credenciales:** Claves de API (tokens de Penpot, Cloudflare, GitHub, Gemini) residen en `~/.fenix/secrets/api_keys.json` con permisos de lectura restringidos (`chmod 600`), nunca commiteadas en repositorios públicos.
- **[SEG-03] Aislamiento Sandbox Linux:** La terminal y herramientas de compilación operan en contenedor aislado (chroot/proot) para impedir el acceso no autorizado a los datos privados del sistema Android anfitrión.
- **[SEG-04] Aislamiento de Credenciales en Asistencia Guiada:** Al asistir al usuario mediante agentes (OpenCode, Antigravity, etc.) para conectar proveedores, el prompt despachado NUNCA transporta la API Key. El agente investiga parámetros públicos de conexión (URLs, headers, modelos compatibles) y el sistema inyecta la credencial directamente en la bóveda segura del cliente sin pasar por el LLM.
- **[SEG-05] Gobernanza y Exención Legal en Permisos de Accesibilidad:** La aplicación base no fuerza accesibilidad para evitar vetos de Google Play Protect. La activación se delega al sandbox bajo aceptación legal explícita del usuario mediante checkbox y exención de responsabilidad firmada digitalmente en el datastore local.
- **[SEG-06] Mitigación $W \wedge X$ y Ejecución Legal en Android 15/16:** Prohibido el uso de hacks basados en targetSdk <= 28 para evadir W^X. La ejecución de binarios Linux en la sandbox se realiza exclusivamente a través de `libproot.so` empaquetado como biblioteca compartida nativa en `jniLibs` (`applicationInfo.nativeLibraryDir`), garantizando el cumplimiento irrestricto de las reglas SELinux de Android sin generar alertas ni bloqueos por parte de Google Play Protect.
- **[SEG-07] Aislamiento de Memoria Pedagógica y Cuarentena Epistémica:** Los proyectos de estudio y chats de F-Docs operan en sandboxes aisladas sin intercambio de contexto conversacional entre sí, previniendo alucinaciones cruzadas o filtración de notas privadas entre materias. La única entidad compartida globalmente es el bus de eventos de Tareas Programadas / Exámenes.
- **[SEG-08] Frontera Ética y de Seguridad Humana (Prohibición Psicológica y Médica Personal):**
  - La IA no actúa como psicóloga ni provee consejos terapéuticos/psicológicos. Ante señales de riesgo o angustia, deriva amablemente a ayuda profesional humana.
  - La IA no actúa como médico ni emite diagnósticos personales. En cátedras de salud, el conocimiento médico es estrictamente teórico/curricular sin aplicación clínica a la persona del usuario.
- **[SEG-09] Bóveda de Contraseñas de Aplicación y Secretos de Conectores:**
  - Las contraseñas de aplicación de Gmail (16 caracteres) y tokens de servicios externos (GitHub PAT, Cloudflare, Supabase, Telegram Bot Token) se almacenan cifradas en Android KeyStore con AES-256-GCM. Los agentes CLI o enjambres MCP nunca reciben las credenciales en texto plano; las peticiones SMTP o llamadas a API se firman mediante adaptadores nativos en el puente local Ktor sin exponer las claves en prompts, logs o memoria compartida.
- **[SEG-10] Aislamiento de Extensiones Comunitarias y Auditoría Criticón:**
  - Toda Instant App o extensión comunitaria se ejecuta bajo un contenedor aislado (sandbox PRoot o WebView restringido) con permisos mínimos previamente auditados por "Criticón". Las creaciones que intenten vulnerar la frontera de la sandbox o exfiltrar datos son vetadas y marcadas con tarjeta roja de alerta perimétrica, bloqueando cualquier ejecución local.



