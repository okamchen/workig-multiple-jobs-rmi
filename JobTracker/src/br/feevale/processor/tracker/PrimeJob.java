package br.feevale.processor.tracker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import br.feevale.processor.share.Job;

public class PrimeJob extends UnicastRemoteObject implements Job {

	protected PrimeJob() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
    
    @Override
    public List<Integer> getLastList() throws RemoteException {
    	if (Tracker.lista.size() == 0) {
    		return null;
    	}
		int index = Tracker.lista.size() - 1;
		List<Integer> lista = Tracker.lista.get(index);
		Tracker.lista.remove(index);
		return  lista;
    }

    @Override
    public void sendJobResult(List<Integer> listaOrdenada) throws RemoteException {
		Tracker.listResults(listaOrdenada);
    }
    
}
