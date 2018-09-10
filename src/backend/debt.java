package backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class debt {
    //this is where all the money related transactions happen for each customer

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    //TODO:log to a new file.
    //method for paying off usd
    void customerPaidUSD(customer customer, double usdAmount){
        customer.totalUSDvalue=-usdAmount;
        Date date = new Date();
        System.out.println(customer.name+" paid "+usdAmount + " on " + dateFormat.format(date));
    }
    //method for paying off inr
    void customerPaidINR(customer customer, double usdAmount){
        customer.totalINRvalue=-usdAmount;
        Date date = new Date();
        System.out.println(customer.name+" paid "+usdAmount + " on " + dateFormat.format(date));
    }
    //method for paying off mmk
    void customerPaidMMK(customer customer, double usdAmount){
        customer.totalMMKvalue=-usdAmount;
        Date date = new Date();
        System.out.println(customer.name+" paid "+usdAmount + " on " + dateFormat.format(date));
    }

    //show last paid date
    //parse from data files






}
