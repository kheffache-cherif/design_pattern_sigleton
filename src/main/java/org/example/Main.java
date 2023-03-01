package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = BankAccount.MonBuilder()

                .accountId(1l)
                .currency("EURO")
                .status(AccountStatus.CREATED)
                .type(AccountType.SAVING_ACCOUNT)
                .balance(20000.00)



                .build();
        System.out.println(bankAccount.toString());

    }
}