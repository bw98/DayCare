package edu.neu.csye6200.daycare.vaccineInfo;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class VaccineInitialization {

    public static void init(Vector<Vaccine> immunizationList){
        immunizationList.add(getHib());
        immunizationList.add(getDTaP());
        immunizationList.add(getPolio());
        immunizationList.add(getHepatitis());
        immunizationList.add(getMMR());
        immunizationList.add(getVaricella());
        immunizationList.add(getMeningococcal());
    }


    public static Vaccine getHib(){
        Vaccine Hib = new Vaccine("Hib", 4);
        Hib.getAgeList().add(new ConcurrentHashMap<>());
        Hib.getAgeList().get(0).put(new int[]{24, 59}, 4);
        return Hib;
    }
    public static Vaccine getDTaP(){
        Vaccine DTaP = new Vaccine("DTaP", 5);
        DTaP.getAgeList().add(new ConcurrentHashMap<>());
        DTaP.getAgeList().add(new ConcurrentHashMap<>());
        DTaP.getAgeList().get(0).put(new int[]{24, 59}, 4);
        DTaP.getAgeList().get(1).put(new int[]{60, 143}, 1);
        return DTaP;
    }

    public static Vaccine getPolio(){
        Vaccine Polio = new Vaccine("Polio", 4, 6);
        Polio.getAgeList().add(new ConcurrentHashMap<>());
        Polio.getAgeList().add(new ConcurrentHashMap<>());
        Polio.getAgeList().get(0).put(new int[]{24, 59}, 3);
        Polio.getAgeList().get(1).put(new int[]{60, 143}, 1);
        return Polio;
    }

    public static Vaccine getHepatitis(){
        Vaccine HepatitisB = new Vaccine("Hepatitis B", 3);
        HepatitisB.getAgeList().add(new ConcurrentHashMap<>());
        HepatitisB.getAgeList().get(0).put(new int[]{24, 1200}, 3);
        return HepatitisB;
    }

    public static Vaccine getMMR(){
        Vaccine MMR = new Vaccine("MMR", 2, 1);
        MMR.getAgeList().add(new ConcurrentHashMap<>());
        MMR.getAgeList().get(0).put(new int[]{24, 59}, 1);
        MMR.getAgeList().get(0).put(new int[]{60, 1200}, 1);
        return MMR;
    }

    public static Vaccine getVaricella(){
        Vaccine Varicella = new Vaccine("Varicella", 2, 1);
        Varicella.getAgeList().add(new ConcurrentHashMap<>());
        Varicella.getAgeList().add(new ConcurrentHashMap<>());
        Varicella.getAgeList().get(0).put(new int[]{24, 59}, 1);
        Varicella.getAgeList().get(1).put(new int[]{60, 1200}, 1);
        return Varicella;
    }

    public static Vaccine getMeningococcal(){
        Vaccine Meningococcal = new Vaccine("Meningococcal", 1);
        Meningococcal.getAgeList().add(new ConcurrentHashMap<>());
        Meningococcal.getAgeList().get(0).put(new int[]{144, 1200}, 1);
        return Meningococcal;
    }

}
