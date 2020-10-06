import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import service.core.Constants;
import service.core.QuotationService;
import service.core.Quotation;
import static org.junit.Assert.assertTrue;

import service.core.ClientInfo;

import service.girlpower.GPQService;

import org.junit.*;
import static org.junit.Assert.assertNotNull;
public class GPQServiceTest {
 private static Registry registry;
 @BeforeClass
    public static void setup() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            QuotationService quotationService = (QuotationService)
 UnicastRemoteObject.exportObject(new GPQService(),0);

            // Register the object with the RMI Registry
            registry.bind(Constants.GIRL_POWER_SERVICE, quotationService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 @Test
 public void connectionTest() throws Exception {
    QuotationService service = (QuotationService) registry.lookup(Constants.GIRL_POWER_SERVICE);
 assertNotNull(service);
 }
@Test
 public void generateQuotationTest() throws Exception {
    QuotationService service = (QuotationService)registry.lookup(Constants.GIRL_POWER_SERVICE);
     ClientInfo client=new ClientInfo("martin",'M',22,0,0,"2601422");
 Quotation quotation=service.generateQuotation(client);
assertTrue("worked",quotation instanceof Quotation );
 }
 
}
/*

 }*/