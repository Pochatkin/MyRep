using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Calculator
{
	public partial class Form1 : Form
	{

		public Form1()
		{
			InitializeComponent();
		}

		int n = 0;
		string buff = "0";
		bool smth = false;

		private bool isFull(string s)
		{
			if (s.Length > 10)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		private string correction(string s,int n)
		{
			int k = 0;
			int i = 0;
			while (k < n) 
			{
				if (s[i].Equals('('))
				{
					s.Remove(i, 1);
					k++;
				}
			}
			return s;
		}

		private string doSmth(string s)
		{
			bool reallyDoingSmth = false;
			double temp = 0;
			for (int i = 1; i < s.Length; i++)
			{
				if (s[i] == '/'){
					temp = Convert.ToDouble(s.Substring(0, i)) / Convert.ToDouble(s.Substring(i + 1));
					reallyDoingSmth = true;
					break;
				}
				if (s[i] == '*') {
					temp = Convert.ToDouble(s.Substring(0, i)) * Convert.ToDouble(s.Substring(i + 1));
					reallyDoingSmth = true;
					break;
				}
				if (s[i] == '+') 
				{
					temp = Convert.ToDouble(s.Substring(0, i)) + Convert.ToDouble(s.Substring(i + 1));
					reallyDoingSmth = true;
					break;
				}
				if (s[i] == '-') {
					temp = Convert.ToDouble(s.Substring(0, i)) - Convert.ToDouble(s.Substring(i + 1));
					reallyDoingSmth = true;
					break;
				}
			}
			smth = false;
			if (reallyDoingSmth)
			{
				return temp.ToString();
			}
			else
			{
				return s;
			}
		}

		private double doSmthToo(string s)
		{
			List<double> tempNum = new List<double>();
			List<char> tempOper = new List<char>();
			double temp = 0;
			for (int i = 0; i < s.Length; i++)
			{
				if ((s[i].Equals('-')) || (s[i].Equals('+')) || (s[i].Equals('*')) || (s[i].Equals('/')))
				{
					tempNum.Add(Convert.ToDouble(s.Substring(0, i)));
					tempOper.Add(s.Substring(i, 1).ToCharArray()[0]);
				}
			}
			for (int i = 0; i < tempNum.Count; i++) 
			{
				if (tempOper[i].Equals('-'))
				{
					temp -= tempNum[i];
				}
				if (tempOper[i].Equals('+'))
				{
					temp += tempNum[i];
				}
				if (tempOper[i].Equals('*'))
				{
					temp *= tempNum[i];
				}
				if (tempOper[i].Equals('/'))
				{
					temp /= tempNum[i];
				}
			}
			return temp;
		}


		private void button1_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "1";
				}
				else
				{
					buff += "1";
				}
			}
			textBox3.Text = buff;
		}

		private void button3_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "2";
				}
				else
				{
					buff += "2";
				}
			}
			textBox3.Text = buff;
		}

		private void button2_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "3";
				}
				else
				{
					buff += "3";
				}
			}
			textBox3.Text = buff;
		}

		private void button4_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "4";
				}
				else
				{
					buff += "4";
				}
			}
			textBox3.Text = buff;
		}

		private void button5_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "5";
				}
				else
				{
					buff += "5";
				}
			}
			textBox3.Text = buff;
		}

		private void button6_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "6";
				}
				else
				{
					buff += "6";
				}
			}
			textBox3.Text = buff;
		}

		private void button7_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "7";
				}
				else
				{
					buff += "7";
				}
			}
			textBox3.Text = buff;
		}

		private void button8_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "8";
				}
				else
				{
					buff += "8";
				}
			}
			textBox3.Text = buff;
		}

		private void button9_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "9";
				}
				else
				{
					buff += "9";
				}
			}
			textBox3.Text = buff;
		}

		private void button16_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (buff.Length > 0)
				{
					buff += "0";
				}
			}
			textBox3.Text = buff;
		}

		private void button14_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (n == 0)
				{
					if (smth)
					{
						buff = doSmth(buff).ToString();
						buff += "+";
						smth = true;
					}
					else
					{
						buff += "+";
						smth = true;
					}
				}
				else
				{
					buff += "+";
				}
			}
			textBox3.Text = buff;
		}

		private void button10_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (n == 0)
				{

					if (smth)
					{
						buff = doSmth(buff).ToString();
						buff += "-";
						smth = true;
					}
					else
					{
						buff += "-";
						smth = true;
					}
				}
				else
				{
					buff += "-";
				}
			}
			textBox3.Text = buff;

		}

		private void button11_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (n == 0)
				{

					if (smth)
					{
						buff = doSmth(buff).ToString();
						buff += "*";
						smth = true;
					}
					else
					{
						buff += "*";
						smth = true;
					}
				}
				else
				{
					buff += "*";
				}
			}
			textBox3.Text = buff;

		}

		private void button12_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				if (n == 0)
				{
					if (smth)
					{
						buff = doSmth(buff).ToString();
						buff += "/";
						smth = true;
					}
					else
					{
						buff += "/";
						smth = true;
					}
				}
				else
				{
					buff += "/";
				}
			}
			textBox3.Text = buff;
		}

		private void button13_Click(object sender, EventArgs e)
		{
			buff = doSmth(buff);
			textBox3.Text = buff;
		}

		private void button15_Click(object sender, EventArgs e)
		{
			textBox3.Clear();
			buff = "0";
			textBox3.Text = buff;
		}

		private void button19_Click(object sender, EventArgs e)
		{
			for (int i = buff.Length - 1; i >= 0; i--)
			{
				if (buff[i] == '/')
				{
					buff = buff.Insert(i + 1, "-");
					break;
				}
				if (buff[i] == '*')
				{
					buff = buff.Insert(i + 1, "-");
					break;
				}
				if (buff[i] == '+')
				{
					buff = buff.Insert(i + 1, "-");
					break;
				}
				if (buff[i] == '-')
				{
					buff = buff.Remove(i, 1);
					
					break;
				}
				if (i == 0)
				{
					buff = buff.Insert(0, "-");
				}
			}
			textBox3.Text = buff;
		}

		private void button17_Click(object sender, EventArgs e)
		{
			n++; 
			if (!isFull(buff))
			{
				if (buff.Equals("0"))
				{
					buff = "(";
				}
				else
				{
					buff += "(";
				}
			}
			textBox3.Text = buff;
		}

		private void button18_Click(object sender, EventArgs e)
		{
			n--;
			if (!isFull(buff))
			{
				if (!buff.Equals("0"))
				{
					buff += ")";
				}
			}
			textBox3.Text = buff;
		}
	}
}
