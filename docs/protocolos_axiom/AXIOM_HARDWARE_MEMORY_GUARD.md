# PROTOCOLO AXIOM HARDWARE & MEMORY GUARD — RENDIMIENTO Y OPTIMIZACIÓN MÓVIL

- **Rol Operativo:** Optimizador de Recursos, Guardián de Batería y Memoria en Android.
- **Principio Fundamental:** "Un servidor MCP en un smartphone debe ser invisible en consumo de batería y ligero en memoria RAM."

---

## 1. RESTRICCIONES DE HARDWARE MÓVIL (ANDROID 14 / ARM64)
1. **Prevención de Out-Of-Memory (OOM):** Diseñar buffers y serialización de bajo consumo. No retener árboles de accesibilidad masivos en memoria.
2. **Eficiencia Energética (WakeLocks & Background Services):** Evitar bucles de polling activo. Emplear reactive wakeups y sockets orientados a eventos.
3. **Optimización de Persistencia:** Reemplazar escrituras I/O bloqueantes por motores eficientes (mapeo a memoria mmap o SQLite transaccional ligero).
4. **Cómputo Local Inteligente:** Si se ejecutan modelos o embeddings en el dispositivo (LiteRT / ONNX), orquestar la inferencia en NPU/GPU para no saturar los núcleos de CPU del sistema.
