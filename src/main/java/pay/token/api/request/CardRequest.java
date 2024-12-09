/*
 * Copyright ⓒ 2011 Hellomarket Inc. All Rights Reserved
 */
package pay.token.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 카드 등록 파라미터
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
public class CardRequest {

    /**
     * 결제자 CI
     */
    private String payerCi;


}
