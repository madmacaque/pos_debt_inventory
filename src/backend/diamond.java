package backend;

import java.util.Set;

public class diamond {

    private String cost_disc;
    private String cost;
    public double memoDisc;
    protected double sz;
    private String colour;
    private Clarity cla;
    private Set<Double> Measurement;
    private boolean isCertified;
    private String certLocation;
    private String certNumber;
    private String certURL;
    private Set<String> cutGrades;
    private String fluo;
    public boolean isSold;
    protected double rapValue;


    //creates a diamond with size colour clarity and sees if diamond is certified.
    public diamond(double size, String colour, Clarity clarity, boolean isCertified){
        this.sz=size;
        this.colour=colour;
        this.cla=clarity;
        this.isCertified=isCertified;
    }

    //sets the certification if it's certified and adds rap value
    private void setCertificaiton(String certificationLocation, String certificationNumber, double rapvalue){
        if (isCertified) {
            certLocation = certificationLocation;
            certNumber = certificationNumber;
            certURL = "https://www.gia.edu/report-check?reportno=" + certNumber;
            rapValue=rapvalue;
        }
    }


    //adds additional details about the certified stone
    private void setadditionalGrades(String cut, String polish, String symmetry, String fluoresence){
        cutGrades.add(cut);
        cutGrades.add(polish);
        cutGrades.add(symmetry);
        fluo=fluoresence;
    }
    private void setMeasurement(double min_girld_diameter, double max_girdle_diameter, double height){
        Measurement.add(min_girld_diameter);
        Measurement.add(max_girdle_diameter);
        Measurement.add(height);

    }



}
