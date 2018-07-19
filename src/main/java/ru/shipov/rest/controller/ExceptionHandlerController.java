package ru.shipov.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.shipov.rest.exceptions.NotFoundException;
import ru.shipov.rest.exceptions.NotValidArgumentException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find")
    @ExceptionHandler(NotFoundException.class)
    public void notFound(Exception e) {
        logger.error(e.toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Argument is not valid")
    @ExceptionHandler(NotValidArgumentException.class)
    public void bedRequest(Exception e) {
        logger.error(e.toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Uncorrected request type")
    @ExceptionHandler(NumberFormatException.class)
    public void bedRequest2(Exception e) {
        logger.error(e.toString());
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Database Error")
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public void databaseError(Exception e) {
        logger.error(e.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}