package com.skumar.kms.users.api;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skumar.kms.users.dto.UserDto;
import com.skumar.kms.users.model.CreateUserRequestModel;
import com.skumar.kms.users.model.CreateUserResponseModel;
import com.skumar.kms.users.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on Port: " + env.getProperty("local.server.port") + ", Token= " + env.getProperty("token.secret") ;
	}
	
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
				 produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	//public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails)
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails)
	{
		
		 ModelMapper modelMapper = new ModelMapper();
		 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 
		 
		 UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		 
		 UserDto createdUser = usersService.createUser(userDto);
		 
		 CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
