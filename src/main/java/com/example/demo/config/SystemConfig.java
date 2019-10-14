package com.example.demo.config;

import org.hibernate.dialect.MySQL57Dialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

//@Component
//@ConfigurationProperties(prefix = "spring.datasource")
public class SystemConfig {
    private String url;
    private String driveClassName;
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDriveClassName(String driveClassName) {
        this.driveClassName = driveClassName;
    }

    public String getDriveClassName() {
        return driveClassName;
    }

  //  @Bean(destroyMethod = "", name = "EmbeddedDataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driveClassName);
        dataSourceBuilder.url(url);
        //dataSourceBuilder.type(SQLiteDataSource.class);
        dataSourceBuilder.type(MySQL57Dialect.class);
        return dataSourceBuilder.build();
    }
}
