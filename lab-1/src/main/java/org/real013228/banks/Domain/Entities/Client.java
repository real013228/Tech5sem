package org.real013228.banks.Domain.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.real013228.banks.Domain.CustomExceptions.ClientException;
import org.real013228.banks.Domain.Models.AccountCollection;
import org.real013228.banks.Domain.Models.Passport;

import java.util.UUID;

/***
 * Client entity
 */
@Getter
public class Client {
    private AccountCollection accounts;
    private UUID id;
    private final String firstName;
    private final String lastName;
    @Setter(AccessLevel.PRIVATE)
    private Passport passport;
    @Setter(AccessLevel.PRIVATE)
    private String address;
    @Setter(AccessLevel.PRIVATE)
    private boolean isSus;
    public static ClientBuilder builder() {
        return new ClientBuilder();
    }
    private Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
    }

    /***
     * Method, that verifies client passport and address
     * @param passport client passport
     * @param address client address
     */
    public void verifyClient(Passport passport, String address) {
        this.passport = passport;
        this.address = address;
        isSus = false;
    }

    /***
     * Client static builder, for convenient client creation
     */
    public static class ClientBuilder {
        private String firstName = "";
        private String lastName = "";

        /***
         * Method, that sets client name
         * @param firstName first name of client
         * @return client builder for setting last name
         */
        public ClientBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /***
         * Method, that sets last name
         * @param lastName last name of client
         * @return client builder for calling build() method
         */
        public ClientBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /***
         * Method, that builds new client
         * @return new client, with set name
         * @throws ClientException if name isn't correct
         */
        public Client build() throws ClientException {
            if (this.firstName.equals("") || this.lastName.equals(""))
                throw ClientException.invalidNameException();
            return new Client(firstName, lastName);
        }
    }
}
