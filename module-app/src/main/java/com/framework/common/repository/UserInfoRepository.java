package com.framework.common.repository;

import com.framework.common.entity.UserInfoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoJpa, Long> {
    public UserInfoJpa findByEmail(String userEmail);
    public UserInfoJpa findById(long userId);
}
