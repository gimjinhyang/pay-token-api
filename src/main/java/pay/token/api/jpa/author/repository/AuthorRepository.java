package pay.token.api.jpa.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pay.token.api.jpa.author.model.AuthorEntity;

/**
 * Author JPA 저장소
 *
 * @author Jinhyang
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
