using System;
using System.Collections.Generic;
using System.Linq;

/*
 Week 5 Challenge:

Convert a string of words to the appropriate number.  For instance,
One thousand twenty five should be converted to 1025 or 1,025
One thousand and twenty-five should be converted to 1025 or 1,025
Two million one hundred and twenty-five 2000125 or 2,000,125
Two million, one hundred and twenty-five thousand, one hundred and fifty-five
... 2125155 or 2,125,155

 */
namespace CodeChallenge05_NumStringConverter
{
	class Program
	{
		public static void Main()
		{
			string input;
			do
			{
				Console.WriteLine("\n\nEnter a string of words to convert to a number (q to quit): ");
				input = Console.ReadLine();
				Console.WriteLine("\nResult: {0}", ParseWordsAsNumbers(input));
			} while (input != "q");
		}

        public static string ParseWordsAsNumbers(string input)
		{
			Int64? num = ConvertWordsToNumber(input);
            if (num.HasValue)
            {
                return String.Format("{0} OR {0:#,###0}", num);
            }
            return "Error converting words to number.  Did you misspell something?";
		}

		public static Int64? ConvertWordsToNumber(string str)
		{
			var lookupValue = GetNumStringDictionary();
            var words = str.Split(new[] {",", " ", "-"}, StringSplitOptions.RemoveEmptyEntries);
            var numbers = new List<Int64>(words.Length); // numerical reprsentation of the words (ignores words not found in dictionary)
            var operands = new Int64?[words.Length]; // groups the numbers for final summation
            var operandIndex = 0;

            // loop through words and extract all valid numbers
            for (var i = 0; i < words.Length; i++)
            {
                var currentWord = words[i].ToLower();
                if (lookupValue.ContainsKey(currentWord) || IsCastableAsInt(currentWord))
                {
                    var currentNumber = IsCastableAsInt(currentWord) ? Convert.ToInt64(currentWord) : lookupValue[currentWord];
                    numbers.Add(currentNumber);
                }
            }

            // build operands according to positional base, then simply sum these operands together
            // e.g. Two million, one hundred and twenty-five thousand, one hundred and fifty-five
            // becomes 2,000,000 + 125,000 + 100 + 50 + 5
            while (numbers.Count > 0)
            {
                var maxNum = numbers.Max();
                var maxNumIndex = numbers.IndexOf(maxNum); // this index marks the end of the list for building the current operand
                operands[operandIndex] = GetOperand(numbers.GetRange(0, maxNumIndex + 1));
                operandIndex++;
                numbers.RemoveRange(0, maxNumIndex + 1); // remove numbers from list after operand has been extracted
            }

            Int64? result = null;
            foreach (var operand in operands)
            {
                if (operand != null)
                {
                    if (result == null)
                        result = operand;
                    else
                        result += operand;
                }
            }
			return result;
		}

        public static bool IsCastableAsInt(string str)
        {
            Int64 num;
            return Int64.TryParse(str, out num);
        }

        // builds a single number based off the numerical representation of each "number word" in a list
        // e.g. if the list was {1, 100, 25, 1000} (aka one hundred twenty-five thousand)
        // the operand would be 125,000
        public static Int64? GetOperand(List<Int64> nums)
        {
            Int64? operand = null;

            for (var i = 0; i < nums.Count; i++)
            {
                var currentNum = nums[i];

                if (operand == null)
                {
                    operand = currentNum;
                    continue;
                }

                if (currentNum > operand)
                    operand = (operand * currentNum);
                else
                    operand = (operand + currentNum);
            }

            return operand;
        }

		public static Dictionary<string, Int64> GetNumStringDictionary(string lang = "eng")
		{
			return new Dictionary<string, Int64>
			{
	            {"quintillion", 1000000000000000000},
                {"quadrillion", 1000000000000000},
                {"trillion", 1000000000000},
                {"billion", 1000000000},
				{"million", 1000000},
                {"thousand", 1000},
                {"hundred", 100},
                {"ninety", 90},
				{"eighty", 80},
				{"seventy", 70},
				{"sixty", 60},
				{"fifty", 50},
				{"forty", 40},
				{"thirty", 30},
				{"twenty", 20},
				{"nineteen", 19},
				{"eighteen", 18},
                {"seventeen", 17},
				{"sixteen", 16},
				{"fifteen", 15},
				{"fourteen", 14},
				{"thirteen", 13},
				{"twelve", 12},
				{"eleven", 11},
				{"ten", 10},
				{"nine", 9},
				{"eight", 8},
				{"seven", 7},
				{"six", 6},
				{"five", 5},
				{"four", 4},
				{"three", 3},
                {"two", 2},
				{"one", 1},
                {"zero", 0}				
			};
		}
	}
}
