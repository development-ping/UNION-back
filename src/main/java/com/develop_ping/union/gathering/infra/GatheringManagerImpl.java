package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GatheringManagerImpl implements GatheringManager {

    private final GatheringRepository gatheringRepository;

    @Override
    @Transactional
    public GatheringInfo createGathering(Gathering gathering) {
        log.info("모임 ManagerImpl 클래스 : {}", gathering);

        Gathering savedGathering = gatheringRepository.save(gathering);

        // TODO: party 테이블의 추가
        // partyRepository.save(Party.builder().gathering(save).build());
        return GatheringInfo.of(savedGathering);
    }

    @Override
    public GatheringInfo getGatheringDetail(Long gatheringId) {
        log.info("모임 상세 조회 ManagerImpl 클래스 : {}", gatheringId);

        return GatheringInfo.of(gatheringRepository.findById(gatheringId)
                                                   .orElseThrow(() -> new GatheringNotFoundException(gatheringId)));
    }
}
