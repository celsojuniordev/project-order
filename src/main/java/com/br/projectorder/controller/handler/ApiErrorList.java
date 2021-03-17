package com.br.projectorder.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorList extends ApiError {

    private List<String> errors;

    public ApiErrorList(Integer code, String msg, Date date, List<String> errors){
        super(code, msg, date);
        this.errors = errors;
    }
}
