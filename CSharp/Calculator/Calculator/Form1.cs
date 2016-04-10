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

		
		private List<string> standart_operators =
				new List<string>(new string[] { "(", ")", "+", "-", "*", "/", "^", "@" });

		private List<string> standart_number =
				new List<string>(new string[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" });
		//int n = 0;
		string buff = "0";
		//bool smth = false;

		public Form1()
		{
			InitializeComponent();
		}


		public decimal result(string input)
		{
			input = correction(input);
			input = toUnarMinus(input);
			List<string> operators = new List<string>(standart_operators);
			Stack<string> stack = new Stack<string>();
			Queue<string> queue = new Queue<string>(ConvertToPostfixNotation(input));
			string str = queue.Dequeue();
			while (queue.Count >= 0)
			{
				if (!operators.Contains(str))
				{
					try
					{
						stack.Push(str);
						str = queue.Dequeue();
					}
					catch 
					{
						stack.Clear();
						stack.Push("Unparsable");
						break;
						
					}
				}
				else
				{
					decimal summ = 0;
					switch (str)
					{

						case "+":
							{
								try 
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									decimal b = Convert.ToDecimal(stack.Pop());
									summ = a + b;
									break;
								}
								catch { break; }
							}
						case "-":
							{
								try
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									decimal b = Convert.ToDecimal(stack.Pop());
									summ = b - a;
									break;
								}
								catch { break; }
							}
						case "*":
							{
								try
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									decimal b = Convert.ToDecimal(stack.Pop());
									summ = a * b;
									break;
								}
								catch { break; }
							}
						case "/":
							{
								try
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									decimal b = Convert.ToDecimal(stack.Pop());

									summ = b / a;
									break;
								}
								catch { break; }
							}
						case "^":
							{
								try
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									decimal b = Convert.ToDecimal(stack.Pop());
									summ = Convert.ToDecimal(Math.Pow(Convert.ToDouble(b), Convert.ToDouble(a)));
									break;
								}
								catch { break; }
			
							}
						case "@":
							{
								try
								{
									decimal a = Convert.ToDecimal(stack.Pop());
									summ = -a;
									break;
								}
								catch {
									break;
								}
							}
					}


					stack.Push(summ.ToString());
					if (queue.Count > 0)
						str = queue.Dequeue();
					else
						break;
				}

			}
			return Convert.ToDecimal(stack.Pop());
		}

		private byte GetPriority(string s)
		{
			switch (s)
			{
				case "(":
				case ")":
					return 0;
				case "+":
				case "-":
					return 1;
				case "*":
				case "/":
					return 2;
				case "^":
					return 3;
				case "@":
					return 4;
				default:
					return 5;
			}
		}

		public string[] ConvertToPostfixNotation(string input)
		{
			List<string> operators = new List<string>(standart_operators);
			List<string> outputSeparated = new List<string>();
			Stack<string> stack = new Stack<string>();
			foreach (string c in Separate(input))
			{
				if (operators.Contains(c))
				{
					if (stack.Count > 0 && !c.Equals("("))
					{
						if (c.Equals(")"))
						{
							string s = stack.Pop();
							while (!s.Equals("("))
							{
								outputSeparated.Add(s);
								s = stack.Pop();
							}
						}
						else if (GetPriority(c) > GetPriority(stack.Peek()))
							stack.Push(c);
						else
						{
							while (stack.Count > 0 && GetPriority(c) <= GetPriority(stack.Peek()))
								outputSeparated.Add(stack.Pop());
							stack.Push(c);
						}
					}
					else
						stack.Push(c);
				}
				else
					outputSeparated.Add(c);
			}
			if (stack.Count > 0)
				foreach (string c in stack)
					outputSeparated.Add(c);

			return outputSeparated.ToArray();
		}

		private IEnumerable<string> Separate(string input)
		{
			int pos = 0;
			while (pos < input.Length)
			{
				string s = string.Empty + input[pos];
				if (!standart_operators.Contains(input[pos].ToString()))
				{
					if (Char.IsDigit(input[pos]))
						for (int i = pos + 1; i < input.Length &&
							(Char.IsDigit(input[i]) || input[i] == ',' || input[i] == '.'); i++)
							s += input[i];
					else if (Char.IsLetter(input[pos]))
						for (int i = pos + 1; i < input.Length &&
							(Char.IsLetter(input[i]) || Char.IsDigit(input[i])); i++)
							s += input[i];
				}
				yield return s;
				pos += s.Length;
			}
		}



		private bool isFull(string s)
		{
			if (s.Length > 30)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		private string correction(string input)
		{
			int n = 0;
			for (int i = 0; i < input.Length; i++)
			{
				if (input[i].Equals('('))
				{
					n++;
				}
				if (input[i].Equals(')'))
				{
					n--;
				}
			}
			if (n >= 0)
			{
				for (int i = 0; i < n; i++)
				{
					input = input.Insert(input.Length, ")");
				}
			}
			else
			{
				for (int i = 0; i < n; i++)
				{
					input = input.Insert(0, "(");
				}
			}
			return input;
		}

		//private string doSmth(string s)
		//{
		//	bool reallyDoingSmth = false;
		//	double temp = 0;
		//	for (int i = 1; i < s.Length; i++)
		//	{
		//		if (s[i] == '/'){
		//			temp = Convert.ToDouble(s.Substring(0, i)) / Convert.ToDouble(s.Substring(i + 1));
		//			reallyDoingSmth = true;
		//			break;
		//		}
		//		if (s[i] == '*') {
		//			temp = Convert.ToDouble(s.Substring(0, i)) * Convert.ToDouble(s.Substring(i + 1));
		//			reallyDoingSmth = true;
		//			break;
		//		}
		//		if (s[i] == '+') 
		//		{
		//			temp = Convert.ToDouble(s.Substring(0, i)) + Convert.ToDouble(s.Substring(i + 1));
		//			reallyDoingSmth = true;
		//			break;
		//		}
		//		if (s[i] == '-') {
		//			temp = Convert.ToDouble(s.Substring(0, i)) - Convert.ToDouble(s.Substring(i + 1));
		//			reallyDoingSmth = true;
		//			break;
		//		}
		//	}
		//	smth = false;
		//	if (reallyDoingSmth)
		//	{
		//		return temp.ToString();
		//	}
		//	else
		//	{
		//		return s;
		//	}
		//}

		//private double doSmthToo(string s)
		//{
		//	List<double> tempNum = new List<double>();
		//	List<char> tempOper = new List<char>();
		//	double temp = 0;
		//	for (int i = 0; i < s.Length; i++)
		//	{
		//		if ((s[i].Equals('-')) || (s[i].Equals('+')) || (s[i].Equals('*')) || (s[i].Equals('/')))
		//		{
		//			tempNum.Add(Convert.ToDouble(s.Substring(0, i)));
		//			tempOper.Add(s.Substring(i, 1).ToCharArray()[0]);
		//		}
		//	}
		//	for (int i = 0; i < tempNum.Count; i++) 
		//	{
		//		if (tempOper[i].Equals('-'))
		//		{
		//			temp -= tempNum[i];
		//		}
		//		if (tempOper[i].Equals('+'))
		//		{
		//			temp += tempNum[i];
		//		}
		//		if (tempOper[i].Equals('*'))
		//		{
		//			temp *= tempNum[i];
		//		}
		//		if (tempOper[i].Equals('/'))
		//		{
		//			temp /= tempNum[i];
		//		}
		//	}
		//	return temp;
		//}

		private string toUnarMinus(string input)
		{
			
			List<string> number = new List<string>(standart_number);
			for (int i = 0; i < input.Length; i++)
			{
				if (input[i].Equals('-'))
				{
					try
					{
						if (!number.Contains(input[i - 1].ToString()))
						{
							input = input.Remove(i, 1);
							input = input.Insert(i, "@");
						}
					}
					catch 
					{
						input = input.Remove(0, 1);
						input = input.Insert(0, "@");
					}
				}
			}
			return input;
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
				//if (n == 0)
				//{
				//	if (smth)
				//	{
				//		buff = doSmth(buff).ToString();
				//		buff += "+";
				//		smth = true;
				//	}
				//	else
				//	{
				//		buff += "+";
				//		smth = true;
				//	}
				//}
				//else
				//{
				//	buff += "+";
				//}
				buff += "+";
			}
			textBox3.Text = buff;
		}

		private void button10_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				//if (n == 0)
				//{
				//	if (smth)
				//	{
				//		buff = doSmth(buff).ToString();
				//		buff += "-";
				//		smth = true;
				//	}
				//	else
				//	{
				//		buff += "-";
				//		smth = true;
				//	}
				//}
				//else
				//{
				//	buff += "-";
				//}
				buff += "-";
			}
			textBox3.Text = buff;

		}

		private void button11_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				//if (n == 0)
				//{

				//	if (smth)
				//	{
				//		buff = doSmth(buff).ToString();
				//		buff += "*";
				//		smth = true;
				//	}
				//	else
				//	{
				//		buff += "*";
				//		smth = true;
				//	}
				//}
				//else
				//{
				//	buff += "*";
				//}
				buff += "*";
			}
			textBox3.Text = buff;

		}

		private void button12_Click(object sender, EventArgs e)
		{
			if (!isFull(buff))
			{
				//if (n == 0)
				//{
				//	if (smth)
				//	{
				//		buff = doSmth(buff).ToString();
				//		buff += "/";
				//		smth = true;
				//	}
				//	else
				//	{
				//		buff += "/";
				//		smth = true;
				//	}
				//}
				//else
				//{
				//	buff += "/";
				//}
				buff += "/";
			}
			textBox3.Text = buff;
		}

		private void button13_Click(object sender, EventArgs e)
		{
			buff = result(buff).ToString();
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
