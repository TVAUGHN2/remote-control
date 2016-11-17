package edu.depaul.tvaughn2.tvaughn2remotecontrol;

/**
 * Created by Travis on 10/22/2016.
 */

public class FavChannel {
    private static FavChannel theInstance;
    private String fav_ch1 = "006";
    private String fav_ch2 = "111";
    private String fav_ch3 = "002";

    public static FavChannel getInsstance(){
        if (theInstance == null){
            theInstance = new FavChannel();
        }
        return theInstance;
    }
    private FavChannel(){}

    public void setFav_ch1(String i){fav_ch1 = i;}
    public void setFav_ch2(String i){fav_ch2 = i;}
    public void setFav_ch3(String i){fav_ch3 = i;}

    public String getFav_ch1(){return fav_ch1;}
    public String getFav_ch2(){return fav_ch2;}
    public String getFav_ch3(){return fav_ch3;}

}
