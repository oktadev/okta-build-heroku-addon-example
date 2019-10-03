package com.okta.example.herokuaddon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.example.herokuaddon.model.HerokuAddOnConfig;
import com.okta.example.herokuaddon.model.HerokuProvisionRequest;
import com.okta.example.herokuaddon.model.HerokuTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class HerokuProvisionServiceImpl implements HerokuProvisionService {

    @Autowired
    ObjectMapper mapper;

    private HerokuCommunicatorService herokuCommunicatorService;

    private static final Logger log = LoggerFactory.getLogger(HerokuProvisionServiceImpl.class);

    public HerokuProvisionServiceImpl(HerokuCommunicatorService herokuCommunicatorService) {
        this.herokuCommunicatorService = herokuCommunicatorService;
    }

    @Override
    @Async
    public void provision(HerokuProvisionRequest request) throws IOException, InterruptedException {
        doProvision(request);
    }

    private void doProvision(HerokuProvisionRequest request) throws IOException, InterruptedException {
        HerokuTokenResponse tokens =
            herokuCommunicatorService.exchangeCodeForTokens(request.getOauthGrant().getCode());

        // artificial time lapse
        Thread.sleep(10000);

        HerokuAddOnConfig herokuAddOnConfig = new HerokuAddOnConfig();
        herokuAddOnConfig.getConfig().add(new HerokuAddOnConfig.HerokuConfig("VAR1", "VAL1"));

        HerokuAddOnConfig herokuAddOnConfigResponse =
            herokuCommunicatorService.updateConfig(tokens.getAccessToken(), request, herokuAddOnConfig);

        log.info(
            "Update config response: {}",
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(herokuAddOnConfigResponse)
        );

        herokuCommunicatorService.provisionComplete(tokens.getAccessToken(), request);
    }
}
