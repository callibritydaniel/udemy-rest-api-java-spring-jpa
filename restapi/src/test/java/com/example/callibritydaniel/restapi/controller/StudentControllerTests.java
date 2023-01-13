package com.example.callibritydaniel.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testGetStudent() {
		StudentController c = new StudentController();
        assertEquals("Hello Student", c.getStudent());
        
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/student/get", String.class)).contains("Hello Student");
	}
    
}
