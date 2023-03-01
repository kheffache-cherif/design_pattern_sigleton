package org.example.repository2;

import org.example.model.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AccountRepository2 {  //Alt+Entrée pour redefinir les methode

    BankAccount save(BankAccount bankAccount);
    List<BankAccount> findAll();

}
