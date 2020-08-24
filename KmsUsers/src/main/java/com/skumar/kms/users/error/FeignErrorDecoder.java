package com.skumar.kms.users.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
	
	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			// Do something
			// return new BadRequestException();
			break;
		case 404: {
			if (methodKey.contains("getSubjectsByUser")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Subjects Webservice not found");
			}
			break;
		}
		default:
			return new Exception(response.reason());
		}
		return null;
	}

}
