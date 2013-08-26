package simple;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class Main {

   public static void main(String[] args) {

      SingleClassTest sct = new SingleClassTest(1, 100000, 1, 0.001, 1);
     // TwinClassTest sct = new TwinClassTest(1, 100000, 1, 0.001, 1);
      double[][] res = sct.test();
      plot(res);

   }


   private static void plot(double[][] points) {
      double[] x = column(points, 0);
      double[] y = column(points, 1);
      /*
      Plot2DPanel plot = new Plot2DPanel();
      plot.setPreferredSize(new Dimension(400,400));
      plot.addLinePlot("Single class", x, y);
      JFrame frame = new JFrame("a plot panel");
      frame.setPreferredSize(new Dimension(400,400));
      frame.setContentPane(plot);
      frame.setVisible(true);
      */
   }


   private static double[] column(double[][] matrix, int column) {
      int size = matrix.length;
      double[] ret = new double[size];
      for (int i = 0; i < size; i++) {
         ret[i] = matrix[i][column];
      }
      return ret;
   }
}
