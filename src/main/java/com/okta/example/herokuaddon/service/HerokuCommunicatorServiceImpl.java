package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.example.herokuaddon.model.HerokuAddOnConfig;
import com.okta.example.herokuaddon.model.HerokuProvisionRequest;
import com.okta.example.herokuaddon.model.HerokuProvisionResponse;
import com.okta.example.herokuaddon.model.HerokuTokenResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class HerokuCommunicatorServiceImpl implements HerokuCommunicatorService {

    @Value("#{ @environment['heroku.clientSecret'] }")
    private String clientSecret;

    private final ObjectMapper mapper;

    HerokuCommunicatorServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public HerokuTokenResponse exchangeCodeForTokens(String code) throws IOException {
        HttpResponse response = Request.Post(HEROKU_TOKEN_URL)
            .bodyForm(Form.form()
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("client_secret", clientSecret)
                .build())
            .execute()
            .returnResponse();

        return mapper.readValue(response.getEntity().getContent(), HerokuTokenResponse.class);
    }

    @Override
    public HerokuAddOnConfig updateConfig(
        String accessToken, HerokuProvisionRequest request, HerokuAddOnConfig config
    ) throws IOException {
        HttpResponse response = Request.Patch(
            HEROKU_API_BASE_URL + "/addons/" + request.getUuid() + "/config"
        )
        .addHeader("Authorization", "Bearer " + accessToken)
        .addHeader("Accept", "application/vnd.heroku+json; version=3")
        .bodyString(mapper.writeValueAsString(config), ContentType.APPLICATION_JSON)
        .execute().returnResponse();

        return new HerokuAddOnConfig(mapper.readValue(response.getEntity().getContent(), herokuConfigTypeRef));
    }

    @Override
    public HerokuProvisionResponse provisionComplete(
        String accessToken, HerokuProvisionRequest request
    ) throws IOException {
        HttpResponse response = Request.Post(
            HEROKU_API_BASE_URL + "/addons/" + request.getUuid() + "/actions/provision"
        )
        .addHeader("Authorization", "Bearer " + accessToken)
        .addHeader("Accept", "application/vnd.heroku+json; version=3")
        .execute().returnResponse();

        return mapper.readValue(response.getEntity().getContent(), HerokuProvisionResponse.class);
    }
}
