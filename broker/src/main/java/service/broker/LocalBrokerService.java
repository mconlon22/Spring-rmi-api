package service.broker;

import java.util.LinkedList;
import java.util.List;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;
import java.rmi.registry.*;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import service.core.Constants;





/**
 * Implementation of the broker service that uses the Service Registry.
 * 
 * @author Rem
 *
 */
public class LocalBrokerService implements BrokerService {
	public List<Quotation> getQuotations(ClientInfo info) throws RemoteException {
		
	Registry registry = null;
	try{
	
	 registry = LocateRegistry.getRegistry("localhost", 1099);
	
	List<Quotation> quotations = new LinkedList<Quotation>();
		
		for (String name : registry.list()) {
			if (name.startsWith("qs-")) {
				QuotationService service =(QuotationService) registry.lookup(name) ;
				
				quotations.add(service.generateQuotation(info));
			}
		}
		return quotations;
	 } catch (NotBoundException e) {
	 System.out.println("Trouble: " + e);
	 }
	 return null;
		
	}
}
