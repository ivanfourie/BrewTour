package za.co.brewtour.client;

import za.co.brewtour.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase. Using
 * <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to navigate
 * with your browser to a specific url given by Maven. See
 * http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html for
 * details.
 */
public class GwtTestBrewTour extends GWTTestCase {

   /**
    * Must refer to a valid module that sources this class.
    */
   @Override
   public String getModuleName() {
      return "za.co.brewtour.BrewTourJUnit";
   }

   /**
    * Testing the FieldValidator
    */
   public void testValidators() {
      assertFalse(FieldVerifier.isValidName(""));
      assertFalse(FieldVerifier.isValidName("aa"));
      assertTrue(FieldVerifier.isValidName("abs"));
   }

   /**
    * This test will send a request to the server using the greetServer method
    * in GreetingService and verify the response.
    */
   public void testGreetingService() {
      // Create the service that we will test.
      GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
      ServiceDefTarget target = (ServiceDefTarget) greetingService;
      target.setServiceEntryPoint(GWT.getModuleBaseURL() + "BrewTour/greet");

      delayTestFinish(10000);

      // Send a request to the server.
      greetingService.greetServer("GWT User", new AsyncCallback<String>() {

         public void onSuccess(String result) {
            assertTrue(result.startsWith("Hello, GWT User!"));
         }

         public void onFailure(Throwable caught) {
            fail("Request failure: " + caught.getMessage());
         }
      });
   }

}
