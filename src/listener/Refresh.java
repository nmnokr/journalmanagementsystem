package listener;
//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Extend HttpServlet class
public class Refresh extends HttpServlet {

// Method to handle GET method request.
public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	  SerialTest main = new SerialTest();
	    main.initialize();
	    Thread t=new Thread() {
	        public void run() {
	            //the following line will keep this app alive for 1000    seconds,
	            //waiting for events to occur and responding to them    (printing incoming messages to console).
	            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
	        }
	    };
	    t.start();
	    System.out.println("Started");
	 
 
}

// Method to handle POST method request.
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   doGet(request, response);
}
}