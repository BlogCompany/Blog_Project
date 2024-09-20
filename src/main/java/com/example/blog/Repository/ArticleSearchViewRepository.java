package com.example.blog.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.blog.Model.ArticleSearchView;

@Repository
public interface ArticleSearchViewRepository extends JpaRepository<ArticleSearchView, Long> {
   
    @Query("SELECT a FROM ArticleSearchView a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.username) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY a.lastEditedAt DESC")
    Page<ArticleSearchView> searchArticles(@Param("keyword") String keyword, Pageable pageable);
    

    // 分頁查詢，標題或作者名稱包含關鍵字，按更新時間排序
    Page<ArticleSearchView> findByTitleContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrderByLastEditedAtDesc(String titleKeyword, String usernameKeyword, Pageable pageable);


    // 添加根據標籤 ID 查詢的方法
    @Query("SELECT a FROM ArticleSearchView a WHERE a.tag_id = :tagId ORDER BY a.lastEditedAt DESC")
    Page<ArticleSearchView> findByTagId(@Param("tagId") Long tagId, Pageable pageable);
}