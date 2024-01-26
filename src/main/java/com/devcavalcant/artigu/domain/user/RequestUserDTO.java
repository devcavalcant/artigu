package com.devcavalcant.artigu.domain.user;

public record RequestUserDTO(
        String nickname,
        String email,
        String password,
        UserRole role
) {
}
