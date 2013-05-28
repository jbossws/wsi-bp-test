/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.ws.jaxws.bp12.wsa.test1189;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

@WebService(targetNamespace = "http://example.org", name = "wsaTestPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WsaTestPortType {

    @Oneway
    @Action(input = "http://example.org/action/notify")
    @WebMethod(operationName = "Notify", action = "http://example.org/action/notify")
    public void notify(
        @WebParam(partName = "notify", name = "notify", targetNamespace = "http://example.org/notify")
        java.lang.String notify
    );

    @WebResult(name = "echoOut", targetNamespace = "http://example.org/echo", partName = "echoOut")
    @Action(input = "http://example.org/action/echoIn", output = "http://example.org/action/echoOut")
    @WebMethod(operationName = "Echo", action = "http://example.org/action/echoIn")
    public java.lang.String echo(
        @WebParam(partName = "echoIn", name = "echoIn", targetNamespace = "http://example.org/echo")
        java.lang.String echoIn
    );
}
