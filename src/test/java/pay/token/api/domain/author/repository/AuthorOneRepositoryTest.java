package pay.token.api.domain.author.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pay.token.jooq.schema.public_.tables.records.AuthorRecord;

import static pay.token.jooq.schema.public_.Tables.AUTHOR;

/**
 * Author 조회 저장소
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Repository
public class AuthorOneRepositoryTest {

    private final DSLContext dsl;

    public AuthorRecord findOne() {
        return dsl.selectFrom(AUTHOR).orderBy(AUTHOR.ID.asc()).limit(1).fetchOne();
    }


}
