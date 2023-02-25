package org.real013228.banks.Domain.Models;

import lombok.Getter;
import org.real013228.banks.Domain.CustomExceptions.PassportException;

/***
 * Clients' passport class
 */
public class Passport {
    @Getter
    private final String passportName;
    public Passport(String passport) throws PassportException {
        if (passport.length() != 10 || !correctPassportValue(passport))
            throw PassportException.invalidPassportException(passport);
        passportName = passport;
    }

    /***
     * Method, that checks correctness of the passport
     * @param passport passport's number
     * @return returns false if passport isn't correct
     */
    private boolean correctPassportValue(String passport) {
        for(int i = 0; i < passport.length(); ++i) {
            if (!Character.isDigit(passport.charAt(i)))
                return false;
        }

        return true;
    }
}
