package queuing.open;/*
 *
 *  * INESC-ID, Instituto de Engenharia de Sistemas e Computadores Investigação e Desevolvimento em Lisboa
 *  * Copyright 2013 INESC-ID and/or its affiliates and other
 *  * contributors as indicated by the @author tags. All rights reserved.
 *  * See the copyright.txt in the distribution for a full listing of
 *  * individual contributors.
 *  *
 *  * This is free software; you can redistribute it and/or modify it
 *  * under the terms of the GNU Lesser General Public License as
 *  * published by the Free Software Foundation; either version 3.0 of
 *  * the License, or (at your option) any later version.
 *  *
 *  * This software is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  * Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public
 *  * License along with this software; if not, write to the Free
 *  * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */





import queuing.common.AbstractMMK;
import queuing.common.Clazz;

import java.util.Arrays;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 01/10/12
 */
public abstract class AbstractOpenMMK extends AbstractMMK {


   protected OpenClazz[] serviceTimes;


   protected AbstractOpenMMK(double numServers, OpenClazz... serviceTimes) {
      super(numServers);
      this.serviceTimes = serviceTimes;
   }

   public void setClasses(OpenClazz... serviceTimes){
      this.serviceTimes = serviceTimes;
   }


   public double getClassServiceTime(int clazz) {
      return serviceTimes[clazz].getServiceTime();
   }


   public abstract double utilization(int clazz);

   public final double utilization() {
      double ro = 0D;
      for (Clazz c : this.serviceTimes)
         ro += this.utilization(c.getClazz());
      return ro;
   }


   public OpenClazz[] getOpenClazzes() {
      return Arrays.copyOf(serviceTimes, serviceTimes.length, OpenClazz[].class);
   }


   protected double effectiveLambda() {
      double sum = 0D;
      OpenClazz openClazz;
      for (Clazz c : this.serviceTimes) {
         openClazz = (OpenClazz) c;
         sum += getLambdaIfAlsoMu(openClazz);
      }
      return sum;
   }

   protected double getLambdaIfAlsoMu(OpenClazz o) {
      if (o.getServiceTime() != 0)
         return o.getLambda();
      return 0D;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      for(int i=0;i<serviceTimes.length;i++){
         sb.append(serviceTimes[i].toString()).append(" U = ").append(this.utilization(i));
      }
      return sb.toString();
   }
}
