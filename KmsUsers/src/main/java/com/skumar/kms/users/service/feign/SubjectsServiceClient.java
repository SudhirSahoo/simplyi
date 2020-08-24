package com.skumar.kms.users.service.feign;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skumar.kms.users.model.SubjectResponseModel;

@FeignClient(name="kms-subjects", fallback = SubjectFallback.class)
public interface SubjectsServiceClient {
	
	//@GetMapping("/kms/subjects/by-user/{userId}")
	@GetMapping("/kms/subjects/by-userx/{userId}")
	public List<SubjectResponseModel> getSubjectsByUser(@PathVariable String userId);
	
}

@Component
class SubjectFallback implements SubjectsServiceClient {

	@Override
	public List<SubjectResponseModel> getSubjectsByUser(String userId) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
