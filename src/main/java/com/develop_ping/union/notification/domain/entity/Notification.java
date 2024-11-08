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
    private NotiType type;
    @Column(nullable = false)
    private Long creatorTypeId;
    @Column(nullable = false)
    private Long attendeeTypeId;
    @Column(nullable = false)
    private Boolean isRead;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    public static Notification of(NotiType type, Long creatorTypeId, Long attendeeTypeId, Boolean isRead, User creator, User attendee){
        return Notification.builder()
                .type(type)
                .creatorTypeId(creatorTypeId)
                .attendeeTypeId(attendeeTypeId)
                .isRead(isRead)
                .creator(creator)
                .attendee(attendee)
                .build();
    }
}
