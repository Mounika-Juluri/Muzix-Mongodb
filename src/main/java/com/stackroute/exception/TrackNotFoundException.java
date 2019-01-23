package com.stackroute.exception;

public class TrackNotFoundException extends Exception{          //Global exception thrown for track not found
    private String message;
    public TrackNotFoundException(String message){
        super(message);
        this.message=message;                                   //TrackNotFound displayed
    }
}
