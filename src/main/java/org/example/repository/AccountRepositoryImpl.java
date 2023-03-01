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

// 1---> une variable qui porte le meme nom que la class à instancier comme un singleton
    static final AccountRepositoryImpl accountRepository;
    //final = une fois la valeur définie ---> non modifiable
// 2 ---> Initialiser la variable dans un bloc static

    // le block static est toujours le premier à s'initialiser au demarrage
    static {
        System.out.println("Singleton initialisation");
        accountRepository = new AccountRepositoryImpl();
    }
 // 5 ----> Utilisation: Inetrdire l'instanciation en dehors de cette class via un constructeur privé
    // c'est la methode get instance qui fait le boulot
    private AccountRepositoryImpl(){


    }

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
    // 3---> pour utiliser le sigleton on cree une methode static qui return la class l'instance  .
    public  static  AccountRepositoryImpl getInstance(){
      /* if(accountRepository==null){
        ccountRepositoryImpl = new AccountRepositoryImpl()
        + Enlever le block static et la restriction final

        }*/
        return accountRepository;
    }
}
