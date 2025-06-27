package com.nehemiah.homedemo.repository;

import com.nehemiah.homedemo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
    // 활성화된 게시판 목록 조회
    List<Board> findByIsActiveTrueOrderByDisplayOrderAsc();
    
    // 게시판 이름으로 조회
    Optional<Board> findByName(String name);
    
    // 게시판 타입으로 조회
    List<Board> findByBoardType(String boardType);
    
    // 활성화된 게시판 중 특정 타입 조회
    List<Board> findByIsActiveTrueAndBoardTypeOrderByDisplayOrderAsc(String boardType);
} 