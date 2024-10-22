package com.develop_ping.union.auth.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "oauth_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String profileImage;

    // UUID 기반의 고유 토큰
    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @Builder
    public OauthUser(String email, String profileImage, String token) {
        this.email = email;
        this.profileImage = profileImage;
        this.token = token;
    }
}
