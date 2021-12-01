package edu.neu.csye6200.daycare.vaccineInfo;

import java.util.Vector;

public class VaccineFactory {
    private VaccineFactory(){}

    private static Vector<Vaccine> immunizationList;

    public static void getInstance(){
        if(immunizationList == null){
            immunizationList = new Vector<>();
        }
    }

    public static Vector<Vaccine> getObject(){
        if(immunizationList.size() == 0){
            VaccineInitialization.init(immunizationList);
        }
        return immunizationList;
    }

    public static Vaccine getVaccine(String name){
        getInstance();
        for(Vaccine v : getObject()){
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }

}
