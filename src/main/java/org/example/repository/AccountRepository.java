package org.example.repository;

import org.example.model.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AccountRepository {  //Alt+Entrée pour redefinir les methode

    BankAccount save(BankAccount bankAccount);
    List<BankAccount> findAll();
    //BankAccount findById(Long id); ou lieu de cette methode on utilise Optional  ('type generique ') qui est utiliser dans spring Data
    Optional<BankAccount> findById(Long id);  // soit un compte soit rien
    //Predicate est une interface qui contient une methode test On lui passe un objet BankAccount et retouner oui ou non.
    List<BankAccount> searchAccount(Predicate<BankAccount>predicate);

    BankAccount update(BankAccount bankAccount);
    void deleteById(Long id );
}
