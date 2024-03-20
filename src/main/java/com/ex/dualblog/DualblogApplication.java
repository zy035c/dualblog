package com.ex.dualblog;

// import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.jdbc.datasource.init.DataSourceInitializer;
// import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
@MapperScan("com.ex.dualblog.mapper")
public class DualblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DualblogApplication.class, args);
	}
	// @Bean
    // public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    //     ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    //     databasePopulator.addScript(new ClassPathResource("mybatis/schema.sql"));

    //     DataSourceInitializer initializer = new DataSourceInitializer();
    //     initializer.setDataSource(dataSource);
    //     initializer.setDatabasePopulator(databasePopulator);
    //     return initializer;
    // }
}
