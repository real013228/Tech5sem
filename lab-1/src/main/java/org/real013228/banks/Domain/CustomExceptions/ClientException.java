package org.real013228.banks.Domain.CustomExceptions;

import java.util.UUID;

/***
 * Client-related exceptions
 */
public class ClientException extends Exception {
    private ClientException() {
    }

    private ClientException(String message) {
        super(message);
    }

    /***
     * client exception
     * @return throwing exception, if name isn't correct
     */
    public static ClientException invalidNameException(){
        return new ClientException("Invalid client exception");
    }
    public static ClientException cannotFindClientById(UUID id) { return new ClientException("Cannot find client by this id: " + id); }
}
