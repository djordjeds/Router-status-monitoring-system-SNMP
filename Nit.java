package projekat;

public class Nit implements Runnable {
	private Glavni tren;
	private Thread tok=new Thread(this);
	private boolean aktivno=true;
	
	public Nit(Glavni tren) {
		this.tren=tren;
	}
	
	public void  start() {
		tok.start();
	}
	public void stop() {
		aktivno=false;
	}
	
	@Override
	public void run() {
		while(aktivno) {	
			try {
				tren.azuriraj();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
