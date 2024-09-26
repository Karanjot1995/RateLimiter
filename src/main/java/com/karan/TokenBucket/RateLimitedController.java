package com.karan.TokenBucket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class RateLimitedController {

    private final TokenBucketService tokenBucketService;

    public RateLimitedController(TokenBucketService tokenBucketService) {
        this.tokenBucketService = tokenBucketService;
    }

    @GetMapping("/limited-endpoint")
    public ResponseEntity<String> rateLimitedEndpoint() {
        if (tokenBucketService.consumeToken()) {
            return ResponseEntity.ok("Request successful");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded. Try again later.");
        }
    }
}
