package com.skumar.kms.subjects.service;

import java.util.List;

import com.skumar.kms.subjects.entity.SubjectEntity;

public interface SubjectService {
	List<SubjectEntity> getSubjects(String userId);
}
