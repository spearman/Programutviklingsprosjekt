package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import logic.FinnEkstremData;

import data.Stedliste;

/**
 * @author Henrik Hermansen
 *
 */
public class EkstremverdiPanel extends MetroPanel
{
	private JPanel stedtype, datotype, spesAvgEnkelt, spesType;
	private ButtonGroup stedtypegruppe, datotypegruppe, spesAvgEnkeltGruppe, spesTypeGruppe;
	protected JRadioButton stedLandet, stedFylke, stedSted, rdag, rm�ned, r�r, rEnkelverdi, rAvgverdi, rNedb�r, rMintemp, rMaxtemp;
	private JButton hentData;
	
	/**
	 * @author Henrik Hermansen
	 * @param sl
	 */
	public EkstremverdiPanel(Stedliste sl) {
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		sted.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
		stedtype = new JPanel(new GridLayout(0,3));
		stedtypegruppe = new ButtonGroup();
		stedLandet = new JRadioButton("Hele landet", true);
		stedFylke = new JRadioButton("Et fylke", false);
		stedSted = new JRadioButton("Et sted", false);
		stedLandet.addActionListener(handlingslytter);
		stedFylke.addActionListener(handlingslytter);
		stedSted.addActionListener(handlingslytter);
		stedtypegruppe.add(stedLandet);
		stedtypegruppe.add(stedFylke);
		stedtypegruppe.add(stedSted);
		stedtype.add(stedLandet);
		stedtype.add(stedFylke);
		stedtype.add(stedSted);
		
		datotype = new JPanel(new GridLayout(0,3));
		datotypegruppe = new ButtonGroup();
		rdag = new JRadioButton("Dag", true);
		rm�ned = new JRadioButton("M�ned", false);
		r�r = new JRadioButton("�r", false);
		rdag.addActionListener(handlingslytter);
		rm�ned.addActionListener(handlingslytter);
		r�r.addActionListener(handlingslytter);
		datotypegruppe.add(rdag);
		datotypegruppe.add(rm�ned);
		datotypegruppe.add(r�r);
		datotype.add(rdag);
		datotype.add(rm�ned);
		datotype.add(r�r);
		
		spesAvgEnkelt = new JPanel(new GridLayout(0,2));
		spesAvgEnkeltGruppe = new ButtonGroup();
		rEnkelverdi = new JRadioButton("Enkeltverdi", true);
		rAvgverdi = new JRadioButton("Gjennomsnitt", false);
		rEnkelverdi.addActionListener(handlingslytter);
		rAvgverdi.addActionListener(handlingslytter);
		spesAvgEnkeltGruppe.add(rEnkelverdi);
		spesAvgEnkeltGruppe.add(rAvgverdi);
		spesAvgEnkelt.add(rEnkelverdi);
		spesAvgEnkelt.add(rAvgverdi);
		
		spesType = new JPanel(new GridLayout(0,3));
		spesTypeGruppe = new ButtonGroup();
		rNedb�r = new JRadioButton("Nedb�r", true);
		rMintemp = new JRadioButton("Mintemp", false);
		rMaxtemp = new JRadioButton("Maxtemp", false);
		rNedb�r.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		spesTypeGruppe.add(rNedb�r);
		spesTypeGruppe.add(rMintemp);
		spesTypeGruppe.add(rMaxtemp);
		spesType.add(rNedb�r);
		spesType.add(rMintemp);
		spesType.add(rMaxtemp);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg s�keomr�de"));
		grid.add(stedtype);
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type s�k"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel("Spesifiser s�ket"));
		grid.add(spesAvgEnkelt);
		grid.add(new JLabel(""));
		grid.add(spesType);
		grid.add(new JLabel(""));
		grid.add(hentData);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
	}
	
	/**
	 * Privat lytterklasse for elementene i panelet.
	 * @author Henrik Hermansen
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				Object[][] data = FinnEkstremData.finnData(sl, panel, stedLandet, stedFylke, stedSted, fylke, sted, rdag, rm�ned, r�r, ldag, lm�ned, l�r, rEnkelverdi, rAvgverdi, rNedb�r, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data);
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(stedLandet.isSelected())
			{
				fylke.setEnabled(false);
				sted.setEnabled(false);
			}
			if(stedFylke.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(false);
			}
			if(stedSted.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(true);
			}
			if(rdag.isSelected())
			{
				ldag.setEnabled(true);
				lm�ned.setEnabled(true);
			}
			if(rm�ned.isSelected())
			{
				ldag.setEnabled(false);
				lm�ned.setEnabled(true);
			}
			if(r�r.isSelected())
			{
				ldag.setEnabled(false);
				lm�ned.setEnabled(false);
			}
			if(rEnkelverdi.isSelected())
			{
				ldag.setEnabled(true);
				rdag.setEnabled(true);
			}
			if(rAvgverdi.isSelected())
			{
				ldag.setEnabled(false);
				rdag.setEnabled(false);
				rm�ned.setSelected(true);
			}
		}
	}
}
