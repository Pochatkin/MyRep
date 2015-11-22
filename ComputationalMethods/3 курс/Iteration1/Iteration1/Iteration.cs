using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Iteration1
{
	class Iteration
	{
		static void Main(string[] args)
		{
			double[] real = new double[3];
			real[0] = 0.10000019;
			real[1] = 0.49999963;
			real[2] = 1.000000105;
			double[,] a = new double[3, 3];
			a[0, 0] = 3.278164; a[1, 0] = 1.046583; a[2, 0] = -1.378574;
			a[0, 1] = 1.046583; a[1, 1] = 2.975937; a[2, 1] = 0.934251;
			a[0, 2] = -1.378574; a[1, 2] = 0.934251; a[2, 2] = 4.836173;
			double[] b = new double[3];
			b[0] = -0.527466;
			b[1] = 2.526877;
			b[2] = 5.165441;
			Console.WriteLine("Input:");
			output(a, b, 3);
			double[,] H = new double[3, 3];
			H = build(a);
			double[] g = new double[3];
			for (int i = 0; i < 3; i++)
			{
				g[i] = b[i] / a[i, i];
			}
			Console.WriteLine("Method of Gauss:");
			Console.WriteLine("x = ({0}, {1}, {2})", real[0], real[1], real[2]);
			Console.WriteLine();
			Console.WriteLine("------------------------------------");
			Console.WriteLine("Matrix H, and vector g");
			output(H, g, 3);
			double n = norm(H);
			Console.WriteLine();
			Console.WriteLine("||H|| = {0}", n);

			double[] x = new double[3];
			double[] y = new double[3];
			for (int i = 0; i < 3; i++)
			{
				x[i] = 0;
			}
			for (int i = 0; i < 8; i++)
			{
				y = x;
				x = iteration(H, g, x);
			}
			double z = difference(real, x);
			Console.WriteLine("------------------------------------");
			Console.WriteLine("Simple iteration:");
			Console.WriteLine("x7 = ({0}, {1}, {2})", x[0], x[1], x[2]);
			Console.WriteLine("Actual error: {0}", z);
			Console.WriteLine("Aprior evaluation: {0}", (n * n * n * n * n * n * n * n / (1 - n)) * Math.Sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]));
			Console.WriteLine("Postariori evalution: {0}", (n / (1 - n)) * Math.Sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]) + (x[2] - y[2]) * (x[2] - y[2])));
			
			x[0] = y[0] + (1 / (1 - 0.627675)) * (x[0] - y[0]);
			x[1] = y[1] + (1 / (1 - 0.627675)) * (x[1] - y[1]);
			x[2] = y[2] + (1 / (1 - 0.627675)) * (x[2] - y[2]);
			Console.WriteLine("Approxmation of Lyusterik: ");
			Console.WriteLine("({0}, {1}, {2}) ", x[0], x[1], x[2]);
			z = difference(real, x);
			Console.WriteLine("Actual error: {0}", z);
			Console.WriteLine();
			Console.WriteLine("------------------------------------");
			Console.WriteLine("Method of Seidel:");
			for (int i = 0; i < 3; i++) 
			{
				x[i] = 0;
			}
			for (int i = 0; i < 7; i++)
			{
				x = Seidel(H, g, ref x);
			}
			z = difference(real, x);
			Console.WriteLine("x7 = ({0}, {1}, {2})", x[0], x[1], x[2]);
			Console.WriteLine("Actual error: {0}", z);
			Console.WriteLine();
			Console.WriteLine("------------------------------------");
			for (int i = 0; i < 3; i++)
			{
				x[i] = 0;
			}
			while(difference(real, x) > 0.000001)
			//for(int i = 0; i < 8; i++ )
			{
				x = relaxation(H, g, x);
			}
			z = difference(real, x);
			Console.WriteLine("Method of relaxation:");
			Console.WriteLine("x = ({0}, {1}, {2})",x[0], x[1], x[2]);
			Console.WriteLine("Actual error: {0}", z);


			Console.Read();


		}

		static double[] relaxation(double[,] H, double[] g, double[] x)
		{
			//double q = 2 / (1 + Math.Sqrt(1 - (0.627675 * 0.627675)));
			double q = 1;
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				temp[i] = g[i] - x[i];
			}
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					temp[i] += H[j, i] * x[j];
				}
				temp[i] = q * temp[i];
				temp[i] += x[i];
			}
			return (temp);
		}

		static double[] Seidel(double[,] H, double[] g, ref double[] x)
		{
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				temp[i] = g[i];
			}
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					temp[i] += H[j, i] * x[j];
				}
				x[i] = temp[i];
			}
			return (temp);
		}

		static double[] iteration(double[,] H, double[] g, double[] x)
		{
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++) 
			{
				temp[i] = g[i];
			}
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++) 
				{
					temp[i] += H[j, i] * x[j]; 
				} 
			}
			return (temp);
		}

		static double norm(double[,] H)
		{
			double n = 0;
			double temp = 0;
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					temp += Math.Abs(H[i, j]);
				}
				if (n < temp)
				{
					n = temp;
				}
				temp = 0;
			}
			return (n);
		}


		static void output(double[,] a)
		{
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					Console.Write("{0} ", a[j, i]);
				}
				Console.WriteLine();
			}
		}

		static double[,] build(double[,] a)
		{
			double[,] H = new double[3, 3];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (i == j)
					{
						H[j, i] = 0;
					}
					else
					{
						H[i, j] = ( -a[j, i]) / a[j, j];
					}
				}
			}
			return (H);

		}

		static double difference(double[] real, double[] x) 
		{
			double z = 0;
			for (int i = 0; i < 3; i++) 
			{
				z += (real[i] - x[i]) * (real[i] - x[i]);
            }
			z = Math.Sqrt(z);
			return (z);
		}

		static double[] fault(double[] x, double[,] a, double[] b)
		{
			double[] y = new double[x.Length];
			for (int i = 0; i < x.Length; i++)
			{
				y[i] = b[i];
				for (int j = 0; j < x.Length; j++)
				{
					y[i] -= a[j, i] * x[j];
				}
			}
			return (y);
		}

		static void output(double[,] a, double[] b, int n)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					Console.Write("{0:f6}      ", a[j, i]);
				}
				Console.WriteLine(b[i]);
			}
			Console.WriteLine();
		}
	}
}
