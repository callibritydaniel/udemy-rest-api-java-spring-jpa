package com.example.callibritydaniel.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SystemControllerTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private SystemController systemControllerMock;

    @Test
    public void testGetAppName() {
        when(systemControllerMock.getAppName()).thenCallRealMethod();
        ReflectionTestUtils.setField(systemControllerMock, "appName", "Test App Name");
        String expect = "Test App Name";
        String actual = restTemplate.getForObject("http://localhost:"+port+"/api/system/name", String.class);
        assertEquals(expect, actual);
    }

    @Test
    public void testGetAppNameAPIMapping() {
        String uri = "/api/system/name";
        restTemplate.getForObject("http://localhost:"+port+uri, String.class);
        verify(systemControllerMock).getAppName();
        verifyNoMoreInteractions(systemControllerMock);
    }
    
}
