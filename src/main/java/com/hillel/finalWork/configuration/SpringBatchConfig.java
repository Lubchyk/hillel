package com.hillel.finalWork.configuration;

import com.hillel.finalWork.batch.*;
import com.hillel.finalWork.batch.RecordFieldSetMapper;
import com.hillel.finalWork.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableConfigurationProperties(BatchProperties.class)
public class SpringBatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    @Qualifier("data")
    private DataSource dataSource;

    @Value("Id;   Created;   Status")
    private String sampleOrders;

    @Value("Name;   Price;   Created;   Status")
    private String report;

    @Value("SELECT id, created, status, user_id FROM Orders")
    private String sampleQueryForOrders;

    @Value("SELECT p.name, p.price, o.created, o.status FROM Orders AS o INNER JOIN Product AS p ON o.id = p.order_id WHERE (o.status = 'DONE' AND o.created = CURDATE()) ORDER BY p.price DESC")
    private String queryForReport;

    @Value("/home/ant/IdeaProjects/finalWork/src/main/resources/report/Orders.csv")
    private String outputCsvForOrders;

    @Value("/home/ant/IdeaProjects/finalWork/src/main/resources/report/Report.csv")
    private String outputCsvForReport;

//    @Value({"id", "created", "status"})
//    private String[] orderColumn;



    @Bean
    public JdbcCursorItemReader<Transaction> itemReader() {
        JdbcCursorItemReader<Transaction> reader = new JdbcCursorItemReader<Transaction>();
        reader.setDataSource(dataSource);
        reader.setSql(queryForReport);
        reader.setRowMapper(new RecordFieldSetMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<Transaction, Transaction> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<Transaction> itemWriter() {
        FlatFileItemWriter<Transaction> csvFileWriter = new FlatFileItemWriter<>();

        String exportFileHeader = report;
        StringHeaderWriter headerWriter = new StringHeaderWriter (exportFileHeader);
        csvFileWriter.setHeaderCallback(headerWriter);

        String exportFilePath = outputCsvForReport;
        csvFileWriter.setResource(new FileSystemResource(exportFilePath));

        LineAggregator<Transaction> lineAggregator = createReportLineAggregator();
        csvFileWriter.setLineAggregator(lineAggregator);

        return csvFileWriter;
    }
    private LineAggregator<Transaction> createReportLineAggregator() {
        DelimitedLineAggregator<Transaction> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";   ");

        FieldExtractor<Transaction> fieldExtractor = createReportFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);

        return lineAggregator;
    }

    private FieldExtractor<Transaction> createReportFieldExtractor() {
        BeanWrapperFieldExtractor<Transaction> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"name", "price", "created", "status"});
        return extractor;
    }

    @Bean
    public Step step1() {
        TaskletStep step1 = steps.get("step1").<Transaction, Transaction>chunk(3)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .startLimit(1)
                .build();
        return step1;
    }

    @Bean
    public Job job(JobBuilderFactory jobs, Step step1, JobExecutionListener listener) {
        return jobs.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
