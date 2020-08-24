package com.skumar.kms.users.service.feign;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skumar.kms.users.model.SubjectResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name="kms-subjects", fallbackFactory = SubjectFallbackFatcory.class)
public interface SubjectsServiceClient {
	
	//@GetMapping("/kms/subjects/by-user/{userId}")
	@GetMapping("/kms/subjects/by-user/{userId}")
	public List<SubjectResponseModel> getSubjectsByUser(@PathVariable String userId);
	
}

@Component
class SubjectFallbackFatcory implements FallbackFactory<SubjectsServiceClient> {

	@Override
	public SubjectsServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new SubjectsServiceClientFallback(cause);
	}

}

class SubjectsServiceClientFallback implements SubjectsServiceClient {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public SubjectsServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	
	@Override
	public List<SubjectResponseModel> getSubjectsByUser(String userId) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getSubjectsByUser was called with userId: " + userId + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}
		return new ArrayList<>();
	}
	
}
