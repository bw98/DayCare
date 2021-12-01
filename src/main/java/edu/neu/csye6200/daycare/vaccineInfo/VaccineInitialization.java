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
        Hib.ageList.add(new ConcurrentHashMap<>());
        Hib.ageList.get(0).put(new int[]{24, 59}, 4);
        return Hib;
    }
    public static Vaccine getDTaP(){
        Vaccine DTaP = new Vaccine("DTaP", 5);
        DTaP.ageList.add(new ConcurrentHashMap<>());
        DTaP.ageList.add(new ConcurrentHashMap<>());
        DTaP.ageList.get(0).put(new int[]{24, 59}, 4);
        DTaP.ageList.get(1).put(new int[]{60, 143}, 1);
        return DTaP;
    }

    public static Vaccine getPolio(){
        Vaccine Polio = new Vaccine("Polio", 4, 6);
        Polio.ageList.add(new ConcurrentHashMap<>());
        Polio.ageList.add(new ConcurrentHashMap<>());
        Polio.ageList.get(0).put(new int[]{24, 59}, 3);
        Polio.ageList.get(1).put(new int[]{60, 143}, 1);
        return Polio;
    }

    public static Vaccine getHepatitis(){
        Vaccine HepatitisB = new Vaccine("Hepatitis B", 3);
        HepatitisB.ageList.add(new ConcurrentHashMap<>());
        HepatitisB.ageList.get(0).put(new int[]{24, 1200}, 3);
        return HepatitisB;
    }

    public static Vaccine getMMR(){
        Vaccine MMR = new Vaccine("MMR", 2, 1);
        MMR.ageList.add(new ConcurrentHashMap<>());
        MMR.ageList.get(0).put(new int[]{24, 59}, 1);
        MMR.ageList.get(0).put(new int[]{60, 1200}, 1);
        return MMR;
    }

    public static Vaccine getVaricella(){
        Vaccine Varicella = new Vaccine("Varicella", 2, 1);
        Varicella.ageList.add(new ConcurrentHashMap<>());
        Varicella.ageList.add(new ConcurrentHashMap<>());
        Varicella.ageList.get(0).put(new int[]{24, 59}, 1);
        Varicella.ageList.get(1).put(new int[]{60, 1200}, 1);
        return Varicella;
    }

    public static Vaccine getMeningococcal(){
        Vaccine Meningococcal = new Vaccine("Meningococcal", 1);
        Meningococcal.ageList.add(new ConcurrentHashMap<>());
        Meningococcal.ageList.get(0).put(new int[]{144, 1200}, 1);
        return Meningococcal;
    }

}
