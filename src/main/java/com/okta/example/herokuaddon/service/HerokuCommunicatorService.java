package com.okta.example.herokuaddon.service;

import com.okta.example.herokuaddon.model.HerokuTokenResponse;

import java.io.IOException;

public interface HerokuCommunicatorService {

    String HEROKU_TOKEN_URL = "https://id.heroku.com/oauth/token";

    HerokuTokenResponse exchangeCodeForTokens(String code) throws IOException;
}
