package com.zt.demo.controller.conf;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * dataSource 数据源配置
 * @author 朱同
 */
@Configuration
@Data
public class DruidDataSourceConfiguration {

	@Value("${spring.datasource.druid.filters}")
	private String filters ;
	/**
	 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Tomcat
	 * @return DruidDataSource
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DruidDataSource dataSource() {

		DruidDataSource dataSource = new DruidDataSource();
		try {
			System.out.println(filters);
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}
