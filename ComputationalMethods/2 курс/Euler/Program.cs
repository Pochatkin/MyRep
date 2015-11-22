using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;

namespace Euler
{
	class Program
	{
		static void Main(string[] args)
		{
			double h1 = 0.1;
			double h2 = 0.2;
			double h3 = 0.05;
			double y0 = 1;
			double x0 = 0;
			MethodEuler(x0, y0, h1);
			MethodEuler(x0, y0, h2);
			MethodEuler(x0, y0, h3);
			Console.ReadLine();

		}

		static void MethodEuler(double x,double y, double h)
		{
			using(StreamWriter sw = new StreamWriter("Text.txt"))
			{

				do
				{
					sw.Write("(");
					sw.Write(x);
					sw.Write(";");
					sw.Write(y);
					sw.WriteLine(")");
					Console.WriteLine("{0:f6}   {1:f6}   {2:f6}    {3:f6}", x, y, Math.Abs(y - Math.Exp(-x)), modulo(x, y, h));
					y += Eta(x, y, h);
					x += h;
				}
				while(x < 1.001);
			}
			Console.WriteLine();
			Console.WriteLine();
		}

		static double Eta(double x, double y,double h)
		{
			double f = h *( y * y * Math.Exp(x) - 2 * y);
			return (f);
		}

		static double value(double x,double y)
		{
			return (y * y * Math.Exp(x) - 2 * y);
		}

		static double modulo(double x, double y,double h)
		{
			double m3 = 4;
			double m4 = 5;
			return (m4 / (2 * m3) * h * Math.Exp(m3 * x));
		}
	}
}
