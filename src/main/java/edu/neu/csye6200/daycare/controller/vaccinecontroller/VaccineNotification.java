package edu.neu.csye6200.daycare.controller.vaccinecontroller;

import edu.neu.csye6200.daycare.controller.vaccinecontroller.VaccineFactory;
import edu.neu.csye6200.daycare.model.Student;
import edu.neu.csye6200.daycare.model.Vaccine;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VaccineNotification {

    public static Map<String, Integer> getNotification(Student s){
        Map<String, Integer> map = new HashMap<>();
        int age = s.getAge() * 12;
        VaccineFactory.getInstance();
        Vector<Vaccine> list = VaccineFactory.getObject();
        for(Vaccine immu : list){
            if(age < 24){
                continue;
            }
            if(immu.getDoses() == s.getImmuRecord().get(immu).size()){
                continue;
            }
            int doses = s.getImmuRecord().get(immu).size();
            for(Map<int[], Integer> each :  immu.getAgeList()){
                for(Map.Entry<int[], Integer> eachMap : each.entrySet()){

                    if(doses < eachMap.getValue()){
                        if(s.getImmuRecord().get(immu).size() == 0 || immu.getSeperateMonths() != 0 && age - s.getImmuRecord().get(immu).get(s.getImmuRecord().get(immu).size() - 1) > immu.getSeperateMonths()){
                            map.put(immu.getName(), eachMap.getValue() - doses);
                        }
                        break;
                    }else{
                        doses -= eachMap.getValue();
                        continue;
                    }

                }
            }
        }

        return  map;
    }
}
