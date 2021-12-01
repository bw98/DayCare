package edu.neu.csye6200.daycare.vaccineInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccineUtil {

    public static List<String> getVaccineList(int id){
        List<String> res = new ArrayList<>();
        try(BufferedReader inLine = new BufferedReader(new FileReader("src/main/java/edu/neu/csye6200/daycare/vaccineInfo/vaccine.txt"));){
            String in = null;
            while((in = inLine.readLine()) != null) {
                String[] temp = in.split(",");
                if(Integer.valueOf(temp[0]) == id){
                    res = Arrays.asList(temp);
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println("Caught an ERROR!");
            e.printStackTrace();
        }

        return res;
    }

}
