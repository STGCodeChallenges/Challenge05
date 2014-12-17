using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace STGCodeChallenge141215
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnGo_Click(object sender, EventArgs e)
        {
            long total = 0, tval = 0;
            string input = txtInput.Text.Trim();

            // Remove any dashes, etc., and convert to lowercase
            input = RemoveExtraChars(input).ToLower();

            // Spit the words into an array
            string[] words = input.Split(' ');

            // Loop through the word array and convert to numbers
            foreach (string w in words)
            {
                long l = WordToLong(w);
                if (l >= 1000)
                {
                    tval *= l;
                    total += tval;
                    tval = 0;
                }
                else if (l >= 100)
                {
                    tval *= l;
                }
                else
                {
                    tval += l;
                }
            }
            total += tval;
            MessageBox.Show(string.Format("The answer is {0}.", total.ToString("N0")));
        }

        // Remove any characters (like dashes) that are not part of words
        private string RemoveExtraChars(string input)
        {
            StringBuilder sb = new StringBuilder();
            foreach (char c in input)
            {
                if (char.IsLetter(c))
                {
                    sb.Append(c);
                }
                else
                {
                    sb.Append(' ');
                }
            }
            return sb.ToString();
        }

        // Convert the word to a numeric value
        private long WordToLong(string word)
        {
            switch (word)
            {
                case "zero": return 0;
                case "one": return 1;
                case "two": return 2;
                case "three": return 3;
                case "four": return 4;
                case "five": return 5;
                case "six": return 6;
                case "seven": return 7;
                case "eight": return 8;
                case "nine": return 9;
                case "ten": return 10;
                case "eleven": return 11;
                case "twelve": return 12;
                case "thirteen": return 13;
                case "fourteen": return 14;
                case "fifteen": return 15;
                case "sixteen": return 16;
                case "seventeen": return 17;
                case "eighteen": return 18;
                case "nineteen": return 19;
                case "twenty": return 20;
                case "thirty": return 30;
                case "forty": return 40;
                case "fifty": return 50;
                case "sixty": return 60;
                case "seventy": return 70;
                case "eighty": return 80;
                case "ninety": return 90;
                case "hundred": return 100;
                case "thousand": return 1000;
                case "million": return 1000000;
                case "billion": return 1000000000;
                case "trillion": return 1000000000000;
                case "quadrillion": return 1000000000000000;
                case "quintillion": return 1000000000000000000;
                default:
                    return 0;
            }
        }
    }
}
