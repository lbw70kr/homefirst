package com.nehemiah.homedemo.repository;

import com.nehemiah.homedemo.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    
    // 게시판 목록 조회
    List<Board> findAll();
    
    // 활성화된 게시판 목록 조회
    List<Board> findActiveBoards();
    
    // ID로 게시판 조회
    Optional<Board> findById(@Param("id") Long id);
    
    // 이름으로 게시판 조회
    Optional<Board> findByName(@Param("name") String name);
    
    // 게시판 타입으로 조회
    List<Board> findByBoardType(@Param("boardType") String boardType);
    
    // 게시판 저장
    int save(Board board);
    
    // 게시판 수정
    int update(Board board);
    
    // 게시판 삭제
    int deleteById(@Param("id") Long id);
    
    // 게시판 존재 여부 확인
    boolean existsByName(@Param("name") String name);
} 