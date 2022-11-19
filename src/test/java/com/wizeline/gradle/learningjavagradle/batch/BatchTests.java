package com.wizeline.gradle.learningjavagradle.batch;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.wizeline.gradle.learningjavagradle.controller.BatchController;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class BatchTests {

	@Mock
	UserJob userJob;

	@Mock
	Job job;

	@Mock
	JobLauncher jobLauncher;

	@Mock
	JobParameters jobParameters;

	@Mock
	JobExecution jobExecution;

	@InjectMocks
	BatchController batchController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public void batch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		when (userJob.printUsersJob()).thenReturn(job);

		when(jobLauncher.run(job, jobParameters)).thenReturn(jobExecution);

		assertNotNull(batchController.startBatch());

	}

}
