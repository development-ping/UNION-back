package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 조회 (프로젝트 초대 기능 및 로그인 기능에서 사용)
    Optional<User> findByToken(String email);
    Optional<User> findByNickname(String email);
    Optional<User> findByEmail(String email);
}
