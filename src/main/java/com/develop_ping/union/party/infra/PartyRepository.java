package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByGatheringIdAndUserId(Long id, Long userId);

    // 파티 참여자 정보 조회 API
    Optional<Party> findByGatheringIdAndRole(Long gatheringId, PartyRole partyRole);

    Optional<Party> findByGatheringId(Long gatheringId);
}