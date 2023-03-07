package com.jet.TestJet.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jpa.hibernate")
public class JpaProperties {

    private String showSql;

    private String ddlAuto;

    private String dialect;

    private String genetaeDdl;
}
