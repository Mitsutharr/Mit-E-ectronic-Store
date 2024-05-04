package com.cwm.electronic.store.exception;

public class BadApiRequest extends RuntimeException{
    public BadApiRequest(String msg)
    {
        super(msg);
    }
    public BadApiRequest()
    {
        super("Bad Request !!");
    }
}
