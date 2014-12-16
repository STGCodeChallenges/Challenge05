using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;

namespace STGCodeChallenge5
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Dictionary<string, int> numbers = new Dictionary<string, int>(){{"zero", 0}, {"one", 1}, {"two", 2}, {"three", 3}, {"four", 4}, {"five", 5}, {"six", 6}, {"seven", 7},
            {"eight", 8}, {"nine", 9}, {"ten", 10}, {"eleven", 11}, {"twelve", 12}, {"thirteen", 13}, {"fourteen", 14}, {"fifteen", 15}, {"sixteen", 16}, {"seventeen", 17},
            {"eighteen", 18}, {"nineteen", 19}, {"twenty", 20}, {"thirty", 30}, {"fourty", 40}, {"fifty", 50}, {"sixty", 60}, {"seventy", 70}, {"eighty", 80}, {"ninety", 90}};
        private Dictionary<string, int> placeSpecifiers = new Dictionary<string, int>() { { "hundred", 100 }, { "thousand", 1000 }, { "million", 1000000 }, { "billion", 1000000000 } };

        public MainWindow()
        {
            InitializeComponent();
            txtText.Text = "One thousand twenty five";
            //txtText.Text = "One thousand and twenty-five";
            //txtText.Text = "Two million one hundred and twenty-five";
            //txtText.Text = "two million, one hundred and twenty-five thousand, one hundred and fifty-five";
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            lblFinalValue.Content = "";
            lblErrorMessage.Content = "";
            lblValue.Visibility = System.Windows.Visibility.Hidden;
            try
            {
                lblValue.Visibility = System.Windows.Visibility.Visible;
                lblFinalValue.Content = processText(txtText.Text);
            }
            catch (Exception ex)
            {
                lblErrorMessage.Content = ex.Message;
            }
        }

        /// <summary>
        /// Process the input string.  Converts numbers in word form to numbers.
        /// </summary>
        /// <param name="textToConvert">Input text</param>
        /// <returns>The numeric value of the input text as a string</returns>
        private string processText(string textToConvert)
        {
            int total = 0;
            bool lastWasPlace = false;
            bool isNegative = false;
            List<int> numbersFromString = new List<int>();
            textToConvert = textToConvert.ToLower().Replace(" and ", " ");
            List<string> textPieces = textToConvert.Split(new char[] {' ', '-', ','}, StringSplitOptions.RemoveEmptyEntries).ToList();
            foreach (string piece in textPieces)
            {
                if (numbers.Keys.Contains(piece))
                {
                    if ((numbersFromString.Count > 0 && !lastWasPlace) || (numbersFromString.Count() > 0 && hasLargerModifier(textPieces.IndexOf(piece), numbersFromString.Last(), textPieces)))
                    {
                        numbersFromString[numbersFromString.Count() - 1] += numbers[piece];
                        lastWasPlace = false;
                    }
                    else
                    {
                        numbersFromString.Add(numbers[piece]);
                        lastWasPlace = false;
                    }
                }
                else if (placeSpecifiers.Keys.Contains(piece))
                {
                    numbersFromString[numbersFromString.Count() - 1] *= placeSpecifiers[piece];
                    lastWasPlace = true;
                }
                else if (piece == "negative" && textPieces.IndexOf(piece) == 0)
                {
                    isNegative = true;
                }
                else
                {
                    throw new Exception("Unrecognized word found: " + piece);
                }
            }
            foreach (int number in numbersFromString)
            {
                total += number;
            }
            if (isNegative)
            {
                total *= -1;
            }
            return string.Format("{0:n0}",total);
        }

        /// <summary>
        /// Check to see if there is another place holder yet to be processed.
        /// </summary>
        /// <param name="startingIndex">Index of current value being processed within list of words in the input string</param>
        /// <param name="lastProcessedValue">Value of the last word(s) already processed</param>
        /// <param name="textPieces">List of the words from the input string</param>
        /// <returns>Returns true if there is a place holder modifier found after the index of the current value being processed; otherwise returns false.</returns>
        private bool hasLargerModifier(int startingIndex, int lastProcessedValue, List<string> textPieces)
        {
            for (int i = startingIndex; i < textPieces.Count(); i++)
            {
                if (placeSpecifiers.Keys.Contains(textPieces[i]) && placeSpecifiers[textPieces[i]] > lastProcessedValue)
                    return true;
            }
            return false;
        }
    }
}
