using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InvertMatrix
{
	class Program
	{

		static void Main(string[] args)
		{
			Console.WriteLine("Введите размерность матрицы:");
			int n;
			correctInt(out n);
			double[,] a = new double[2 * n + 1 , 2 * n];
			double[,] b = new double[n, n];
			input(b, n);
			for (int i = 0; i < 2 * n; i++)
			{
				for (int j = 0; j < 2 * n; j++)
				{
					a[i, j] = 0;
				}
			}
			int k = 0;
			for (int i = 0; i < 2 * n; i++) 
			{
				for (int j = 0; j < 2 * n; j++)
				{
					if ((i + j) % 3 == 0)
					{
						a[i, j] = b[i % 3, j % 3];
					}
					k++;
					if (k >= 3)
					{
						k = 0;
					}
				}
			}
			output(a, 2 * n);
			Gauss(ref a, 2 * n);
			output(a, 2 * n);
			double z = 0;
			double[] x = new double[2 * n];
			double[] y = new double[2 * n];
			x = ReversGauss(a, 2 * n);
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
				for (int j = 0; j < x.Length; j++)
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
					if (t < 0.00000001)
					{
						Console.WriteLine("Диагностика!");
					}
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] / t;
					}
				}
				output(a, n);
				for (int j = i + 1; j < n; j++)
				{
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] - a[k, i];
					}
				}
				output(a, n);
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
	//		Console.WriteLine("Введите вектор значения:");
		//	for (int i = 0; i < n; i++)
		//	{
		//		correctDouble(out a[n, i]);
		//	}
		}
	}
}
