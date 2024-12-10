package pay.token.api.jpa.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pay.token.api.jpa.card.entity.CardEntity;

/**
 * Card JPA 저장소
 *
 * @author Jinhyang
 */
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}