package org.example.model;

public class BankDirector {

    //GENERALEMENT : on peut trouver plusieurs Builder c'espour cela l'idéal est de creer une class specifique Director
     //---------------------une methode static qui return un  nouveau objet AccountBuilder-------------------------
    public static BankAccount.AccountBuilder AccountBuilder(){
        return new BankAccount.AccountBuilder();
    }
}
