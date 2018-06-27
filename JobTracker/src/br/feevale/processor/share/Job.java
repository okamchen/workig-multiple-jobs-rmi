package br.feevale.processor.share;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Job extends Remote {
	
    public List<Integer> getLastList() throws RemoteException;
    
    public void sendJobResult(List<Integer> listaOrdenada) throws RemoteException;
    
}
