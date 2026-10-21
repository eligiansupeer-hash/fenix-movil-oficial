# DOCUMENTO TEMPORAL DE ARQUITECTURA — F-DOCS & PROTOCOLO FÉNIX MÓVIL
> **Estado:** Documento Vivo Temporal / Fase de Espera de Deep Research  
> **Fecha de Creación:** 2026-10-21T02:42:00-03:00  
> **Estándar:** ISO/IEC/IEEE 29148 — Gestión de Requisitos y Arquitectura Limpia  

---

## 1. OBJETIVO DEL DOCUMENTO
Preservar y estructurar las directivas arquitectónicas para la integración del **Protocolo Maestro Fénix v2.1** dentro de la pestaña `📚 F-Docs` de Fénix Móvil, en anticipación al informe de investigación autónoma de Gemini Deep Research (~30 minutos).

---

## 2. DIRECTIVAS ARQUITECTÓNICAS CONFIRMADAS POR EL USUARIO

### 2.1 Coexistencia Dual: Renderizado Completo de Documentos + Agente Dinámico
- **Visualizador / Editor Nativo Completo:**  
  F-Docs **sí renderiza y edita los documentos en su totalidad** (A4 paginado para DOCX/PDF, celdas interactivas para XLSM, markdown enriquecido). La interfaz cuenta con el lienzo completo del documento y las 20 herramientas de edición.
- **Agente Especializado con Herramientas Dinámicas:**  
  Coexiste con el documento. El agente dispone de un set de **herramientas MCP / plugins especializados** que le permiten:
  - Levantar paneles laterales e inferiores en tiempo real.
  - Inyectar tarjetas interactivas y dinámicas en el chat (Generative UI).
  - Modificar partes específicas del documento activo respetando el Protocolo Fénix v2.1.

---

### 2.2 Proactividad Adaptativa ("Modo Examen Implacable") & Crons Locales
- **Intensidad Dinámica según Fecha de Examen:**  
  Conforme se acerca la fecha límite de una cátedra o examen, el agente incrementa automáticamente su nivel de insistencia ("rompe bolas" pedagógico):
  - Recordatorios de Active Recall espaciados (repetición espaciada).
  - Desafíos espontáneos de preguntas de examen (Parte D).
  - Revisión de conceptos clave del glosario (Parte F).
- **Ejecución en Background (App Cerrada):**  
  El agente programa **Crons locales / AlarmManager / WorkManager de Android** para disparar notificaciones en el móvil incluso cuando la aplicación está completamente cerrada o en segundo plano (costo $0, sin servidores push de pago).
- **Interrupciones In-App en Cualquier Sala:**  
  Si la fecha es inminente (ej. faltan 48 o 24 horas), el agente puede irrumpir mediante banners sutiles o alertas flotantes en cualquier pantalla de la app (Home, F-Code, Terminal) recordando los temas pendientes.

---

### 2.3 Soberanía y Control del Usuario (Panel de Ajustes de Notificaciones y Asistencia)
- **Configuración Granular de Permisos:**  
  En los ajustes de la app (o dentro del panel del proyecto), el usuario tiene el control absoluto para:
  - Activar / Desactivar notificaciones en segundo plano.
  - Regular la intensidad de insistencia:
    - `Modo Silencioso`: Solo ayuda si el usuario le pregunta en el chat.
    - `Modo Moderado`: Recordatorios 1 vez al día.
    - `Modo Examen Implacable`: Notificaciones frecuentes, repasos activos y alertas in-app.
  - Definir horarios de estudio permitidos (evitando interrupciones en horarios de descanso).

---

### 2.4 Aislamiento Estricto de Contexto y Memoria (Sanitización Sandbox)
- **Regla de No Contaminación Epistémica:**  
  Cada chat y asistente pedagógico opera dentro de un **proyecto o entorno aislado** (Workspace Sandbox).
- **Prohibición de Memoria Cruzada:**  
  La memoria conversacional, apuntes cargados y textos de un proyecto académico específico **NO se mezclan ni comparten** con los agentes generales del sistema ni con otros proyectos.
- **Beneficios:**
  - Evita alucinaciones cruzadas entre distintas materias o ciencias.
  - Mantiene el contexto de tokens limpio, eficiente y enfocado exclusivamente en las fuentes oficiales cargadas por el estudiante.
  - Garantiza privacidad absoluta en el dispositivo.

