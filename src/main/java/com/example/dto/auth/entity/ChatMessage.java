package com.example.dto.auth.entity;

import com.example.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ChatMessage {
    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls; //JSON ARRAY OF TOOLS WILL BE SUPPLIED
    Integer tokenUsed;
    Instant createdAt;

}
