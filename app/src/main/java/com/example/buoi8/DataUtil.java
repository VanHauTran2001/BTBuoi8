package com.example.buoi8;

public class DataUtil {
    public static DataSpinner[] getDataspiners(){
        DataSpinner data1 = new DataSpinner("Work");
        DataSpinner data2 = new DataSpinner("Friend");
        DataSpinner data3 = new DataSpinner("Family");
        return new DataSpinner[]{data1,data2,data3};
    }
}
