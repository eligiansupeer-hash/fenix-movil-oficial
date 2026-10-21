# 🐦‍🔥 Fénix Móvil — Repositorio Oficial & Blueprint Arquitectónico

> **Estándar Rector:** ISO/IEC/IEEE 29148 (Systems and software engineering — Requirements engineering)  
> **Protocolo de Gobernanza:** Protocolo GUARDIAN  
> **Titular y Creador:** Elí Gian Cortez  
> **Licencia:** MIT License  
> **Estado:** Arquitectura y Maquetación 100% Sellada (28 Tableros Oficiales)  

---

## 📌 1. PROPÓSITO DEL REPOSITORIO
Este repositorio constituye la **fuente de verdad limpia y soberana** para la construcción, depuración e implementación fase a fase de **Fénix Móvil**. Contiene:
1. La suite viva de 9 documentos de ingeniería de requisitos bajo norma **ISO/IEC/IEEE 29148**.
2. Los informes técnicos de **Deep Research** (Arquitectura Linux en Android, Diagnóstico de Tráfico Debian, Protocolo Modular v3.0, FSRS, Biglan/Becher y Prospección de 13 apps de IA).
3. El **Dossier Maestro de Maquetación** en PDF de alta fidelidad y las capturas oficiales de los 28 tableros diseñados en Penpot.
4. El **Plan Maestro de Implementación para Jules**, con directivas de limpieza quirúrgica, reemplazo de código heredado y pruebas automatizadas.

---

## 🧱 2. ESTRUCTURA DEL REPOSITORIO
```text
├── docs/
│   ├── REQUISITOS.md                     # Requisitos funcionales y no funcionales ISO 29148
│   ├── Diseno_penpot.md                  # Especificaciones de lienzo, tokens y matriz de tableros
│   ├── interacciones.md                  # Matriz de eventos, gestos y atajos
│   ├── reglas de negocio.md              # Lógica de dominio, estados y restricciones operativas
│   ├── datos_api.md                      # Contratos JSON-RPC, MCP y Provider Pool Router
│   ├── seguridad.md                      # Bóveda Secrets, SELinux, sandbox y límites éticos (Regla 25)
│   ├── arquitectura.md                   # Arquitectura limpia, NDK r29 en jniLibs y motor local
│   ├── plan_pruebas.md                   # Casos de prueba, matrices de verificación y criterios
│   ├── despliegue.md                     # Pipeline Gradle, empaquetado ELF a 16 KB y rootfs
│   ├── fdocs_arquitectura_temporal.md    # Especificación de F-Docs dual y Split View Maestro
│   ├── auditorias_apps/                  # 13 informes de ingeniería inversa (ChatGPT, Claude, etc.)
│   ├── investigaciones/                  # Deep researchs de Linux, Debian, FSRS y Protocolo v3.0
│   ├── protocolo/                        # Especificación del Protocolo Fénix v2.1
│   ├── protocolos_axiom/                 # Definición de agentes y subagentes Axiom de OpenCode
│   ├── maquetacion/                      # Maquetación interactiva HTML/JS
│   └── dossier/                          # Dossier Maestro PDF y los 28 tableros en PNG
├── PLAN_MAESTRO_IMPLEMENTACION_JULES.md  # Directivas de ejecución fase a fase para Jules
├── CREDITOS_Y_LICENCIAS.md               # Atribuciones Open Source y eximición jurídica
├── LICENSE                               # Licencia MIT oficial
└── README.md                             # Documento actual
```

---

## 🚀 3. DIRECTIVAS FUNDAMENTALES DE ARQUITECTURA
1. **Principio de Costo Cero Absoluto:** Ningún componente ni dependencia debe obligar al usuario ni al desarrollador a pagar suscripciones o cuotas de infraestructura.
2. **Cero Hardcodeo Académico:** La PWA es 100% universal e independiente de cualquier institución, facultad o cátedra concreta. La cátedra es dato de entrada del usuario.
3. **Bypass Legal de $W \wedge X$ en Android 14–16:** `libproot.so` empaquetado en `jniLibs/arm64-v8a` con `useLegacyPackaging = true` y compilado con NDK r29 (alineación a 16 KB), compatible con las directivas de Google Play Protect.
4. **Soberanía Epistémica & Local-First:** Indexación semántica local en C nativo con `sqlite-vec` (instrucciones ARM NEON).
5. **Límites Éticos Inviolables (Regla 25):** Prohibición absoluta de asesoramiento psicológico y consejos médicos personales.

---

*Creado por Elí Gian Cortez. Todos los derechos reservados. Licencia MIT.*
