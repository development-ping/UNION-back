package com.develop_ping.union.user.domain;

import com.develop_ping.union.user.domain.entity.User;

public interface UserManager {
    User findById (Long userId);

    User findByEmail(String email);

    User save(User user);

    void delete(User user);
}
