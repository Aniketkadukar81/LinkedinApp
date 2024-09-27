package com.aniket.linkedin.posts_service.service;

import com.aniket.linkedin.posts_service.entity.PostLike;
import com.aniket.linkedin.posts_service.exception.BadRequestException;
import com.aniket.linkedin.posts_service.exception.ResourceNotFoundException;
import com.aniket.linkedin.posts_service.repository.PostLikeRepository;
import com.aniket.linkedin.posts_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostsRepository postsRepository;

    public void likePost(Long postId, Long userId) {
        log.info("Attempting to like the post with id: {}",postId);
        boolean exists = postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not Found with id: "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(alreadyLiked) throw  new BadRequestException("Cannot like the same post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
        log.info("Post with id:{} like successfully",postId);
    }

    public void unlikePost(Long postId, Long userId) {
        log.info("Attempting to unlike the post with id: {}",postId);
        boolean exists = postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not Found with id: "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!alreadyLiked) throw  new BadRequestException("Cannot unlike the same post which is not liked");

        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
        log.info("Post with id:{} unlike successfully",postId);
    }
}
