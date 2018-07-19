package ru.shipov.rest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.entity.Contact;
import ru.shipov.rest.repository.ApplicationRepository;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class, secure = false)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationRepository applicationRepository;

    private Contact contact = new Contact(1);
    private Application application = new Application(1, contact, new Date(), "product", contact.getId());

    @Test
    public void applicationJson() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contact.getId())).thenReturn(application);

        mockMvc.perform(get("/json/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.APPLICATION_ID").value(application.getId().toString()))
                .andExpect(jsonPath("$.PRODUCT_NAME").value(application.getProductName()))
                .andExpect(jsonPath("$.CONTACT_ID").value(is(application.getContactId())));

        verify(applicationRepository, times(1)).findFirstByContactIdOrderByDtCreatedDesc(contact.getId());
        verifyNoMoreInteractions(applicationRepository);
    }

    @Test
    public void applicationXml() throws Exception {
        when(applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contact.getId())).thenReturn(application);

        MvcResult mvcResult = mockMvc.perform(get("/xml/1"))
                .andExpect(content().contentType("application/xml;charset=UTF-8")).andReturn();
        Assert.assertNotNull(mvcResult.getResponse());

        verify(applicationRepository, times(1)).findFirstByContactIdOrderByDtCreatedDesc(contact.getId());
        verifyNoMoreInteractions(applicationRepository);
    }
}