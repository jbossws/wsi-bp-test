/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.bp12.wsa.test1197;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.ws.jaxws.bp.common.BPTest;
//import org.jboss.wsf.test.JBossWSCXFTestSetup;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(Arquillian.class)
public class Test1197TestCase extends BPTest
{
   @ArquillianResource
   private URL baseURL;

   @Deployment(testable = false)
   public static WebArchive createDeployment()
   {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-bp12test1197.war");
      archive.setManifest(new StringAsset("Manifest-Version: 1.0\n" + "Dependencies: org.apache.cxf\n"))
            .addPackages(false, Filters.exclude(Test1197TestCase.class), Test1197TestCase.class.getPackage().getName())
            .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/bp12/wsa/test1197/WEB-INF/web.xml"));
      addResroucesToWebInf(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/bp12/wsa/test1197/WEB-INF", archive, "wsdl", "xsd");
      return archive;
   }
   @Test
   @RunAsClient    
   public void testSignature() throws Exception
   {
      // construct proxy
      QName serviceName = new QName("http://tempuri.org/", "WSAddressingCR");
      URL wsdlURL = new URL(baseURL + "/Test1197" + "?wsdl");
      Service service = Service.create(wsdlURL, serviceName);
      SignatureDocumentLiteral port = (SignatureDocumentLiteral) service.getPort(SignatureDocumentLiteral.class);
      // invoke method
      ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
            getProxyAddress(baseURL) + "/Test1197"); 

      System.out.println("Invoking sign1...");
      String response = port.sign1("Hello");
      System.out.println(response);
      
      
      System.out.println("Invoking sign2...");
      response = port.sign2("Hello");
      System.out.println(response);
      
      System.out.println("Invoking sign3...");
      response = port.sign3();
      System.out.println(response);
      
      System.out.println("Invoking sign4...");
      response = port.sign4();
      System.out.println(response);
      
      System.out.println("Invoking sign5...");
      port.sign5();
      
      System.out.println("Invoking sign6...");
      SignatureInMultipartMessage inMessage = new SignatureInMultipartMessage();
      inMessage.setSignatureInFirstPart(new ObjectFactory().createSignatureInMultipartMessageSignatureInFirstPart("Hello"));
      inMessage.setSignatureInSecondPart(new ObjectFactory().createSignatureInMultipartMessageSignatureInSecondPart("World"));
      response = port.sign6(inMessage);
      System.out.println(response);
      
      //request 7
      System.out.println("Invoking sign7...");
      javax.xml.ws.Holder<String> headerText = new javax.xml.ws.Holder<String>();
      headerText.value = "World";
      response = port.sign7("Hello", headerText);
      System.out.println(response);
      
      
      //request 8
      System.out.println("Invoking sign7 and exception is expected...");
      headerText.value = "Hello";
      try
      {
         port.sign7("World", headerText);
         fail("Exception is expected.");
      }
      catch (Exception e)
      {
         System.out.println("sign7 exception message : " + e.getMessage());
      }
      
   }
 
   
}