---

### 2.3 Excepción Controlada de Memoria Compartida — "Tareas Programadas & Cronograma Académico"
- **Único Directorio Compartido:**  
  La única entidad de datos que trasciende el aislamiento del proyecto es el bus de **Tareas Programadas / Fechas Críticas de Examen**.
- **Comportamiento Operativo:**
  - Si el usuario interactúa en cualquier chat con una instrucción temporal (ej: *"El 15 de noviembre rindo el examen final de la Unidad 3"*), el agente extrae la fecha, el evento y el hito académico.
  - La tarea se registra en un repositorio soberano local (`/sandbox/scheduler/academic_calendar.json`).
  - **Uso Compartido:** Tanto el agente general de Fénix IA como los agentes especializados en otras secciones tienen visibilidad de estas fechas para:
    - Organizar la distribución de lecturas y repasos de Active Recall.
    - Emitir notificaciones locales sin permisos invasivos.
    - Adaptar el tono y la prioridad de estudio conforme se acerca la fecha del examen.

---

## 3. COMPONENTES Y LÓGICA RECUPERADA DE REPOSITORIOS PREVIOS (ORO ARQUITECTÓNICO)

A partir de la inspección de los proyectos y repositorios previos (`fenix-web-pwa`, `fenix-legacy`, `fenix-proyecto`), se han recuperado 3 pilares de código y diseño ya probados que dan sustento directo a la visión del usuario:

### 3.1 Algoritmo SM-2 (SuperMemo-2) para Active Recall y Notificaciones Dinámicas
- **Archivo Fuente:** `spacedRepetition.ts`.
- **Lógica:** Implementa el algoritmo matemático SuperMemo-2 (SM-2):
  - Calificación de calidad de retención de 0 a 5.
  - Recálculo dinámico de `easeFactor` (mínimo 1.3), `interval` (en días) y `nextReviewDate`.
  - **Integración con el Agente Proactivo:** El agente usará esta misma fórmula para determinar qué conceptos de la Parte D (Active Recall) y Parte F (Glosario) están vencidos o por vencerse en función de la fecha de examen, orquestando las notificaciones en segundo plano y las interrupciones in-app.

---

### 3.2 Los 7 Arquetipos Curriculares Universales (Cero Hardcodeo)
- **Archivo Fuente:** `archetypes/types.ts`.
- **Aporte a la Universalidad Disciplinar (Reglas 2 y 3):** Permite que la app no encasille los programas académicos en un único formato, soportando cualquier cátedra mediante 7 moldes:
  1. `UNIT_BASED`: Unidades o módulos temáticos (Estándar universitario).
  2. `LEGAL_BOLILLAS`: Bolillas de examen (Derecho, Cs. Jurídicas y Notariales).
  3. `THEMATIC_AXES`: Ejes temáticos o bloques conceptuales (Ciencias Sociales y Humanidades).
  4. `WEEKLY_CHRONO`: Clases o semanas por fecha (Cátedras de cursado intensivo).
  5. `DUAL_THEORY_PRACTICE`: Estructura dual de teoría y laboratorio/talleres (Ingenierías, Exactas).
  6. `PBL_CASE`: Aprendizaje Basado en Problemas y Casos Clínicos (Medicina, Salud).
  7. `FREE_HYBRID`: Estructura libre, anómala o mixta (Escape soberano).

---

### 3.3 Modos de Procesamiento Fénix & Cuarentena Epistémica
- **Archivo Fuente:** `OPEN_CODE_IMPLEMENTACION_FENIX_MCP.md` y `Enjambre IA para Procesamiento Documental.pdf`.
- **Modos de Operación:**
  - `core_v21`: Material estructurado con programa oficial (Partes 0 a F completas).
  - `doc_dense`: Corpus de apuntes densos sin programa oficial (infiere un syllabus analítico sin inventar autores).
  - `sr_summary_ready`: Resumen hipercomprimido bajo *cuarentena epistémica* (respeta estrictamente las notas del estudiante, cero interferencia o alucinación).

---

