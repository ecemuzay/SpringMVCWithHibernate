package com.spring.template.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(HttpServletRequest req,CustomException ex)  throws Exception{

        logger.error("[URL] : {}", req.getRequestURL(), ex);

        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getErrMsg());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest req, Exception ex) throws Exception{

        logger.error("[URL] : {}", req.getRequestURL(), ex);

        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;


    }

}

