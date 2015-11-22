using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ResZd15_pm
{
	class CIPln_pm
	{
		private double[] a;
		public int n;
		private double[] root;

		public CIPln_pm()
		{
			degree(out n);
			if (n == -1)
			{

			}
			else
			{
				a = new double[n + 1];
				root = new double[n];
				for (int i = 0; i < n; i++)
				{
					root[i] = 0.99;
				}
				coefficients(ref a);
			}
		}

		static void coefficients(ref double[] a)
		{
			for (int i = 0; i < a.Length; i++)
			{
				try
				{
					Console.Write("   Коэффицент при x^{0} = ", a.Length - (i + 1));
					a[i] = Convert.ToDouble(Console.ReadLine());
				}
				catch
				{
					Console.WriteLine("Неверные формат ввода данных! Повторите");
					i--;
				}
			}

		}
		private void degree(out int x)
		{
			Console.Write(" Введите степень полинома (-1 - конец работы):");
			try
			{
				x = Convert.ToInt32(Console.ReadLine());
			}
			catch
			{
				Console.WriteLine("Неверный формат ввода, повторите попытку!");
				degree(out x);
			}
		}

		public void output()
		{
			Console.Write("   ");
			Console.Write("{0}x^{1} ", a[0], n);
			for (int i = 1; i <= n; i++)
			{
				if (a[i] >= 0)
					Console.Write("+{0}x^{1} ", a[i], n - i);
				else
					Console.Write("{0}x^{1} ", a[i], n - i);
			}
			Console.WriteLine();
			Console.WriteLine();
		}

		public double value(double[] a,double x)
		{
			double f = a[0];
			for (int i = 0; i < a.Length - 1; i++)
			{
				f *= x;
				f += a[i + 1];
			}
			return (f);
		}

		public double[] derivative(double[] a)
		{
			double[] b = new double[n];
			for (int i = 0; i < a.Length - 1; i++)
			{
				b[i] = a[i] * (n - i);
			}
			return (b);
		}

    public double Mc1()
    {
      int m = a.Length - 1;
      int o = a.Length - 1;
      for( int i = a.Length - 1; i > 0; i-- )
      {
        if( a[i] < 0 )
        {
          if( Math.Abs(a[i]) > Math.Abs(a[m]) )
          {
            m = i;
          }
        }
      }
      for( int i = a.Length - 1; i > 0; i-- )
      {
        if( a[i] < 0 )
        {
          o = i;
          break;
        }
      }
      return (1 + Math.Pow(a[m] / a[a.Length - 1], o));
    }

		public double Mc4()
		{
			int m = a.Length - 1;
			int o = a.Length - 1;
			for (int i = a.Length - 1; i > 0; i--)
			{
				if (Math.Abs(a[i]) > Math.Abs(a[m]))
				{
					m = i;
				}
			}
			for (int i = 0; i < a.Length - 1; i = i + 2)
			{
				if (a[i] > 0)
				{
					o = i;
				}
			}
			return (-1*(1 + Math.Pow(a[m]/a[a.Length - 1],o)));
		}

		public void Newton(double c, double d)
		{
			double h = 0.1;
			while(c + h < d)
				{
					if (value(a, c) * value(a, c + h) < 0)
					{
						roots(c);
						c = c + h;
					}
					else
					{
						c = c + h;
					}
				}
		}

		public void roots(double x0)
		{
			double [] x = new double[100];
			x[0] = x0;
			int i = -1;
			do
			{
				i++;
				x[i + 1] = x[i] - (value(a, x[i]) / value(derivative(a), x[i]));
                Console.WriteLine("{0} x[{0}] = {1}  f(x[{0}]) = {2}", i + 1, x[i], value(a,x[i]));
			}
			while (Math.Abs(x[i + 1] - x[i]) > 0.000000001);
			Console.WriteLine("___________________________");
			Console.WriteLine();
			Console.WriteLine();
			Console.WriteLine();
                
		}
	}
}