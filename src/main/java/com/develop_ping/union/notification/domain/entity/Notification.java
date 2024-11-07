package com.develop_ping.union.notification.domain.entity;


import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;

@Entity
@Getter
@Builder
@Table(name = "notifications")
public class Notification extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String srcToken;
    @Column(nullable = false)
    private String dstToken;
    @Column(nullable = false)
    private NotiType type;
    @Column(nullable = false)
    private Long typeId;
    @Column(nullable = false)
    private Boolean isRead;

    public static Notification of(String srcToken, String dstToken, NotiType type, Long typeId, Boolean isRead){
        return Notification.builder()
                .srcToken(srcToken)
                .dstToken(dstToken)
                .type(type)
                .typeId(typeId)
                .isRead(isRead)
                .build();
    }
}
