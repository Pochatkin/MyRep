using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ResZd15_pm
{
	class CIPln_pm
	{
		private int[] a;
		public int n;
		private double[] root;

		public CIPln_pm()
		{
			degree (out n);
			if (n == -1)
			{

			}
			else
			{
				a = new int[n + 1];
				root = new double[n];
				for (int i = 0; i < n; i++)
				{
					root[i] = 0.99;
				}
				coefficients(ref a);
			}
		}

		static void coefficients(ref int[] a)
		{
			for ( int i = 0; i < a.Length; i++ )
			{
				try
				{
					Console.Write ("   Коэффицент при x^{0} = ", a.Length - ( i + 1 ));
					a[i] = Convert.ToInt32 (Console.ReadLine ());
				}
				catch
				{
					Console.WriteLine ("Неверные формат ввода данных! Повторите");
					i--;
				}
			}

		}
		private void degree(out int x)
		{
			Console.Write (" Введите степень полинома (-1 - конец работы):");
			try
			{
				x = Convert.ToInt32 (Console.ReadLine ());
			}
			catch
			{
				Console.WriteLine ("Неверный формат ввода, повторите попытку!");
				degree (out x);
			}
		}

		public void output()
		{
			Console.Write ("   ");
			Console.Write ("{0}x^{1} ", a[0], n);
			for ( int i = 1; i <= n; i++ )
			{
				if ( a[i] >= 0 )
					Console.Write ("+{0}x^{1} ", a[i], n - i);
				else
					Console.Write ("{0}x^{1} ", a[i], n - i);
			}
			Console.WriteLine ();
			Console.WriteLine ();
		}

		public double value(double x)
		{
			double f = a[0];
			for ( int i = 0; i < n; i++ )
			{
				f *= x;
				f += a[i + 1];
			}
			return(f);
		}

		public void derivative()
		{
			for ( int i = 0; i < n - 1; i++ )
			{
				a[i] = a[i] * ( n - i );
			}
			n--;
			output();
		}


		public void roots(double a, double b, double e1, double e2, double h)
		{
			int[] t = new int[n];
			int l = 0;
      int k = 0;
			double c = a + h;
			while(c < b)
			{
				if (value(a) * value(c) >= 0)
				{
					if ((value(a) == 0 || value(c) == 0) && root[k-1] != a && root[k-1] != c)
					{
						if (value(a) == 0)
						{
							root[k] = a;
							k++;
							l++;
						}
						else
						{
							root[k] = c;
							k++;
							l++;
						}
					}

				}
				else
				{
					double a1 = a;
					double c1 = c;
					while (Math.Abs(a1 - c1) >= e1)
					{
						if (value(a1) < 0)
						{
							if (value((a1 + c1) / 2) < 0)
							{
								a1 = (a1 + c1) / 2;
							}
							else
							{
								if (value((a1 + c1)/2) == 0 && root[k-1] != (a1+c1)/2)
								{
									root[k] = (a1 + c1)/2;
									k++;
									l++;
									break;
								}
								c1 = (a1 + c1)/2;
							}
						}
						else
						{
							if (value((a1 + c1) / 2) < 0)
							{
								c1 = (a1 + c1) / 2;
							}
							else
							{
								if (value((a1 + c1)/2) == 0)
								{
									root[k] = (a1 + c1)/2;
									k++;
									l++;
									break;
								}
								a1 = (a1 + c1)/2;
							}

						}
					}
					if (Math.Abs(a1-c1) <= e1)
					{
						root[k] = a1;
						k++;
						l++;
					}
				}
				a = c;
				c = c + h;
				t[l]++;
			}
			for (int j = 0; j < n; j++)
			{
				int m = 1;
			  if (Math.Abs(root[j] - Math.Round(root[j])) <= 0.0001)
				{
					root[j] = Math.Round(root[j]);
				}
				if (Math.Abs(value(root[j] + e1) - value(root[j] - e1)) < e2)
				{
					if (root[j] != 0.99)
					{
						Console.WriteLine("x[{0}] = {1}    f(x[{0}]) = {2}    {3}", m, root[j],value(root[j]),t[m]);
						m++;
						Console.WriteLine("x[{0}] = {1}    f(x[{0}]) = {2}    {3}", m, root[j], value(root[j]),t[m]);
						m++;
					}
				}
				else
				{
					if (root[j] != 0.99)
					{
						Console.WriteLine("x[{0}] = {1}     f(x[{0}]) = {2}     {3}", m, root[j], value(root[j]), t[m]);
						m++;
					}
				}
				
			}
		}
	}
}