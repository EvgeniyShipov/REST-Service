package ru.shipov.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.exceptions.NotFoundException;
import ru.shipov.rest.exceptions.NotValidArgumentException;
import ru.shipov.rest.repository.ApplicationRepository;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(path = "/start/{contactId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Application getApplication(@PathVariable Long contactId) {
        checkArgument(contactId, "contactId");
        Application result = applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contactId);
        checkResult(result, contactId);
        return result;
    }

    private void checkArgument(Long contactId, String argument) {
        if (contactId == null)
            throw new NotValidArgumentException(argument);
    }

    private void checkResult(Application application, Long id) {
        if (application == null)
            throw new NotFoundException(id.toString());
    }
}
