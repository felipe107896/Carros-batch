package com.wordpress.carlewinti.newcarshop.batchapi.chuncklet;

import java.io.IOException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;
import com.wordpress.carledwinti.newcarshop.batchapi.repository.CarroRepository;
import com.wordpress.carledwinti.newcarshop.batchapi.utils.CsvFileUtils;

public class CarroItemWriter implements ItemWriter<Carro>, StepExecutionListener {
	
	private static final  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarroItemWriter.class);
	private CsvFileUtils cvsSavedCars;
	
	@Autowired
	private CarroRepository carroRepository;


	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.cvsSavedCars = new CsvFileUtils("savedCars", false);		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		try {
			this.cvsSavedCars.closeWriter();
		} catch (IOException e) {
			e.printStackTrace();		}
		
		LOGGER.info("FINALIZANDO WRITER");

		return ExitStatus.COMPLETED;
	}

	@Override
	public void write(List<? extends Carro> items) throws Exception {
		List<? extends Carro> saveCarroList = this.carroRepository.saveAll(items);
		
		saveCarroList.stream().forEach(value -> {
			try {
				this.cvsSavedCars.writer(value);
			} catch (IOException e) {
				e.printStackTrace();			}
		});
		
	}

}
