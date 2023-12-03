package daythree;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class day3 {
    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        String rawInput = null;
        try{
            rawInput = FileUtils.readFileToString(new File("src/main/java/daythree/input.txt"), Charset.defaultCharset());
        }catch(Exception e){
            e.printStackTrace();
        }
        String[] inputLines = rawInput.split("\r\n");

        Character[][] map = new Character[inputLines.length][inputLines[0].length()];
        for(int i = 0;i< inputLines.length;i++){
            for(int j=0;j<inputLines[0].length();j++){
                map[i][j] = inputLines[i].charAt(j);
            }
        }
        // print for fun
        for(int i = 0;i< inputLines.length;i++){
            for(int j=0;j<inputLines[0].length();j++){
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }       
        // part one
        
        List<NumberPosition> allNumbers = new ArrayList<>();
        NumberPosition numberPosition = new NumberPosition();
        for(int i = 0;i< inputLines.length;i++){
            numberPosition = new NumberPosition();
            for(int j=0;j<inputLines[0].length();j++) {                
                if (isDigit(map[i][j])){
                    numberPosition.value+=map[i][j];    // i'm not using a public accessor you are
                    numberPosition.points.add(new Point(i,j));
                }else if(!numberPosition.value.equals("")){
                    allNumbers.add(numberPosition);
                    numberPosition = new NumberPosition();
                }
            }
        }
        
        for(int i = 0;i< inputLines.length;i++){
            for(int j=0;j<inputLines[0].length();j++) {
                if( !isDigit(map[i][j]) && !map[i][j].equals('.') ){
                    
                    // if symbol, look for surrounding numbers
                    for(NumberPosition currentNumber : allNumbers){
                        for(Point point : currentNumber.getPoints()){
                            if(point.x == i-1 && point.y == j-1 || point.x == i-1 && point.y == j || point.x == i-1 && point.y == j+1 
                                    || point.x == i && point.y == j-1 || point.x == i && point.y == j+1 
                                    || point.x == i+1 && point.y == j-1 || point.x == i+1 && point.y == j || point.x == i+1 && point.y == j+1 ){
                                currentNumber.isValid = true;
                            }
                        }
                    }                    
                }
            }
        }
        int sum = 0;
        for(NumberPosition num : allNumbers){
            if(num.isValid){
                sum+=Integer.parseInt(num.value);
            }
        }

        System.out.println("Solution 1 is : "+sum);
        // solution was : 556057


        // part 2
        sum=0;
        for(NumberPosition num : allNumbers){
            num.isValid=false;
        }
        for(int i = 0;i< inputLines.length;i++){
            for(int j=0;j<inputLines[0].length();j++) {
                if( map[i][j].equals('*') ){

                    // if gear, look for surrounding numbers
                    int gearTouchesCountNumbers = 0;
                    //reset all valids to avoid duplicates
                    for(NumberPosition num : allNumbers){
                        num.isValid=false;
                    }
                    for(NumberPosition currentNumber : allNumbers){
                        boolean touches = false;
                        for(Point point : currentNumber.getPoints()){
                            if(point.x == i-1 && point.y == j-1 || point.x == i-1 && point.y == j || point.x == i-1 && point.y == j+1
                                    || point.x == i && point.y == j-1 || point.x == i && point.y == j+1
                                    || point.x == i+1 && point.y == j-1 || point.x == i+1 && point.y == j || point.x == i+1 && point.y == j+1 ){
                                touches = true;
                                currentNumber.isValid = true;
                            }
                        }
                        if(touches)gearTouchesCountNumbers += 1;
                    }
                    List<Integer> horsePowers = new ArrayList<>();
                    if(gearTouchesCountNumbers == 2){
                        
                        for(NumberPosition num : allNumbers){
                            if (num.isValid)horsePowers.add(Integer.valueOf(num.value));
                        }
                        if(horsePowers.size()>2)throw new Exception("More than 2 horsepowers");
                        sum += (horsePowers.get(0) * horsePowers.get(1));
                    }         
                }
            }
        }

        System.out.println("Solution 2 is : "+sum);
        // solution was : 82824352
        
    }
}
