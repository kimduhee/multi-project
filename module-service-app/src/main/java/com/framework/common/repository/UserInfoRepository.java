package com.framework.common.repository;

import com.framework.common.entity.UserInfoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoJpa, Integer> {
    public UserInfoJpa findByUserEmail(String userEmail);
    public UserInfoJpa findByUserId(int userId);
}
