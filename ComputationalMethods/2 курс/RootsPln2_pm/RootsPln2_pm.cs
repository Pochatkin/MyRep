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
			Console.WriteLine("методом половинного деления отрезка");
			Console.WriteLine("c заданой  точностью, до 6 знаков после запятой");
			Console.WriteLine();

			int n = 0;


			while (1 == 1)
			{
				CIPln_pm pln = new CIPln_pm();
				if (pln.n == -1)
				{
					break;
				}
				
				double a, b, e1, e2, h;
				a = -100;
				b = 100;
				e1 = 0.0000001;
				e2 = 0.0000001;
				h = 0.1;
				pln.roots(a, b, e1, e2, h);
				Console.ReadLine();
				n = pln.n;
			}

			





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
