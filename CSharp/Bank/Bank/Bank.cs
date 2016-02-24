using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank
{
	class Bank
	{
		static void Main(string[] args)
		{
			 List<int[]> result = new List<int[]>();
			 List<int> x = new List<int>();
			string str = null;
			int sum = 0;
			int amount = 0;
			string s = Console.ReadLine();
			string[] c = s.Split(' ');

			for(int i = 0; i < c.Length; i++)
			{
				try
				{
					x.Add(Convert.ToInt32(c[i]));
				}
				catch
				{
					Console.WriteLine("Exception smth");
					return;
				}

			}
			sum = x[0];
			amount = x.Count();
			int k = 0;
			int[] ratigs = (x.GetRange(1, amount - 1)).ToArray();
			for (int i = 0; i < ratigs.Length; i++)
			{
				if (ratigs[i] <= 0)
				{
					k++;
				}
			}

			if (k == 0)
			{
				result = ATM.exchange(sum, ratigs);
				for (int i = 0; i < result.Count(); i++)
				{
					for (int j = 0; j < ratigs.Length; j++)
					{
						Console.WriteLine(result[i][j] + "x" + ratigs[j] + " ");
					}
					Console.WriteLine();
				}
			}
			else
			{
				Console.WriteLine("Ratigs is negative ");
			}
			Console.ReadLine();
		}

		



	}
}
