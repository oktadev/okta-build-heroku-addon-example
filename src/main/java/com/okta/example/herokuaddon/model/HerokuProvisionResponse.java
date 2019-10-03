package com.okta.example.herokuaddon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HerokuProvisionResponse {

    private String id;
    private String name;
    private String state;

    private AddOnEntity app;

    @JsonProperty("addon_service")
    private AddOnEntity service;

    private AddOnEntity plan;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private String message;

    public HerokuProvisionResponse() {}

    public HerokuProvisionResponse(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AddOnEntity getApp() {
        return app;
    }

    public void setApp(AddOnEntity app) {
        this.app = app;
    }

    public AddOnEntity getService() {
        return service;
    }

    public void setService(AddOnEntity service) {
        this.service = service;
    }

    public AddOnEntity getPlan() {
        return plan;
    }

    public void setPlan(AddOnEntity plan) {
        this.plan = plan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class AddOnEntity {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
