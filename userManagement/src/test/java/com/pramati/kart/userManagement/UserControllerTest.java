//package com.pramati.kart.userManagement;
//import com.pramati.kart.userManagement.entity.User;
//import com.pramati.kart.userManagement.utility.Response;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerTest {
//
//    @Autowired
//    private TestRestTemplate template;
//
//
//    @Test
//    public void createUser1() throws Exception {
//        User user=new User();
//        user.setName("Priyank");
//        user.setEmail("priyanktripathi007@gmail.com");
//        user.setPhone("iuywieryuir");
//        user.setCreatedTime(new Date());
//        user.setLastModifiedTime(new Date());
//        ResponseEntity<Response> response = template.postForEntity("/user", user, Response.class);
//        Assert.assertEquals("User created successfully", response.getBody().getMessage());
//    }
//}