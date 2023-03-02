package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;

public class TestClonePatternPrototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1 = new BankAccount();
        account1.setAccountId(12L);
        account1.setBalance(56.00);
        account1.setCurrency("Dinnards");
        account1.setType(AccountType.SAVING_ACCOUNT);
        account1.setStatus(AccountStatus.CREATED);

    //************************* Une copie sans utilisation de clone ************************/
                                // les meme valeurs que le account1//
        BankAccount account2 = new BankAccount();
        account2.setAccountId(12L);
        account2.setBalance(56.00);
        account2.setCurrency("Dinnards");
        account2.setType(AccountType.SAVING_ACCOUNT);
        account2.setStatus(AccountStatus.CREATED);

    //************************ Une copie en utilisant le pattern Prototype ******************/
        //- Methode redefinie dans la class de l'objet à clonner
        //- Ajouter lexception  l'exception

        BankAccount account3 = account1.clone();



        //****************************************TESTER***************************************/

        BankAccount account4 = account1;

        account1.setBalance(5555555555.00);

//******************************************Affichage ****************************************/
        System.out.println("                     Affichage du  compte 1 ="+ account1);
        System.out.println("Affichage du  compte 2 la copie du copmte 1 ="+account2);
        System.out.println("Affichage du  compte 3 le clone du copmte 1 ="+account3);

        /////////////////////////////Supprimer methode to string pour voir adress memoire
        System.out.println("Affichage du  compte 4 qui = le compte 1  ="+ account4);

        System.out.println("                     Affichage du  compte 1 ="+account1.getBalance());
        System.out.println("                     Affichage du  compte 3 ="+account2.getBalance());
        System.out.println("                     Affichage du  compte 4 ="+account4.getBalance());

    }
}
