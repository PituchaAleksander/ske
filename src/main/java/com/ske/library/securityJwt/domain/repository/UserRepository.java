package com.ske.library.securityJwt.domain.repository;

import java.util.Optional;

import com.ske.library.securityJwt.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);
  Optional<User> findByUserUUID(String uuid);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);


}
