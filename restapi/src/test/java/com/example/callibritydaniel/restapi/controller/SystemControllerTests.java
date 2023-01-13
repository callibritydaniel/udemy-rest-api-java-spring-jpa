package com.example.callibritydaniel.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "app.name=Test App Name")
public class SystemControllerTests {

    private final String uri = "/api/system/name";

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SystemController c = new SystemController();

    @Test
    public void testGetAppName() {
        String expect = "Test App Name";
        String actual = c.getAppName();
        assertEquals(expect, actual);
    }

    @Test
    public void testGetAppNameAPIMapping() {
        String expect = "Test App Name";
        String actual = restTemplate.getForObject("http://localhost:"+port+uri, String.class);
        assertEquals(expect, actual);
    }
    
}
