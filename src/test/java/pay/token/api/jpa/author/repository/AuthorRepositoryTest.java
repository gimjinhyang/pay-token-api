package pay.token.api.jpa.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pay.token.api.jpa.author.model.AuthorEntityTest;

/**
 * Author JPA 저장소
 *
 * @author Jinhyang
 */
public interface AuthorRepositoryTest extends JpaRepository<AuthorEntityTest, Integer> {
}
