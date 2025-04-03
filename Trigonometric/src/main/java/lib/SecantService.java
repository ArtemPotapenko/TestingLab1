package lib;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SecantService {
    private static final MathContext MC = new MathContext(20, RoundingMode.HALF_UP);
    private final double eps;

    private final Map<Integer, BigDecimal> factorialCache = new HashMap<>();
    private final Map<Integer, BigDecimal> eulerNumberCache = new HashMap<>();

    public BigDecimal factorial(int n) {
        if (factorialCache.containsKey(n)) {
            return factorialCache.get(n);
        }

        BigDecimal result = (n == 0 || n == 1) ? BigDecimal.ONE : BigDecimal.valueOf(n).multiply(factorial(n - 1));
        factorialCache.put(n, result);
        return result;
    }

    public BigDecimal binomialCoefficient(int n, int k) {
        if (k == n) return BigDecimal.ONE;
        return factorial(n).divide(factorial(k).multiply(factorial(n - k)), MC);
    }

    public BigDecimal eulerNumber(int n) {
        if (eulerNumberCache.containsKey(n)) {
            return eulerNumberCache.get(n);
        }

        if (n == 0) {
            eulerNumberCache.put(0, BigDecimal.ONE);
            return BigDecimal.ONE;
        }
        if (n % 2 != 0) {
            eulerNumberCache.put(n, BigDecimal.ZERO);
            return BigDecimal.ZERO;
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (int k = 0; k < n; k += 2) {
            sum = sum.add(binomialCoefficient(n, k).multiply(eulerNumber(k)));
        }

        BigDecimal result = sum.negate();
        eulerNumberCache.put(n, result);
        return result;
    }

    public double secant(double x, double eps) {
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal term;
        int n = 1;
        double epsInverse = 1 / eps;

        x = reduceToValidRange(x);

        int sign = (x > Math.PI / 2 || x < -Math.PI / 2) ? -1 : 1;  // Учитываем знак секанса

        x = Math.abs(x);
        x = (x > Math.PI / 2) ? x - Math.PI : x;

        x = Math.abs(x);


        if (Math.abs(cosine(BigDecimal.valueOf(x), eps).doubleValue()) < eps) {
            return Double.POSITIVE_INFINITY;
        }

        do {
            term = eulerNumber(2 * n).multiply(BigDecimal.valueOf(x).pow(2 * n))
                    .divide(factorial(2 * n), MC);


            sum = sum.add(term);
            n++;
            if (n > 30){
                return sum.doubleValue() * sign;
            }
        } while (term.abs().compareTo(BigDecimal.valueOf(eps)) > 0);

        return sum.doubleValue() * sign;
    }

    public double secant(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new ArithmeticException();
        }
        return secant(x, eps);
    }

    private double reduceToValidRange(double x) {
        double pi = Math.PI;
        while (x > pi) x -= 2 * pi;
        while (x < -pi) x += 2 * pi;
        return x;
    }

    private BigDecimal cosine(BigDecimal x, double eps) {
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal term;
        int n = 1;

        do {
            term = BigDecimal.valueOf(-1).pow(n)
                    .multiply(x.pow(2 * n))
                    .divide(factorial(2 * n), MC);
            sum = sum.add(term);
            n++;
        } while (term.abs().compareTo(BigDecimal.valueOf(eps)) > 0);

        return sum;
    }
}
