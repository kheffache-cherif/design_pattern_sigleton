package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import org.example.utils.JsonSerializer;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;
import org.example.repository.AccountRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /* Creation de l'objet avec constructeur SANS params
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountId(2L);
        bankAccount.setBalance(33000.00);
        */
        /* Creation de l'objet avec constructeur AVEC  params
        BankAccount bankAccount = new BankAccount(3L,3000.00,"Dinnars",AccountType.CURRENT_ACCOUNT,AccountStatus.ACTIVATED);
        */

       /* -- Avant creation de la class Director de centralisation des build

       BankAccount bankAccount = BankAccount.MonBuilder()

                .accountId(1l) // le . vient du this de la class BankAccount.
                .currency("EURO")
                .status(AccountStatus.CREATED)
                .type(AccountType.SAVING_ACCOUNT)
                .balance(20000.00)
                .build();
        System.out.println(bankAccount.toString());

    // APRES CREATION DE LA CLASS BankDirector
        BankAccount bankAccount =   BankDirector.AccountBuilder()
                .accountId(1l) // le . vient du this de la class BankAccount.
                .currency("EURO")
                .status(AccountStatus.CREATED)
                .type(AccountType.SAVING_ACCOUNT)
                .balance(20000.00)
                .build();
        System.out.println(bankAccount.toString());*/

        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();

        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        accountRepository.populateData();
        List<BankAccount> bankAccountsList= accountRepository.findAll();
       // bankAccountsList.forEach(System.out::println);
       // bankAccountsList.forEach(bankAccountJsonSerializer::toJson);
        bankAccountsList.stream()
                //.map(acc->bankAccountJsonSerializer.toJson(acc))
                .map(bankAccountJsonSerializer::toJson)
                .forEach(System.out::println);
    }
}