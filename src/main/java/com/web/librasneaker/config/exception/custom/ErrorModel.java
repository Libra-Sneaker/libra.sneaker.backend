package com.web.librasneaker.config.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorModel {

    private String fieldError;

    private String message;

}
