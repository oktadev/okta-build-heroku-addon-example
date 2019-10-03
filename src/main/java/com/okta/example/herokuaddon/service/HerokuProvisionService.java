package com.okta.example.herokuaddon.service;

import com.okta.example.herokuaddon.model.HerokuProvisionRequest;

import java.io.IOException;

public interface HerokuProvisionService {

    void provision(HerokuProvisionRequest request) throws IOException, InterruptedException;
}
