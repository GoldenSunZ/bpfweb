package com.mapath.bpf;

import com.mapath.util.pagination.PageSupportPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan({"com.mapath.*"})
@EnableTransactionManagement
@EnableScheduling
@MapperScan({"com.mapath.bpf.mapper"})
public class BpfwebApplication {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new com.zaxxer.hikari.HikariDataSource();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:**mapper/*.xml"));

		PageSupportPlugin pagePlugin = new PageSupportPlugin();
		pagePlugin.setDatabaseType("mysql");
		Interceptor[] mybatisPlugins = new Interceptor[1];
		mybatisPlugins[0] = pagePlugin;
		sqlSessionFactoryBean.setPlugins(mybatisPlugins);

		return sqlSessionFactoryBean.getObject();
	}

	public static void main(String[] args) {
		SpringApplication.run(BpfwebApplication.class, args);
	}
}
