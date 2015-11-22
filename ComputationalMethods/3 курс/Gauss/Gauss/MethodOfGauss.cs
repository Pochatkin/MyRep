using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gauss
{
    class MethodOfGauss
    {
		static void Main(string[] args)
		{
			Console.WriteLine();
			Console.WriteLine();
			Console.WriteLine();
			Console.WriteLine();
			Console.WriteLine();


			//Console.WriteLine("Введите размерность матрицы:");
			int n = 3;
			//correctInt(out n);
			double[,] a = new double[n + 1, n];
			//input(a, n);
			a[0, 0] = 3.278164; a[1, 0] = 1.046583; a[2, 0] = -1.378574; a[3, 0] = -0.527466;
			a[0, 1] = 1.046583; a[1, 1] = 2.975937; a[2, 1] = 0.934251; a[3, 1] = 2.526877;
			a[0, 2] = -1.378574; a[1, 2] = 0.934251; a[2, 2] = 4.836173; a[3, 2] = 5.165441;
			output(a, n);
			double z = 0;
			Gauss(ref a, n);
			output(a, n);
			double[] x = new double[n];
			double[] y = new double[n];
			x = ReversGauss(a, n);
			y = fault(x, a);
			for (int i = 0; i < x.Length; i++)
			{
				Console.WriteLine("x[{0}] = {1}", i, x[i]);
			}
			for (int i = 0; i < n; i++)
			{
				z += y[i] * y[i];
			}
			Console.WriteLine(Math.Sqrt(z));
				Console.ReadLine();





		}

		static double[] fault(double[] x, double[,] a)
		{
			double[] y = new double[x.Length];
			for (int i = 0; i < x.Length; i++)
			{
				y[i] = a[x.Length, i];
				for (int j = 0;  j < x.Length; j++)
				{
					y[i] -= a[j, i] * x[j];
				}
			}
			return (y);
		}


		static double[] ReversGauss(double[,] a, int n)
		{
			double t = 0;
			double[] x = new double[n];
			x[n - 1] = a[n, n - 1];
			for (int i = n - 1; i > 0; i--)
			{
				t = a[n, i - 1];
				for (int j = n - 1; j >= i; j--)
				{
					t -= a[j, i - 1] * x[j];
				}
				x[i - 1] = t;
				t = 0;
			}
			return (x);
		}

		static void output(double[,] a, int n)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n + 1; j++)
				{
					Console.Write("{0:f9}      ", a[j, i]);
				}
				Console.WriteLine();
			}
			Console.WriteLine();
		}

		static double Check(double[,] a, ref int i, int j)
		{
			if (a[i, j] == 0)
			{
				i++;
				Check(a, ref i, j);
				return (a[i, j]);
			}
			else
			{
				return (a[i, j]);
			}
		}
		static double[,] Gauss(ref double[,] a, int n)
		{
			double t = 0;
			for (int i = 0; i < n - 1; i++)
			{
				for (int j = i; j < n; j++)
				{
					t = Check(a, ref i, j);
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] / t;
					}
				}
				
				for (int j = i + 1; j < n; j++)
				{
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] - a[k, i];
					}
				}
				
			}
			a[n, n - 1] = a[n, n - 1] / a[n - 1, n - 1];
			a[n - 1, n - 1] = 1;
			return (a);
		}

		static void correctInt(out int a)
		{
			try
			{
				a = Convert.ToInt32(Console.ReadLine());
			}
			catch
			{
				Console.WriteLine("Неверный формат ввода, повторите попытку!");
				correctInt(out a);
			}
		}

		static void correctDouble(out double a)
		{
			try
			{
				a = Convert.ToDouble(Console.ReadLine());
			}
			catch
			{
				Console.WriteLine("Неверный формат ввода, повторите попытку!");
				correctDouble(out a);
			}

		}

		static void input(double[,] a, int n)
		{
			Console.WriteLine("Введите элементы матрицы:");
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					correctDouble(out a[j, i]);
				}
			}
			Console.WriteLine("Введите вектор значения:");
			for (int i = 0; i < n; i++)
			{
				correctDouble(out a[n, i]);
			}
		}
	}
}
