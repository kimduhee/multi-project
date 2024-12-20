package com.framework.common.repository;

import com.framework.common.entity.UserInfoTokenJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.framework.common.repository
 * fileName       : UserInfoTokenRepository
 * author         : NAMANOK
 * date           : 2024-12-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-20        NAMANOK       최초 생성
 */
@Repository
public interface UserInfoTokenRepository extends JpaRepository<UserInfoTokenJpa, Integer> {
    public UserInfoTokenJpa findByUserId(int userId);
}
