package com.ifennanwafor.week8task.repository;

import com.ifennanwafor.week8task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where user_name=?1 and password=?2", nativeQuery = true)
    User findByUserNameAndPassword(String userName, String password);

    @Query(value = "select * from users where email =?1", nativeQuery = true)

    User findByEmail(String email);

    @Query(value = "select * from users where user_name=?1", nativeQuery = true)

    User findByUserName(String userName);

    @Query(value = "select  * from users where password =?1", nativeQuery = true)

    User findByPassword(String password);
}