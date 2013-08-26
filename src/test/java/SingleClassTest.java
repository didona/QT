import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import queuing.exceptions.UnstableQueueException;
import queuing.open.OpenClazz;
import queuing.open.YuLoadIndependentOpenMMK;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class SingleClassTest {

   protected double minLambda = 1;
   protected double maxLambda = 100;
   protected double stepLambda = 1;
   protected double serviceTime;
   protected int numServers = 1;


   private final static Log log = LogFactory.getLog(SingleClassTest.class);
   private final static int DEFAULT_CLASS = 0;



   public SingleClassTest(double minLambda, double maxLambda, double stepLambda, double serviceTime, int numServers) {
      this.maxLambda = maxLambda;
      this.minLambda = minLambda;
      this.stepLambda = stepLambda;
      this.serviceTime = serviceTime;
      this.numServers = numServers;
   }

   public double[][] test() {
      double numSteps = (maxLambda - minLambda) / stepLambda;
      double[][] ys = new double[(int) numSteps][2];
      OpenClazz o;
      YuLoadIndependentOpenMMK queue;
      int i = 0;
      for (double l = minLambda; l < maxLambda; l += stepLambda) {
         o = buildClazz(l);
         queue = new YuLoadIndependentOpenMMK(numServers, o);
         try {
            queue.solve();
         } catch (UnstableQueueException e) {
            break;
         }
         ys[i][0] = l;
         ys[i][1] = queue.getResponseTimeByServiceTime(o.getServiceTime());
         System.out.println(l+";"+queue.utilization()+";"+queue.getResponseTimeByServiceTime(o.getServiceTime()));
         i++;
      }
      return ys;
   }

   protected OpenClazz buildClazz(double lambda) {
      return new OpenClazz(DEFAULT_CLASS, serviceTime, lambda);
   }


}
