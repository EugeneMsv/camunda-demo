package org.camunda.bpm.twit.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetaDataManager implements JavaDelegate {
    private static final Logger logger = LoggerFactory.getLogger(MetaDataManager.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("Setting up confirmation dateTime for processInstanceId={}", execution.getProcessInstanceId());
        logger.info("Current variables={} for processInstanceId={}", execution.getVariables());
        execution.setVariable("confirmationDateTime", LocalDateTime.now());
    }

}
