package day4;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day4 {
    @SuppressWarnings("all")
    public static void main(String[] args){
        String rawInput = null;
        try{
            rawInput = FileUtils.readFileToString(new File("src/main/java/day4/input.txt"), Charset.defaultCharset());
        }catch(Exception e){
            e.printStackTrace();
        }
        String[] inputLines = rawInput.split("\r\n");
        
        int sum = 0;
        int points = 0;
        List<Integer> winningNumbers = new ArrayList<>();
        List<Integer> myDraws = new ArrayList<>();
        for(String line : inputLines){
            winningNumbers = new ArrayList<>();
            myDraws = new ArrayList<>();
            points=0;
            // this doesn't work with test input anymore, was i=8
            for(int i=10;i<line.split("\\|")[0].length();i+=3 ){
                winningNumbers.add( Integer.parseInt(line.split("\\|")[0].substring(i,i+2).replace(' ','0') ) );
            }
            for(int i=1;i<line.split("\\|")[1].length();i+=3 ){
                myDraws.add( Integer.parseInt(line.split("\\|")[1].substring(i,i+2).replace(' ','0') ) );
            }
            
            for(Integer i:winningNumbers){
                if(myDraws.contains(i)){
                    if(points==0) points++;
                    else points *= 2;
                }
            }
            sum+=points;
        }
        System.out.println(winningNumbers);
        System.out.println(myDraws);
        System.out.println(sum);
        //sol 1 is 21558


        // takes  about a min to execute >.<

        sum = 0;
        winningNumbers = new ArrayList<>();
        myDraws = new ArrayList<>();
        Map<Integer, StringReps> mapToRepetitions = new TreeMap<>();
        int k=0;
        for(String s: inputLines){
            StringReps rep = new StringReps();
            rep.value = s;
            rep.repetitions=1;
            mapToRepetitions.put(k++ , rep);
        }
        
        for(Integer cardNumber : mapToRepetitions.keySet() ){
            
            for(int n = 0; n<mapToRepetitions.get(cardNumber).repetitions; n++){

                System.out.println("Card "+(cardNumber+1)+" executed "+(n+1)+" times");
                
                winningNumbers = new ArrayList<>();
                myDraws = new ArrayList<>();
                
                // this doesn't work with test input anymore, was i=8
                for(int i=10;i<mapToRepetitions.get(cardNumber).value.split("\\|")[0].length();i+=3 ){
                    winningNumbers.add( Integer.parseInt(mapToRepetitions.get(cardNumber).value.split("\\|")[0].substring(i,i+2).replace(' ','0') ) );
                }
                for(int i=1;i<mapToRepetitions.get(cardNumber).value.split("\\|")[1].length();i+=3 ){
                    myDraws.add( Integer.parseInt(mapToRepetitions.get(cardNumber).value.split("\\|")[1].substring(i,i+2).replace(' ','0') ) );
                }
                int matches = 0;
                for(Integer i:winningNumbers){
                    if(myDraws.contains(i)){
                        matches++;
                    }
                }
                for(int i=1;i<matches+1;i++){
                    System.out.println("adding rep to card "+(cardNumber+i+1) );
                    mapToRepetitions.get((cardNumber+i)).repetitions++;
                }
            }
            
            
        }
        
        for(StringReps stringRep : mapToRepetitions.values()){
            sum+=stringRep.repetitions;
        }
        System.out.println("Sol 2: "+sum);
        // sol 2 was 10425665
    }
}
