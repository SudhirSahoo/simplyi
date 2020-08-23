package com.skumar.kms.subjects.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.skumar.kms.subjects.entity.SubjectEntity;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Override
    public List<SubjectEntity> getSubjects(String userId) {
        List<SubjectEntity> returnValue = new ArrayList<>();
        
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setUserId(userId);
        subjectEntity.setSubjectId("subjects1Id");
        subjectEntity.setDescription("subjects 1 description");
        subjectEntity.setId(1L);
        subjectEntity.setName("subjects 1 name");
        
        SubjectEntity subjectEntity2 = new SubjectEntity();
        subjectEntity2.setUserId(userId);
        subjectEntity2.setSubjectId("subjects2Id");
        subjectEntity2.setDescription("subjects 2 description");
        subjectEntity2.setId(2L);
        subjectEntity2.setName("subjects 2 name");
        
        returnValue.add(subjectEntity);
        returnValue.add(subjectEntity2);
        
        return returnValue;
    }
}
