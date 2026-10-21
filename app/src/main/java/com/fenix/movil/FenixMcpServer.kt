package com.fenix.movil

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class FenixMcpServer {
    fun startServer() {
        embeddedServer(CIO, port = 8080, host = "127.0.0.1", module = Application::mcpModule).start(wait = false)
    }
}

fun Application.mcpModule() {
    routing {
        get("/mcp") {
            call.respondText("Fénix MCP Server is running!")
        }
    }
}
