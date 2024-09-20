package com.example.blog.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article_search_view")
public class ArticleSearchView {

    @Id
    private Long article_id;
    private Long author_id;
    private String title;
    private String contentTEXT;
    private Integer tag_id; // 使用包裝類型 Integer 來處理可能為 NULL 的值
    private String username;
    @Column(name = "last_edited_at") // 確保這裡與資料庫欄位匹配
    private LocalDateTime lastEditedAt; 
    
    public Long getArticle_id() {
        return article_id;
    }
    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }
    public Long getAuthor_id() {
        return author_id;
    }
    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContentTEXT() {
        return contentTEXT;
    }
    public void setContentTEXT(String contentTEXT) {
        this.contentTEXT = contentTEXT;
    }
    public Integer getTag_id() {
        return tag_id;
    }
    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getLastEditedAt() {
        return lastEditedAt;
    }
    public void setLastEditedAt(LocalDateTime lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
    
}