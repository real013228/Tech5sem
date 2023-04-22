package com.real013228.dto;


import com.real013228.ERole;

public class RegisterRequest{
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private ERole role;

    public RegisterRequest(String login, String password, String firstName, String LastName, ERole role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = LastName;
        this.role = role;
    }

    public RegisterRequest() {
    }

    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterRequest)) return false;
        final RegisterRequest other = (RegisterRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$login = this.getLogin();
        final Object other$login = other.getLogin();
        if (this$login == null ? other$login != null : !this$login.equals(other$login)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$LastName = this.getLastName();
        final Object other$LastName = other.getLastName();
        if (this$LastName == null ? other$LastName != null : !this$LastName.equals(other$LastName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RegisterRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $login = this.getLogin();
        result = result * PRIME + ($login == null ? 43 : $login.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $LastName = this.getLastName();
        result = result * PRIME + ($LastName == null ? 43 : $LastName.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterRequest(login=" + this.getLogin() + ", password=" + this.getPassword() + ", firstName=" + this.getFirstName() + ", LastName=" + this.getLastName() + ")";
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public static class RegisterRequestBuilder {
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private ERole role;

        RegisterRequestBuilder() {
        }

        public RegisterRequestBuilder login(String login) {
            this.login = login;
            return this;
        }

        public RegisterRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequestBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public RegisterRequestBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public RegisterRequestBuilder role(ERole role) {
            this.role = role;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(login, password, firstName, lastName, role);
        }

        public String toString() {
            return "RegisterRequest.RegisterRequestBuilder(login=" + this.login + ", password=" + this.password + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", role=" + this.role + ")";
        }
    }
}
