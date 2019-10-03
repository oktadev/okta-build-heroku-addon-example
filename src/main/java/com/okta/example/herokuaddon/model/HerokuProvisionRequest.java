package com.okta.example.herokuaddon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class HerokuProvisionRequest {

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("oauth_grant")
    private OAuthGrant oauthGrant;

    private String name;
    Map<String, String> options;
    private String plan;
    private String region;
    private String uuid;

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public OAuthGrant getOauthGrant() {
        return oauthGrant;
    }

    public void setOauthGrant(OAuthGrant oauthGrant) {
        this.oauthGrant = oauthGrant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static class OAuthGrant {

        @JsonProperty("expires_at")
        private String expiresAt;

        private String code;
        private String type;

        public String getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(String expiresAt) {
            this.expiresAt = expiresAt;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
