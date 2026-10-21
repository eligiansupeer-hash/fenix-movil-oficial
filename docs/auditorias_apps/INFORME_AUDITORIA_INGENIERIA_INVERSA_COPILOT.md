# INFORME DE AUDITORÍA TÉCNICA E INGENIERÍA INVERSA: MICROSOFT COPILOT ANDROID

**Fecha de Auditoría:** 17 de octubre de 2026  
**Dispositivo:** Motorola Moto G77 (Motorola penangf / `ZY22MK7B2P`), Android 14 (API 34)  
**Aplicación:** Microsoft Copilot (`com.microsoft.copilot`)  
**Versión de App:** `30.0.440926001` (Build `440926001`, minSdk 26, targetSdk 35)  
**Arquitectura Binaria:** ARM64-v8a  
**Directorio de Auditoría en Dispositivo:** `/sdcard/fenix_auditorias/05_copilot/`

---

## 1. RESUMEN EJECUTIVO & PERFIL DE LA APLICACIÓN

Microsoft Copilot para Android presenta una arquitectura de ingeniería sumamente rica e híbrida, integrando componentes nativos multiplataforma de vanguardia:
- Un motor JavaScript V8 completo (`libv8android.so` de 18.6 MB).
- Motores de renderizado 3D y gráficos vectoriales: Google Filament PBR (`libfilament-jni.so`, `libgltfio-jni.so`) y Babylon.js Native (`libBabylonNativeJNI.so` de 6.75 MB).
- Motor cartográfico vectorial MapLibre (`libmaplibre.so` de 13.1 MB) para búsquedas geoespaciales y localización de puntos de interés.
- Pila de autenticación empresarial y personal unificada OneAuth de Microsoft (`libOneAuth.so` de 5.12 MB) y Microsoft Authentication & Entra SDK (`libmaesdk.so` de 2.02 MB).
- Motor de maquetación Flexbox Yoga (`libyoga.so`).

---

## 2. INGENIERÍA INVERSA ESTÁTICA DEL PAQUETE (APK & LIBRERÍAS NATIVAS)

### 2.1. Librerías Nativas en `split_config.arm64_v8a.apk`
* **`libv8android.so` (18.6 MB):**
  - Motor V8 JIT/JS de Google compilado para Android ARM64. Provee ejecución de JavaScript de alto rendimiento en el cliente para componentes interactivos y visualizaciones dinámicas.
* **`libmaplibre.so` (13.1 MB):**
  - Renderizador de mapas vectoriales por hardware (OpenGL ES / Vulkan). Utilizado para renderizar mapas interactivos Bing Maps directamente en las respuestas del chat.
* **`libBabylonNativeJNI.so` (6.75 MB) & `libfilament-jni.so` (2.26 MB) & `libgltfio-jni.so` (4.68 MB):**
  - Pipeline de visualización 3D y renderizado basado en física (PBR). Permite desplegar modelos tridimensionales interactivos en formato GLTF en el visor móvil.
* **`libOneAuth.so` (5.12 MB) & `libmaesdk.so` (2.02 MB):**
  - SDK de identidad y gestión de tokens OAuth2 / OIDC de Microsoft, con soporte para cuentas personales (MSA), cuentas educativas y corporativas (Azure Active Directory / Entra ID).
* **`libsigner.so` (1.3 MB):**
  - Módulo nativo de firma criptográfica para sellar solicitudes hacia los gateways de Bing y Azure AI.

---

## 3. AUDITORÍA DE INTERFAZ, NAVEGACIÓN Y CONECTORES

### 3.1. Modos de Operación (Selector en Cabecera)
Copilot expone cuatro modos diferenciados de procesamiento:
1. **Inteligente (Smart Mode):** Orquestación dinámica que evalúa la complejidad del prompt; utiliza modelos rápidos de menor latencia para respuestas simples y escala automáticamente a modelos de mayor capacidad ante problemas elaborados.
2. **Razonamiento profundo:** Enfocado en tareas analíticas, programación, matemáticas y deducción lógica de múltiples pasos (modelos de razonamiento de OpenAI).
3. **Estudiar y aprender:** Modo tutor adaptativo enfocado en pedagogía, diseño de cuestionarios y explicaciones socráticas guiadas.
4. **Buscar:** Grounding estricto con el índice web de Bing Search, priorizando referencias citadas y noticias en tiempo real.

