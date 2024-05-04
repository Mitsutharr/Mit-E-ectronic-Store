package com.cwm.electronic.store.exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundExveption extends RuntimeException{
    public ResourceNotFoundExveption()
    {
        super("Resource Not Found !!");
    }
    public ResourceNotFoundExveption( String message)
    {
        super(message);
    }
}
