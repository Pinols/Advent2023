package day1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Character.isDigit;

public class DayOne {
    public static void main(String[] args){
        String rawInput = null;
        try{
            rawInput = FileUtils.readFileToString(new File("C:\\Users\\Stefano\\Desktop\\Advent 2023\\day 1\\input.txt"), Charset.defaultCharset());
        }catch(Exception e){
            e.printStackTrace();
        }
        String[] inputLines = rawInput.split("\r\n");
        int totalSum = 0;
        
        // Part 1
        for(String line : inputLines){

            List<Character> numbers = new ArrayList<>();
            for(Character c : line.toCharArray()){
                if(isDigit(c)){
                    numbers.add(c);
                }
            }
            if(!numbers.isEmpty()){
                int lineNumber = Integer.parseInt( "" + numbers.get(0) + numbers.get((numbers.size()-1)) );
                totalSum += lineNumber;
            }
        }
        System.out.println("Part 1's solution : "+totalSum);
        
        // Part 2
        totalSum =0;
        Map<String, Integer> mapping = Map.of(
                "zero",0,
                "one",1,
                "two",2,
                "three",3,
                "four",4,
                "five",5,
                "six",6,
                "seven",7,
                "eight",8,
                "nine",9
        );
        for(String line : inputLines){
            
            List<Integer> numbers = new ArrayList<>();
            
            for(int i=0; i<line.length();i++){
                
                if(isDigit(line.charAt(i))){
                    numbers.add(Integer.parseInt(String.valueOf(line.charAt(i))));
                }else{
                    for (String key : mapping.keySet()){
                        if( line.substring( i, Math.min((i + key.length()), line.length())).equalsIgnoreCase(key) ){
                            numbers.add(mapping.get(key));
                        }
                    }
                }
                
            }
            int lineNumber = Integer.parseInt( "" + numbers.get(0) + numbers.get((numbers.size()-1)) );
            totalSum += lineNumber;
        }
        System.out.println("Part 2's solution is : " + totalSum);
    }
}
