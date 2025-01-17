package com.factoria.proyecto_final_ecom_fs.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private List<String> messages;
    private LocalDateTime timestamp;

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
