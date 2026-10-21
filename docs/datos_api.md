# CONTRATOS DE DATOS Y API — FÉNIX MÓVIL
> **Estándar:** ISO/IEC/IEEE 29148  
> **Estado:** Documento Vivo Oficial  

---

## 1. PROTOCOLO MODEL CONTEXT PROTOCOL (MCP)
- **Transporte Oficial:** HTTP Streamable (JSON-RPC 2.0) sobre HTTPS con header obligatorio `mcp-session-id`.
- **Endpoints:**
  - Servidor Penpot Plugin: `https://design.penpot.app/mcp/stream?userToken={TOKEN}`
  - Servidor Móvil Ktor: `http://127.0.0.1:8080/mcp` (o túnel Cloudflare seguro).
- **Herramientas Expuestas:**
  - `execute_code`: Ejecución de scripts JS en contexto del plugin de diseño.
  - `export_shape`: Renderizado y exportación de imágenes PNG/SVG.
  - `penpot_api_info`: Inspección de interfaces y tipos.

## 2. ESQUEMAS DE EVENTOS
```json
{
  "jsonrpc": "2.0",
  "method": "tools/call",
  "params": {
    "name": "execute_code",
    "arguments": { "code": "/* código javascript */" }
  }
}
```

## 3. CONTRATO DE AUTODESCUBRIMIENTO DE MODELOS (`/v1/models`)
- **Petición HTTP:** `GET {BASE_URL}/models` con cabecera `Authorization: Bearer {API_KEY}`.
- **Respuesta Estándar (OpenAI / Anthropic / Local Gateway compatible):**
```json
{
  "data": [
    {
      "id": "modelo-id-dinamico",
      "object": "model",
      "created": 1700000000,
      "owned_by": "proveedor-registrado"
    }
  ]
}
```
- **Principio:** No se admiten catálogos hardcodeados. Todo modelo se consulta directamente a la URL informada por el usuario.

## 4. CONTRATO DE ALMACENAMIENTO SEGURO DE PROVEEDOR
```json
{
  "id": "uuid-v4",
  "name": "Nombre asignado por el usuario",
  "base_url": "https://api.proveedor.com/v1",
  "status": "connected | quota_error | connection_error | unconfigured",
  "models": ["model-a", "model-b"],
  "selected_model": "model-a"
}
```
*Nota:* La `api_key` se guarda disociada en el almacén de claves del sistema (Android Keystore / `secrets`), nunca en la estructura del modelo transferible.

## 5. CONTRATO DE INTENT DE ACCESIBILIDAD Y NOTIFICACIÓN LOCAL
- **Intent de Android:** `android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS`
  - Flags: `FLAG_ACTIVITY_NEW_TASK`
  - Payload del Intent: Sin extras sensibles, acceso directo a la lista de servicios de accesibilidad del dispositivo.
- **Notificación Local (Android 13+):**
  - Canal de Notificación: `fenix_accessibility_status`
  - Importancia: `IMPORTANCE_HIGH`
  - PendingIntent: Vinculado a `ACTION_ACCESSIBILITY_SETTINGS`
  - Permiso requerido: `android.permission.POST_NOTIFICATIONS` (Totalmente disociado de `BIND_ACCESSIBILITY_SERVICE`).

---

## 6. CONTRATO DE PROVIDER POOL ROUTER (MULTI-KEY FAILOVER)
```json
{
  "provider_id": "gemini",
  "name": "Google Gemini API",
  "base_url": "https://generativelanguage.googleapis.com/v1beta",
  "active_key_index": 0,
  "keys_pool": [
    {
      "key_alias": "gemini-free-01",
      "status": "HEALTHY",
      "rate_limit_reset_epoch": null,
      "consecutive_failures": 0
    },
    {
      "key_alias": "gemini-free-02",
      "status": "COOLDOWN",
      "rate_limit_reset_epoch": 1774182900,
      "consecutive_failures": 1
    }
  ],
  "failover_policy": {
    "retry_on_status": [429, 402, 403, 500, 503],
    "backoff_multiplier": 1.5,
    "max_retries_per_request": 3
  }
}
```

---

## 7. CONTRATO DE DESCRIPTOR DE SESIÓN PTY PARA AGENTES CLI
```json
{
  "session_id": "pty-opencode-01",
  "cli_target": "opencode",
  "command": "/bin/bash --login -c 'opencode --headless'",
  "pty_device": "/dev/ptmx",
  "dimensions": {
    "rows": 24,
    "cols": 80
  },
  "env": {
    "TERM": "xterm-256color",
    "COLORTERM": "truecolor",
    "LANG": "es_AR.UTF-8"
  }
}
```

---

## 8. CONTRATO DE CONECTORES Y GENERACIÓN DE SKILLS DINÁMICAS
```json
{
  "connector_id": "gmail-smtp-01",
  "type": "gmail",
  "name": "Gmail Apuntes Personales",
  "enabled": true,
  "auth_type": "app_password_16",
  "secret_alias": "keystore_gmail_app_pwd",
  "metadata": {
    "user_email": "usuario@gmail.com",
    "host": "smtp.gmail.com",
    "port": 587,
    "tls": true
  },
  "skills": [
    {
      "skill_id": "send_academic_summary",
      "name": "Enviar Resumen por Correo",
      "description": "Despacha apuntes y documentos PDF a la casilla del estudiante",
      "tools": [
        {
          "name": "mail_send_document",
          "parameters": {
            "to": "string",
            "subject": "string",
            "attachment_uri": "string"
          }
        }
      ]
    }
  ]
}
```

    "cols": 80
  },
  "env": {
    "TERM": "xterm-256color",
    "LANG": "C.UTF-8",
    "FENIX_MCP_URL": "http://127.0.0.1:8080/mcp",
    "FENIX_BEARER_TOKEN": "sovereign-vault-token-local"
  },
  "status": "RUNNING"
}
```

