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
		String url = "rmi://localhost:" + 8877 + "/tracker";
		while(true) {
			try {
				job = (Job) Naming.lookup(url);
				this.orderListJob();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		}
    }

    private void orderListJob() {
        try {
        	List<Integer> listaJob = job.getLastList();
        	if (listaJob == null) {
        		return;
        	}
        	BubbleSort bs = BubbleSort.build();
        	
        	while (!bs.isOrdenado()) {
				bs.execute(listaJob);
            }
        	
			 Thread.sleep(400);
        	
        	job.sendJobResult(bs.getListaOrdenada());
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

    }    

}
