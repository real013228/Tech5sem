import org.real013228.banks.Domain.Abstractions.CentralBank;
import org.real013228.banks.Domain.Entities.Bank;
import org.real013228.banks.Domain.Entities.CentralBankImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DomainLogicTest {
    private final CentralBank centralBank = new CentralBankImplementation();
    @Test
    void commissionCheck() {
        var bank = centralBank.createBank(Bank.builder()
                .debitPercent(1)
                .commission(1)
                .creditLimit(1)
                .expirationDays(2)
                .transactionLimit(1));
        assertEquals(bank.getCommission(), 1d);
    }
    @Test
    void debitPercentCheck() {
        var bank = centralBank.createBank(Bank.builder()
                .debitPercent(1)
                .commission(1)
                .creditLimit(1)
                .expirationDays(2)
                .transactionLimit(1));
        assertEquals(bank.getDebitPercent(), 1d);
    }
    @Test
    void transactionLimitCheck() {
        var bank = centralBank.createBank(Bank.builder()
                .debitPercent(1)
                .commission(1)
                .creditLimit(1)
                .expirationDays(2)
                .transactionLimit(1));
        assertEquals(bank.getTransactionLimit(), 1d);
    }
    @Test
    void expirationDaysCheck() {
        var bank = centralBank.createBank(Bank.builder()
                .debitPercent(1)
                .commission(1)
                .creditLimit(1)
                .expirationDays(2)
                .transactionLimit(1));
        assertEquals(bank.getExpirationDays(), 2);
    }
}
