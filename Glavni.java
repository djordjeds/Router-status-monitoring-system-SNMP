package projekat;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
U terminalu odraditi komande:
sudo ip route add 192.168.10.0/24 via 192.168.122.100 dev virbr0
sudo ip route add 192.168.20.0/24 via 192.168.122.100 dev virbr0
sudo ip route add 192.168.30.0/24 via 192.168.122.100 dev virbr0
sudo ip route add 192.168.12.0/24 via 192.168.122.100 dev virbr0
sudo ip route add 192.168.13.0/24 via 192.168.122.100 dev virbr0
sudo ip route add 192.168.23.0/24 via 192.168.122.100 dev virbr0
*/

public class Glavni extends JFrame{
	private Ruter r1;
	private Ruter r2;
	private Ruter r3;
	private JTextPane Pane1= new JTextPane();
	private JTextPane Pane2= new JTextPane();
	private JTextPane Pane3= new JTextPane();
	private JPanel panel=new JPanel(new GridLayout(1,3));
	public Nit nit;
	
	private void dodaj_na_pane(JTextPane pane, String poruka, Color boja){
		int duzina;
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet atributset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, boja);
        atributset = sc.addAttribute(atributset, StyleConstants.FontFamily, "Lucida Console");
        duzina = pane.getDocument().getLength();
        pane.setCaretPosition(duzina);
        pane.setCharacterAttributes(atributset, false);
        pane.replaceSelection(poruka);
    }
	
	private void ruterpane(JTextPane tPane, Ruter r) {
		dodaj_na_pane(tPane, "● Ruter "+r.br_rutera+"\n", Color.black);
        
        for(int i=0;i<r.interfejsi.size();i++) {
        	dodaj_na_pane(tPane, "  ◦  interfejs "+r.interfejsi.get(i).broj+"\n", Color.black);
        	dodaj_na_pane(tPane, "\t▪  opis: "+r.interfejsi.get(i).opis+"\n", Color.black);
        	dodaj_na_pane(tPane, "\t▪  tip: "+r.interfejsi.get(i).tip+"\n", Color.black);
        	dodaj_na_pane(tPane, "\t▪  MTU: "+r.interfejsi.get(i).MTU+"\n", Color.black);
        	dodaj_na_pane(tPane, "\t▪  brzina: "+r.interfejsi.get(i).brzina+"\n", Color.black);
        	dodaj_na_pane(tPane, "\t▪  fizicka adresa: "+r.interfejsi.get(i).fizicka_adr+"\n", Color.black);
        	
        	if(r.interfejsi.get(i).admin_status==1) {
        		dodaj_na_pane(tPane, "\t▪", Color.green);
        	}
        	else {
        		dodaj_na_pane(tPane, "\t▪", Color.red);
        	}
        	dodaj_na_pane(tPane, " admin. status: "+r.interfejsi.get(i).admin_status+"\n", Color.black);
        	
        	if(r.interfejsi.get(i).oper_status==1) {
        		dodaj_na_pane(tPane, "\t▪", Color.green);
        	}
        	else {
        		dodaj_na_pane(tPane, "\t▪", Color.red);
        	}
        	dodaj_na_pane(tPane, " operat. status: "+r.interfejsi.get(i).oper_status+"\n", Color.black);
        }		
	}
	
	private void populateWindow() {
		
		ruterpane(Pane1=new JTextPane(), r1);
		JScrollPane jedan = new JScrollPane(Pane1);
		panel.add(jedan);	
		ruterpane(Pane2=new JTextPane(), r2);
		JScrollPane dva = new JScrollPane(Pane2);
		panel.add(dva);		
		ruterpane(Pane3=new JTextPane(), r3);
		JScrollPane tri = new JScrollPane(Pane3);
		panel.add(tri);
		
		this.add(panel);
	}
	
	public void azuriraj(){
		r1=new Ruter(1,"192.168.10.1");
		r2=new Ruter(2,"192.168.20.1");
		r3=new Ruter(3,"192.168.30.1");
		this.remove(panel);
		panel=new JPanel(new GridLayout(1,3)); 
		
		ruterpane(Pane1=new JTextPane(), r1);
		JScrollPane jedan = new JScrollPane(Pane1);
		panel.add(jedan);		
		ruterpane(Pane2=new JTextPane(), r2);
		JScrollPane dva = new JScrollPane(Pane2);
		panel.add(dva);		
		ruterpane(Pane3=new JTextPane(), r3);
		JScrollPane tri = new JScrollPane(Pane3);
		panel.add(tri);
		
		this.add(panel);
		panel.revalidate();
		panel.repaint();
		this.revalidate();
		System.out.println("Azurirao");
	}
	
	
	public Glavni() {
		r1=new Ruter(1,"192.168.10.1");;
		r2=new Ruter(1,"192.168.20.1");;
		r3=new Ruter(1,"192.168.30.1");;
		setLocation(200, 200);
		setResizable(true);
		setTitle("Interfejsi");
		setPreferredSize(new Dimension(1400, 800));
		populateWindow();
		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				nit.stop();
				r1.ugasi();
				r2.ugasi();
				r3.ugasi();
			}
		});
		setVisible(true);
		
		nit=new Nit(this);
		nit.start();
	}

	public static void main(String[] args) {
		new Glavni();
	}

}