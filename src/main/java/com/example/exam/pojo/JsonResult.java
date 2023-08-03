package com.example.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装JSON格式的响应结果
 * @param <T>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JsonResult<T> {
    private int status;
    private String message;
    private T data;

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
