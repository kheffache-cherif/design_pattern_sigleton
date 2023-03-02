package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;

public class TestClonePatternPrototypeWithTwoObject {
    public static void main(String[] args) throws CloneNotSupportedException {


        /* Dans cette partie on rajoute un atribut Customer pour l'objet BankAccount
        *
        *
        *
        *
        *
        *  */

        BankAccount account1 = new BankAccount();
        account1.setAccountId(12L);
        account1.setBalance(56.00);
        account1.setCurrency("Dinnards");
        account1.setType(AccountType.SAVING_ACCOUNT);
        account1.setStatus(AccountStatus.CREATED);

        //************************ Une copie en utilisant le pattern Prototype ******************/


        BankAccount account3 = account1.clone();




//******************************************Affichage ****************************************/
        System.out.println("                     Affichage du  compte 1 ="+ account1);

        System.out.println("Affichage du  compte 3 le clone du copmte 1 ="+account3);

        /////////////////////////////Supprimer methode to string pour voir adress memoire

    }

}
