using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank
{
	public class ATM
	{

		public static List<int[]> exchange(int sum, int[] ratigs)
		{
			List<int[]> temp = new List<int[]>();
			int n = ratigs.Length;
			int[] x = new int[n + 1];
			for (int i = 0; i < n; i++)
			{
				x[i] = 0;
			}
			do
			{
				x[0]++;
				if (summ(ratigs, x) == sum)
				{
					temp.Add((int[])x.Clone());
				}
				for (int i = 0; summ(ratigs, x) > sum; i++)
				{
					x[i] = 0;
					x[i + 1]++;
				}
			}
			while (x[n] != 1);
			return (temp);
		}

		private static int summ(int[] ratigs, int[] x)
		{
			int n = ratigs.Length;
			int s = 0;
			for (int i = 0; i < n; i++)
			{
				s += x[i] * ratigs[i];
			}
			return (s);
		}

	}
}
