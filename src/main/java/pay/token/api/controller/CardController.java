package pay.token.api.controller;

import com.google.common.collect.ImmutableMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pay.token.api.domain.card.service.command.CardEntryService;
import pay.token.api.domain.card.service.query.CardEntryValidateService;
import pay.token.api.request.CardRequest;
import pay.token.api.response.DataResponse;
import pay.token.api.response.ErrorResponse;

/**
 * 카드 관리 컨트롤러
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {


  private final CardEntryValidateService cardEntryValidateService;
  private final CardEntryService cardEntryService;

  /**
   * 카드 등록 요청
   *
   * @param param
   * @return
   */
  @PostMapping
  public ResponseEntity<?> post(@RequestBody CardRequest param) {
    try {

      // 등록 요청 파라미터 확인
      cardEntryValidateService.validate(param);

      // 카드 정보 등록
      final Long cardRefId = cardEntryService.entry(param);
      return ResponseEntity.ok(new DataResponse(ImmutableMap.of("cardRefId", cardRefId)));

    } catch (IllegalArgumentException e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    } catch (Exception e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, "카드 등록을 실패했습니다"));
    }
  }

}
