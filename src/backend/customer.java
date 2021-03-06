package backend;



import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mmthein on 6/9/18.
 */
public class customer {
    public String name;
    public String phonenumber;
    public List<diamond> diamMemo;
    public List<loose_packet>  looseMemo;
    public List<transactions> transc;
    public List<diamond> diamSold;
    public List<loose_packet> looseSold;
    public double totalUSDvalue;
    public double totalINRvalue;
    public double totalMMKvalue;


    public static void main (String[] argv){
        try{
            //build document
            File customerXMLFile=new File("data/customers.xml");
            DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbfactory.newDocumentBuilder();
            Document customerDoc=dBuilder.parse(customerXMLFile);

            //customer operations
            //add new customer
            Node rootCustomers= customerDoc.getFirstChild();

            //create new customer
            Element customer= customerDoc.createElement("customer");
            rootCustomers.appendChild(customer);

            //set customer ID
            setLatestID(customerDoc, customer);

            //enter user given name and phno
            customer newcustomer=new customer();
            Element custName= customerDoc.createElement("name");
            Element custPHNO= customerDoc.createElement("phNO");
            custName.appendChild(customer);
            custPHNO.appendChild(customer);
            custName.setTextContent(newcustomer.name);
            custPHNO.setTextContent(newcustomer.phonenumber);



            //write to xml data/customers.xml
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(customerDoc);
            StreamResult result= new StreamResult(new File("data/customers.xml"));
            transformer.transform(source, result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //TODO: log every single action


    //create a new customer with name, ph no
    public customer () {
        Scanner nameRead=new Scanner(System.in);
        System.out.println("Enter Name: ");
        this.name=nameRead.next();
        Scanner phNORead=new Scanner(System.in);
        System.out.println("Enter Phone Number: ");
        this.phonenumber=phNORead.next();
    }

    //set customer ID with latest id
    static void setLatestID(Document doc, Element newCustomer){
        //check latest ID
        NodeList listofCustomers=doc.getElementsByTagName("customer");
        int latestID=listofCustomers.getLength() +1;

        //attribute for customer element: ID
        //ID number goes up by one each time we add a customer
        Attr attr = doc.createAttribute("ID");
        attr.setValue(Integer.toString(latestID));
        newCustomer.setAttributeNode(attr);
    }

    //adds items to list of memo
    void giveDiamonMemo(diamond Diam, double price){
        Diam.memoDisc=price;
        this.diamMemo.add(Diam);
    }
    void giveLooseonMemo(loose_packet looseP, double price){
        looseP.memoPrice=price;
        this.looseMemo.add(looseP);
    }


    //checks to see if diamond is on memo. if not prints out error and returns.
    //if so, removes diam from memo, marks diam as sold, creates a new transaction with given price,
    // adds the new transaction to list of transactions, adds the diamond to sold list
    //add to total values to the transaction values and their currency values to customer's total currency debt.
    void sellDiam(diamond Diam, double discount){
        if(this.diamMemo.contains(Diam)){
            this.diamMemo.remove(Diam);
            Diam.isSold=true;
            transactions newDiamTransc= new transactions(this);
            newDiamTransc.sellDiam(Diam,discount);
            newDiamTransc.totalvalue=Diam.rapValue*Diam.sz*(1-(discount/100));
            this.totalUSDvalue=+newDiamTransc.totalvalue;

            this.transc.add(newDiamTransc);
            this.diamSold.add(Diam);
        }
        System.out.println("ERROR: Diamond not on memo to this customer");
    }

    void sellLoose(loose_packet looseP, double price, String currency, double carats){
        if(this.looseMemo.contains(looseP)){
            this.looseMemo.remove(looseP);
            looseP.isSold=true;
            transactions newLooseTransc= new transactions(this);
            newLooseTransc.sellLoose(looseP,price,currency, carats);
            //TODO: check if needed to multiply 1.1 or not. usd no, inr idk, mmk yes.
            newLooseTransc.totalvalue=newLooseTransc.carats*price;
            if (currency.equals("MMK")) {
                newLooseTransc.totalvalue *= 1.1;
                this.totalMMKvalue=+newLooseTransc.totalvalue;
            }
            else if (currency.equals("INR")){
                newLooseTransc.totalvalue *= 1.1;
                this.totalINRvalue=+newLooseTransc.totalvalue;
            }
            this.transc.add(newLooseTransc);
            this.looseMemo.add(looseP);
        }
        System.out.println("ERROR: Packet not on memo to this customer");
    }



}
