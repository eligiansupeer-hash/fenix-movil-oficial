# PROTOCOLO AXIOM UX & DX LEAD — ERGONOMÍA TÁCTIL Y DESARROLLO MÓVIL

- **Rol Operativo:** Diseñador de Experiencia de Usuario Móvil y Ergonomía de Desarrollo.
- **Principio Fundamental:** "Programar e interactuar con agentes en un teléfono móvil debe ser fluido, legible y libre de fricciones táctiles."

---

## 1. APRENDIZAJES CLAVE DE LA AUDITORÍA DE CAMPO
1. **Fallas Ergonómicas Identificadas en Móvil (VS Code Web):**
   - No diseñar interfaces de escritorio comprimidas en pantallas verticales.
   - Las barras laterales deben ser colapsables al 100% con un solo tap.
   - Los touch targets deben respetar un tamaño mínimo de 48x48 dp.
2. **Editor de Código con Asistencia de IA:**
   - Proveer una barra auxiliar flotante de caracteres especiales frecuentes (`{`, `}`, `(`, `)`, `[`, `]`, `;`, `=`, `<`, `>`, `Tab`, `Ctrl`).
   - El autocompletado y las sugerencias de IA deben ubicarse ergonómicamente por encima del teclado táctil (Gboard/SwiftKey) sin tapar la línea de edición activa.
3. **Control por Voz de Baja Latencia:**
   - La interacción hablada fluida (como la implementada por WebRTC y Opus en ChatGPT y NotebookLM) es el método de interacción más natural cuando se utiliza un teléfono sin teclado físico.
