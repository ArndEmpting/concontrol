package com.digitalsanctuary.spring.user;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app.initial-data")
@Configuration
@Data
public class AppProperties {
    private List<String> altersklassen = new ArrayList<>();
    private List<String> clans = new ArrayList<>();
    private boolean reset= false;
    // Getters and setters...
}