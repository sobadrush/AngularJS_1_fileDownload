package _01_config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.ctbc.model.dao" })
@EnableTransactionManagement
public class RootConfig {

	@Bean
	public DataSource driverManagerDS() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername("sa");
		ds.setPassword("sa123456");
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;" + "databaseName=" + "DB_Emp_Dept");
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource ds) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager(ds);
		return txManager;
	}

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

		DataSource ds = context.getBean("driverManagerDS", DataSource.class);
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		System.err.println(" 資料庫廠商： " + ds.getConnection().getMetaData().getDatabaseProductName());
		System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<< ");

		context.close();
	}
	
}









