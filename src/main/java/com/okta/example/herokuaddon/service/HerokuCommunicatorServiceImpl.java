package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.example.herokuaddon.model.HerokuTokenResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
}
