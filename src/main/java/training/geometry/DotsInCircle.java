package training.geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DotsInCircle {

    //private static final Scanner scanner = new Scanner(System.in);
    private static final BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Circle circle = new Circle(new Point(1, 1), 10);
        List<Point> points = Arrays.asList(
                new Point(1, 1),
                new Point(0, 1),
                new Point(1, 0),
                new Point(7, 7)
        );

        String result = checkPointsInCircle(circle);
        System.out.println(result);

    }

    static String checkPointsInCircle(Circle circle) {
        String result = "YES";
        Point point = readPointFromKeyBoard();
        while (isValidCoordinate(point)) {
            if (!isInCircle(point, circle)) {
                result = "NO";
                System.out.println(point + "is not in circle");
            }
            point = readPointFromKeyBoard();
        }
        return result;
    }


    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    static boolean isInCircle(Point point, Circle circle) {
        double distance = distance(point, circle.center);
        return distance <= circle.rad;
    }


    static Point readPointFromKeyBoard() {
        try {
            String line = buffReader.readLine();
            String[] lineArr = line.split(",");

            if (lineArr.length != 2) {
                throw new RuntimeException("Bad input");
            }
            int x = Integer.parseInt(lineArr[0]);
            int y = Integer.parseInt(lineArr[1]);

            return new Point(x, y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isValidCoordinate(Point point) {
        return !(point.x == 0 && point.y == 0);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point (" + x + ", " + y + ")";
        }
    }

    static class Circle {
        Point center;
        int rad;

        public Circle(Point center, int rad) {
            this.center = center;
            this.rad = rad;
        }
    }
}
