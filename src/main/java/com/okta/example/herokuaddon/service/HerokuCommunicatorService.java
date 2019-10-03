package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.okta.example.herokuaddon.model.HerokuAddOnConfig;
import com.okta.example.herokuaddon.model.HerokuProvisionRequest;
import com.okta.example.herokuaddon.model.HerokuProvisionResponse;
import com.okta.example.herokuaddon.model.HerokuTokenResponse;

import java.io.IOException;
import java.util.List;

public interface HerokuCommunicatorService {

    TypeReference herokuConfigTypeRef = new TypeReference<List<HerokuAddOnConfig.HerokuConfig>>(){};

    String HEROKU_TOKEN_URL = "https://id.heroku.com/oauth/token";
    String HEROKU_API_BASE_URL = "https://api.heroku.com";

    HerokuTokenResponse exchangeCodeForTokens(String code) throws IOException;
    HerokuAddOnConfig updateConfig(
        String accessToken, HerokuProvisionRequest request, HerokuAddOnConfig config
    ) throws IOException;
    HerokuProvisionResponse provisionComplete(String accessToken, HerokuProvisionRequest request) throws IOException;

}
