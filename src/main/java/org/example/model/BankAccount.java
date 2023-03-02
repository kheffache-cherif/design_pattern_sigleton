package org.example.model;

public class BankAccount implements  Cloneable {  // ---> Cloneable veut dire que c'est un objet que on peu clonner
    private Long accountId;
    private double balance;
    private String currency;
    private AccountType type;
    private AccountStatus status;
    private Customer customer;

    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountType getType() {
        return type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public BankAccount(Long accountId, double balance, String currency, AccountType type, AccountStatus status) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.status = status;
    }

    public BankAccount() {
    }

    @Override
    public String toString() {  //Alt + fn+inser pour autocomp
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
    /******************* Partie avec clone avec  l'ajout du custommer ************************/
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*---------------------une methode static qui return un objet AccountBuilder-------------------------/
    public static AccountBuilder MonBuilder(){
        return new AccountBuilder();
    }
    Cette methode est creéer de preference dans une autre class Director
*/
    // ***************Creation dune class interne que pour construire les compte *************///
    public static class AccountBuilder {
        private BankAccount bankAccount = new BankAccount();

        //ajouter des methode pour constuire les attributs
        public AccountBuilder accountId(Long id) { // la methode return la meme chose donc un AccountBuilder
            //elle vat dans l'objet bankAccountId +acceder à accountId = à id
            bankAccount.accountId = id;
            return this;  // chaque methode doit returner  le meme objet de type  AccountBuilder
        }
        public AccountBuilder balance(Double balance) {
            bankAccount.balance = balance;
            return this;
        }

        public AccountBuilder currency(String currency) {
            bankAccount.currency = currency;
            return this;
        }

        public AccountBuilder type(AccountType type) {
            bankAccount.type = type;
            return this;
        }

        public AccountBuilder status(AccountStatus status) {
            bankAccount.status = status;
            return this;
        }

        //******************************* Une methode build pour construire l'objet******************
        public  BankAccount build(){
            return this.bankAccount;  // du   bankAccount = new BankAccount(); ---->
        }
    }
/* *************************** Pour utiliser le pattern prototype il faut redefinir la methode clone
    - Elle herite de la class object
    - Elle est protected donc la redefinir acces jute dans le package et class dérivée ---> la rendre public
    - Remplacer objectn avec le nom de l'objet à cloner
    - Comme elle retourn un objet de type Object  ----> faire un cast vers cette objet .(BankAccount)
     - La methode clone se charge d'aller à la memoire et clonner l'objet
     - Comme la methode leve une exception donc la soulever dan le main
     */
    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        BankAccount bankAccount= (BankAccount) super.clone();
        //Je veux cloner aussi l'objet customer
        bankAccount.setCustomer(this.customer.clone());
        return bankAccount;
    }
}