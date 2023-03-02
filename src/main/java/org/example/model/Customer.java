package org.example.model;

public class Customer implements Cloneable {
    private Long IdCustomer ;
    private String name;


    public Customer(Long idCustomer, String name) {
        IdCustomer = idCustomer;
        this.name = name;
    }
    public Customer() {
    }

    public Long getIdCustomer() {
        return IdCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        IdCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "IdCustomer=" + IdCustomer +
                ", name='" + name + '\'' +
                '}';
    }
//* REDEFENIR LA METHODE ET L4AJOUTER DANS LOBJET PRINCIPALE BankAccount
    @Override
    protected Customer clone() throws CloneNotSupportedException {
        return(Customer) super.clone();
    }
}



