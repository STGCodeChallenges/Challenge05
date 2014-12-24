package com.code.challenge;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringNumberConvert {

	
	private static final Map<String, Integer> NUMBERS = new HashMap<String, Integer>();	
	static {		
		NUMBERS.put("ONE",   1);
		NUMBERS.put("TWO",   2);
		NUMBERS.put("THREE", 3);
		NUMBERS.put("THIR",  3);
		NUMBERS.put("FOUR",  4);
		NUMBERS.put("FIVE",  5);
		NUMBERS.put("FIV",   5); //Common misspelling inclusion
		NUMBERS.put("FIF",   5);
		NUMBERS.put("SIX",   6);
		NUMBERS.put("SEVEN", 7);
		NUMBERS.put("EIGHT", 8);
		NUMBERS.put("EIGH",  8);
		NUMBERS.put("NINE",  9);
		NUMBERS.put("TEN",  10);
		NUMBERS.put("ELEVEN", 11);
		NUMBERS.put("TWELVE", 12);
		NUMBERS.put("TEEN",   10);	//Repeat use of 'teen' sufix (add to ten)
		NUMBERS.put("TWENTY",  20);
		NUMBERS.put("THIRTY",  30);
		NUMBERS.put("FORTY",   40);
		NUMBERS.put("FOURTY",  40); //Common misspelling inclusion
		NUMBERS.put("FIFTY",   50);
		NUMBERS.put("SIXTY",   60);
		NUMBERS.put("SEVENTY", 70);
		NUMBERS.put("EIGHTY",  80);
		NUMBERS.put("NINETY",  90);
		NUMBERS.put("HUNDRED", 100);
		NUMBERS.put("THOUSAND",1000);
		NUMBERS.put("MILLION", 1000000);
		NUMBERS.put("BILLION", 1000000000);
		
//  Convert to BigDecimal to support very large numbers:
//		NUMBERS.put("TRILLION", 1000000000000);
//		NUMBERS.put("QUADRILLION",  1000000000000000);
//		NUMBERS.put("QUINTILLION",  1000000000000000000);
//		NUMBERS.put("SEXTILLION",   1000000000000000000000);
//		NUMBERS.put("SEPTILLION",   100000000000000000000000);
//		NUMBERS.put("OCTILLION",    1000000000000000000000000000);
//		NUMBERS.put("NONILLION",    1000000000000000000000000000000);
//		NUMBERS.put("DECILLION",    1000000000000000000000000000000000);
//		NUMBERS.put("UNDECILLION",  1000000000000000000000000000000000000);
//		NUMBERS.put("DUODECILLION", 1000000000000000000000000000000000000000);
//		NUMBERS.put("TREDECILLION", 1000000000000000000000000000000000000000000);
//		NUMBERS.put("QUATTUORDECILLION", 1000000000000000000000000000000000);
//		NUMBERS.put("VIGINTILLION", 1000000000000000000000000000000000000000000000000000000000000000);
//		NUMBERS.put("GOOGOL",       10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000);
//		NUMBERS.put("CENTILLION",   1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000); //1,000 followed by 100 groups of 3-zero sets.
		
	}
	

		
	/** Main Function to dissect a number expressed in words using the English Language.
	 *    - Only whole numbers are supported, not fractional values.
	 *    - Add support for negative numbers using 'negative, minus' and other synonyms.  
	 * @param args
	 */
	public static void main(String[] args) {
		
		displayInstructionsMarquee();
		int programKeepAlive = 1;
		
		while (programKeepAlive > 0) {
			System.out.print( "\n\n Please enter a number using English words: " );

			try  {
		        // Read in the text entered by the User:
		        String userInput = scanner.nextLine().toUpperCase();
		        
		        if (userInput.toLowerCase().matches("quit|end|exit|e|q|x")) {
		        	System.out.println("User requested exit. Terminating process... ");
		        	break;
		        }

				//Parse for Tokens, based on delimiters (' ', 'AND', '-', ',')
		        String[] numberSegments = userInput.split(" AND | |-|, |,");
		        
		        Integer convertedNumber = 0;
		        Integer placeholder = 0;  //Improve code using placeholder value (append it)
		        
		        //Reverse-traversal (process number backwards):
		        for (int i = numberSegments.length - 1; i >= 0; i--) {
		        	String numberString = numberSegments[i];
		        	Integer numberValue = NUMBERS.get(numberString);
		        		        		
	        		//Construct Special Cases:
	        		if (numberString.endsWith("TEEN")) {
	        			numberValue = NUMBERS.get("TEEN") + NUMBERS.get(numberString.subSequence(0, numberString.length() - 4));
	        		}
	        			        	
	        		//Incremental Addition:
	        		if (numberValue != null && numberValue > 0) {
	        		
	        			//Set numerical placement:
		        		if (i + 1 < numberSegments.length) {
			        		if (numberSegments[i+1].matches("HUNDRED|THOUSAND|MILLION|BILLION")) {
			        			convertedNumber = convertedNumber + placementCalculation(numberSegments, i);
			        			
			        		} else {
			        			convertedNumber = convertedNumber + numberValue;	
			        		}
			        		
		        		} else {
		        			convertedNumber = convertedNumber + numberValue;
		        		}
		        		
	        		} else {
		        		System.out.println("Unrecognized Number String or symbol: " + numberSegments[i]);
		        	}	        		
		        }	

		        //Comma-fy:
		        String convertedNumberString = addCommas(convertedNumber);		        
		        System.out.println("\n  -> Number:  " + convertedNumberString);
		        
		        //TESTING CALLS:
		        	//outputDeliminations(numberSegments);
		        		
			
			} catch (NumberFormatException nfe) {
	        	System.out.println( "\n  A non-numerical value was processed." );
	        	
	        } catch (Exception e) {
	        	System.out.println( "\n  An Exception Error has occurred." );
			}
			
		}

	}
	
	/*
	 *  Calculate the placement of the number, based the scale of it's neighbor. 
	 *  Also, we subtract the base amount, so we do not count it twice
	 */
	private static Integer placementCalculation(String[] numberSegments, int position) {
		return NUMBERS.get(numberSegments[position + 1]) 
				* NUMBERS.get(numberSegments[position]) 
				- NUMBERS.get(numberSegments[position + 1]);
	}
	
	
	/*
	 *  Convert number into a String representation with standard 3-digit comma separation.
	 *    -> Eventually convert to use BigDecimal
	 */
	private static String addCommas(Integer number) {
	    DecimalFormat df = new DecimalFormat("#,##0");
	    return df.format(number);
	}
	
	
	/*
	 *  Create a single shared Scanner for keyboard input
	 */
    private static Scanner scanner = new Scanner( System.in );
	
	
	private static void displayInstructionsMarquee() {
		String headerBorder = "\n\t +------------------------------------+";
		String contentFill  = "\n\t |                                    |";
		String footerBorder = "\n\t +------------------------------------+";
		System.out.print( headerBorder + contentFill );
		System.out.print( "\n\t |      STG ~ Code Challenge #5       |" );
		System.out.print( "\n\t | Number Word String Value Converter |"  + contentFill );
		System.out.print( "\n\t |           December, 2015           |" );
		System.out.print( contentFill + footerBorder );
		System.out.print( "\n\n Convert a string of words to the appropriate number." );
	}
	
	/**
	 * TESTING:  This simple method can be called to kick out a list 
	 *           of the numerical words in the user input.
	 * 
	 * @param textData
	 * @return
	 */
	private static void outputDeliminations (String[] textData) {        
        for (String numberSegment : textData) {
        	System.out.println("|" + numberSegment + "|");	
        }		
	}
	

}
