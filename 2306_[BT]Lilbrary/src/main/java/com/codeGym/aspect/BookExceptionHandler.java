package com.codeGym.aspect;

import com.codeGym.exception.NotFoundException;
import com.codeGym.exception.ServerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showError404() {
        return new ModelAndView("/error-404");
    }

    @ExceptionHandler(ServerException.class)
    public ModelAndView showError500() {
        return new ModelAndView("/error-500");
    }
}
