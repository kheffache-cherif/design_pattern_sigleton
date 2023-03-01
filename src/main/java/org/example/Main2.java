package org.example;

import org.example.model.AccountType;
import org.example.model.BankAccount;

import org.example.repository2.AccountRepositoryImpl2;
import org.example.utils.JsonSerializer;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class Main2 {
    public static void main(String[] args) throws IOException {

        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();
        AccountRepositoryImpl2 accountRepository2 = AccountRepositoryImpl2.getInstance();

        // plusieurs threads qui partage le mme objet accountRepository2

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() { //posibilité d'ecreture autre avec expression lambda
                @Override
                public void run() {
                    // 10 thread chaqu'un doit executer population data (donc ont aura 100 objet)
                    accountRepository2.populateData();
                }
            }).start(); // .start elle execute automatiquement le run()
            
        }
        System.out.println("Tape à character:");  // je demande à l'utilisateur de tapper un cract pour continuer
        System.in.read(); // ajout lexception arret lecture
        List<BankAccount> bankAccountsList= accountRepository2.findAll();
        bankAccountsList.stream()
                .map(bankAccountJsonSerializer::toJson)
                .forEach(System.out::println);



    }
}