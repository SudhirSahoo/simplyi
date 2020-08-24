package com.skumar.kms.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skumar.kms.users.dto.UserDto;
import com.skumar.kms.users.entity.UserEntity;
import com.skumar.kms.users.model.SubjectResponseModel;
import com.skumar.kms.users.repository.UsersRepository;
import com.skumar.kms.users.service.feign.SubjectsServiceClient;

import feign.FeignException;


//import feign.FeignException;


@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	//RestTemplate restTemplate;
	SubjectsServiceClient subjectsServiceClient;
	Environment environment;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			//RestTemplate restTemplate,
			SubjectsServiceClient subjectsServiceClient,
			Environment environment) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		//this.restTemplate= restTemplate;
		this.subjectsServiceClient = subjectsServiceClient;
		this.environment= environment;
	}
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		usersRepository.save(userEntity);
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);	
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}
	
	@Override
	public UserDto getUserDetailsByEmail(String email) { 
		UserEntity userEntity = usersRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}
	
	@Override
	public UserDto getUserByUserId(String userId) {
		
        UserEntity userEntity = usersRepository.findByUserId(userId);     
        if(userEntity == null) throw new UsernameNotFoundException("User not found");
        
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        
        
        /*String subjectsUrl = String.format(environment.getProperty("subjects.url"), userId);
        
        ResponseEntity<List<SubjectResponseModel>> subjectListResponse = restTemplate.exchange(subjectsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<SubjectResponseModel>>() {
        });
        List<SubjectResponseModel> subjectsList = subjectListResponse.getBody(); 
        */
        
        logger.info("Before Calling Subjects webservice");
        List<SubjectResponseModel> subjectsList = null;
		//try {
			subjectsList = subjectsServiceClient.getSubjectsByUser(userId);
		//} catch (FeignException e) {
		//	logger.error(e.getLocalizedMessage());
		//}
        
		logger.info("After Calling Subjects webservice");
        userDto.setSubjects(subjectsList);
		return userDto;
	}
}
