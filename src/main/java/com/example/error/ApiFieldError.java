package com.example.error;


public record ApiFieldError
        (
                String field,
                String message
        ) {
}
