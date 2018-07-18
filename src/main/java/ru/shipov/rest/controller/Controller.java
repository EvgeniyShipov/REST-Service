package ru.shipov.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.entity.Contact;
import ru.shipov.rest.repository.ApplicationRepository;
import ru.shipov.rest.repository.ContactRepository;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(path = "/application",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Application getApplication(@RequestParam(value = "contactId", required = false) Long contactId) {
        return applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contactId);
    }

    @RequestMapping(path = "/contact",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Contact getContact(@RequestParam(value = "contactId", required = false) Long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }
}
