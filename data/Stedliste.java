package data;

import java.io.Serializable;
import java.util.*;

/**
 * @author	Gruppe 3
 * @version	1
 * @since	1.6
 */
public class Stedliste implements Serializable
{
	private static final long serialVersionUID = 4783661286758461364L;
	/**
	 * Arrayliste som inneholder stedene
	 */
	private ArrayList<Sted> liste = new ArrayList<Sted>();
	
	/**
	 * Setter inn et sted-objekt bakerst i listen
	 * @author Lars Smeby
	 * @param s	Sted-objekt
	 */
	public void settInn(Sted s)
	{
		liste.add(s);
		sorter();		
	}
	
	/**
	 * Sorterer listen etter stedsnavn
	 * @author Lars Smeby
	 */
	public void sorter()
	{
		Collections.sort(liste, new Stedsammenligner(true));
	}
	
	/**
	 * Returnerer antall elementer i listen
	 * @author Lars Smeby
	 * @return	int antall elementer
	 */
	public int size()
	{
		return liste.size();
	}
	
	/**
	 * Returnerer iteratoren til listen
	 * @author Lars Smeby
	 * @return	Iterator<Sted> til listen
	 */
	public Iterator<Sted> iterator()
	{
		return liste.iterator();
	}
	
	/**
	 * Finner et sted-objekt hvis det finnes i listen, bruker bin�rs�k
	 * @author Lars Smeby
	 * @param navn	Navn p� sted
	 * @param fylke	Fylket stedet ligger i (SSBs fylkesnummer)
	 * @return	Et sted-objekt hvis det finnes, ellers null
	 */
	public Sted finnSted(String navn, int fylke)
	{
		int index=-1;
		try
		{
			index = Collections.binarySearch(liste, new Sted(navn, fylke), new Stedsammenligner(true));
		}
		catch(NullPointerException npe)
		{
			System.out.println("Kan ikke s�ke etter et sted der navn er null.");
			return null;
		}
		if(index<0)
			return null;
		return liste.get(index);
	}
	
	/**
	 * Returnerer en Stedliste med stedene i et gitt fylke
	 * @author Henrik Hermansen
	 * @param fylke	int fylkesnummeret
	 * @return	Stedliste med Stedobjektene i et gitt fylke
	 */
	public Stedliste finnSted(int fylke)
	{
		Stedliste stedliste = new Stedliste();
		Iterator<Sted> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			if(neste.getFylke()==fylke)
				stedliste.settInn(neste);
		}
		return stedliste;
	}
	
	/**
	 * Metode for � f� tak i en array med alle stedene i et gitt fylke
	 * @author Lars Smeby
	 * @param fylke	Fylkesnummer
	 * @return	String-array av alle stedsnavnene i et gitt fylke
	 */
	public String[] toString(int fylke)
	{
		Stedliste templiste = finnSted(fylke);
		Iterator<Sted> iterator = templiste.iterator();
		String[] steder = new String[templiste.size()];
		int l�kketeller = 0;
		
		while(iterator.hasNext())
			steder[l�kketeller++] = iterator.next().getNavn();

		return steder;
	}
	
	/**
	 * Sletter et sted og alle dets data fra listen (Alle v�rdata g�r ogs� tapt)
	 * @author Lars Smeby
	 * @param s	Sted-objekt
	 */
	public void slettSted(Sted s)
	{
		liste.remove(s);
		sorter();
	}
}
