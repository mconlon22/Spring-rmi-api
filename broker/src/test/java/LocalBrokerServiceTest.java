import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import service.core.Constants;
import service.core.BrokerService;
import service.core.Quotation;
import service.core.QuotationService;

import static org.junit.Assert.assertTrue;
import service.broker.LocalBrokerService;

import service.core.ClientInfo;
import java.util.List;



import org.junit.*;
import static org.junit.Assert.assertNotNull;
public class LocalBrokerServiceTest {
 private static Registry registry;
 @BeforeClass
    public static void setup() {
        try {
            
                System.out.println("createReg");
                registry = LocateRegistry.createRegistry(1099);
                registry = LocateRegistry.getRegistry("localhost", 1099);
             
                System.out.println("getReg");
          
            BrokerService brokerServiceCore = (BrokerService) 
            UnicastRemoteObject.exportObject(new LocalBrokerService(),0);
            // Register the object with the RMI Registry
            registry.bind(Constants.BROKER_SERVICE, brokerServiceCore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 @Test
 public void connectionTest() throws Exception {
    BrokerService service = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);
 assertNotNull(service);
 }
@Test
 public void generateQuotationsTest() throws Exception {
    BrokerService service = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);
     ClientInfo client=new ClientInfo("martin",'M',22,0,0,"2601422");
     List<Quotation> quotations=service.getQuotations(client);
    System.out.println("***********"+quotations.size());
assertTrue("worked",quotations instanceof List);
 }
 
 
}
/*

 }*/