### 3.2. Ecosistema de Conectores Oficiales de Copilot
En la sección de ajustes (`Configuración > Conectores`), Copilot expone 6 conectores oficiales listos para enlazar:
1. **Gmail:** Búsqueda, lectura y análisis de correos en la bandeja de entrada.
2. **Google Calendar:** Lectura y consulta de eventos y cronogramas.
3. **Google Contacts:** Búsqueda de libreta de contactos.
4. **Google Drive:** Indexación y búsqueda de documentos en la nube de Google.
5. **Microsoft OneDrive:** Lectura y análisis de archivos en el almacenamiento de Microsoft.
6. **Microsoft Outlook:** Gestión integrada de correos e invitaciones de calendario.

---

## 4. INTERROGATORIO TÉCNICO Y AUDITORÍA DE ARQUITECTURA

### 4.1. Conectividad MCP (Model Context Protocol) vs Microsoft Graph
* **Respuesta Oficial del Sistema:**
  - El protocolo **Model Context Protocol (MCP)** **no está soportado directamente a nivel de cliente** en la aplicación móvil de Copilot.
  - La extensibilidad hacia herramientas y datos externos se canaliza formalmente a través de **Copilot Studio** y conectores estándar basados en **Microsoft Graph API** y especificaciones **OpenAPI REST** seguras y autenticadas por OAuth.
  - Para conectar el ecosistema MCP de Fénix con Copilot, la arquitectura requerida es un **puente OpenAPI-to-MCP** expuesto como un conector personalizado o API Plugin en Copilot Studio.

### 4.2. Sandbox de Backend y Ejecución de Código
* Los scripts y cálculos (Python) se ejecutan en microentornos aislados de Azure (sandboxes seguros y efímeros).
* Los sandboxes no exponen rutas internas de backend ni acceso a la red host.
* Salida y exportación multiformato directa: Copilot permite la generación y descarga nativa de archivos **CSV, JSON, PDF, DOCX, PPTX, TXT y XLSX**.

### 4.3. Cuotas y Límites de Almacenamiento
* **Chat Temporal:** Sesiones efímeras sin guardado en memoria ni historial; retención máxima de seguridad de 30 días.
* **Proyectos y Biblioteca:** Habilitados en modo de chat regular. En entornos Microsoft 365, el almacenamiento se apoya sobre la cuota de 1 TB por usuario de OneDrive/SharePoint.

---

## 5. COMPARATIVA Y VALOR PARA EL ECOSISTEMA FÉNIX

1. **Exportación Multiformato:** La capacidad de Copilot de generar directamente documentos Office (`.docx`, `.xlsx`, `.pptx`) además de PDFs y CSVs es una referencia clave para los exportadores académicos de Fénix.
2. **Estrategia Híbrida de Renderizado:** La inclusión de MapLibre (13.1 MB) y Babylon Native (6.75 MB) demuestra que un asistente moderno puede beneficiarse de renderizado nativo local para datos espaciales y 3D interactivos.
3. **Selector de Intención Pedagógica:** El modo "Estudiar y aprender" valida el enfoque de Fénix de contar con un perfil socrático/didáctico para los estudiantes universitarios.

---

## 6. REGISTRO DE EVIDENCIA EN DISPOSITIVO
Archivado en `/sdcard/fenix_auditorias/05_copilot/`:
- `copilot_home.png`: Pantalla inicial de bienvenida.
- `copilot_modes_dropdown.png`: Desplegable con los 4 modos (Inteligente, Razonamiento profundo, Estudiar y aprender, Buscar).
- `copilot_drawer.png`: Menú lateral con Proyectos, Biblioteca y Chat temporal.
- `copilot_settings_screen.png`: Menú de configuración y perfil.
- `copilot_connectors_screen.png` & `copilot_connectors_scrolled.png`: Pantalla de conectores (Gmail, Drive, Calendar, OneDrive, Outlook).
- `copilot_response1.png` a `copilot_response_top3.png`: Batería completa de respuestas técnicas de auditoría.
