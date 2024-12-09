package pay.token.api.jpa.token.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    private long createdTime = System.currentTimeMillis();


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


    // //////////////////////////////////////////////////////////////
    //
    // Constructors
    //
    // //////////////////////////////////////////////////////////////

    public TokenEntity(long cardRefId, String text, long expire, TokenStatus status) {
        setCardRefId(cardRefId);
        setText(text);
        setExpire(expire);
        setStatus(status);
    }


    // //////////////////////////////////////////////////////////////
    //
    // Methods
    //
    // //////////////////////////////////////////////////////////////


    public void addStatuses(TokenStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("no status");
        }
        this.statuses.add(new TokenStatusEntity(status, this));
    }

    private void setCardRefId(long cardRefId) {
        if (cardRefId < 0L) {
            throw new IllegalArgumentException("no card ref id");
        }
        this.cardRefId = cardRefId;
    }

    private void setText(String text) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("no text");
        }
        this.text = text;
    }

    private void setExpire(long expire) {
        if (expire < 0L) {
            throw new IllegalArgumentException("no expire");
        }
        this.expire = expire;
    }

    private void setStatus(TokenStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("no status");
        }
        this.status = status;
    }

}
