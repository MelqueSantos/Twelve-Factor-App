package com.lab.app_de_doze_fatores.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GracefulShutdownHook {

    @EventListener
    public void onShutdown(ContextClosedEvent event) {
        log.info("[STDOUT-LOG] Sinal de desligamento interceptado.");
        log.info("[STDOUT-LOG] Iniciando desligamento gracioso (Graceful Shutdown)... Recusando novas requisições.");
    }

    @PreDestroy
    public void onPreDestroy() {
        log.info("[STDOUT-LOG] Limpeza concluída. A aplicação foi terminada de forma segura.");
    }
}
