package com.example.callibritydaniel.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private StudentController mockController;

    @Test
    public void testGetStudent() {
        StudentController c = new StudentController();
        assertEquals("Hello Student", c.getStudent());
    }

    @Test
    public void testGetStudentAPIMapping() {
        verifyMapping("/api/student/get");
    }

    private void verifyMapping(String mapping) {
        restTemplate.getForObject("http://localhost:"+port+mapping, String.class);
        verify(mockController).getStudent();
    }
    
}
