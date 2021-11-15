package com.pj.common.repository;

import org.springframework.data.repository.CrudRepository;
import com.pj.common.models.Users;

public interface UsersRepository extends CrudRepository<Users, String>{
}
