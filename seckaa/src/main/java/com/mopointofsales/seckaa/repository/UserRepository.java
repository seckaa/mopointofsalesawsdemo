package com.mopointofsales.seckaa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mopointofsales.seckaa.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}