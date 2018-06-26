package br.feevale.processor.tracker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import br.feevale.processor.share.Job;

public class PrimeJob extends UnicastRemoteObject implements Job {

	private static final long serialVersionUID = 1L;
	
	private Integer idWorker;
	private List<Integer> listWorker;

	public PrimeJob(Integer idWorker, List<Integer> listWorker) throws RemoteException {
        super();
        this.idWorker = idWorker;
        this.listWorker = listWorker;  
    }
    
    @Override
    public List<Integer> getListById() throws RemoteException {
    	return listWorker;
    }

    @Override
    public void sendJobResult(List<Integer> listaOrdenada) throws RemoteException {
        Tracker.listResults(idWorker, listaOrdenada);
    }
    
}
