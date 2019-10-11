package org.camunda.bpm.twit.event;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CamundaEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CamundaEventListener.class);

    @EventListener
    public void onTaskEvent(DelegateTask taskDelegate) {
        logger.info("Catch task event ={}", taskDelegate);
        // handle task event
    }

    @EventListener
    public void onExecutionEvent(DelegateExecution executionDelegate) {
        // handle execution event
        logger.info("Catch execution event ={}", executionDelegate);
    }

    @EventListener
    public void onHistoryEvent(HistoryEvent historyEvent) {
        // handle history event
        logger.info("Catch history event ={}", historyEvent);
    }

}
