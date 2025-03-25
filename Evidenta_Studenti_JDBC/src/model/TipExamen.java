package model;

import java.util.Optional;

public enum TipExamen {

	E("E","Examen",1),
	C("C","Colocviu",2),
	P("P","Proiect",3);
	
	private String abreviere;
	private String nume;
	private int nrOrdine;
	
	/**
	 * @param abreviere
	 * @param nume
	 * @param nrOrdine
	 */
	private TipExamen(String abreviere, String nume, int nrOrdine) {
		this.abreviere = abreviere;
		this.nume = nume;
		this.nrOrdine = nrOrdine;
	}

	/**
	 * @return the abreviere
	 */
	public String getAbreviere() {
		return abreviere;
	}

	/**
	 * @return the nume
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * @return the nrOrdine
	 */
	public int getNrOrdine() {
		return nrOrdine;
	}
	
	public final static Optional<TipExamen> string2CategorieFormativa(String val) {
			
			Optional<TipExamen> obj=Optional.empty();;
			for(int i=0;i<values().length;i++) {
					if (  val.equalsIgnoreCase(TipExamen.values()[i].abreviere) ==true ) {
							obj=Optional.of(TipExamen.values()[i]);
							return obj;
				    }
		   }
			return obj;
		}

	
	
}
