package pay.token.api.jpa.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.token.api.jpa.token.entity.TokenEntity;

/**
 * Token JPA 저장소
 *
 * @author Jinhyang
 */
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
}