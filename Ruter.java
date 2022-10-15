package projekat;

import java.util.ArrayList;

import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;

public class Ruter {
	public int br_rutera;
	public ArrayList<Interfejs> interfejsi = new ArrayList<Interfejs>();
	private SnmpSession sesija;
	private SnmpTableModel tabela;
	
	public Ruter(int br_rutera, String host) {
	try {
		this.br_rutera=br_rutera;
		sesija=new SnmpSession(host,161,"si2019","si2019",SnmpConst.SNMPV2);
		SnmpSession.loadMib2();
		tabela = sesija.snmpGetTable("ifTable");
		//System.out.println("ROWCOUNT: "+tabela.getRowCount());
		
		for(int i=0; i<tabela.getRowCount(); i++) {
			try {
			Interfejs interf=new Interfejs(Integer.valueOf(tabela.getValueAt(i,0).toString()), tabela.getValueAt(i,1).toString(),
				tabela.getValueAt(i,2).toString(), tabela.getValueAt(i,3).toString(),
				Long.valueOf(tabela.getValueAt(i,4).toString()), tabela.getValueAt(i,5).toString(),
				Long.valueOf(tabela.getValueAt(i,6).toString()), Long.valueOf(tabela.getValueAt(i,7).toString()));
			
				//System.out.println("Dodat "+i+"-ti interfejs.");
				interfejsi.add(interf);
				
			}
			catch(Exception e){
				System.out.println("**Preskocen "+i+"-ti interfejs.**");
				continue;
			}
		}
	
	}catch(Exception e){
		System.out.println("Router info error!");
	}
	
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("● Ruter "+br_rutera+"\n");
		for(int i=0; i<interfejsi.size(); i++) {
			sb.append(interfejsi.get(i));
		}
		return sb.toString();
		
		/*
		StringBuilder sb= new StringBuilder();
		sb.append("● Ruter "+br_rutera+"\n");
		for(int i=0; i<tabela.getRowCount(); i++) {
			sb.append("  ◦  interfejs "+tabela.getValueAt(i,0)+"\n");
			sb.append("\t▪  opis: "+tabela.getValueAt(i,1)+"\n");
			sb.append("\t▪  tip: "+tabela.getValueAt(i,2)+"\n");
			sb.append("\t▪  MTU: "+tabela.getValueAt(i,3)+"\n");
			sb.append("\t▪  brzina: "+tabela.getValueAt(i,4)+"\n");
			sb.append("\t▪  fizicka adresa: "+tabela.getValueAt(i,5)+"\n");
			sb.append("\t▪  admin. status: "+tabela.getValueAt(i,6)+"\n");
			sb.append("\t▪  operat. status: "+tabela.getValueAt(i,7)+"\n");
			if(i!=tabela.getRowCount())sb.append("\n");
		}
		return sb.toString();
		*/
	}  
	
	public void ugasi(){
		sesija.close();
	}
	
	public static  void main(String [] args) {
		Ruter ruter_primer=new Ruter(1, "192.168.10.1");
		System.out.print(ruter_primer);

	}

}