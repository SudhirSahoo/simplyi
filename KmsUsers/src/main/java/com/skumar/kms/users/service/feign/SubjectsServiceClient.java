package com.skumar.kms.users.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skumar.kms.users.model.SubjectResponseModel;

@FeignClient(name="kms-subjects")
public interface SubjectsServiceClient {
	
	@GetMapping("/kms/subjects/by-user/{userId}")
	public List<SubjectResponseModel> getSubjectsByUser(@PathVariable String userId);
}
