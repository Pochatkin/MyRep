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

		int n;
		string buff = "";
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

		private double doSmth(string s)
		{
			double temp = 0;
			for (int i = 1; i < s.Length; i++)
			{
				if (s[i] == '/'){
					temp = Convert.ToDouble(s.Substring(0, i)) / Convert.ToDouble(s.Substring(i + 1));
					break;
				}
				if (s[i] == '*') {
					temp = Convert.ToDouble(s.Substring(0, i)) * Convert.ToDouble(s.Substring(i + 1));
					break;
				}
				if (s[i] == '+') {
					temp = Convert.ToDouble(s.Substring(0, i)) + Convert.ToDouble(s.Substring(i + 1));
					break;
				}
				if (s[i] == '-') {
					temp = Convert.ToDouble(s.Substring(0, i)) - Convert.ToDouble(s.Substring(i + 1));
					break;
				}
			}
			smth = false;
			return temp;
		}



		private void button1_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "1";
			}
			textBox3.Text = buff;
		}

		private void button3_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "2";
			}
			textBox3.Text = buff;
		}

		private void button2_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "3";
			}
			textBox3.Text = buff;
		}

		private void button4_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "4";
			}
			textBox3.Text = buff;
		}

		private void button5_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "5";
			}
			textBox3.Text = buff;
		}

		private void button6_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "6";
			}
			textBox3.Text = buff;
		}

		private void button7_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "7";
			}
			textBox3.Text = buff;
		}

		private void button8_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "8";
			}
			textBox3.Text = buff;
		}

		private void button9_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				buff += "9";
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
			textBox3.Text = buff;
		}

		private void button10_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
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
			textBox3.Text = buff;

		}

		private void button11_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
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
			textBox3.Text = buff;

		}

		private void button12_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
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
			textBox3.Text = buff;
		}

		private void button13_Click(object sender, EventArgs e)
		{
			buff = doSmth(buff).ToString();
			textBox3.Text = buff;
		}

		private void button15_Click(object sender, EventArgs e)
		{
			textBox3.Clear();
			buff = "";
			textBox3.Text = buff;
		}

		private void textBox3_TextChanged(object sender, EventArgs e)
		{
			
		}
		private void Form1_Load(object sender, EventArgs e)
		{

		}
	}
}
