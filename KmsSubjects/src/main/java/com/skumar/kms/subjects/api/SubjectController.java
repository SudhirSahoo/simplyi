package com.skumar.kms.subjects.api;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import java.lang.reflect.Type;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skumar.kms.subjects.entity.SubjectEntity;
import com.skumar.kms.subjects.model.SubjectResponseModel;
import com.skumar.kms.subjects.service.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/kms/user/{id}/subjects")
public class SubjectController {

	   @Autowired
	    SubjectService subjectService;
	    Logger logger = LoggerFactory.getLogger(this.getClass());
	    @GetMapping( 
	            produces = { 
	                MediaType.APPLICATION_JSON_VALUE,
	                MediaType.APPLICATION_XML_VALUE,
	            })
	    public List<SubjectResponseModel> userAlbums(@PathVariable String id) {

	        List<SubjectResponseModel> returnValue = new ArrayList<>();
	        
	        List<SubjectEntity> subjectEntities = subjectService.getSubjects(id);
	        
	        if(subjectEntities == null || subjectEntities.isEmpty())
	        {
	            return returnValue;
	        }
	        
	        Type listType = new TypeToken<List<SubjectResponseModel>>(){}.getType();
	 
	        returnValue = new ModelMapper().map(subjectEntities, listType);
	        logger.info("Returning " + returnValue.size() + " subject");
	        return returnValue;
	    }
	    
}
