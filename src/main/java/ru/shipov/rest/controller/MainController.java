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

    @RequestMapping(path = "/json/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Return application by contact id, in JSON")
    public Application applicationJson(@PathVariable Integer contactId) {
        checkArgument(contactId, "contactId");
        Application result = applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contactId);
        checkResult(result, contactId);
        return result;
    }

    @RequestMapping(path = "/xml/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    @ApiOperation(value = "Return application by contact id, in XML")
    public Application applicationXml(@PathVariable Integer contactId) {
        checkArgument(contactId, "contactId");
        Application result = applicationRepository.findFirstByContactIdOrderByDtCreatedDesc(contactId);
        checkResult(result, contactId);
        return result;
    }

    private void checkArgument(Integer contactId, String argument) {
        if (contactId == null)
            throw new NotValidArgumentException(argument);
    }

    private void checkResult(Application application, Object id) {
        if (application == null)
            throw new NotFoundException(id.toString());
    }
}
