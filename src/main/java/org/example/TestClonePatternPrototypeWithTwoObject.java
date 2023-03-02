package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.Customer;

public class TestClonePatternPrototypeWithTwoObject {
    public static void main(String[] args) throws CloneNotSupportedException {


        /* Dans cette partie on rajoute un atribut Customer pour l'objet BankAccount
        * Creation de l'objet Custumer qui impliments l'interface cloneable
        * Redefinition de la methode clone dans Custumer
        * Ajout du clone custumer à la methode clone de l'objetBankAccount
        *
        *
        *  */

        BankAccount account1 = new BankAccount();
        account1.setAccountId(12L);
        account1.setBalance(56.00);
        account1.setCurrency("Dinnards");
        account1.setType(AccountType.SAVING_ACCOUNT);
        account1.setStatus(AccountStatus.CREATED);
        account1.setCustomer(new Customer(3L,"Zidane "));

        //************************ Une copie en utilisant le pattern Prototype ******************/


        BankAccount account3 = account1.clone();




//******************************************Affichage ****************************************/
        System.out.println( account1);   // nameCustomer = zidane
                                                                // --> le meme objet customer
        System.out.println(account3);   // // nameCustomer = zidane
//**************************************** test apres modification ****************************/

        account1.getCustomer().setName("Ronaldinhoo");

        System.out.println( account1);   // nameCustomer = Ronaldinhoo
                                                                         // --> un autre objet customer
        System.out.println(account3);   // // nameCustomer = zidane //

    }

}
