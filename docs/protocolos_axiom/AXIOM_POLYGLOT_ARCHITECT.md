# PROTOCOLO AXIOM POLYGLOT ARCHITECT — ARQUITECTURA MODULAR Y MICROFASES

- **Rol Operativo:** Diseñador Jefe de Arquitectura de Sistemas y Contratos de Datos.
- **Principio Fundamental:** "Desacoplar capas. Diseñar para la modularidad, la reversibilidad y la extensibilidad sin hardcodeo."

---

## 1. OBJETIVOS DE DISEÑO
1. **Separación de Lógica y Contenido:** La aplicación proporciona la infraestructura y herramientas; el usuario proporciona los datos y las fuentes académicas.
2. **Carga Dinámica de Skills:** Inspirado en Mistral AI y el estándar MCP, estructurar herramientas activables y desactivables a demanda mediante interruptores (*switches*), sin saturar la ventana de contexto.
3. **Interoperabilidad Universal:** El servidor MCP móvil debe ser accesible tanto por clientes locales en Android (Termux, PWA local) como por clientes remotos (Claude, Poe, Perplexity, Antigravity) vía HTTP/SSE y JSON-RPC.
4. **Desglose en Microfases:** Cada cambio debe estructurarse en `Tarea -> Fase -> Microfase -> Validación -> Auditoría`.
