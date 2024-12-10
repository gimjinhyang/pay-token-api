package pay.token.api.jpa.token.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
  private LocalDateTime createdTime = LocalDateTime.now();


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


  // //////////////////////////////////////////////////////////////
  //
  // Constructors
  //
  // //////////////////////////////////////////////////////////////

  public TokenStatusEntity(TokenStatus status, TokenEntity token) {
    setStatus(status);
    setToken(token);
  }


  // //////////////////////////////////////////////////////////////
  //
  // Methods
  //
  // //////////////////////////////////////////////////////////////

  private void setStatus(TokenStatus status) {
    if (status == null) {
      throw new IllegalArgumentException("no status");
    }
    this.status = status;
  }

  private void setToken(TokenEntity token) {
    if (token == null) {
      throw new IllegalArgumentException("no token");
    }
    this.token = token;
  }

}
