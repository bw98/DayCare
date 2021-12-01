package edu.neu.csye6200.daycare.vaccineInfo;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Student {

    private String name;
    private int age;
    private int studentId;

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

    public int getStudentId(){
        return this.studentId;
    }

    public void setStudentId(int n){
        this.studentId = n;
    }

    public void setImmuRecord(){
        List<String> record = VaccineUtil.getVaccineList(this.studentId);
        if(record.isEmpty()){
            System.out.println("record empty");
            return;
        }
        int l = 1, r = record.size() - 1;
        if((r - l) % 2 == 0){
            System.out.println("err");
            return;
        }
        for(int i = l; i <= r; i+= 2){
            Vaccine temp = VaccineFactory.getVaccine(record.get(i));
            if(temp == null){
                System.out.println("Student init vaccine err!");
                continue;
            }
            immuRecord.putIfAbsent(temp, new Vector<>());
            immuRecord.get(temp).add(Integer.valueOf(record.get(i + 1)));
        }
    }

    public void initRecord(){
        VaccineFactory.getInstance();
        for(Vaccine v : VaccineFactory.getObject()){
            this.immuRecord.put(v, new Vector<>());
        }
    }

    public Student(int id, String name, int age){
        this.studentId = id;
        this.name = name;
        this.age = age;
        immuRecord = new ConcurrentHashMap<>();
        initRecord();
        setImmuRecord();
    }
}
