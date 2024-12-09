package pay.token.api.domain.author.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pay.token.api.jpa.author.model.AuthorEntity;
import pay.token.api.jpa.author.repository.AuthorRepository;

import java.util.List;

@Slf4j
@SpringBootTest
class AuthorTests {

    private final AuthorRepository authorRepository;

    @Autowired
    AuthorTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void getList() {
        final List<AuthorEntity> list = authorRepository.findAll();
        log.info("list size: {}", list.size());
    }

}
