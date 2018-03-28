package com.hillel.finalWork.batch;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Data
public class QuartzJobLauncher extends QuartzJobBean {

    private String jobName;
    private JobLauncher jobLauncher;
    private JobLocator jobLocator;

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext)  {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        Job job = null;
        try {
            job = jobLocator.getJob("job");
            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("####### status: " +  execution.getStatus());
        } catch (NoSuchJobException | JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException
                | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }
    }
}
