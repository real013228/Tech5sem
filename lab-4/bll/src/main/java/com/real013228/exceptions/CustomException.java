package com.real013228.exceptions;

public class CustomException extends Exception{
    private CustomException(String message) {
        super(message);
    }

    public static CustomException InvalidIdException(Long id) {
        return new CustomException("Invalid id: " + id);
    }
    public static CustomException InvalidColorException(String color) {
        return new CustomException("Invalid color:" + color);
    }
}
