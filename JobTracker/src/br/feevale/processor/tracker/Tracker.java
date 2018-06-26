package br.feevale.processor.tracker;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.feevale.processor.share.Job;

public class Tracker {
	
    public static void main(String[] args) {
    	
    	int numberWorks = 100;
    	Tracker tracker = new Tracker();
    	
    	String url;
    	try {
    		startRegistry(8877);
    		for (int i = 0; i < numberWorks; i++) {    			
    			url = "rmi://localhost:" + 8877 + "/tracker" + i;
    			Job job = new PrimeJob(i, tracker.getRandomList(1000));
    			Naming.rebind(url, job);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println("Tracker Ready!");
    }
    
    public List<Integer> getRandomList(int maxInteiros) {
		List<Integer> lista = new ArrayList<>();

		for (int j = 0; j < maxInteiros; j++) {
			lista.add(Integer.valueOf(ThreadLocalRandom.current().nextInt(1, maxInteiros)));
		}
 
		return lista;
    }
    
    public static void listResults(Integer id, List<Integer> listaOrdenada){
    	for(Integer n : listaOrdenada){
          System.out.println("Worker: " + id + ", value: " + n);
    	}
    }
    
    private static void startRegistry(int port) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(port);
        }
    }


}
