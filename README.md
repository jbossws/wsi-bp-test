# WS-I Basic Profile Tests
Set of tests used to generate SOAP messages that can be later analyzed for compliance with particular WS-I standard. To be able to do this, these messages are going through proxy catching communication. Proxy settings can be specified via system properties.

## Structure
  This project contains four modules:
  - bp-common - shared functionality for other modules
  - bp12-tests - testsuite used for testing Basic Profile 1.2 compliance
  - bp20-tests - testsuite used for testing Basic Profile 2.0 compliance
  - bsp11-tests - testsuite used for testing Basic Security Profile 1.1 compliance

## Run these tests
  By default, only bp-common module is executed when you build the project.
  To execute particular testsuite you can activate a corresponding profile:

  - to run BSP1.1 tests module:
  
 *mvn clean test -Dproxy.port=8080 -Pwildfly1000,bsp11 -Djboss.home=/path/to/wildfy.10.0.0/home*  
  
  - to run BP1.2 tests module activated via property:
  
  *mvn clean test -Dproxy.address=http://localhost:8080 -Pwildfly1000 -Djboss.home=/path/to/wildfy.10.0.0/home -Dts.bp12*
 
  - to run all modules with default settings:
  
  *mvn clean test -Pwildfly1000,all -Djboss.home=/path/to/wildfy.10.0.0/home*
  

### Profiles
* **wildfly900** or **wildfly1000** - **[mandatory]** loads server integration dependencies for WildFly 9.x or 10.x
* **bp12** - execute bp12-tests module, you can also use -Dts.bp12 property to activate it 
* **bp20** - execute bp20-tests module, you can also use -Dts.bp20 property to activate it
* **bsp11** - execute bsp11-tests module, you can also use -Dts.bsp11 property to activate it
* **all** - convenient profile to execute all modules, you can also use -Dts.bp12 property to activate this
* **use-eap7-bom** - import productized artifacts versions used in EAP product


### Properties
* **jboss.home** - **[mandatory]** path to tested server home. Server will be started by Arquillian during tests.
* **jboss.bind.address** - bind server to this address, defaults to *localhost* if not specified.
* **proxy.port** - used to replace the EPR address in wsa test soap message, used only in bsp11-tests module. Defaults to *8080* if not specified. Proxy is expected to listen on the same address as *jboss.bind.address*.
* **proxy.address** - proxy address is the target endpoint address which monitor the inbound and outbound message. Used in bp12-tests and bp20-tests modules. Defaults to *http://localhost:8080* if not specified.
 
Proxy params defaults let you run the project without additional configuration. Use proper system properties to let messages go through your proxy.
 

## Test utils class and improvement

  There are couple of repeat utily class like BP12Test, BP20Test and BSP11Test. These utily class will be removed and merged into JBossWSTest in jbossws-cxf : 
  https://issues.jboss.org/browse/JBWS-3943
  