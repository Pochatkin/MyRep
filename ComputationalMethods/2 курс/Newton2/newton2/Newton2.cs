using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Newton2
{
	class Newton2
	{
		static void Main(string[] args)
		{
			Console.WriteLine("Початкин М.А. март 2015г.");
			Console.WriteLine("Программа решения систем линейных уравнений ");
			Console.WriteLine("методом Ньютона");
			Console.WriteLine();
			Console.WriteLine();

			double a, k;

			Console.WriteLine("Введите коэффициент k:");
			input(out k);
			Console.WriteLine("Введите коэффициент a");
			input(out a);
			Newton(k, a);
			Console.ReadLine();
			
			






		}


		static void input(out double x)
		{
			try
			{
				x = Convert.ToDouble(Console.ReadLine());
			}
			catch
			{
				Console.WriteLine("Неверный формат ввода. Повторите попытку!");
				input(out x);
			}

		}
		static double f(double x, double y, double k)
		{
			return (Math.Tanh(x * x - y) - k * (x + y));
		}

		static double g(double x, double y, double a)
		{
			return ((x - 0.2) * (x - 0.2) - a * y * y - 1.5);
		}

		static double fx(double x, double y, double k)
		{
            return (2 * x / (Math.Cosh(x * x - y) * Math.Cosh(x * x - y)) - k);
		}

		static double fy(double x, double y, double k)
		{
            return ((-1) / (Math.Cosh(x * x - y) * Math.Cosh(x * x - y)) - k);
		}

		static double gx(double x)
		{
			return (2 * (x - 0.2));
		}

		static double gy(double y, double a)
		{
			return ((-a) * 2 * y);
		}
		

		static void Newton(double k, double a)
		{

			double h = 0.01;
			double u,v;
			u = v = 0;
			while(v + h < 2)
			{
				while (u + h < 2)
				{
					if (Math.Abs(f(u,v,k)) < 0.1 && Math.Abs(g(u,v,a)) < 0.1)
					{
						roots(u, v, a, k);
                        break;
                    
					}
					u = u + h;
				}
                if (u + h < 2)
                {
                    break;
 
                }
				u = 0;
				v = v + h;
			}
		}

		static void roots(double x0, double y0, double a, double k)
		{
			double[] x = new double[10];
			double[] y = new double[10];
            if (Math.Abs(x0 - x[0]) > 0.1 || Math.Abs(y0 - y[0]) > 0.1)
            {
                Console.WriteLine("i               x[i]       y[i]       f(x[i],y[i])       g(x[i],y[i])");
                x[0] = x0;
                y[0] = y0;
                int i = -1;
                Console.WriteLine("{0}    {1:f8}        {2:f8}       {3:f8}        {4:f8}", i + 1, x[i + 1], y[i + 1], f(x[i + 1], y[i + 1], k), g(x[i + 1], y[i + 1], a));
                do
                {
                    i++;
                    x[i + 1] = x[i] + ((fy(x[i], y[i], k) * g(x[i], y[i], a) - f(x[i], y[i], k) * gy(y[i], a)) / (fx(x[i], y[i], k) * gy(y[i], a) - fy(x[i], y[i], k) * gx(x[i])));
                    y[i + 1] = y[i] + ((f(x[i], y[i], k) * gx(x[i]) - fx(x[i], y[i], k) * g(x[i], y[i], a)) / (fx(x[i], y[i], k) * gy(y[i], a) - fy(x[i], y[i], k) * gx(x[i])));
                    Console.WriteLine("{0}    {1:f8}        {2:f8}       {3:f8}        {4:f8}", i + 1, x[i + 1], y[i + 1], f(x[i + 1], y[i + 1], k), g(x[i + 1], y[i + 1], a));
                }
                while (Math.Abs(x[i + 1] - x[i]) > 0.000001 && Math.Abs(y[i + 1] - y[i]) > 0.000001);
                Console.WriteLine("___________________________");
                Console.WriteLine();
                Console.WriteLine();
                Console.WriteLine();
            }

		}

	}
}
