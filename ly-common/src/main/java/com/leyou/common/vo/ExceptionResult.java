package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResult {
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em){
        this.statusCode = em.getStatusCode();
        this.httpStatus = em.getHttpStatus();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
