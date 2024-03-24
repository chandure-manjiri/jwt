package com.jwt.jwt.Repository;

import com.jwt.jwt.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Integer> {
     UserData findUserByUsername(String username);
}
