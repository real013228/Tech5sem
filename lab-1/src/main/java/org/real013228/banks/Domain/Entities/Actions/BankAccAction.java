package org.real013228.banks.Domain.Entities.Actions;

import lombok.Getter;
import org.real013228.banks.Domain.Abstractions.Action;
import org.real013228.banks.Domain.Abstractions.BankAccount;

import java.util.function.Consumer;

public class BankAccAction implements Action {
    public BankAccAction(Consumer<BankAccount> consumer) {
        this.consumer = consumer;
    }
    @Getter
    private final Consumer<BankAccount> consumer;
    @Override
    public void invoke(Object acc) {
        consumer.accept((BankAccount) acc);
    }
}
