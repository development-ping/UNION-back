package com.develop_ping.union.gathering.presentation;

import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.service.GatheringService;
import com.develop_ping.union.gathering.presentation.dto.request.GatheringRequest;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gatherings")
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping
    public ResponseEntity<Slice<GatheringListResponse>> getGatheringList(
        @RequestParam(value = "sortType", defaultValue = "LATEST") SortType sortType,
        @RequestParam(value = "latitude", defaultValue = "37.556016") Double latitude,
        @RequestParam(value = "longitude", defaultValue = "126.972355") Double longitude,
        @PageableDefault(size = 3) Pageable pageable
    ) {
        log.info("모임 리스트 조회 요청 - sortType: {}, latitude: {}, longitude: {}, pageable: {}", sortType, latitude, longitude, pageable);

        Slice<GatheringListInfo> gatheringList = gatheringService.getGatheringList(
            GatheringListCommand.of(sortType, latitude, longitude, pageable)
        );
        Slice<GatheringListResponse> response = gatheringList.map(GatheringListResponse::from);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Long> createGathering(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 생성 요청 - userId: {}, request: {}", user.getId(), request);

        GatheringInfo gathering = gatheringService.createGathering(request.toCommand(), user);
        return ResponseEntity.ok(gathering.getId());
    }

    @GetMapping("/{gatheringId}")
    public ResponseEntity<GatheringDetailResponse> getGatheringDetail(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 상세 조회 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        GatheringDetailInfo gatheringDetailInfo = gatheringService.getGatheringDetail(gatheringId, user);
        GatheringDetailResponse response = GatheringDetailResponse.from(gatheringDetailInfo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gatheringId}")
    public ResponseEntity<Void> updateGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 수정 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.updateGathering(gatheringId, request.toCommand(), user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}")
    public ResponseEntity<Void> deleteGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 삭제 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.deleteGathering(gatheringId, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gatheringId}/participants")
    public ResponseEntity<Void> joinGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 가입 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.joinGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}/exit")
    public ResponseEntity<Void> exitGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 나가기 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.exitGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<Slice<GatheringListResponse>> getMyGatheringList(
        @AuthenticationPrincipal User user,
        @PageableDefault(size = 5) Pageable pageable
    ) {
        log.info("내가 생성한 모임 리스트 조회 요청 - userId: {}, pageable: {}", user.getId(), pageable);

        Slice<GatheringListInfo> gatheringList = gatheringService.getMyGatheringList(user, pageable);
        Slice<GatheringListResponse> response = gatheringList.map(GatheringListResponse::from);

        return ResponseEntity.ok(response);
    }
}
