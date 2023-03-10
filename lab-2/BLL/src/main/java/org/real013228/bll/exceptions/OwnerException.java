package org.real013228.bll.exceptions;

public class OwnerException extends Exception {

    private OwnerException() {
    }

    private OwnerException(String message) {
        super(message);
    }

    public static OwnerException OwnerAlreadyGetCatWithId(int id) {
        return new OwnerException(String.format("Owner already contains the cat with id: %d in cats list", id));
    }
}
