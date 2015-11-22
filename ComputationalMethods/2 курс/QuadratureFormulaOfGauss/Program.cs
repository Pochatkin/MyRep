using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuadratureFormulaOfGauss
{
	class Program
	{
		static void Main(string[] args)
		{
			double[] x = new double[2];
			x[0] = 0.050309;
			x[1] = 0.366666;
			double A1 = 1.18032;
			double A2 = 0.545868;
			double[] y = new double[4];
			y[0] = 0.250657;
			y[1] = 0.332881;
			y[2] = 0.03649;
			y[3] = 0.402658;
			double[] B = new double[4];
			B[0] = -0.012832;
			B[1] = 0.475716;
			B[2] = 1.12168;
			B[3] = 0.141631;
				
			Console.WriteLine("Для 2-х узлов:  {0}",QFOG2(x[0], x[1], A1, A2));
			Console.WriteLine("Для 4-х узлов:  {0}",QFOG4(y[0], y[1], y[2], y[3], B[0], B[1], B[2], B[3]));
			Console.ReadLine();

		}

		static double QFOG2(double x1,double x2,double A1,double A2)
		{
			return (A1 * Math.Cos(x1) + A2 * Math.Cos(x2));
		}

		static double QFOG4(double x1, double x2, double x3, double x4, double A1, double A2, double A3, double A4)
		{
			return (A1 * Math.Cos(x1) + A2 * Math.Cos(x2) + A3 * Math.Cos(x3) + Math.Cos(x4) * A4);
		}
		

	}
}
