package com.ezypay.backendapi.errorhandling;

import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	   
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processValidationError(Exception ex, final WebRequest request) {
        String result = ex.getMessage();
        Date today = new Date();
        String jsonString = new JSONObject()
				.put("timestamp",today)
				.put("status",500)
				.put("error","Internal Server Error")
				.put("path","/subscription/create")
				.put("message", result)
				.toString();
        return jsonString;
    }
}