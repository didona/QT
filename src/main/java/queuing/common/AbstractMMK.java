package queuing.common;/*
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


import queuing.exceptions.UnstableQueueException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 01/10/12
 */
public abstract class AbstractMMK implements Queue {

   protected double numServers;
   protected double ro;
   protected double additionalLoad;
   protected String ID;
   protected boolean solved = false;

   public AbstractMMK(double numServers) {
      this.numServers = numServers;
   }

   public final void solve() throws UnstableQueueException {
      flowInflowOut();
      solved = true;
   }

   public void setID(String ID) {
      this.ID = ID;
   }

   public final double getClassResponseTime(int clazz) {
      double service = this.getClassServiceTime(clazz);
      return getResponseTimeByServiceTime(service);
   }

   protected abstract double getClassServiceTime(int clazz);

   public abstract double avgQueueingTime();

   protected abstract void flowInflowOut() throws UnstableQueueException;

   public void injectLoad(double additionalLoad) {
      this.additionalLoad = additionalLoad;
      solved = false;
   }

   public double getNumServers() {
      return numServers;
   }


}
