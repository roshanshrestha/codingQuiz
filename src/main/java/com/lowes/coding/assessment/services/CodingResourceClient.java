package com.lowes.coding.assessment.services;

import com.lowes.coding.assessment.domain.DataSourceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class CodingResourceClient {
    private static final String ERR_MSG = "Error: Issue hitting downstream api %s. Expected response code = 200 but obtained %s";

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<DataSourceResponse> getQuizAsync(String url) throws Exception {
        ResponseEntity<DataSourceResponse> response =  restTemplate.getForEntity(url, DataSourceResponse.class);
        if(response==null || response.getStatusCode() != HttpStatus.OK){
            throw new Exception(String.format(ERR_MSG, url, response == null ? null:response.getStatusCode()));
        }
        return CompletableFuture.completedFuture((DataSourceResponse)response.getBody());
    }
}
