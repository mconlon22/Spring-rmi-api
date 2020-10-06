package service.girlpower;

import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import service.girlpower.GPQService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;
import service.core.Constants;
public class Server {
 public static void main(String[] args) {
 try {
 // Connect to the RMI Registry - creating the registry will be the 
 // responsibility of the broker.
 Registry registry = null;
if (args.length == 0) {
 registry = LocateRegistry.createRegistry(1099);
} else {
 registry = LocateRegistry.getRegistry("localhost", 1099);
} 

 // Create the Remote Object
 QuotationService quotationService = (QuotationService) 
 UnicastRemoteObject.exportObject(new GPQService(),0);
 // Register the object with the RMI Registry
 registry.bind(Constants.GIRL_POWER_SERVICE, quotationService);

 System.out.println("STOPPING SERVER SHUTDOWN");
 while (true) {Thread.sleep(1000); }
 } catch (Exception e) {
 System.out.println("Trouble: " + e);
 }
 }
} 