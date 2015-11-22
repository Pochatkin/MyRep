using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;

namespace Adams
{
	class Program
	{
		static void Main(string[] args)
		{
			double[] x = new double[11];
			double[] y = new double[11];
			for(int i = 0; i < 11; i++)
			{
				x[i] = 0.1* i;
			}
			y[0] = 1;
			for(int i = 1; i < 5; i++)
			{
				y[i] = RungeKutt(x[i - 1], y[i - 1]);
 			}
			for(int i = 5; i < 11; i++)
			{
				y[i] = Adams(x, y, i - 1);
			}
			Output(x,y);
			using(StreamWriter sw = new StreamWriter("Text.txt"))
			{
				for(int i = 0; i < 5; i++)
				{
					sw.Write("(");
					sw.Write(x[i]);
					sw.Write(";");
					sw.Write(y[i]);
					sw.WriteLine(")");
				}

				for(int i = 5; i < 11; i++)
				{
					sw.Write("(");
					sw.Write(x[i]);
					sw.Write(";");
					sw.Write(y[i]);
					sw.WriteLine(")");
				}
			}
			Console.ReadLine();
		}

		static void Output(double[] x, double[] y)
		{
			double[,] f = new double[11,11];
			for(int i = 0; i < 11; i++)
			{
				for(int j = 0; j < 11; j++)
				{
					f[i, j] = 111;
				}
			}
			for(int j = 0; j < 11; j++)
			{
				f[j, 0] = x[j];
			}
			for(int j = 0; j < 11; j++)
			{
				f[j, 1] = y[j];
			}
			for(int j = 0; j < 11; j++)
			{
				f[j, 2] = Eta(x[j], y[j]);
			}
			for(int j = 1; j < 11; j++)
			{
				f[j, 3] = (Eta(x[j], y[j]) - Eta(x[j - 1], y[j - 1]));
			}
			for(int j = 2; j < 11; j++)
			{
				f[j, 4] = (Eta(x[j], y[j]) - 2 * Eta(x[j - 1], y[j - 1]) + Eta(x[j - 2], y[j - 2]));
			}
			for(int j = 3; j < 11; j++)
			{
				f[j, 5] = (Eta(x[j], y[j]) - 3 * Eta(x[j - 1], y[j - 1]) + 3 * Eta(x[j - 2], y[j - 2]) - Eta(x[j - 3], y[j - 3]));
			}
			for(int j = 4; j < 11; j++)
			{
				f[j, 6] = (Eta(x[j], y[j]) - 4 * Eta(x[j - 1], y[j - 1]) + 6 * Eta(x[j - 2], y[j - 2]) - 4 * Eta(x[j - 3], y[j - 3]) + Eta(x[j - 4], y[j - 4]));
			}
			for(int i = 0; i < 11; i++)
			{
				for(int j = 0; j < 11; j++)
				{
					if(f[i, j] != 111)
					{
						Console.Write("{0:f6}  ", f[i, j]);
					}
				}
				Console.WriteLine();
			}

		}

		static double Adams(double[] x, double[] y, int i)
		{
			double[] n = new double[5];
			n[4] = Eta(x[i],y[i]);
			n[3] = (Eta(x[i], y[i]) - Eta(x[i - 1], y[i - 1])) / 2;
			n[2] = (5 / 12) * (Eta(x[i], y[i]) - 2 * Eta(x[i - 1], y[i - 1]) + Eta(x[i - 2], y[i - 2]));
			n[1] = (3 / 8) * (Eta(x[i], y[i]) - 3 * Eta(x[i - 1], y[i - 1]) + 3 * Eta(x[i - 2], y[i - 2]) - Eta(x[i - 3], y[i - 3]));
			n[0] = (251 / 720) * (Eta(x[i], y[i]) - 4 * Eta(x[i - 1], y[i - 1]) + 6 * Eta(x[i - 2], y[i - 2]) - 4 * Eta(x[i - 3], y[i - 3]) + Eta(x[i - 4], y[i - 4]));
			return (y[i] + n[4] + n[3] + n[2] + n[1] + n[0]);
		}

		static double RungeKutt(double x,double y)
		{
			double k1 = Eta(x,y);
			double k2 = Eta(x + 0.05, y + k1 / 2);
			double k3 = Eta(x + 0.05, y + k2 / 2);
			double k4 = Eta(x + 0.1, y + k3);
			return (y + (k1 + 2 * k2 + 2 * k3 + k4) / 6);
		}

		static double Eta(double x, double y)
		{
			return (0.1 * (y * y * Math.Exp(x) - 2 * y));
		}
	}
}
