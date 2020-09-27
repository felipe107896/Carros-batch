package com.wordpress.carlewinti.newcarshop.batchapi.chuncklet;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.wordpress.carledwinti.newcarshop.batchapi.dto.CarroDto;

public class CarroItemReader implements ItemReader<CarroDto>, StepExecutionListener {
	
	private static final  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarroItemReader.class);
	private Iterator<CarroDto> carroIterator;
	

	@Override
	public void beforeStep(StepExecution stepExecution) {
		ExecutionContext ec = stepExecution.getJobExecution().getExecutionContext();
		List<CarroDto> carroDtoList = (List<CarroDto>) ec.get("carroInList");
		this.carroIterator = carroDtoList.iterator();
		LOGGER.info("Iniciando READER....");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.info("Iniciando READER....");

		return ExitStatus.COMPLETED;
	}

	@Override
	public CarroDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(this.carroIterator != null &&  this.carroIterator.hasNext()) {
			return this.carroIterator.next();
		}
		
		return null;
	}

}
