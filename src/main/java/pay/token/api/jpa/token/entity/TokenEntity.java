package pay.token.api.jpa.token.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pay.token.api.jpa.token.enums.TokenStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Token Entity
 *
 * @author Jinhyang
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "token")
public class TokenEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", insertable = false, updatable = false)
    private Long tokenId;

    /**
     * 등록 카드 ID
     */
    @Column(name = "card_ref_id", updatable = false, nullable = false)
    private long cardRefId;

    /**
     * 토큰 값
     */
    @Column(name = "token_text", updatable = false, nullable = false)
    private String text;

    /**
     * 토큰 만료시간
     */
    @Column(name = "token_expire", updatable = false, nullable = false)
    private long expire;

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
     * 상태 이력 목록
     */
    @OneToMany(mappedBy = "token", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<TokenStatusEntity> statuses = new ArrayList<>();


}
