using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ResZd15_pm;

namespace RootsPln2_pm
{
	class pln2_pm
	{
		static void Main(string[] args)
		{

			Console.WriteLine("RootsPln2_pm      Початкин М.А ноябрь 2014г.");
			Console.WriteLine("Программа нахождения корней многчлена");
			Console.WriteLine("методом НЬютона");
			Console.WriteLine("");
			Console.WriteLine();

			int n = 0;


			while (1 == 1)
			{
				CIPln_pm pln = new CIPln_pm();
				if (pln.n == -1)
				{
					break;
				}
				double c, d;
				d = 100;
				c = pln.Mc4();
				pln.Newton(c, d);
				Console.ReadLine();
			}







		}

        static void output(double[] a)
        {
            Console.Write("   ");
            Console.Write("{0}x^{1} ", a[0], a.Length);
            for (int i = 1; i <= a.Length - 1; i++)
            {
                if (a[i] >= 0)
                    Console.Write("+{0}x^{1} ", a[i], a.Length - i);
                else
                    Console.Write("{0}x^{1} ", a[i], a.Length - i);
            }
            Console.WriteLine();
            Console.WriteLine();
        }

		static void inputD(out double x)
		{
			try
			{
				x = Convert.ToDouble(Console.ReadLine());
			}
			catch
			{
				Console.WriteLine("Неверный формат ввода, повторите попытку!!");
				inputD(out x);
			}
		}


	}
}