package ru.shipov.rest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.entity.Contact;
import ru.shipov.rest.exceptions.NotValidArgumentException;
import ru.shipov.rest.repository.ApplicationRepository;

import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class, secure = false)
public class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationRepository applicationRepository;

    private Contact contact = new Contact(1);
    private Application application = new Application(1, contact, new Date(), "product", contact.getId());

    @Test
    public void goodTest() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(1)).thenReturn(application);

        MvcResult mvcResult = mockMvc.perform(get("/json/1")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    public void notFound() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(1)).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(get("/json/1")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 404);
    }

    @Test
    public void bedRequest() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(1)).thenThrow(new NotValidArgumentException("1"));

        MvcResult mvcResult = mockMvc.perform(get("/json/1")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 400);
    }

    @Test
    public void bedRequest2() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(1)).thenThrow(new NumberFormatException("1"));

        MvcResult mvcResult = mockMvc.perform(get("/json/1")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 400);
    }

    @Test
    public void databaseError() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(1)).thenThrow(new RecoverableDataAccessException("Data base error"));

        MvcResult mvcResult = mockMvc.perform(get("/json/1")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 503);
    }

    @Test
    public void defaultErrorHandler() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/error")).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 500);
    }
}