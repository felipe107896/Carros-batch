package com.wordpress.carlewinti.newcarshop.batchapi.chuncklet;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import com.wordpress.carledwinti.newcarshop.batchapi.converter.CarroConverter;
import com.wordpress.carledwinti.newcarshop.batchapi.dto.CarroDto;
import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;

public class CarroItemProcessor implements ItemProcessor<CarroDto, Carro>, StepExecutionListener {
	
	private static final  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarroItemProcessor.class);


	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOGGER.info("Iniciando READER....");
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.info("Iniciando READER....");

		return ExitStatus.COMPLETED;
	}

	@Override
	public Carro process(CarroDto item) throws Exception {
		// TODO Auto-generated method stub
		return CarroConverter.getCarro(item);
	}

}
