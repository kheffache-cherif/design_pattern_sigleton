package org.example.repository2;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;
import org.example.repository.AccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepositoryImpl2 implements AccountRepository2 {
    static final AccountRepositoryImpl2 accountRepository;
    static {
        System.out.println("Singleton initialisation");
        accountRepository = new AccountRepositoryImpl2();
    }
    private AccountRepositoryImpl2(){

    }
    private Map<Long,BankAccount>bankAccountMap=new HashMap<>();
    private long  accountsConteur =0;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        Long accountId;  // creation d'une fil d'attente
        synchronized(this) { // verrouiller l'objet tant que le 1er thread n'a pas terminer
            accountId = ++accountsConteur; //zone critique

        }
        bankAccount.setAccountId(accountId); // chaque thread va creer son propre compte et l'ajouter
            synchronized(this) {
                bankAccountMap.put(accountId, bankAccount);
            // le put aussi on doit savoir sur quelle place tu veux inserer dans la collection
            }

        return bankAccount;
    }
    @Override
    public List<BankAccount> findAll() {
        return bankAccountMap.values().stream().toList();
    }
// public synchronized populateData() -----> c'est posible mais creation d'une fil attente non exploitation du multi thread
    public void populateData(){
        for (int i = 0; i <10 ; i++) {
            BankAccount MonCompte = BankDirector.AccountBuilder()
                    .balance(5000+Math.random()*10000)
                    .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                    .status(Math.random()>0.50? AccountStatus.CREATED:AccountStatus.SUSPENDED)
                    .currency(Math.random()>0.5? "$":"Euros")
                    .build();
            save(MonCompte);

        }

        System.out.println("*****************************************************************");
        System.out.println(Thread.currentThread().getName()); //afficher le nom du threads
        System.out.println("le compteur ="+accountsConteur+ " pour le thread"+Thread.currentThread().getName());
        System.out.println("size"+bankAccountMap.values().size());// le nombre de compte dans la liste

        System.out.println("*****************************************************************");

    }

    public  static AccountRepositoryImpl2 getInstance(){
        return accountRepository;
    }
}
