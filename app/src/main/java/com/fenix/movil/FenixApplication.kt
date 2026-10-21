package com.fenix.movil

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FenixApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicia el servidor Ktor MCP en un hilo en segundo plano (I/O)
        // para no bloquear el hilo principal y prevenir NetworkOnMainThreadException.
        // El uso del dispatcher global asegura que no retenga el contexto de la Activity, evitando memory leaks.
        CoroutineScope(Dispatchers.IO).launch {
            FenixMcpServer().startServer()
        }
    }
}
