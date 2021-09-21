import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// What you can do with this is to make an automated writer to type stuff for you
// You can also give it commands to do like "|w5000" whitch will make it wait for 5 seconds
/* the commands you can do are the following

either "|n" or new line for an enter press
"|N" for writing a |
"|wTime|" for waiting a time amount in milliseconds
NOT WORKING: "|rMin|Max|" gets a random number between Min and Max
"|lLine|" goto a certain line of the code
"|aDirection" can move the arrow in a certain direction. The directions are udlr

 */

public class Main {

    private static final char Slash = "|".charAt(0);
    private static int pointer = 0;

    // Code to wait a time in milliseconds
    public static void Wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static String countTill(char[] item){
        String amount = "";
        pointer++;
        while (item[pointer]!=Slash){
            amount=amount+item[pointer];
            pointer++;
        }
        return amount;
    }

    public static void main(String[] args){
        String WhatToDo = "";

        // Read the file
        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                WhatToDo = WhatToDo + myReader.nextLine() + "|n";
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            Robot robot = new Robot();

            final char[] WhatToDoCapChar = WhatToDo.toUpperCase().toCharArray();
            final char[] WhatToDoChar = WhatToDo.toCharArray();
            // n= newLine, N= |, w= wait, l= gotoLine, r= random, a= arrow
            final char[] Suffixes = "nNwlra".toCharArray();
            final char[] ArrowDir = "udlr".toCharArray();

            for(pointer = 0;pointer < WhatToDoChar.length; pointer++) {
                if(WhatToDoChar[pointer]==Slash) {
                    pointer++;
                    char item = WhatToDoChar[pointer];
//                    System.out.println(item);
                    if(item == Suffixes[0]){
                        // New line
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                    }   if(item == Suffixes[1]) {
                        // Write |
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_BACK_SLASH);
                        robot.keyRelease(KeyEvent.VK_BACK_SLASH);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }   if(item == Suffixes[2]){
                        // Wait
                        Wait(Integer.parseInt(countTill(WhatToDoChar)));
                    }   if(item == Suffixes[3]){
                        // Goto line
                        pointer=Integer.parseInt(countTill(WhatToDoChar))-1;
                    }   if(item == Suffixes[4]){
//                        //  Random number
//                        int min = Integer.parseInt(countTill(WhatToDoChar));
//                        int max = Integer.parseInt(countTill(WhatToDoChar));
//                        System.out.println(Math.random(min, max));
                    }   if(item == Suffixes[5]){
                        pointer++;
                        char itemA = WhatToDoChar[pointer];
                        if(itemA == ArrowDir[0]){
                            robot.keyPress(KeyEvent.VK_UP);
                            robot.keyRelease(KeyEvent.VK_UP);
                        }   if(itemA == ArrowDir[1]){
                            robot.keyPress(KeyEvent.VK_DOWN);
                            robot.keyRelease(KeyEvent.VK_DOWN);
                        }   if(itemA == ArrowDir[2]){
                            robot.keyPress(KeyEvent.VK_LEFT);
                            robot.keyRelease(KeyEvent.VK_LEFT);
                        }   if(itemA == ArrowDir[3]){
                            robot.keyPress(KeyEvent.VK_RIGHT);
                            robot.keyRelease(KeyEvent.VK_RIGHT);
                        }
                    }
                } else {
                    boolean shift = checkCase.checkCase(WhatToDoChar[pointer]);
                    if(shift){
                        robot.keyPress(KeyEvent.VK_SHIFT);
                    }
                    robot.keyPress(WhatToDoCapChar[pointer]);
                    robot.keyRelease(WhatToDoCapChar[pointer]);
                    if(shift){
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
