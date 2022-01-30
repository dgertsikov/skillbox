public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {

        return Math.PI * Math.pow(Math.abs(radius), 2);

    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {

        double result = (4 * Math.PI * Math.pow(Math.abs(radius), 3)) / 3;

        return result;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        if ((a + b) > c && (b + c) > a && (c + a) > b)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {

        if (isTrianglePossible(a, b, c)) {
            double p = (a + b + c) / 2;
            double result = Math.sqrt(p * (p - a) * (p - b) * (p - c));

            return result;
        }
        else {
            return -1;
        }
    }
}
