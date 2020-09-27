package com.wordpress.carledwinti.newcarshop.batchapi;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import com.wordpress.carledwinti.newcarshop.batchapi.dto.CarroDto;
import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;
import com.wordpress.carledwinti.newcarshop.batchapi.tasklet.CarroValidateTasklet;
import com.wordpress.carlewinti.newcarshop.batchapi.chuncklet.CarroItemProcessor;
import com.wordpress.carlewinti.newcarshop.batchapi.chuncklet.CarroItemReader;
import com.wordpress.carlewinti.newcarshop.batchapi.chuncklet.CarroItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	//por padrao o spring batch cria na base de dados as tabelas referente ao batch como BATCH_JOB_EXECUTION,BATCH_JOB_EXECUTION_CONTEXT,BATCH_STEP_EXECUTION
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("carrosJob")
				.start(carroValidateTaskletStep())
				.next(carroEnriquecimentoChunckletStep(carroItemReader(),carroItemProcessor(), carroItemWriter()))
				.build();
	}
	
	@Bean
	public Step carroValidateTaskletStep() {
		return stepBuilderFactory.get("carroValidateTaskletStep")
				.tasklet(new CarroValidateTasklet("carros-import"))
				.build();
	}
	
	@Bean
	public Step carroEnriquecimentoChunckletStep(ItemReader<CarroDto> carroItemReader,
			ItemProcessor<CarroDto,Carro> carroItemProcessor,
			ItemWriter<Carro> carroItemWriter) {
		
		return stepBuilderFactory.get("carroEnriquecimentoChunckletStep")
				.<CarroDto,Carro>chunk(5)
				.reader(carroItemReader)
				.processor(carroItemProcessor)
				.writer(carroItemWriter)
				.build();
	}
	
	@Bean
	public ItemReader<CarroDto> carroItemReader(){
		return new CarroItemReader();
	}
	
	@Bean
	public ItemProcessor<CarroDto,Carro> carroItemProcessor(){
		return new CarroItemProcessor();
	}
	
	
	@Bean
	public ItemWriter<Carro> carroItemWriter(){
		return new CarroItemWriter();
	}
	
	
	
	

}
