package com.nehemiah.homedemo.repository;

import com.nehemiah.homedemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//AI코딩시 namespace를 혼동 할경우가 있다.
//import org.springframework.data.repository.param.Param;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // 활성화된 카테고리 목록 조회
    List<Category> findByIsActiveTrueOrderByDisplayOrderAsc();
    
    // 상위 카테고리가 없는 루트 카테고리들 조회
    List<Category> findByParentCategoryIsNullAndIsActiveTrueOrderByDisplayOrderAsc();
    
    // 특정 상위 카테고리의 하위 카테고리들 조회
    List<Category> findByParentCategoryIdAndIsActiveTrueOrderByDisplayOrderAsc(Long parentCategoryId);
    
    // 카테고리 이름으로 조회
    Optional<Category> findByName(String name);
    
    // 상위 카테고리 ID로 조회
    List<Category> findByParentCategoryId(Long parentCategoryId);
    
    // 트리 구조의 모든 하위 카테고리 조회 (재귀적)
    @Query("SELECT c FROM Category c WHERE c.parentCategory.id = :parentId OR c.parentCategory.parentCategory.id = :parentId")
    List<Category> findAllChildCategories(@Param("parentId") Long parentId);
} 