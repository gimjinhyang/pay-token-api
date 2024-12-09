package pay.token.api.jpa.token.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pay.token.api.jpa.token.enums.TokenStatus;

/**
 * Token Status Entity
 *
 * @author Jinhyang
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "token_status")
public class TokenStatusEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_status_id", insertable = false, updatable = false)
    private Long tokenStatusId;

    /**
     * 토큰 상태
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "token_status_code", updatable = false, nullable = false)
    private TokenStatus status;

    /**
     * 등록시간
     */
    @Column(name = "created_time", updatable = false, nullable = false)
    private long createdTime;


    // //////////////////////////////////////////////////////////////
    //
    // Relationships
    //
    // //////////////////////////////////////////////////////////////

    /**
     * 토큰 정보
     */
    @ManyToOne
    @JoinColumn(name = "token_id", nullable = false)
    private TokenEntity token;


}
