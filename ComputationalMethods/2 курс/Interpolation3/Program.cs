using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Interpolation
{
    class interpolation
    {
        static void Main(string[] args)
        {

            Console.WriteLine();
            Console.WriteLine("Початкин М.А. 224гр    март 2015г.");
            Console.WriteLine("Программа интерполирования многочлена");
            Console.WriteLine("по начальной таблице");
            Console.WriteLine();

            double x0 = 0.4;
            double x1 = 0.444748;
            double h = 0.1;
            double t = (x1 - x0) / h;
            double[] x = new double[10];
            x[0] = 0;

            for (int i = 1; i < 10; i++)
            {
                x[i] = x[0] + i * h;
            }
            double[] f = new double[10];
            f[0] = 1.623250;
            f[1] = 1.664792;
            f[2] = 1.701977;
            f[3] = 1.734832;
            f[4] = 1.763404;
            f[5] = 1.787764;
            f[6] = 1.808002;
            f[7] = 1.824230;
            f[8] = 1.836580;
            f[9] = 1.845201;


            Output(x, f);
            Console.WriteLine();
            Console.WriteLine();
            double a = Interpolation(f, t);
            Console.WriteLine("x* = {0}", x1);
            Console.WriteLine("t = {0}", t);
            Console.WriteLine("Середина : P(x*) = {0}", a);
            Console.WriteLine("Начало : P(x*) = {0}", Interpolation1(f, t));
            Console.WriteLine("Конец : P(x*) = {0}", Interpolation2(f, t));

            Console.ReadLine();



        }

        static void Output(double[] x, double[] f)
        {
            for (int i = 0; i < 10; i++)
            {
                Console.Write("{0:f1}     ", x[i]);
            }
            Console.WriteLine();
            for (int i = 0; i < 10; i++)
            {
                Console.Write("{0:f4}  ", f[i]);
            }
            Console.WriteLine();
            Console.Write("   ");
            for (int i = 0; i < 9; i++)
            {
                Console.Write("{0:f6}  ", f[i + 1] - f[i]);
            }
            Console.WriteLine();
            Console.Write("       ");
            for (int i = 0; i < 8; i++)
            {
                Console.Write("{0:f6}  ", f[i + 2] - 2 * f[i + 1] + f[i]);
            }
            Console.WriteLine();
            Console.Write("           ");
            for (int i = 0; i < 7; i++)
            {
                Console.Write("{0:f6}  ", f[i + 3] - 3 * f[i + 2] + 3 * f[i + 1] - f[i]);
            }
            Console.WriteLine();
            Console.Write("              ");
            for (int i = 0; i < 6; i++)
            {
                Console.Write("{0:f6}  ", f[i + 4] - 4 * f[i + 3] + 6 * f[i + 2] - 4 * f[i + 1] + f[i]);
            }
            Console.WriteLine();




        }

        static double Interpolation(double[] f, double t)
        {
            double[] y = new double[4];
            y[0] = f[5] - f[4];
            y[1] = f[5] - 2 * f[4] + f[3];
            y[2] = f[6] - 3 * f[5] + 3 * f[4] - f[3];
            y[3] = f[6] - 4 * f[5] + 6 * f[4] - 4 * f[3] + f[2];
            return
                (f[4] +
                t * y[0] +
               (t * (t - 1) * y[1]) / 2 +
               (t * (t - 1) * (t + 1) * y[2]) / 6 +
               (t * (t - 1) * (t + 1) * (t - 2) * y[3]) / 24);


        }

        static double Interpolation1(double[] f, double t) 
        {
            double[] y = new double[4];
            y[0] = f[5] - f[4];
            y[1] = f[6] - 2 * f[5] + f[4];
            y[2] = f[7] - 3 * f[6] + 3 * f[5] - f[4];
            y[3] = f[8] - 4 * f[7] + 6 * f[6] - 4 * f[5] + f[4];
            return
                (f[4] +
                t * y[0] +
               (t * (t - 1) * y[1]) / 2 +
               (t * (t - 1) * (t - 2) * y[2]) / 6 +
               (t * (t - 1) * (t - 2) * (t - 3) * y[3]) / 24);
        }

        static double Interpolation2(double[] f, double t)
        {
            double[] y = new double[4];
            y[0] = f[4] - f[3];
            y[1] = f[4] - 2 * f[3] + f[2];
            y[2] = f[4] - 3 * f[3] + 3 * f[2] - f[1];
            y[3] = f[4] - 4 * f[3] + 6 * f[2] - 4 * f[1] + f[0];
            return
                (f[4] +
                t * y[0] +
               (t * (t + 1) * y[1]) / 2 +
               (t * (t + 1) * (t + 2) * y[2]) / 6 +
               (t * (t + 1) * (t + 2) * (t + 3) * y[3]) / 24);


        }

        static void Input(ref int a)
        {
            try
            {
                a = Convert.ToInt32(Console.ReadLine());
            }
            catch
            {
                Console.WriteLine("Неверный формат ввода. Повторите попытку!!!");
                Input(ref a);
            }
        }
    }
}
