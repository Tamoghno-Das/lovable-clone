package com.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UsageLog {
    Long id;
    Project project;
    User user;
    String action;
    Integer tokenUsed;
    Integer durationMS;
    String metadata; // JSON OF {MODEL_USED , PROMPT_USED}
    Instant createdAt;
}
