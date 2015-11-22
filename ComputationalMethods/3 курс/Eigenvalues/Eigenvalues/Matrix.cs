using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Eigenvalues
{
	class Matrix
	{
		public static double[,] matrix = new double[3,3];

		public static double[,] multi(double[,] x, double[,] y)
		{
			double[,] temp = new double[3, 3];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					temp[j, i] = 0;
				}
			}
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					for (int k = 0; k < 3; k++)
					{
						temp[j, i] += x[k, i] * y[j, k];
					} 
				}
			}
			return (temp);
		}

		public static int[] max(double[,] matrix)
		{
			int[] temp = new int[2];
			double max = 0;
			for (int i = 0; i < 3; i++)
			{
				for (int j = i + 1; j < 3; j++)
				{
					if (Math.Abs(max) < Math.Abs(matrix[j, i]))
					{
						max = matrix[j, i];
						temp[0] = j;
						temp[1] = i;
					}
				}
			}
			return (temp);
		}

		public static double c(double[,] matrix, int[] max)
		{
			int i = max[0];
			int j = max[1];
			double d = Math.Sqrt((matrix[i, i] - matrix[j, j]) * (matrix[i, i] - matrix[j, j]) + 4 * matrix[j, i] * matrix[j, i]);
			double c = Math.Sqrt(0.5 * (1 + (Math.Abs(matrix[i, i] - matrix[j, j]) / d)));
			return c;
		}


		public static double s(double[,] matrix, int[] max) 
		{
			int i = max[0];
			int j = max[1];
			double d = Math.Sqrt((matrix[i, i] - matrix[j, j]) * (matrix[i, i] - matrix[j, j]) + 4 * matrix[j, i] * matrix[j, i]);
			double s = Math.Sign(matrix[j, i] * (matrix[i, i] - matrix[j, j])) * Math.Sqrt(0.5 * (1 - (Math.Abs(matrix[i, i] - matrix[j, j]) / d)));
			return s;
		}

		public static double[,] V(double[,] matrix, int[] max)
		{
			int i = max[0];
			int j = max[1];
			double[,] V = new double[3, 3];
			for (int k = 0; k < 3; k++)
			{
				for (int l = 0; l < 3; l++)
				{
					V[l, k] = 0;
				}
			}
			for (int k = 0; k < 3; k++)
			{
				if (k != i || k != j)
				{
					V[k, k] = 1;
				}
			}
			V[i, i] = V[j,j] =  c(matrix, max);
			V[j, i] = -s(matrix, max);
			V[i, j] = -V[j, i];
			return V;

		}

		public static double[,] multiX(double[,] x, double[,] V, int[] max) 
		{
			int i = max[0];
			int j = max[1];
			double[,] temp = new double[3, 3];
			for (int k = 0; k < 3; k++) 
			{
				for(int l = 0; l < 3; l++) 
				{
					temp[l, k] = x[l, k];
				} 
			}

			for (int k = 0; k < 3; k++) 
			{
				temp[i, k] = temp[k, i] = c(x, max) * x[i, k] + s(x, max) * x[j, k];
			}
			for (int k = 0; k < 3; k++) 
			{
				temp[j, k] = temp[k, i] = -s(x, max) * x[i, k] + c(x, max) * x[j, k]; 
			}
			return temp;
		}

		public static double[,] Iteration(double[,] x, int[] max)
		{
			double[,] y = new double[3, 3];
			for (int k = 0; k < 3; k++) 
			{
				for (int l = 0; l < 3; l++) 
				{
					y[l, k] = x[l, k];
				}
			}
			int i = max[0];
			int j = max[1];
			for (int k = 0; k < 3; k++)
			{
				if (k != j)
				{
					if (k != i)
					{
						y[i, k] = y[k, i] = c(x, max) * x[i, k] + s(x, max) * x[j, k];
					}
				}
			}
			for (int k = 0; k < 3; k++)
			{
				if (k != j)
				{
					if (k != i)
					{
						y[j, k] = y[k, j] = -s(x, max) * x[i, k] + c(x, max) * x[j, k];
					}
				}
			}
			y[i, i] = c(x, max) * c(x, max) * x[i, i] + 2 * c(x, max) * s(x, max) * x[j, i] + s(x, max) * s(x, max) * x[j, j];
			y[j, j] = s(x, max) * s(x, max) * x[i, i] - 2 * c(x, max) * s(x, max) * x[j, i] + c(x, max) * c(x, max) * x[j, j];
			y[j, i] = y[i, j] = (c(x, max) * c(x, max) - s(x, max) * s(x, max)) * x[j, i] + c(x, max) * s(x, max) * (x[j, j] - x[i, i]);
			return y;
        }

		public static double scal(double[] x, double[] y)
		{
			double temp = 0;
			for (int i = 0; i < 3; i++)
			{
				temp += x[i] * y[i];
			}
			return (temp);			
		}


		public static void print(double[,] x)
		{
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					Console.Write("{0:f6} ", x[j, i]);
				}
				Console.WriteLine();
			}
		}
	}
}
