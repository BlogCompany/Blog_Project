package com.example.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountVo, String> {
    // 根據用戶名查詢帳戶
    Optional<AccountVo> findByUsername(String username);
    
    // 根據電子郵件查詢帳戶
    Optional<AccountVo> findByEmail(String email);

    // 根據驗證 token 查詢帳戶
    Optional<AccountVo> findByVerificationToken(String verificationToken);
    
    
}

