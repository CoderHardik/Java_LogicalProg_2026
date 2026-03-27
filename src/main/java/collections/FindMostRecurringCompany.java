/*
1. split string and extract last string in array and add it to arraylist
 */

import java.util.*;
public class FindMostRecurringCompany {
    public static void main(String[] args) {
        // Input as an array of strings (simulating the log file lines)
        String[] logs = {
            "Error at time 10:54 is for customer Google",
            "Error at time 11:00 is for customer Apple",
            "Error at time 11:05 is for customer Google",
            "Error at time 11:10 is for customer Apple",
            "Error at time 11:15 is for customer Apple"
        };

        analyzeErrors(logs);
    }

    public static void analyzeErrors(String [] logs){
        ArrayList <String> CompanyList = new ArrayList<>();
        //1. Create Arraylist with list of companies from error log
        for (String comp: logs)
            { if(comp.startsWith("Error")){
                String temp [] = comp.split(" ");
                CompanyList.add(temp[temp.length-1]);
            }//end of if
        }//end of for
        //2. Create map with company and their occurance
        HashMap<String, Integer> hm = new HashMap<>();
        for (String company: CompanyList){
            if (hm.containsKey(company)){
                hm.put(company,hm.get(company)+1);} // if -else can be replaced by hm.put(company, hm.getOrDefault(company, 0) + 1);
                else{
                    hm.put(company,1);
                }  
            } 
        //3. Find the maximum occurance of values of company with collections.max    
        int maxValueInMap=Collections.max(hm.values());
        //4. Find the name of company based on highest occurance found earlier
        for (Map.Entry <String, Integer> me: hm.entrySet()){
            if(me.getValue()==maxValueInMap){
                System.out.println("highest impacted company is "+me.getKey());
            }
        }
    }//End of analyzeError   
}
