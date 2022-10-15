package projekat;

public class Interfejs {
	
	public int broj;
	public long brzina, admin_status, oper_status;
	public String opis, tip, MTU, fizicka_adr;
	public Interfejs(int brojj, String opiss, String tipp, String MTUU, long brzinaa,
			String fizicka_adrr, long admin_statuss, long oper_statuss){
		broj=brojj;
		opis=opiss;
		tip=tipp;
		MTU=MTUU;
		brzina=brzinaa;
		fizicka_adr=fizicka_adrr;
		admin_status=admin_statuss;
		oper_status=oper_statuss;		
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("  ◦  interfejs "+broj+"\n");
		sb.append("\t▪  opis: "+opis+"\n");
		sb.append("\t▪  tip: "+tip+"\n");
		sb.append("\t▪  MTU: "+MTU+"\n");
		sb.append("\t▪  brzina: "+brzina+"\n");
		sb.append("\t▪  fizicka adresa: "+fizicka_adr+"\n");
		sb.append("\t▪  admin. status: "+admin_status+"\n");
		sb.append("\t▪  operat. status: "+oper_status+"\n");
		return sb.toString();
	}  
}
