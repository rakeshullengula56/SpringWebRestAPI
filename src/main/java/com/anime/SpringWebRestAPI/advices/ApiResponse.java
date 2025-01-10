package com.anime.SpringWebRestAPI.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse <T>{
    @JsonFormat(pattern = "hh:mm:ss DD-MM-YYYY")
    private LocalDateTime timeStamp;
    private T data;
    private ApiError apiError;


    public ApiResponse() {
        this.timeStamp=LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
