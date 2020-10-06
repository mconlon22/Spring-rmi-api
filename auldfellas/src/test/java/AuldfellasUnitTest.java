import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import service.core.Constants;
import service.core.QuotationService;
import service.core.Quotation;
import static org.junit.Assert.assertTrue;

import service.core.ClientInfo;

import service.auldfellas.AFQService;

import org.junit.*;
import static org.junit.Assert.assertNotNull;
public class AuldfellasUnitTest {
 private static Registry registry;
 @BeforeClass
    public static void setup() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            QuotationService quotationService = (QuotationService) 
                UnicastRemoteObject.exportObject(new AFQService(),0);

            // Register the object with the RMI Registry
            registry.bind(Constants.AULD_FELLAS_SERVICE, quotationService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 @Test
 public void connectionTest() throws Exception {
 QuotationService service = (QuotationService) registry.lookup(Constants.AULD_FELLAS_SERVICE);
 assertNotNull(service);
 }
@Test
 public void generateQuotationTest() throws Exception {
    QuotationService qservice = (QuotationService)registry.lookup(Constants.AULD_FELLAS_SERVICE);
     ClientInfo client=new ClientInfo("martin",'M',22,0,0,"2601422");
 Quotation quotation=qservice.generateQuotation(client);
assertTrue("worked",quotation instanceof Quotation );
 }
 
}
/*

 }*/