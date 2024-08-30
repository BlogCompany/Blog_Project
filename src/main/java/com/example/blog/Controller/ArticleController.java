package com.example.blog.Controller;

import com.example.blog.Model.ArticleVo;
import com.example.blog.Model.TagVo;
import com.example.blog.Repository.ArticleRepository;
import com.example.blog.Service.ArticleService;
import java.lang.String;

import com.example.blog.Service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/search")
    public ResponseEntity<Page<ArticleVo>> searchArticles(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String authorKeyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ArticleVo> articles = articleService.searchArticles(keyword, authorKeyword, page, size);
        return ResponseEntity.ok(articles);
    }
    


    // 直接用get方法 取得全部文章的列表

    @GetMapping
    public Page<ArticleVo> getArticleVo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "articleId") String sortBy) {
        
        // 使用 Sort 進行排序
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return articleRepository.findAll(pageable);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleVo> getArticleById(@PathVariable Long articleId) {
        return articleService.getArticleById(articleId)
                .map(article -> ResponseEntity.ok(article))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ArticleVo createArticle(@RequestBody ArticleVo article) {
        return articleService.createOrUpdateArticle(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleVo> updateArticle(@PathVariable Long articleId, @RequestBody ArticleVo article) {
        return articleService.getArticleById(articleId)
                .map(existingArticle -> {
                    existingArticle.setAuthorId(article.getAuthorId());
                    existingArticle.setTitle(article.getTitle());
                    existingArticle.setContentTEXT(article.getContentTEXT());
                    existingArticle.setPublishedAt(article.getPublishedAt());
                    existingArticle.setLastEditedAt(article.getLastEditedAt());
                    ArticleVo updatedArticle = articleService.createOrUpdateArticle(existingArticle);
                    return ResponseEntity.ok(updatedArticle);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        return articleService.getArticleById(articleId)
                .map(article -> {
                    articleService.deleteArticle(articleId);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Autowired
    private TagService tagService;

    @GetMapping("/articles/{articleId}/tags")
    public List<TagVo> getTagsByArticleId(@PathVariable Long articleId) {
        return tagService.findTagsByArticleId(articleId);
    }
}
