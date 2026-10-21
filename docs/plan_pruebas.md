# PLAN DE PRUEBAS Y VALIDACIÓN — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. MATRIZ DE VERIFICACIÓN
- **[PR-01] Prueba de Integridad Visual:** Validación de renderizado mediante `export_shape` en PNG y cotejo milimétrico de geometrías.
- **[PR-02] Prueba de Regresión de Interacciones:** Comprobación de enlaces interactivos de botones (`+`, mic, enviar, panel).
- **[PR-03] Prueba de Rendimiento de Memoria:** Medición de heap en Android asegurando < 1.5 MB de RAM para la animación del Fénix a 60 FPS.
- **[PR-04] Auditoría de Cero Hardcodeo:** Análisis estático de código para descartar nombres de universidades o cátedras fijas.
- **[PR-05] Validación de Extensión de Accesibilidad por el Agente en Sandbox:**
  - Chequeo de compilación y empaquetado del servicio sin errores.
  - Verificación de enlace socket/IPC entre el sandbox y el runtime anfitrión.
  - Smoke test automatizado de los endpoints de accesibilidad antes de emitir confirmación de éxito.
- **[PR-06] Verificación de Intent de Accesibilidad y Notificación:**
  - Comprobación del disparo de `Settings.ACTION_ACCESSIBILITY_SETTINGS` al pulsar el botón verde o la notificación nativa.
  - Validación del permiso `POST_NOTIFICATIONS` independiente del subsistema de accesibilidad.
- **[PR-07] Verificación de Consola Linux PTY y Renderizado ANSI:**
  - Apertura no bloqueante de `/dev/ptmx` bajo Bionic JNI (`libtermux.so`).
  - Validación de emulación ANSI con 256 colores y redimensionamiento dinámico (SIGWINCH).
  - Comprobación de interrupción limpia con `SIGINT` (`^C` / `0x03`).
- **[PR-08] Prueba de Resiliencia del Pool Router Multi-Key (Failover 429):**
  - Simulación de respuesta `HTTP 429 Too Many Requests` en clave activa `key_01`.
  - Validación de conmutación inmediata a `key_02` en < 200 ms sin pérdida del mensaje del usuario ni propagación de error a la UI.

