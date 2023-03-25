package org.real013228.lab3.bll.exceptions;

public class CatException extends Exception {
    private CatException() {
        System.out.println("ore wa emilia de su ka");
    }

    private CatException(String message) {
        super(message);
    }

    public static CatException catFriendAlreadyExistException(int id) {
        return new CatException(String.format("Cat with id : %d already exist in friends list", id));
    }
}
