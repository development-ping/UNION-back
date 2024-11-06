package com.develop_ping.union.notification.domain.entity;


import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.notification.domain.NotiType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "notifications")
public class Notification extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userToken;
    @Column(nullable = false)
    private NotiType type;
    @Column(nullable = false)
    private Long typeId;
    @Column(nullable = false)
    private Boolean isRead;
}
