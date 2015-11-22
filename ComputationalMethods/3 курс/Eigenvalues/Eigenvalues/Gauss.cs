﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Eigenvalues
{
	class Gauss
	{
		public static double[] start(double[,] A, double[] B)
		{
			int n = 3;
			double[] result = new double[3];
			double[,] NewA = new double[3, 4];
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					NewA[j, i] = A[j, i];
				}
			}
			for (int i = 0; i < 3; i++)
			{
				NewA[i, 3] = B[i];
			}
			result = ReversGauss(NewA, n);
			return result;
		}

		static double[] fault(double[] x, double[,] a)
		{
			double[] y = new double[x.Length];
			for (int i = 0; i < x.Length; i++)
			{
				y[i] = a[x.Length, i];
				for (int j = 0; j < x.Length; j++)
				{
					y[i] -= a[j, i] * x[j];
				}
			}
			return (y);
		}


		static double[] ReversGauss(double[,] a, int n)
		{
			double t = 0;
			double[] x = new double[n];
			x[n - 1] = a[n, n - 1];
			for (int i = n - 1; i > 0; i--)
			{
				t = a[n, i - 1];
				for (int j = n - 1; j >= i; j--)
				{
					t -= a[j, i - 1] * x[j];
				}
				x[i - 1] = t;
				t = 0;
			}
			return (x);
		}



		static double Check(double[,] a, ref int i, int j)
		{
			if (a[i, j] == 0)
			{
				i++;
				Check(a, ref i, j);
				return (a[i, j]);
			}
			else
			{
				return (a[i, j]);
			}
		}
		static double[,] MethodOfGauss(ref double[,] a, int n)
		{
			double t = 0;
			for (int i = 0; i < n - 1; i++)
			{
				for (int j = i; j < n; j++)
				{
					t = Check(a, ref i, j);
					if (t < 0.00000001)
					{
						Console.WriteLine("Диагностика!");
					}
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] / t;
					}
				}

				for (int j = i + 1; j < n; j++)
				{
					for (int k = i; k < n + 1; k++)
					{
						a[k, j] = a[k, j] - a[k, i];
					}
				}

			}
			a[n, n - 1] = a[n, n - 1] / a[n - 1, n - 1];
			a[n - 1, n - 1] = 1;
			return (a);
		}

		


	}
}
