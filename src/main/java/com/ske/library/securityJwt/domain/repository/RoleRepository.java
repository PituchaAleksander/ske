package com.ske.library.securityJwt.domain.repository;

import java.util.Optional;

import com.ske.library.securityJwt.domain.models.ERole;
import com.ske.library.securityJwt.domain.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);


}
