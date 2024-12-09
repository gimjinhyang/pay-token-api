/*
 * Copyright ⓒ 2011 Hellomarket Inc. All Rights Reserved
 */
package pay.token.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pay.token.api.response.DataResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @PostMapping
    public ResponseEntity<?> post() {
        log.info("Health check started");


//        return ResponseEntity.ok("OK");
        return ResponseEntity.ok(new DataResponse(null));
    }

}
