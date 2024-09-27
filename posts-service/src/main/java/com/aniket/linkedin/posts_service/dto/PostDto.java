package com.aniket.linkedin.posts_service.dto;

import lombok.Data;

@Data
public class PostDto {

    private Long id;
    private String content;
    private Long userId;
    private String createdAt;
}
