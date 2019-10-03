package com.okta.example.herokuaddon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.example.herokuaddon.service.HerokuCommunicatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.okta.example.herokuaddon.controller.HerokuController.HEROKU_URI;

@RestController
@RequestMapping(HEROKU_URI)
public class HerokuController {

    @Autowired
    ObjectMapper mapper;

    static final String HEROKU_URI = "/heroku";
    static final String RESOURCES_URI = "/resources";

    private HerokuCommunicatorService herokuCommunicatorService;

    private static final Logger log = LoggerFactory.getLogger(HerokuController.class);

    public HerokuController(HerokuCommunicatorService herokuCommunicatorService) {
        this.herokuCommunicatorService = herokuCommunicatorService;
    }

    @PostMapping(RESOURCES_URI)
    public @ResponseBody Map<String, Object> provision(
        @RequestBody Map<String, Object> request
    ) throws IOException {
        log.info("Incoming Heroku request:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));

        String code = ((String)((Map<String, Object>)request.get("oauth_grant")).get("code"));
        Map<String, Object> tokenResponse = herokuCommunicatorService.exchangeCodeForTokens(code);

        log.info("token response:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tokenResponse));

        return Map.of(
            "id", request.get("uuid"),
            "message", "Your add-on is has been created and is available.",
            "config", Map.of("VAR1", "VAL1")
        );
    }

    @PutMapping(RESOURCES_URI + "/{resource_uuid}")
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody Map<String, String> planChange(
        @PathVariable("resource_uuid") String resourceUuid, @RequestBody Map<String, String> planRequest
    ) {
        return Map.of("message", "plan change not supported");
    }

    @DeleteMapping(RESOURCES_URI + "/{resource_uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deprovision(@PathVariable("resource_uuid") String resourceUuid) {
        log.error("Delete request received for heroku resource: {}. Delete not supported yet.", resourceUuid);
    }
}