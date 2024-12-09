package pay.token.api.jpa.author.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author Entity
 *
 * @author Jinhyang
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "author")
public class AuthorEntityTest {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 브랜드 영문명
     */
    @Column(name = "last_name")
    private String lastName;

}
