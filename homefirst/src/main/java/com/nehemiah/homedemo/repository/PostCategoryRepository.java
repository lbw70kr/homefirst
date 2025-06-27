package com.nehemiah.homedemo.repository;

import com.nehemiah.homedemo.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    
    // 게시글 ID로 카테고리 연결 조회
    List<PostCategory> findByPostId(Long postId);
    
    // 카테고리 ID로 게시글 연결 조회
    List<PostCategory> findByCategoryId(Long categoryId);
    
    // 게시글 ID로 카테고리 ID 목록 조회
    @Query("SELECT pc.category.id FROM PostCategory pc WHERE pc.post.id = :postId")
    List<Long> findCategoryIdsByPostId(@Param("postId") Long postId);
    
    // 카테고리 ID로 게시글 ID 목록 조회
    @Query("SELECT pc.post.id FROM PostCategory pc WHERE pc.category.id = :categoryId")
    List<Long> findPostIdsByCategoryId(@Param("categoryId") Long categoryId);
    
    // 게시글의 모든 카테고리 연결 삭제
    void deleteByPostId(Long postId);
    
    // 카테고리의 모든 게시글 연결 삭제
    void deleteByCategoryId(Long categoryId);
    
    // 특정 게시글과 카테고리 연결 삭제
    void deleteByPostIdAndCategoryId(Long postId, Long categoryId);
} 