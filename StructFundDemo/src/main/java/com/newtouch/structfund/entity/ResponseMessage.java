package com.newtouch.structfund.entity;

import lombok.Data;

@Data
public class ResponseMessage<T> {

    Long totalPieces;

    T content;
}
