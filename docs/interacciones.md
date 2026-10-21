# MATRIZ DE INTERACCIONES Y PROTOTIPADO — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. MATRIZ DE EVENTOS Y TRANSICIONES

| Elemento Origen | Evento (Trigger) | Tablero Destino | Animación | Efecto de Usuario |
| :--- | :--- | :--- | :--- | :--- |
| **`Input_Btn_Mic`** (Tablero 1) | `click` / tap | Tablero 2 (`Grabando Audio`) | `dissolve` (200 ms) | El mic pasa de azul a verde y empieza a grabar. |
| **`Input_Btn_Mic_Active`** (Tablero 2) | `click` / tap | Tablero 1 (`Reposo`) | `dissolve` (200 ms) | Detiene la grabación y vuelve al reposo. |
| **`Input_Pill` / Placeholder** (Tablero 1) | `click` / tap | Tablero 3 (`Texto Escrito`) | `dissolve` (200 ms) | Desaparece el placeholder y el botón enviar se vuelve amarillo. |
| **`Input_Btn_Send_Active`** (Tablero 3) | `click` / tap | Tablero 1 o 5 (`Thinking`) | `dissolve` (200 ms) | Despacha el mensaje y pasa a procesar la respuesta. |
| **`Input_Btn_Plus`** (Tablero 1) | `click` / tap | Tablero 5 (`Panel +`) | `dissolve` (250 ms) | Despliega modal inferior con 4 acciones rápidas. |
| **`Modal_Backdrop`** (Tablero 5) | `click` / tap | Tablero 1 (`Home`) | `dissolve` (250 ms) | Cierra el modal y restablece el foco en la conversación. |
| **`Chip_Selector_Modelos`** | `click` / tap | Tablero 6 (`Selector Modelos`) | `slide-up` (200 ms) | Despliega panel categorizado con CLI, LLMs locales y APIs. |
| **`Btn_Agregar_Proveedor`** (Tablero 6) | `click` / tap | Tablero 7 (`Agregar Proveedor`) | `slide-left` (200 ms) | Abre formulario de conexión y asistente guiado. |
| **`Chip_Selector_Modo`** | `click` / tap | Tablero 8 (`Modo & Razonamiento`) | `slide-up` (200 ms) | Abre configuración de los 5 modos Axiom y nivel de esfuerzo. |
| **`Btn_Asistencia_No_Se`** (Tablero 7) | `click` / tap | Asistente Guiado Activo | Inmediato | Fija intención de búsqueda y conmuta botón Enviar a verde brillante. |
| **`Card_Proveedor_Conectado`** (Tablero 6) | `click` / tap | Acordeón de Modelos | `accordion-expand` (150 ms) | Despliega u oculta la lista de modelos autodescubiertos del proveedor. |
| **`Btn_Falla_Auto_Proxy`** | `click` / tap | Proxy Pool Dispatch | Inmediato | Escanea y conecta con el primer modelo disponible del proveedor. |
| **`Btn_Falla_Manual`** | `click` / tap | Tablero 6 (`Selector Modelos`) | `slide-up` (200 ms) | Permite seleccionar manualmente otro modelo del proveedor. |
| **`Btn_Banner_Api_Secundaria`** | `click` / tap | Tablero 7 (`Agregar API`) | `slide-left` (200 ms) | Abre formulario enfocado en registrar clave de respaldo para failover. |
| **`Card_Permiso_Accesibilidad`** | `click` / tap | Modal Exención Legal Accesibilidad | `slide-up` (200 ms) | Despliega panel de pros/contras, exención legal y checkbox. |
| **`Checkbox_Acepto_Riesgo`** | `change` / tap | Botón Permitir Accesibilidad | Inmediato | Conmuta botón Permitir de deshabilitado (gris) a activo (verde). |
| **`Btn_Permitir_Accesibilidad`** | `click` / tap | Panel Principal Permisos | `slide-down` (200 ms) | Cierra modal, envía instrucciones a OpenCode y transmuta botón a 'OpenCode construyendo...'. |
| **`Tab_Bottom_Terminal`** | `click` / tap | Tablero 23 (`>_ Terminal`) | `fade-through` (150 ms) | Abre la Consola Linux interactiva con sesión PTY activa y teclado virtual con barra auxiliar. |
| **`Btn_Key_ANSI_Ctrl`** | `click` / tap | Modificador de Terminal | Inmediato | Fija el estado de tecla Ctrl activa para el siguiente caracter enviado al socket PTY. |
| **`Btn_Key_ANSI_C`** (con Ctrl) | `click` / tap | Señal SIGINT | Inmediato | Envía `0x03` (`SIGINT`) interrumpiendo el proceso o agente en ejecución en la consola. |
| **`Btn_Quick_Action_CLI`** | `click` / tap | Ejecución de Script | Inmediato | Envía comando predefinido (`opencode`, `agy`, `top`, `clear`) al buffer de entrada de la consola. |
| **`Span_Negrita_F-Z_Seccion_A`** | `click` / tap | Apertura de Tutor Socrático | `slide-up` (200 ms) | Al presionar cualquier término en negrita estratégica, el tutor de IA se despliega con el foco puesto en desglosar, interrogar o clarificar ese concepto específico. |
| **`Tab_Switch_FDocs_Studio`** | `click` / tap | Alternancia Editor ↔ Studio | `crossfade` (180 ms) | Conmuta la vista de `F-Docs` entre el *Editor de Texto Asistido* (documento paginado) y *Fénix Studio* (creación de diapositivas, infografías y lecciones multimedia). |

---

## 2. BUCLE PERIMETRAL DE FUEGO FUTURISTA
- **Comportamiento Autónomo:** Conexión cíclica continua entre fases cromáticas (Magma -> Fuego Solar -> Pico Térmico -> Plasma Carmesí) con trigger `after-delay` (400 ms) y transición `dissolve` (250 ms, `easing: ease`).

