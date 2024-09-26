package com.karan.TokenBucket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TokenBucketService {

    private final int MAX_TOKENS = 10; // Maximum number of tokens in the bucket
    private final int REFILL_RATE = 2; // Tokens added per refill interval

    private AtomicInteger tokens;

    public TokenBucketService() {
        this.tokens = new AtomicInteger(MAX_TOKENS); // Start with a full bucket
    }

    // Method to consume a token, return true if successful, false otherwise
    public boolean consumeToken() {
        int currentTokens = tokens.get();
        if (currentTokens > 0) {
            return tokens.compareAndSet(currentTokens, currentTokens - 1);
        }
        return false;
    }

    // Scheduled refill of tokens every 1 second
    @Scheduled(fixedRate = 1000)
    public void refillTokens() {
        int currentTokens = tokens.get();
        int newTokenCount = Math.min(currentTokens + REFILL_RATE, MAX_TOKENS);
        tokens.set(newTokenCount);
    }
}
