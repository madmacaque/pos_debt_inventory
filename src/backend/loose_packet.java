package backend;


public class loose_packet {

    private String cost;            //cost in code
    private String sz;              //size of stones
    private String col;             //colour of stones
    private Clarity cla;            //clarity of the stones
    protected double totalCarats;     //total carat weight of the packet
    public boolean isSold;          //mark to check if sold.
    public double memoPrice;

    //creates a new packet with size, colour, clarity, carats, and cost in code.
    public loose_packet(String size, String colour, Clarity clarity, double carats, String cost) {
        this.cost = cost;
        this.sz = size;
        this.col = colour;
        this.cla = clarity;
        this.totalCarats = carats;
    }



}

