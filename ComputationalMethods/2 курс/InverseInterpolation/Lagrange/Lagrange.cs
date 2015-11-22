﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Lagrange
{
    class Lagrange
    {
        static void Main(string[] args)
        {
			Console.WriteLine("");
			Console.WriteLine("");
			Console.WriteLine("");
			Console.WriteLine("");
			Console.WriteLine("");
			double pi = Math.PI;

			double[] x = new double[4];
			x[0] = pi / 3;
			x[1] = pi / 2;
			x[2] = 4 * pi / 5;
			x[3] = pi;
			double c = 0;
			double d = pi;
			double h = (d - c) / 20;

			double[] y = new double[21];
			for(int i = 0; i < 21; i++)
			{
				y[i] = c + i * h;
			}
			Table(y, x);
			Console.ReadLine();

				
            

        }
		static double w1(double[] x, double a, int k)
		{
			double f = 1;
			for(int i = 0; i < k; i++)
			{
				f *= (a - x[i]);
			}
			for(int i = k + 1; i < x.Length; i++)
			{
				f *= (a - x[i]);
			}
			return (f);
		}

		static double w(double[] x, double a)
		{
			double f = 1;
			for(int i = 0; i < x.Length; i++)
			{
				f *= (a - x[i]);
			}
			return(f);
		}

		static double dw(double[] x, double a)
		{
			double f = 0;
			double g = 1;
			for(int i = 0; i < x.Length; i++)
			{
				g = 1;
				for(int j = 0; j < i; j++)
				{
					g *= (a - x[j]);
				}
				for(int j = i + 1; j < x.Length; j++ )
				{
					g *= (a - x[j]);
				}
					f += g;
			}
			return (f);
		}

		static double value(double x)
		{
			return (Math.Sin(x));
		}


		static double Lagrang(double y,double[] x)
		{
			double L = 0;
			for(int i = 0; i < x.Length; i++)
			{
			
				L += (w1(x, y, i) / (dw(x, x[i]))) * value(x[i]);
			}
			return (L);	 
		}

		static double factorial(double x)
		{
			if (x==1)
			{
				return(1);
			}
			else
			{
				return (x * factorial(x - 1));
			}
			
		}

		static double modulo(double[] x, double a)
		{
			return (Math.Abs(w(x, a) / factorial(x.Length)));
		}

		static void Table(double[] y, double[] x)
		{
			using(StreamWriter sw = new StreamWriter("Text.txt"))
			{
				sw.WriteLine("x[i]        f(x[i])      L(x[i])        |f - L|        A");
				Console.WriteLine("x[i]        f(x[i])      L(x[i])        |f - L|        A");
				for(int i = 0; i < 21; i++)
				{
					sw.Write("(");
					sw.Write(y[i]);
					sw.Write(";");
					sw.Write(Lagrang(y[i], x));
					sw.WriteLine(")");
					Console.WriteLine("{0:f6}    {1:f6}     {2:f6}      {3:f6}      {4:f6}", y[i], value(y[i]), Lagrang(y[i], x), Math.Abs(value(y[i]) - Lagrang(y[i], x)), modulo(x, y[i]));
				}
			}
		}
    }
}
