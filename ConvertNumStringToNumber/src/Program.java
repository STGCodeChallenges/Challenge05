/**
 * Created with IntelliJ IDEA.
 * User: Kevin Burnett
 * Date: 12/15/14
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 *
 * Code Challenge #5
 * Convert a string of words to the appropriate number.  For instance,
 * One thousand twenty five should be converted to 1025 or 1,025
 * One thousand and twenty-five should be converted to 1025 or 1,025
 * Two million one hundred and twenty-five 2000125 or 2,000,125
 * Two million, one hundred and twenty-five thousand, one hundred and fifty-five ... 2125155 or 2,125,155
 * And so forth.
 *
 */

import java.util.*;

public class Program
{

    static boolean isValidInput = true;
    static long result = 0;
    static long finalResult = 0;

    static List<String> allowedStrings = Arrays.asList
    (
        "zero","one","two","three","four","five","six","seven",
        "eight","nine","ten","eleven","twelve","thirteen","fourteen",
        "fifteen","sixteen","seventeen","eighteen","nineteen","twenty",
        "thirty","forty","fifty","sixty","seventy","eighty","ninety",
        "hundred","thousand","million","billion","trillion"
    );

    public static long ConvertToNum(String input)
    {
        if(input != null && input.length()> 0)
        {
            //Handle input string requirements
            input = input.replaceAll("-", " ");
            input = input.toLowerCase().replaceAll(" and", " ");
            input = input.replaceAll(",", "");

            //Split string into individual words to act on
            String[] splittedParts = input.trim().split("\\s+");

            //Check for invalid words in input string
            for(String str : splittedParts)
            {
                if(!allowedStrings.contains(str))
                {
                    isValidInput = false;
                    System.out.println("Invalid word found : " + str);
                    break;
                }
            }

            //Convert a valid input string component
            if(isValidInput)
            {
                for(String str : splittedParts)
                {
                    if(str.equalsIgnoreCase("zero"))
                    {
                        result += 0;
                    }
                    else if(str.equalsIgnoreCase("one"))
                    {
                        result += 1;
                    }
                    else if(str.equalsIgnoreCase("two"))
                    {
                        result += 2;
                    }
                    else if(str.equalsIgnoreCase("three"))
                    {
                        result += 3;
                    }
                    else if(str.equalsIgnoreCase("four"))
                    {
                        result += 4;
                    }
                    else if(str.equalsIgnoreCase("five"))
                    {
                        result += 5;
                    }
                    else if(str.equalsIgnoreCase("six"))
                    {
                        result += 6;
                    }
                    else if(str.equalsIgnoreCase("seven"))
                    {
                        result += 7;
                    }
                    else if(str.equalsIgnoreCase("eight"))
                    {
                        result += 8;
                    }
                    else if(str.equalsIgnoreCase("nine"))
                    {
                        result += 9;
                    }
                    else if(str.equalsIgnoreCase("ten"))
                    {
                        result += 10;
                    }
                    else if(str.equalsIgnoreCase("eleven"))
                    {
                        result += 11;
                    }
                    else if(str.equalsIgnoreCase("twelve"))
                    {
                        result += 12;
                    }
                    else if(str.equalsIgnoreCase("thirteen"))
                    {
                        result += 13;
                    }
                    else if(str.equalsIgnoreCase("fourteen"))
                    {
                        result += 14;
                    }
                    else if(str.equalsIgnoreCase("fifteen"))
                    {
                        result += 15;
                    }
                    else if(str.equalsIgnoreCase("sixteen"))
                    {
                        result += 16;
                    }
                    else if(str.equalsIgnoreCase("seventeen"))
                    {
                        result += 17;
                    }
                    else if(str.equalsIgnoreCase("eighteen"))
                    {
                        result += 18;
                    }
                    else if(str.equalsIgnoreCase("nineteen"))
                    {
                        result += 19;
                    }
                    else if(str.equalsIgnoreCase("twenty"))
                    {
                        result += 20;
                    }
                    else if(str.equalsIgnoreCase("thirty"))
                    {
                        result += 30;
                    }
                    else if(str.equalsIgnoreCase("forty"))
                    {
                        result += 40;
                    }
                    else if(str.equalsIgnoreCase("fifty"))
                    {
                        result += 50;
                    }
                    else if(str.equalsIgnoreCase("sixty"))
                    {
                        result += 60;
                    }
                    else if(str.equalsIgnoreCase("seventy"))
                    {
                        result += 70;
                    }
                    else if(str.equalsIgnoreCase("eighty"))
                    {
                        result += 80;
                    }
                    else if(str.equalsIgnoreCase("ninety"))
                    {
                        result += 90;
                    }
                    else if(str.equalsIgnoreCase("hundred"))
                    {
                        result *= 100;
                    }
                    else if(str.equalsIgnoreCase("thousand"))
                    {
                        result *= 1000;
                        finalResult += result;
                        result = 0;
                    }
                    else if(str.equalsIgnoreCase("million"))
                    {
                        result *= 1000000;
                        finalResult += result;
                        result = 0;
                    }
                    else if(str.equalsIgnoreCase("billion"))
                    {
                        result *= 1000000000;
                        finalResult += result;
                        result = 0;
                    }
                    else if(str.equalsIgnoreCase("trillion"))
                    {
                        result *= 1000000000000L;
                        finalResult += result;
                        result = 0;
                    }
                }
                finalResult += result;
                result = 0;
            }
        }
        return(finalResult);
    }

    public static void main(String[] args)
    {
        //String input = "One thousand twenty five";
        //String input = "One thousand and twenty-five";
        //String input = "Two million one hundred and twenty-five";
        //String input = "Two million, one hundred and twenty-five thousand, one hundred and fifty-five";

        String input = "Seven trillion, forty billion, two hundred million, six hundred and thirty-five thousand, one hundred and fifty-five";

        long result = ConvertToNum(input);
        System.out.print("Result = " + result);
    }
}
