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
	
	public static Integer count = 0;
	
	public static List<List<Integer>> lista = new ArrayList<>();
	public static List<Integer> listaOrdenada = new ArrayList<>();
	
    public static void main(String[] args) {
    	
    	int maxListNumbers = 100;
    	int maxResult = 1000;
    	
    	Tracker t = new Tracker();
		t.buildRandomList(maxListNumbers, maxResult);
    	
    	String url;
    	try {
    		startRegistry(8877);
			url = "rmi://localhost:" + 8877 + "/tracker";
			Job job = new PrimeJob();
			Naming.rebind(url, job);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println("Tracker Ready!");
    }
    
    public void buildRandomList(int maxListNumbers, int maxResult) {
    	for (int i = 0; i < maxListNumbers; i++) {
    		List<Integer> numbers = new ArrayList<>();
    		for (int j = 0; j < maxResult; j++) {
    			numbers.add(Integer.valueOf(ThreadLocalRandom.current().nextInt(1, maxResult)));
    		}
    		
    		lista.add(numbers);
    	}
 
    }
    
    public static void listResults(List<Integer> listaOrdenada){
    	count++;
    	for(Integer n : listaOrdenada){
          System.out.println("Worker: " + count + ", value: " + n);
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
