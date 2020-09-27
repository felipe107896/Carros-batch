package com.wordpress.carledwinti.newcarshop.batchapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.batch.runtime.JobExecution;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;
import com.wordpress.carledwinti.newcarshop.batchapi.repository.CarroRepository;
import com.wordpress.carledwinti.newcarshop.batchapi.utils.DateUtils;
import com.wordpress.carlewinti.newcarshop.batchapi.chuncklet.CarroItemReader;

@Service
public class CarroService {
	
	private static final  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarroService.class);

	
	@Autowired
	private JobLauncher jobLuncher;
	
	@Autowired
	private Job job;
	
	@Autowired
	private CarroRepository carroRepository;
	
	@Scheduled(cron = "*/10*****")
	public BatchStatus batchExecute() {
		LOGGER.info("Inicio do Job"+DateUtils.getNow());
		
		Map<String, JobParameter> map = new HashMap();
		map.put("time", new JobParameter(System.currentTimeMillis()));
		
		try {
			org.springframework.batch.core.JobExecution jobExecution = jobLuncher.run(job, new JobParameters(map));
			
			while (jobExecution.isRunning()) {
				LOGGER.info("Job em execução...");
				
			}
			LOGGER.info(DateUtils.getNow());
			return jobExecution.getStatus();
		} catch (Exception e) {
			LOGGER.info("Falha ao tentar executar o job." + e.getMessage());
			return BatchStatus.FAILED;
		}
		
	}
	
	public List<Carro> findAll(){
		return carroRepository.findAll();
		
	}

}
