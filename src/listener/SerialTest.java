package listener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener;
import rest.EditorRest;
import rest.RfidRest;

import java.util.Enumeration;

import dto.Editor;
import dto.Rfid;


public class SerialTest implements SerialPortEventListener {
SerialPort serialPort;
private static final String PORT_NAMES[] = {  
        "COM7", 
};
private BufferedReader input;
private OutputStream output;
private static final int TIME_OUT = 2000;
private static final int DATA_RATE = 9600;

public void initialize() {
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    while (portEnum.hasMoreElements()) {
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
    }
    if (portId == null) {
        System.out.println("Could not find COM port.");
        return;
    }

    try {
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}


public synchronized void close() {
    if (serialPort != null) {
        serialPort.removeEventListener();
        serialPort.close();
    }
}

public synchronized void serialEvent(SerialPortEvent oEvent) {
	Rfid rfid ;
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        try {
            String inputLine=null;
            if (input.ready()) {
                inputLine = input.readLine();
                            System.out.println(inputLine);
                            rfid=new Rfid();
                            rfid.setRfidno(Long.valueOf(inputLine));
                            RfidRest rfidRest=new RfidRest();
                            rfidRest.UpdateRfid(rfid); 
                   
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
}

public static void main(String[] args) throws Exception {
    SerialTest main = new SerialTest();
    main.initialize();
    Thread t=new Thread() {
        public void run() {
            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
        }
    };
    t.start();
    System.out.println("Started");
}
}