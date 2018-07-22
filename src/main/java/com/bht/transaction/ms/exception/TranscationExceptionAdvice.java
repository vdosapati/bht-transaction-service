package com.bht.transaction.ms.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bht.transaction.ms.vo.Response;
import com.bht.vo.ErrorInfo;
import com.bht.vo.ServiceResponse;

@ControllerAdvice
public class TranscationExceptionAdvice {
	
	@ExceptionHandler(TranscationException.class)
	public @ResponseBody ResponseEntity<ServiceResponse>  handleDemoException(HttpServletRequest request, TranscationException ex){
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm:ss");
		//String correlationId = reqContext.get
		ErrorInfo errorInfo = ErrorInfo.builder().timeStamp(LocalDateTime.now().format(formatter))
				                           .correlationId("")
				                           .errorCode(ex.getErrorcode())
				                           .errorMessage(ex.getErrorMessage())
				                           .externalErrors(new ArrayList<>())
				                           .jn(System.getProperty("server.id"))
				                           .build();
		ServiceResponse response = new ServiceResponse<>();
		response.setErrorInfo(errorInfo);
		return new ResponseEntity(response,HttpStatus.OK);
	}

}
