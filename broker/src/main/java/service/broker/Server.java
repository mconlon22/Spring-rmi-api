package service.broker;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import service.broker.LocalBrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.BrokerService;
import service.core.Constants;
import service.core.ClientInfo;
import java.util.List;
public class Server {
 public static void main(String[] args) {
    LocalBrokerService brokerService = new LocalBrokerService();
 try {
 // Connect to the RMI Registry - creating the registry will be the 
 // responsibility of the broker.
 Registry registry = null;
if (args.length == 0) {
   System.out.println("createReg");

 registry = LocateRegistry.createRegistry(1099);
} else {
   System.out.println("getReg");
 registry = LocateRegistry.getRegistry("localhost", 1099);
} 

 // Create the Remote Object
 BrokerService brokerServiceCore = (BrokerService) 
 UnicastRemoteObject.exportObject(new LocalBrokerService(),0);
 // Register the object with the RMI Registry
 registry.bind(Constants.BROKER_SERVICE, brokerServiceCore);

 System.out.println("STOPPING SERVER SHUTDOWNhi");
 while (true) {Thread.sleep(1000); }
 } catch (Exception e) {
 System.out.println("Trouble: " + e);
 }
 }
} 