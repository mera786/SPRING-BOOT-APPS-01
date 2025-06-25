package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String fileName;
    private String message;
    private HttpStatus status;

    public FileResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
        this.status = status;
    }
}
