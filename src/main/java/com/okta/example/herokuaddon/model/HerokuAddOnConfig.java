package com.okta.example.herokuaddon.model;

import java.util.ArrayList;
import java.util.List;

public class HerokuAddOnConfig {

    private List<HerokuConfig> config = new ArrayList<>();

    public HerokuAddOnConfig() {
        this.config = new ArrayList<>();
    }

    public HerokuAddOnConfig(List<HerokuConfig> config) {
        this.config = config;
    }

    public List<HerokuConfig> getConfig() {
        return config;
    }

    public void setConfig(List<HerokuConfig> config) {
        this.config = config;
    }

    public static class HerokuConfig {

        String name;
        String value;

        public HerokuConfig() {}

        public HerokuConfig(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
