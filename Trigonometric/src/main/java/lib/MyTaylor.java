package lib;

import static java.lang.Math.pow;

public class MyTaylor {
    private static double cos(double x, int n) {
        int sign = 1;
        int factorial = 1;
        double result = 0;
        double pown = 1;
        for (int i = 0; i < n; i++) {
            result += sign * pown / factorial;
            factorial *= (2 * i + 2) * (2 * i + 1);
            sign *= -1;
            pown *= x * x;
        }
        return result;
    }

    private static double reverseFunction(double x, int n) {
        double result = 0;
        double pow = 1;
        for (int i = 0; i < n; i++) {
            result += pow;
            pow *= x;
        }
        return result;
    }
    public static double sec(double x) {
        if (Double.isInfinite(x) || Double.isNaN(x)){
            return Double.NaN;
        }
        if (x < 0) {
            x = -x;
        }
        while (x >= 2 * Math.PI) {
            x -= 2 * Math.PI;
        }
        int sign = x < Math.PI ? 1 : -1;
        if (x >= Math.PI) {
            x -= Math.PI;
        }
        return x > Math.PI / 2 ? -sign * reverseFunction(1 -  cos(Math.PI - x, 12),20) : sign * reverseFunction(1 - cos(x, 12),20);
    }
}