### 3.4 Protocolo Fénix Modular v3.0 (Recuperado de `informefenix.docx` del Moto G77)
- **Documento Fuente:** `/sdcard/Download/esta/informefenix.docx` (descargado del Moto G77, 655 párrafos).
- **Aportes Epistémicos Clave:**
  1. **Transición v2.1 -> v3.0:** Desacopla el protocolo del formato impreso rígido A4 de Word, convirtiéndolo en un **motor cognitivo interactivo** para Android.
  2. **Núcleo Universal Invariante:**
     - Ingesta documental obligatoria previa (Parte 0).
     - Control estricto anti-invención.
     - Expansión controlada de subpuntos por densidad teórica (`✦`).
     - **Trazabilidad en 3 Estados Epistémicos:** Cada afirmación o párrafo queda explícitamente etiquetado como `[FUENTE]` (documento oficial del usuario), `[INFERENCIA]` (deducción lógica necesaria) o `[EXTERNO]` (conocimiento contextual general).
  3. **Los 6 Perfiles Disciplinares de Biglan-Becher:** Adapta la generación según el tipo de ciencia:
     - *Duras Puras* (Física, Matemática: demostraciones axiomáticas).
     - *Duras Aplicadas* (Ingeniería, Informática: algoritmos, modelos).
     - *Blandas Puras* (Historia, Filosofía, Sociología: corrientes y debates discursivos).
     - *Blandas Aplicadas* (Derecho, Trabajo Social, Educación: jurisprudencia, casos, protocolos).
     - *Ciencias de la Vida y de la Salud* (Medicina, Bioquímica: razonamiento clínico, fisiopatología).
  4. **Motor Matemático FSRS + Búsqueda Vectorial C (`sqlite-vec`):**
     - Evoluciona de SM-2 hacia **FSRS (Free Spaced Repetition Scheduler)** para optimizar la curva de olvido en Active Recall.
     - Búsqueda semántica local con `sqlite-vec` (escrito en C nativo acelerado con instrucciones SIMD ARM NEON en el procesador del teléfono, sin dependencias pesadas).

---

## 4. ESTADO DEL EDITOR ULTRA LIVIANO (F-DOCS)
- **Cero Bloatware:** No dependencias masivas (> 100 MB).
- **Formatos:** DOCX (parser SAX XML streaming < 600 KB), PDF (`PdfRenderer` nativo de Android 0 KB extra), XLSM/XLSX (`LazyGrid` en Compose < 4 MB de RAM), TXT y Markdown.
- **Suite de 20 Herramientas:** Tipografías, márgenes, sangrías, conversor de formato, OCR local (Google ML Kit on-device) y botón de formato automático bajo **Normas APA 7ma**.
- **Costo $0:** 100% de ejecución local en el dispositivo del usuario.

---

## 5. TABLEROS MAQUETADOS Y DISPONIBLES EN PENPOT (VERIFICADOS)
- **Tablero 24 (`[24. F-Docs: Editor Asistido & Jerarquía]`):**
  - Ubicación: `x = 2300, y = 1000`. Archivo: `b24_fdocs_editor.png` (72 KB).
  - Pestaña `Editor Asistido` activa, árbol jerárquico de 4 niveles (`Medicina > 3º Año > Farmacología > Unidad 2`), barra de 20 herramientas de formato con APA 7ma, lienzo A4 con texto de cátedra y negritas interactivas F-Z con callout socrático al tocar, telemetría de palabras y guardado seguro on-device.
- **Tablero 25 (`[25. Fénix Studio: Chat Multimodal & Ruido Blanco]`):**
  - Ubicación: `x = 2760, y = 1000`. Archivo: `b25_fenix_studio.png` (74 KB).
  - Fondo azul noche institucional, pestaña `Fénix Studio` activa, reproductor de 10 ruidos blancos de concentración on-device, acceso directo a Documento Maestro (PDF/DOCX), chat interactivo multimodal con tarjetas de Diapositiva 16:9 con botón de presentación, Tabla Relacional Parte B con trazabilidad `[FUENTE]` y Simulador de Examen Oral por voz.

---

## 6. PRÓXIMOS PASOS (AL RECIBIR EL INFORME DE DEEP RESEARCH)
1. Integrar los hallazgos técnicos del informe de Gemini Deep Research sobre la transposición del Protocolo v2.1 al entorno móvil.
2. Definir el protocolo de renderizado de componentes JSON-RPC para las tarjetas interactivas de chat.
3. Avanzar ordenadamente según las instrucciones del usuario.


