package br.feevale.processor;

import java.rmi.Naming;
import java.util.List;

import br.feevale.processor.share.Job;

public class Processor {

    Job job;

    public static void main(String[] args) {
        new Processor();
        System.out.println("No more Jobs!");
    }
    
    public Processor () {
    	int idJob = 0;
    	while (true) {
    		String url = "rmi://localhost:" + 8877 + "/tracker" + idJob;
    		try {
    			job = (Job) Naming.lookup(url);
    			this.orderListJob();
    			idJob++;
    		} catch (Exception e) {
    			return;
    		}
    	}
    }

    private void orderListJob() {
        try {
        	List<Integer> listaJob = job.getListById();
        	BubbleSort bs = BubbleSort.build();
        	
        	while (!bs.isOrdenado()) {
				bs.execute(listaJob);
            }
        	
			// Thread.sleep(400);
        	
        	job.sendJobResult(bs.getListaOrdenada());
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

    }    

}
