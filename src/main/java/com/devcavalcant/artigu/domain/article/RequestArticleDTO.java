package com.devcavalcant.artigu.domain.article;

import java.util.UUID;

public record RequestArticleDTO(
        String title,
        String text,
        UUID user_id
) {
}
