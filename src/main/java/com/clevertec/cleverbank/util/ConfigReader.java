package com.clevertec.cleverbank.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.yml";

    private static final Yaml yaml = new Yaml();
    private static final InputStream inputStream = ConfigReader.class
            .getClassLoader()
            .getResourceAsStream(CONFIG_FILE);
    private static final Map<String, Object> configMap = yaml.load(inputStream);

    public static BigDecimal getInterestRate() {
        if (configMap.containsKey("interestRate")) {
            return BigDecimal.valueOf((Double) configMap.get("interestRate"));
        } else {
            throw new RuntimeException("Interest rate is not found in config");
        }
    }
}
