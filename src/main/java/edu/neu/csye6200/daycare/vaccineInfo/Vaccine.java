package edu.neu.csye6200.daycare.vaccineInfo;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Vaccine {

    private String name;
    private int doses;
    private int seperateMonths;
    Vector<ConcurrentHashMap<int[], Integer>> ageList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public int getSeperateMonths() {
        return seperateMonths;
    }

    public void setSeperateMonths(int seperateMonths) {
        this.seperateMonths = seperateMonths;
    }

    public Vector<ConcurrentHashMap<int[], Integer>> getAgeList() {
        return ageList;
    }



    public Vaccine(String name, int doses, int seperateMonths){
        setName(name);
        setDoses(doses);
        setSeperateMonths(seperateMonths);
        this.ageList = new Vector<>();
    }

    public Vaccine(String name, int doses){
        setName(name);
        setDoses(doses);
        setSeperateMonths(0);
        this.ageList = new Vector<>();
    }
}
