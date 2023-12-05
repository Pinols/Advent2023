package day2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

public class DayTwo {
    @SuppressWarnings("all")
    public static void main(String[] args){
        String rawInput = null;
        try{
            rawInput = FileUtils.readFileToString(new File("C:\\Users\\Stefano\\Desktop\\Advent 2023\\day 2\\input.txt"), Charset.defaultCharset());
        }catch(Exception e){
            e.printStackTrace();
        }
        String[] inputLines = rawInput.split("\r\n");
        
        final int totRed = 12, totGreen = 13, totBlue = 14;
        int solution = 0;
        
        for(int i=0; i< inputLines.length; i++){
            boolean possible=true;
            String currentLine = inputLines[i].split(": ")[1].replaceAll(";",",");
            
            String[] extractionsPerGame = currentLine.split(", ");
            
            for(String s: extractionsPerGame) {
                int number = Integer.parseInt( s.split(" ")[0] );
                String color = s.split(" ")[1];

                if (color.equals("red") && number>totRed )possible=false;
                if (color.equals("green") && number>totGreen)possible=false;
                if (color.equals("blue") && number>totBlue)possible=false;
            }
            System.out.println("Game "+(i+1)+" is possible :"+possible);
            if (possible) solution+= (i+1);
        }

        System.out.println("Solution 1 is : " + solution);
        // 1 is : 2256
        
        solution = 0;
        int minBlue =0, minRed=0, minGreen=0;
        
        for(int i=0; i< inputLines.length; i++){
            String currentLine = inputLines[i].split(": ")[1].replaceAll(";",",");

            String[] extractionsPerGame = currentLine.split(", ");
            minBlue =0;
            minRed=0;
            minGreen=0;

            for(String s: extractionsPerGame) {
                int number = Integer.parseInt( s.split(" ")[0] );
                String color = s.split(" ")[1];

                if (color.equals("red") && number>minRed )minRed=number;
                if (color.equals("green") && number>minGreen)minGreen=number;
                if (color.equals("blue") && number>minBlue)minBlue=number;
            }

            System.out.println("Game "+ (i+1) +" power is : "+ (minGreen * minBlue * minRed));
            solution += (minGreen * minBlue * minRed);
        }
        System.out.println("Solution 2 is : "+solution);
        //74229
    }
}
