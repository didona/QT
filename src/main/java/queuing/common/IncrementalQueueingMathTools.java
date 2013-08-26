package queuing.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class IncrementalQueueingMathTools {

   private static double[] facCache;
   private static int max = 0;
   private static final Log log = LogFactory.getLog(QueuingMathTools.class);

   private static void init(int newMax) {
      log.trace("QueuingMathTools: initializing the factorial cache with max value = " + newMax);
      double[] newFacCache = new double[newMax + 1];
      int toCopy = facCache==null? 0:facCache.length;
      if(facCache!=null)
         System.arraycopy(facCache, 0, newFacCache, 0, toCopy);
      for (int i = toCopy; i <= newMax; i++) {
         newFacCache[i] = realFac(i);
      }
      max = newMax;
      facCache = newFacCache;
   }

   private static double realFac(int n) {
      if (n < 0)
         throw new RuntimeException("Factorial invoked on a negative number");
      if (n == 0 || n == 1)
         return 1;
      return n * fac(n - 1);
   }


   public synchronized static double fac(int n) {
      if (n > max)
         init(n);
      return facCache[n];
   }

   public static double pow(double a, double b) {
      return Math.pow(a, b);
   }
}
