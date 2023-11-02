package com.library.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LibraryErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}
