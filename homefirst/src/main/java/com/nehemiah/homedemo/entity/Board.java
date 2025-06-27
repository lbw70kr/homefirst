package com.nehemiah.homedemo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
    
    @Column(name = "allow_pinned", nullable = false)
    private boolean allowPinned = true;
    
    @Column(name = "allow_comment", nullable = false)
    private boolean allowComment = true;
    
    @Column(name = "allow_attachment", nullable = false)
    private boolean allowAttachment = true;
    
    @Column(name = "allow_comment_attachment", nullable = false)
    private boolean allowCommentAttachment = false;
    
    @Column(name = "allow_anonymous_post", nullable = false)
    private boolean allowAnonymousPost = false;
    
    @Column(name = "board_type", length = 20)
    private String boardType;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @Column(name = "created_by", length = 50)
    private String createdBy;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_by", length = 50)
    private String updatedBy;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Board() {}
    
    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public boolean isAllowPinned() {
        return allowPinned;
    }
    
    public void setAllowPinned(boolean allowPinned) {
        this.allowPinned = allowPinned;
    }
    
    public boolean isAllowComment() {
        return allowComment;
    }
    
    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }
    
    public boolean isAllowAttachment() {
        return allowAttachment;
    }
    
    public void setAllowAttachment(boolean allowAttachment) {
        this.allowAttachment = allowAttachment;
    }
    
    public boolean isAllowCommentAttachment() {
        return allowCommentAttachment;
    }
    
    public void setAllowCommentAttachment(boolean allowCommentAttachment) {
        this.allowCommentAttachment = allowCommentAttachment;
    }
    
    public boolean isAllowAnonymousPost() {
        return allowAnonymousPost;
    }
    
    public void setAllowAnonymousPost(boolean allowAnonymousPost) {
        this.allowAnonymousPost = allowAnonymousPost;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 