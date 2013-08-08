package test;

import queuing.exceptions.UnstableQueueException;
import queuing.open.OpenClazz;
import queuing.open.YuLoadIndependentOpenMMK;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class TwinClassTest extends SingleClassTest {


   public TwinClassTest(double minLambda, double maxLambda, double stepLambda, double serviceTime, int numServers) {
      super(minLambda, maxLambda, stepLambda, serviceTime, numServers);
   }

   public double[][] test() {
      double numSteps = (maxLambda - minLambda) / stepLambda;
      double[][] ys = new double[(int) numSteps][2];
      OpenClazz o1,o2;
      YuLoadIndependentOpenMMK queue;
      int i = 0;
      for (double l = minLambda; l < maxLambda; l += stepLambda) {
         o1 = buildClazz(l);
         o2 = buildClazz(l);
         queue = new YuLoadIndependentOpenMMK(numServers, o1,o2);
         try {
            queue.solve();
         } catch (UnstableQueueException e) {
            break;
         }
         ys[i][0] = l;
         ys[i][1] = queue.getResponseTimeByServiceTime(o1.getServiceTime());
         System.out.println(l+";"+queue.utilization()+";"+queue.getResponseTimeByServiceTime(o1.getServiceTime()));
         i++;
      }
      return ys;
   }

}
