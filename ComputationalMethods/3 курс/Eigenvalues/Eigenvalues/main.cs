using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Eigenvalues
{
	class main
	{
		static void Main(string[] args)
		{
			def();
			Console.WriteLine("Method of Jacobi:");
			Jacobi();
			Console.WriteLine("----------------------------------");
			Console.WriteLine("Method of degree");
			def();
			MethodOfDegree();
			Console.WriteLine("----------------------------------");
			def();
			Console.WriteLine("Method of Sсalar:");
			MethodOfScal();
			Console.WriteLine("----------------------------------");
			Console.WriteLine("Spectrum:");
			spectrum();
			Console.WriteLine("----------------------------------");
			Console.WriteLine("Wieland:");
			Wieland();
			Console.Read();


		}

		static double norm(double[] x)
		{
			return (Math.Sqrt(x[0] * x[0] + x[1] * x[1] + x[2] * x[2]));
		}

		static void def()
		{
			Matrix.matrix[0, 0] = -0.95121;
			Matrix.matrix[0, 1] = Matrix.matrix[1, 0] = -0.09779;
			Matrix.matrix[0, 2] = Matrix.matrix[2, 0] = 0.35843;
			Matrix.matrix[1, 1] = 0.61545;
			Matrix.matrix[2, 2] = -0.95729;
			Matrix.matrix[2, 1] = 0.02229;
			Matrix.matrix[1, 2] = 0.022286;
		}

		static void MethodOfDegree()
		{
			double eps = 0.001;
			double Olyambda = 0;
			double Nlyambda = 0;
			int l = 0;
			double[] Y = new double[3];
			Y[0] = Y[1] = Y[2] = 1;
			do
			{
				Olyambda = Nlyambda;
				Nlyambda = multi(Matrix.matrix, Y)[2] / Y[2];
				Y = multi(Matrix.matrix, Y);
				l++;
			}
			while (Math.Abs(Nlyambda - Olyambda) > eps);
			double NY = norm(Y);
			for (int i = 0; i < 3; i++)
			{
				Y[i] /= NY;
			}
			Console.WriteLine("lymbda = {0}", Nlyambda);
			Console.WriteLine("x = ({0}, {1}, {2})", Y[0], Y[1], Y[2]);
			Console.WriteLine("Количество итерации = {0}", l);
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				temp[i] = multi(Matrix.matrix, Y)[i] - Nlyambda * Y[i];
			}
			Console.WriteLine("Невязка = {0} ", norm(temp));
		}



		static double[] multi(double[,] x, double[] y)
		{
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					temp[i] += x[j, i] * y[j];
				}
			}
			return (temp);
		}

		static void MethodOfScal()
		{
			double eps = 0.000001;
			double[] Y = new double[3];
			Y[0] = Y[1] = Y[2] = 1;
			double Olyambda = 0;
			int l = 0;
			double Nlyambda = 0;
			do
			{
				Olyambda = Nlyambda;
				Nlyambda = Matrix.scal(multi(Matrix.matrix, Y), Y) / Matrix.scal(Y, Y);
				Y = multi(Matrix.matrix, Y);
				l++;
			}
			while (Math.Abs(Nlyambda - Olyambda) > eps);
			double NY = norm(Y);
			for (int i = 0; i < 3; i++)
			{
				Y[i] /= NY;
			}
			Console.WriteLine("lymbda = {0}", Nlyambda);
			Console.WriteLine("x = ({0}, {1}, {2})", Y[0], Y[1], Y[2]);
			Console.WriteLine("Количество итерации = {0}", l);
			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				temp[i] = multi(Matrix.matrix, Y)[i] - Nlyambda * Y[i];
			}
			Console.WriteLine("Невязка = {0} ", norm(temp));
		}

		static void spectrum()
		{
			double lyambda = -1.316412;
			double[,] B = new double[3, 3];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (i == j)
					{
						B[j, i] = Matrix.matrix[j, i] - lyambda;
					}
					else
					{
						B[j, i] = Matrix.matrix[j, i];
					}
				}
			}
			double eps = 0.000001;
			double Olyambda = 0;
			double Nlyambda = 0;
			double[] Y = new double[3];
			Y[0] = Y[1] = Y[2] = 1;
			do
			{
				Olyambda = Nlyambda;
				Nlyambda = multi(B, Y)[1] / Y[1];
				Y = multi(B, Y);
			}
			while (Math.Abs(Nlyambda - Olyambda) > eps);
			double NY = norm(Y);
			for (int i = 0; i < 3; i++)
			{
				Y[i] /= NY;
			}
			Console.WriteLine("lymbda = {0}", Nlyambda + lyambda);
			Console.WriteLine("x = ({0}, {1}, {2})", Y[0], Y[1], Y[2]);

			double[] temp = new double[3];
			for (int i = 0; i < 3; i++)
			{
				temp[i] = multi(Matrix.matrix, Y)[i] - (Nlyambda + lyambda) * Y[i];
			}
			Console.WriteLine("Невязка = {0} ", norm(temp));

		}

		static void Wieland() 
		{
			double eps = 0.0001;
			double Lyambda = 1;
			double[] Y = new double[3];
			double[] Z = new double[3];
			for (int i = 0; i < 3; i++)
			{
				Y[i] = 1;
			}
			double newLyambda = 100;
			double[,] W = new double[3,3];
			while (newLyambda - Lyambda >= eps)
			{
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						W[j, i] = Matrix.matrix[j, i];
					}
				}
				for (int i = 0; i < 3; i++)
				{
					W[i, i] -= Lyambda;
				}
				Z = Y;
				Y = Gauss.start(W,Z);
				IW = Matr

			}
			Matrix.print(W);



		}




		static void Jacobi()
		{
			int[] max = new int[2];
			max = Matrix.max(Matrix.matrix);
			double[,] X = new double[3, 3];
			double[] lyambda = new double[3];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (i == j)
					{
						X[i, i] = 1;
					}
					else
					{
						X[j, i] = 0;
					}
				}
			}
			double eps = 0.0000001;
			Console.WriteLine();
			do
			{
				X = Matrix.multi(X, Matrix.V(Matrix.matrix, max));
				Matrix.matrix = Matrix.Iteration(Matrix.matrix, max);
				max = Matrix.max(Matrix.matrix);
			}
			while (Math.Abs(Matrix.matrix[max[1], max[0]]) > eps);
			for (int k = 0; k < 3; k++)
			{
				lyambda[k] = Matrix.matrix[k, k];
				Console.WriteLine("{0}  ", lyambda[k]);
				
			}
			Console.WriteLine();
			Matrix.print(X);
		}
	}
}
