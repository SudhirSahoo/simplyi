package com.skumar.kms.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.skumar.kms.users.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}