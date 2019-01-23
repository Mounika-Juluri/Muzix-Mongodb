package com.stackroute.exception;

public class TrackAlreadyExistsException extends Exception{     //Exception thrown when user tries to save duplicate tracks
    private String message;
    public TrackAlreadyExistsException(String message){
        super(message);
        this.message=message;                                   //appropriate message displayed
    }
}
