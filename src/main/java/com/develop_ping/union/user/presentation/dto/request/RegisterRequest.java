package com.develop_ping.union.user.presentation.dto.request;

import com.develop_ping.union.user.domain.dto.SignUpCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    private String oauthUserToken;
    @NotNull
    @Size(max = 30)
    private String nickname;
    @Size(max = 50)
    private String description;
    @NotNull
    @Size(max = 50)
    private String profileImage;
    @NotNull
    @Size(max = 30)
    private String univName;

    public SignUpCommand toCommand () {
        return SignUpCommand.builder()
                .oauthUserToken(oauthUserToken)
                .description(description)
                .profileImage(profileImage)
                .univName(univName)
                .nickname(nickname)
                .build();
    }
}
