package com.jet.TestJet.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jet.TestJet.InfrastructureTestConfig;
import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.repositories.DealerRepository;
import com.jet.TestJet.services.DealerService;
import com.jet.TestJet.wiremock.WireMockConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({SpringExtension.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {InfrastructureTestConfig.class, WireMockConfig.class})
@DirtiesContext
public class DealerRestControllerTest {
    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private DealerRepository dealerRepository;

    @BeforeEach
    void setUp() {
        wireMockServer.start();
        WireMock.configureFor("localhost", 6060);
    }

    @AfterEach
    void deleteAll() {
        dealerRepository.deleteAll();
        wireMockServer.resetAll();
    }

    @Test
    void given_dealerDto_when_request_post_DealerDto_is_created_and_return_DrealerDto() throws IOException {

        //GIVEN
        DealerDto toSaveDealerDto = DealerDto.builder().name("name").tierLimit(10).build();
        DealerDto dealerDtoSaved = dealerService.createDealer(toSaveDealerDto);

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/dealers/")).
                withRequestBody(WireMock.containing(getJsonAsString(toSaveDealerDto)))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(getJsonAsString(dealerDtoSaved))));

        HttpPost request = new HttpPost("http://localhost:6060/api/dealers/");
        StringEntity entity = new StringEntity(getJsonAsString(toSaveDealerDto));
        request.addHeader("Content-Type", "application/json");
        request.setEntity(entity);

        //WHEN
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        //THEN
        String responseString = convertResponseToString(httpResponse);
        WireMock.verify(1, WireMock.postRequestedFor(WireMock.urlEqualTo("/api/dealers/")));
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(responseString, getJsonAsString(dealerDtoSaved));
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

    private String getJsonAsString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @AfterAll
    void stopServer() {
        wireMockServer.stop();
    }
}
