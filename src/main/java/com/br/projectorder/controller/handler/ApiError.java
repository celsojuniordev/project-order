package com.br.projectorder.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

    private int code;
    private String msg;
    private Date date;

    public ApiError(Integer code, String msg, Date date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }
}
