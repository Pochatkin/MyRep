using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Integral
{
	class Program
	{
		static void Main(string[] args)
		{
			double I = 0.890411;
			Console.WriteLine("Прямоугольники");
			Console.WriteLine("I8              I16            IR            |I - I8|          A");
			Console.WriteLine("{0:f6}       {1:f6}      {2:f6}        {3:f6}       {4:f6}", rectangle(8), rectangle(16), RungeR(8), Math.Abs(I - rectangle(8)),modulo(1));
			Console.WriteLine();
			Console.WriteLine("Трапеции");
			Console.WriteLine("I8              I16            IR            |I - I8|          A");
			Console.WriteLine("{0:f6}       {1:f6}      {2:f6}        {3:f6}       {4:f6}", trapeze(8), trapeze(16), RungeT(8), Math.Abs(I - trapeze(8)), modulo(2));
			Console.WriteLine();
			Console.WriteLine("Симпсон");
			Console.WriteLine("I8              I16            IR            |I - I8|          A");
			Console.WriteLine("{0:f6}       {1:f6}      {2:f6}        {3:f6}       {4:f6}", Simpson(8), Simpson(16), RungeS(8), Math.Abs(I - Simpson(8)), modulo(3));

			Console.ReadLine();
		}

		static double modulo(int n)
		{
			switch (n)
			{
				case 1:
					return(0.4 * 0.4 * 0.4 / (24 * 8 * 8) * 91.107871);
				case 2:
					return(0.4 * 0.4 / (12 * 8 * 8) * 91.107871);
				case 3:
					return(0.4 * 0.4 * 0.4 * 0.4 * 0.4 / (2880 * 8 * 8 * 8 * 8) * 13580.650919);
			}
			return (0);
		}
		static double RungeR(int n)
		{
			return((deg(2) * rectangle(2 * n) - rectangle(n)) / (deg(2) - 1));
		}


		static double RungeT(int n)
		{
			return ((deg(2) * trapeze(2 * n) - trapeze(n)) / (deg(2) - 1));
		}


		static double RungeS(int n)
		{
			return ((deg(4) * Simpson(2 * n) - Simpson(n)) / (deg(4) - 1));
		}

		static double deg(int k)
		{
			if(k == 1)
			{
				return (2);
			}
			else
			{
				return(2 * deg(k - 1));
			}
		}

		static double value(double x)
		{
			return(1/(0.28 + Math.Sin(x)));
		}

		static double rectangle(int n)
		{
			double f = 0;
			double h = 0.4 / n;
			for(int i = 1; i <= n; i++)
			{
				f += value((2*i - 1) * h / 2);
			}
			return(h * f);
		}

		static double trapeze(int n)
		{
			double f = 0;
			double h = 0.4 / n;
			for(int i = 1; i < n; i++)
			{
				f += value(i * h);
			}
			return (h * (f + (value(0) + value(0.4)) / 2));
		}

		static double Simpson(int n)
		{
			double f = 0;
			double g = 0;
			double h = 0.4 / n;
			for(int i = 1; i < n; i++)
			{
				f += value((2 * i - 1) * h / 2);
				g += value(i * h);
			}
			f += value((2 * n - 1) * h / 2);
			return(h/6 * (value(0) + value(0.4) + 4 * f + 2 * g));
		}
	}
}
