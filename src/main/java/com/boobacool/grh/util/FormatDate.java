package com.boobacool.grh.util;

import java.util.Calendar;
import java.util.Date;

public class FormatDate {

	public static Date ajouterAnnee(String an,String m,String j, int nbAnnee) {
		Calendar cal = Calendar.getInstance();
		try 
		{
		 cal.set(Integer.valueOf(an),Integer.valueOf(m)-1,Integer.valueOf(j));
			cal.add(Calendar.YEAR, nbAnnee);
		} catch (Exception e) {

		}

		return cal.getTime();
	}
	
	public static Date ajouterJour(String an,String m,String j, int nbJour) {
		Calendar cal = Calendar.getInstance();
		try 
		{
		 cal.set(Integer.valueOf(an),Integer.valueOf(m)-1,Integer.valueOf(j));
			cal.add(Calendar.DATE, nbJour);
		} catch (Exception e) {

		}

		return cal.getTime();
	}

}
