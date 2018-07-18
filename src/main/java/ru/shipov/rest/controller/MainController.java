package ru.shipov.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.shipov.rest.entity.Application;
import ru.shipov.rest.exceptions.NotFoundException;
import ru.shipov.rest.exceptions.NotValidArgumentException;
import ru.shipov.rest.repository.ApplicationRepository;

@RestController
@RequestMapping
@Api(value = "Main Controller", description = "controller handle one GET request")
public class MainController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(path = "/start/{contactId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    @ApiOperation(value = "Return application by contact id, in JSON or XML")
    public Application application(@PathVariable Long contactId) {
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
