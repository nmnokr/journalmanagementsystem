package listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import properties.PropertiesService;
import service.ServiceFacade;;

public class ServletContextListenerImpl implements ServletContextListener {
	final Logger logger = Logger.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextInitialized metodu �al��maya ba�lad�.");
		Properties appProperties = PropertiesService.getProperties();//propertyler dosyadan okundu
		try {
			ServiceFacade.getInstance().initialize(appProperties);
			//Bu Metod rs232 serii leti�im i�in gerekli dir
		  /*  SerialTest main = new SerialTest();
		    main.initialize();
		    Thread t=new Thread() {
		        public void run() {
		            //the following line will keep this app alive for 1000    seconds,
		            //waiting for events to occur and responding to them    (printing incoming messages to console).
		            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
		        }
		    };*/
			System.out.println("ba�ladi");
		} catch (Exception e) {
			logger.error("ServletContextListenerImpl contextInitialized metodu exeption = " + e);
		}
 
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextDestroyed metodu �al��maya ba�lad�.");

	}

}