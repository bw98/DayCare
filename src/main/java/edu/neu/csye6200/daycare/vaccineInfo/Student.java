package edu.neu.csye6200.daycare.vaccineInfo;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Student {

    private String name;
    private int age;
    private ConcurrentHashMap<Vaccine, Vector<Integer>> immuRecord;
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public ConcurrentHashMap<Vaccine, Vector<Integer>> getImmuRecord(){
        return immuRecord;
    }

    public void setImmuRecord(String[] csv, int l, int r){
        if((r - l) % 2 == 0){
            System.out.println("err");
            return;
        }
        for(int i = l; i <= r; i+= 2){
            Vaccine temp = VaccineFactory.getVaccine(csv[i]);
            if(temp == null){
                System.out.println("Student init vaccine err!");
                continue;
            }
            immuRecord.putIfAbsent(temp, new Vector<>());
            immuRecord.get(temp).add(Integer.valueOf(csv[i + 1]));
        }
    }

    public void initRecord(){
        VaccineFactory.getInstance();
        for(Vaccine v : VaccineFactory.getObject()){
            this.immuRecord.put(v, new Vector<>());
        }
    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
        immuRecord = new ConcurrentHashMap<>();
        initRecord();
    }
}
