package com.nehemiah.homedemo.repository;

import com.nehemiah.homedemo.entity.Post;
import com.nehemiah.homedemo.entity.Post.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    // 게시판별 게시글 목록 조회 (공개된 글만)
    Page<Post> findByBoardIdAndStatusAndIsPrivateFalseOrderByPinnedAtDescCreatedAtDesc(
            Long boardId, PostStatus status, Pageable pageable);
    
    // 게시판별 게시글 목록 조회 (모든 글)
    Page<Post> findByBoardIdOrderByPinnedAtDescCreatedAtDesc(Long boardId, Pageable pageable);
    
    // 사용자별 게시글 목록 조회
    Page<Post> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    
    // 제목으로 검색
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% AND p.status = :status")
    Page<Post> findByTitleContainingAndStatus(@Param("keyword") String keyword, 
                                             @Param("status") PostStatus status, 
                                             Pageable pageable);
    
    // 내용으로 검색
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword% AND p.status = :status")
    Page<Post> findByContentContainingAndStatus(@Param("keyword") String keyword, 
                                               @Param("status") PostStatus status, 
                                               Pageable pageable);
    
    // 상단 고정된 게시글 조회
    List<Post> findByBoardIdAndPinnedAtIsNotNullOrderByPinnedAtDesc(Long boardId);
    
    // 조회수 증가
    @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.id = :postId")
    void incrementViewCount(@Param("postId") Long postId);
    
    // 특정 기간 내 게시글 조회
    List<Post> findByBoardIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long boardId, LocalDateTime startDate, LocalDateTime endDate);
    
    // 상태별 게시글 수 조회
    long countByBoardIdAndStatus(Long boardId, PostStatus status);
} 