package san.pp;

public class Point {
  final double x;
  final double y;

  Point() {
    this(0, 0);
  }

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  static String repr(Point p) {
    return "(" + p.x + ", " + p.y + ")";
  }
}
