package com.newtouch.structfund.entity;

import lombok.Data;


public class ResponseMessage<T> {

    Long totalPieces;

    T content;

    public Long getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(Long totalPieces) {
        this.totalPieces = totalPieces;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
