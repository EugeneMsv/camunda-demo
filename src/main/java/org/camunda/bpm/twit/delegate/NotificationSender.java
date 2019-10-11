package org.camunda.bpm.twit.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.twit.notify.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationSender implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private final EventPublisher eventPublisher;

    @Autowired
    public NotificationSender(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("Emulating notification send for processInstanceId={}", execution.getProcessInstanceId());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                eventPublisher.publishNotificationEvent(execution.getProcessInstanceId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
