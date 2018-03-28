package com.hillel.finalWork.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class CustomJobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory
            .getLogger(CustomJobListener.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("templateMessageAboutReport")
    private SimpleMailMessage templateMessageAboutReport;

    @Autowired
    @Qualifier("templateMessageAboutError")
    private SimpleMailMessage templateMessageAboutError;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            SimpleMailMessage mailMessage = new SimpleMailMessage(
                    templateMessageAboutReport);
            javaMailSender.send(mailMessage);
        }
        else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("!!! We have some error");
            SimpleMailMessage mailMessage = new  SimpleMailMessage(templateMessageAboutError);
            System.err.println(jobExecution.getFailureExceptions());
            mailMessage.setText(String.valueOf(jobExecution.getFailureExceptions()));
            javaMailSender.send(mailMessage);
         }
    }
}