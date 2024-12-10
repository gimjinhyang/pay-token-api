package pay.token.api.domain.author.application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import pay.token.api.domain.author.repository.AuthorOneRepositoryTest;
import pay.token.api.jpa.author.entity.AuthorEntityTest;
import pay.token.api.jpa.author.repository.AuthorRepositoryTest;
import pay.token.jooq.schema.public_.tables.records.AuthorRecord;

@Slf4j
@SpringBootTest
class AuthorTests {

  @Autowired
  private AuthorRepositoryTest authorRepositoryTest;

  @Autowired
  private AuthorOneRepositoryTest authorOneRepositoryTest;

  @Disabled
  @Test
  void getList() {
    final List<AuthorEntityTest> list = authorRepositoryTest.findAll();
    log.info("list size: {}", list.size());
  }

  @Disabled
  @Test
  void getOne() {

    final AuthorRecord one = authorOneRepositoryTest.findOne();
    log.info("one: {}", one == null ? null : one.getId());

  }


}
