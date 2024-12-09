package pay.token.api.domain.author.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pay.token.api.domain.author.repository.AuthorOneRepositoryTest;
import pay.token.api.jpa.author.entity.AuthorEntityTest;
import pay.token.api.jpa.author.repository.AuthorRepositoryTest;
import pay.token.jooq.schema.public_.tables.records.AuthorRecord;

import java.util.List;

@Slf4j
@SpringBootTest
class AuthorTests {

    private final AuthorRepositoryTest authorRepositoryTest;
    private final AuthorOneRepositoryTest authorOneRepositoryTest;

    @Autowired
    AuthorTests(AuthorRepositoryTest authorRepositoryTest, AuthorOneRepositoryTest authorOneRepositoryTest) {
        this.authorRepositoryTest = authorRepositoryTest;
        this.authorOneRepositoryTest = authorOneRepositoryTest;
    }


    //    @Test
    void getList() {
        final List<AuthorEntityTest> list = authorRepositoryTest.findAll();
        log.info("list size: {}", list.size());
    }


    @Test
    void getOne() {

        final AuthorRecord one = authorOneRepositoryTest.findOne();
        log.info("one: {}", one == null ? null : one.getId());

    }


}
