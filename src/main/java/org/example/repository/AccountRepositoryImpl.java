package org.example.repository;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    //les comptes seront stockés dans une collection de type Map
    private Map<Long,BankAccount>bankAccountMap=new HashMap<>();
    // un autoincriment pour les comptes
    private long  accountsConteur =0;

    @Override
    public BankAccount save(BankAccount bankAccount) {
    Long accountId=++accountsConteur;
    bankAccount.setAccountId(accountId);
    bankAccountMap.put(accountId,bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
// values c'est les valeur il est de type collection donc le transformer en liste avec .stream().toList();
        return bankAccountMap.values().stream().toList();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        //chercher le compte dans la collection map
        BankAccount account = bankAccountMap.get(id);
        if (account==null)
        return Optional.empty();
        else
            // .of une instance de account
            return Optional.of(account);
    }

    @Override
    public List<BankAccount> searchAccount(Predicate<BankAccount> predicate) {

            //pour la recherche dna les valeur de map je filtre selon le predicat et le transformer en collection pui en liste
        return bankAccountMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount account) {
         bankAccountMap.put(account.getAccountId(),account);
         return account;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountMap.remove(id);

    }
    // une methode pour creer des objet account aléatoires
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
    }
}
