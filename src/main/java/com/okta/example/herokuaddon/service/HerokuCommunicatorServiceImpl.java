package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Map<String, Object> exchangeCodeForTokens(String code) throws IOException {
        HttpResponse response = Request.Post(HEROKU_TOKEN_URL)
            .bodyForm(Form.form()
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("client_secret", clientSecret)
                .build())
            .execute()
            .returnResponse();

        return mapper.readValue(response.getEntity().getContent(), tokenExchangeResponseType);
    }
}
