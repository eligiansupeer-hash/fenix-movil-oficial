# GUÍA Y PIPELINE DE DESPLIEGUE — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. ENTORNO DE COMPILACIÓN
- **Gradle:** 9.7.1 con Java 17/21 (AGP 9.3.2, Kotlin 2.4.10, Hilt 2.60.1).
- **Android Target:** compileSdk = 37, targetSdk = 34 / minSdk = 33 (ABI principal: `arm64-v8a`).
- **Configuración de Empaquetado Nativo:**
  - `packaging { jniLibs { useLegacyPackaging = true } }` para asegurar que `installd` extraiga `libproot.so`, `libproot-loader.so`, `libtalloc.so` y `libtermux.so` en `applicationInfo.nativeLibraryDir`.
  - **Alineación ELF:** Android NDK r29 con alineación de páginas a 16 KB (`max-page-size=16384`) para compatibilidad plena con Android 15 y Android 16.
- **Distribución de Rootfs:**
  - Descarga HTTP reanudable y atómica de Debian 12 Bookworm arm64 (`rootfs.tar.xz`, ~35 MB) desde Linux Containers con validación SHA-256 local.
- **Proceso de Build:**
  - Rama `feature/*` -> verificación determinista -> build APK signed debug/release.
- **Distribución:** Cero costo de publicación obligatoria; instalación directa vía ADB / APK firmado offline con Keystore soberano.

