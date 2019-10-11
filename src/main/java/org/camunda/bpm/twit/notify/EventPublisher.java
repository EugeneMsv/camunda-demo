package org.camunda.bpm.twit.notify;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    private RuntimeService runtimeService;

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }


    public void publishNotificationEvent(String processInstanceId) {
        logger.info("Sending event to processInstanceId={}", processInstanceId);
        String eventName = "EVENT_NOTIFICATION_CONFIRMED";
        Execution execution = findExecution(eventName, processInstanceId);
        Map<String, Object> notificationVariables = new HashMap<>();
        notificationVariables.put("notificationStatus", "OK");
        runtimeService.messageEventReceived(eventName, execution.getId(), notificationVariables);
    }


    private Execution findExecution(String eventName, String processInstanceId) {
        Execution execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName(eventName)
                .processInstanceId(processInstanceId)
                .singleResult();

        Assert.notNull(execution, "Not found execution for processInstanceId="
                + processInstanceId + ", eventName=" + eventName);
        return execution;
    }
}
