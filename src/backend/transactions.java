package backend;

public class transactions {

    public boolean isOpen;
    public customer customer;
    public diamond diam;
    public double discount;
    public loose_packet looseP;
    public double price;
    public String USD_INR_MMK;
    public double carats;

    public double totalvalue;



    //creates a new transaction with customer
    public transactions (customer customer){
        isOpen=true;
        this.customer=customer;
    }

    //sets parameters for pieces and prices/discounts
    public void sellDiam(diamond Diam, double discount){
        this.diam=Diam;
        this.discount=discount;
        this.USD_INR_MMK="USD";
    }
    public void sellLoose(loose_packet looseP, double price, String currency, double carats){
        this.looseP=looseP;
        this.price=price;
        this.carats=carats;
    }

    //TODO: figure out how to handle inventory
    //adds the packet/diam back into inventory with the returned price.
    void customerreturnLoose (customer cust, loose_packet loose, double price){

    }
    void customerreturnDiam (customer cust, diamond diam, double discount){

    }

}
