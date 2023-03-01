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
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        System.out.println("************************ AFFICHAGE FindAll() *********************************************");
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

    // ----> sans le singleton
        // AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
    // ---> Avec le singleton
        AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();
        /*
        AccountRepositoryImpl accountRepository2 = AccountRepositoryImpl.getInstance();
        AccountRepositoryImpl accountRepository3 = AccountRepositoryImpl.getInstance();

         les deux instanciation  ne represente qu'une seule au niveau de la memoire*/
        accountRepository.populateData();
        List<BankAccount> bankAccountsList= accountRepository.findAll();
       // bankAccountsList.forEach(System.out::println);
       // bankAccountsList.forEach(bankAccountJsonSerializer::toJson);
        bankAccountsList.stream()
                //.map(acc->bankAccountJsonSerializer.toJson(acc))
                .map(bankAccountJsonSerializer::toJson)
                .forEach(System.out::println);

        System.out.println("************************ AFFICHAGE findById(2L) *********************************************");

        BankAccount account = accountRepository.findById(2L).orElse(null);
        if(account!=null){
            System.out.println(bankAccountJsonSerializer.toJson(account));
        }
        System.out.println("************************ AFFICHAGE serchAccount " +
                " aFFICHAGE QUE DES COMPTE COURANT *********************************************");

// utilsation de predicate ----> interface avec la methode test
        List<BankAccount> bankAccounts= accountRepository.searchAccount(new Predicate<BankAccount>() {
            @Override  // POSSIBLE DE FAIRE AVEC EXPRESSION LAMBDA
            public boolean test(BankAccount accountPredicate) {
                return (accountPredicate.getType().equals(AccountType.CURRENT_ACCOUNT) &&(accountPredicate.getBalance()>7000));
            }
        });

        bankAccounts.stream()

                .map(bankAccountJsonSerializer::toJson)
                .forEach(System.out::println);
    }
}