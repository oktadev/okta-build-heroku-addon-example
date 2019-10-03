package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Map;

public interface HerokuCommunicatorService {

    TypeReference tokenExchangeResponseType = new TypeReference<Map<String, Object>>(){};

    String HEROKU_TOKEN_URL = "https://id.heroku.com/oauth/token";

    Map<String, Object> exchangeCodeForTokens(String code) throws IOException;
}
