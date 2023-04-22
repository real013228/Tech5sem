package com.real013228.dto;


public class AuthenticationRequest {
    private String login;
    private String password;

    public AuthenticationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public static AuthenticationRequestBuilder builder() {
        return new AuthenticationRequestBuilder();
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthenticationRequest)) return false;
        final AuthenticationRequest other = (AuthenticationRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$login = this.getLogin();
        final Object other$login = other.getLogin();
        if (this$login == null ? other$login != null : !this$login.equals(other$login)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AuthenticationRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $login = this.getLogin();
        result = result * PRIME + ($login == null ? 43 : $login.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "AuthenticationRequest(login=" + this.getLogin() + ", password=" + this.getPassword() + ")";
    }

    public static class AuthenticationRequestBuilder {
        private String login;
        private String password;

        AuthenticationRequestBuilder() {
        }

        public AuthenticationRequestBuilder login(String login) {
            this.login = login;
            return this;
        }

        public AuthenticationRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationRequest build() {
            return new AuthenticationRequest(login, password);
        }

        public String toString() {
            return "AuthenticationRequest.AuthenticationRequestBuilder(login=" + this.login + ", password=" + this.password + ")";
        }
    }
}
