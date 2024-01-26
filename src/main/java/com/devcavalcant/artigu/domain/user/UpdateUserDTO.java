package com.devcavalcant.artigu.domain.user;

public record UpdateUserDTO(
        String nickname,
        String email,
        String password
) {
}
