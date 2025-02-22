package com.javaprojects.services;

import com.javaprojects.dto.CommentDto;
import com.javaprojects.dto.FeedPostDto;
import com.javaprojects.dto.PostDto;

import java.util.List;
import java.util.Set;

public interface PostService {
    List<FeedPostDto> findAllPosts(int from, int number);

    List<FeedPostDto> findPostsByTagName(String tagName, int from, int number);

    PostDto findPostById(long id);

    void deletePost(Long postId);

    void createPost(PostDto post);

    void updatePost(PostDto post);


    void addComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    List<CommentDto> findCommentsByPost(long postId);

    void addLike(long postId);

    long getLikes(long postId);

    Set<String> findTagsByPostId(Long id);

    Set<String> findAllTags();

    long getTotalPostsCount();

    void deleteComment(Long commentId);
}
