package com.example.callibritydaniel.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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
    private StudentController studentControllerMock;

    @Test
    public void testGetStudent() {
        StudentController c = new StudentController();
        assertEquals("Hello Student", c.getStudent());
    }

    @Test
    public void testGetStudentAPIMapping() {
        String uri = "/api/student/get";
        restTemplate.getForObject("http://localhost:"+port+uri, String.class);
        verify(studentControllerMock).getStudent();
        verifyNoMoreInteractions(studentControllerMock);
    }
    
}
