using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MethodOfGauss2
{
	class MethodOfGauss2
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
			a[0, 0] = 3.278164;
			a[1, 0] = 1.046583;
			a[2, 0] = -1.378574;
			a[3, 0] = -0.527466;
			a[0, 1] = 1.046583;
			a[1, 1] = 2.975937;
			a[2, 1] = 0.934251;
			a[3, 1] = 2.526877;
			a[0, 2] = -1.378574;
			a[1, 2] = 0.934251;
			a[2, 2] = 4.836173;
			a[3, 2] = 5.165441;
			output(a, n);
			int[] order = new int[n];
			for (int i = 0; i < n; i++)
			{
				order[i] = i;
			}
            Gauss(ref a, n, ref order);
			output(a, n);
			double z = 0;
			double[] x = new double[n];
			double[] y = new double[n];
			x = ReversGauss(a, n, order);
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


		static void Modif(ref double[,] a, int n, int i, ref int[] order)
		{
			int mj = 0;
			int mi = 0;
			for (int j = 0; j < n; j++)
			{
				order[j] = j;
			}
			max(a, i, n, ref mj, ref mi);
			for (int j = i; j < n; j++)
			{
				swap(ref a[i,j], ref a[mj,j]);
			}
			swap(ref order[i], ref order[mj]);
			for (int j = i; j < n + 1; j++) 
			{
				swap(ref a[j,mi], ref a[j,i]);
			}


			output(a, n);

		}

		static void max(double[,] a, int k, int n, ref int mj, ref int mi)
		{
			double m = -199999999;
			
			for (int i = k; i < n; i++)
			{
				for (int j = k; j < n; j++)
				{
					if (m < a[j, i])
					{
						m = a[j, i];
						mj = j;
						mi = i;
					}
                }
			}
		}

		static void swap(ref double a, ref double b)
		{
			double c;
			c = a;
			a = b;
			b = c;
		}

		static void swap(ref int a, ref int b)
		{
			int c;
			c = a;
			a = b;
			b = c;
		}

			
		static double[] ReversGauss(double[,] a, int n, int[] order)
		{
			double t = 0;
			double[] x = new double[n];
			for (int i = 0; i < n; i++)
			{
				for (int j = i; j < n; j++)
				{
					if (order[j] == i)
					{
						swap(ref x[i], ref x[order[j]]);
						
						break;
					}
				}
			}
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

		static double Check(double[,] a,ref int i, int j)
		{
			if (a[i,j] == 0)
			{
				i++;
				Check(a,ref i, j);
				return (a[i,j]);
			}
			else
			{
				return (a[i,j]);
			}
		}
		static double[,] Gauss(ref double[,] a, int n, ref int[] order)
		{
			double t = 0;
			for (int i = 0; i < n - 1; i++)
			{
				Modif(ref a, n, i, ref order);
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
