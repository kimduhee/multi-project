package com.framework.admin.repository;

import com.framework.admin.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {
    public UserInfoEntity findByUserEmail(String userEmail);
    public UserInfoEntity findByUserNo(int userNo);
}
