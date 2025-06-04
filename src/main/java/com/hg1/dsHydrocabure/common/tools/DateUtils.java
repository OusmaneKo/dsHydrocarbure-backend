package com.hg1.dsHydrocabure.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    public static final long trois_mois = 90;
    public static final long six_mois = 180;
    public static final long douze_mois = 365;
    public static final long heure_to_ms = 3600000;
    public static final long mitune_to_ms = 60000;
    public static final long s_to_ms = 1000;
    public static final long jour_to_heure = 24;

    public static String dateToString(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToStringFile(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static int ecartDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        Calendar calStr1 = Calendar.getInstance();
        Calendar calStr2 = Calendar.getInstance();
        Calendar calStr0 = Calendar.getInstance();

        Date date1 = d1;
        Date date2 = d2;
        Date d = null;

        int nbMois = 0;
        int nbAnnees = 0;
        long nbJours = 0;

        int UN = 1;
        int DOUZE = 12;

        if (date1.equals(date2)) {
            return 0;
        }

        calStr1.setTime(date1);
        calStr2.setTime(date2);

        nbMois = 0;
        while (calStr1.before(calStr2)) {
            calStr1.add(GregorianCalendar.MONTH, UN);
            if (calStr1.before(calStr2) || calStr1.equals(calStr2)) {
                nbMois++;
            }
        }
        nbAnnees = (nbMois / DOUZE);
        nbMois = (nbMois - (nbAnnees * DOUZE));

        calStr0 = Calendar.getInstance();
        calStr0.setTime(date1);
        calStr0.add(GregorianCalendar.YEAR, nbAnnees);
        calStr0.add(GregorianCalendar.MONTH, nbMois);
        nbJours = (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;

        /*
         * System.out.print("Nb Annees : "+nbAnnees+"\n");
         * System.out.print("Nb Mois : "+nbMois+"\n");
         * System.out.print("Nb Jours : "+nbJours+"\n");
         */

        return (Integer) nbAnnees;
    }

    public static long ecartDateJour(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        Calendar calStr1 = Calendar.getInstance();
        Calendar calStr2 = Calendar.getInstance();
        Calendar calStr0 = Calendar.getInstance();

        Date date1 = d1;
        Date date2 = d2;

        long nbJours = 0;
        if (date1.equals(date2)) {
            return 1;
        }

        calStr1.setTime(date1);
        calStr2.setTime(date2);
        calStr0 = Calendar.getInstance();
        calStr0.setTime(date1);
        nbJours = (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;
        return nbJours;
    }

    public static Date stringToDate(String sVal) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (sVal != null && !sVal.equals("")) {
                return df.parse(sVal);
            } else {
                return null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static long getLongYear(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static int getSeconde(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);

        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date createDate(int year, int month, int day, int h, int m, int s) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, s);

        return calendar.getTime();
    }

    public static String dateheureToString(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateheureToStringSansHeure(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String heureToString(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static String dateToStringEn(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToRefExportSmsLink(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToStringSql(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sDate = dateFormat.format(date);
        String jour = sDate.substring(0, 2);
        String mois = sDate.substring(3, 5);
        String annee = sDate.substring(6, 10);
        sDate = annee + "-" + mois + "-" + jour;
        return sDate;
    }

    public static String dateHeureToStringSql(Date date) {
        String sDate = "0000-00-00 00:00:00";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sDate = dateFormat.format(date);
        }
        return sDate;
    }

    /*KALOGA*/
    public static String dateHeureToStringSqlNull(Date date) {
        String sDate = "0000-00-00 00:00:00";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sDate = dateFormat.format(date);
        }
        return sDate;
    }


    // ////////////////////////////////// MEMBRES
    // ///////////////////////////////////////////////
    public static Date getFirstDayeOfyear(Date date) {
        Calendar d = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        d.set(cal.get(Calendar.YEAR), 0, 1);
        return d.getTime();
    }

    //kaloga
    public static Integer getFirstDayOfyear(Date date) {
        Calendar d = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        d.set(cal.get(Calendar.YEAR), 0, 1);
        return d.getActualMaximum(d.DAY_OF_YEAR);
    }

    public static Date getLundi(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int j = cal.get(Calendar.DAY_OF_WEEK);
        Date d = null;
        if (j == 1)
            d = removeDay(date, 6);
        if (j == 2)
            d = date;
        if (j == 3)
            d = removeDay(date, 1);
        if (j == 4)
            d = removeDay(date, 2);
        if (j == 5)
            d = removeDay(date, 3);
        if (j == 6)
            d = removeDay(date, 4);
        if (j == 7)
            d = removeDay(date, 5);
        return d;
    }

    public static Date getNextMonday(Date date) {
        Calendar maDate = new GregorianCalendar();
        maDate.setTime(date);
        // On se positionne sur le Lundi de la semaine courante :
        maDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // Puis on ajoute 7 jours :
        maDate.add(Calendar.DATE, 7);
        return maDate.getTime();
    }

    public static String getDayMonthYear(Date date, String type) {
        String i = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int v = 0;
        if (type.equals("D")) {
            v = cal.get(Calendar.DAY_OF_MONTH);
            if (v < 10)
                i = "0" + v;
            else
                i = v + "";
        }
        if (type.equals("M")) {
            v = (cal.get(Calendar.MONTH) + 1);
            if (v < 10)
                i = "0" + v;
            else
                i = v + "";
        }
        if (type.equals("Y")) {
            v = cal.get(Calendar.YEAR);
            i = v + "";
        }

        return i;
    }

    public static Date addYear(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, delai);
        return cal.getTime();
    }

    public static Date removeYear(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -delai);
        return cal.getTime();
    }

    public static Date addDay(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, delai);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, delai);
        return cal.getTime();
    }

    public static Date addSeconde(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, delai);
        return cal.getTime();
    }

    public static Date removeDay(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -delai);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, delai);
        return cal.getTime();
    }

    public static Date removeMonth(Date date, int delai) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -delai);
        return cal.getTime();
    }

    public static int getCourantYear() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dat = dateFormat.format(new Date());
        return Integer.parseInt(dat.substring(6, 10));
    }

    public static boolean betweenDates(Date date, Date date1, Date date2, String inf, String sup) {
        boolean b = false;

        String sdate = dateToStringFile(date);
        String sdate1 = dateToStringFile(date1);
        String sdate2 = dateToStringFile(date2);

        date = stringMysqlToDateFr(sdate);
        date1 = stringMysqlToDateFr(sdate1);
        date2 = stringMysqlToDateFr(sdate2);

        if (date.after(date1) && date.before(date2)) {
            b = true;
        }
        if (date.equals(date1) || date.equals(date2)) {
            b = true;
        }
        return b;
    }

    public static long DifferenceDate(Date date1, Date date2, String jourf, String jours, String jourd,
                                      List<Date> ljf) {
        long resultat = 0;
        Date d = date1;
        boolean b = true;
        while (b) {
            boolean add = true;
            if (d.after(date2)) {
                b = false;
                break;
            } else {
                if (jours.equals("N")) {
                    if (getDayOfWeek(d) == 7)
                        add = false;
                }
                if (jours.equals("N")) {
                    if (getDayOfWeek(d) == 1)
                        add = false;
                }
                if (jourf.equals("N") && ljf.size() > 0) {
                    if (ljf.contains(d))
                        add = false;
                }
                if (add)
                    resultat++;
                d = addDay(d, 1);
            }
        }

        return resultat;
    }

    public static String StringDateToStringSql(String sDate) {
        String jour = sDate.substring(0, 2);
        String mois = sDate.substring(3, 5);
        String annee = sDate.substring(6, 10);
        sDate = annee + "-" + mois + "-" + jour;
        return sDate;
    }

    public static String StringDateToStringfrSlash(String sDate) {
        String jour = sDate.substring(0, 2);
        String mois = sDate.substring(3, 5);
        String annee = sDate.substring(6, 10);
        sDate = jour + "/" + mois + "/" + annee;
        return sDate;
    }

    public static Date stringMysqlToDate(String sVal) {
        if (sVal == null || sVal.equals("null"))
            return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(sVal);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date stringMysqlToDateFr(String sVal) {
        if (sVal == null || sVal.equals("null"))
            return null;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return df.parse(sVal);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date stringMysqlToDateHeure(String sVal) {
        if (sVal == null || sVal.equals("null"))
            return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        try {
            return df.parse(sVal);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date sgloclToDate(String sVal) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");
        try {
            return df.parse(sVal);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Determiner si une ann�e est bissextile
     */
    public static boolean estBessextile(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * Determiner le jour de la semaine
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date setDate(int j, int m, int a) {
        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        gc.set(GregorianCalendar.DATE, j);
        gc.set(GregorianCalendar.MONTH, m - 1);
        gc.set(GregorianCalendar.YEAR, a);
        return gc.getTime();
    }

    public static int getDate(Date date, String type) {
        int d = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type.equals("Y")) {
            d = cal.get(Calendar.YEAR);
        }
        if (type.equals("M")) {
            d = cal.get(Calendar.MONTH);
        }
        if (type.equals("D")) {
            d = cal.get(Calendar.DAY_OF_YEAR);
        }
        return d;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar d = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        d.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return d.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar d = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        d.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        d.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        Date dret = d.getTime();

        return createDate(getYear(date), getMonth(date), getDay(dret));
    }

    public static boolean comparerDate(Date date1, Date date2) {
        long dif = (date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24;
        return dif >= 1;
    }

    public static String dateToMoisAnneeCourt(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("MMM-yy");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToMois(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("MMMM");
        if (laDate != null)
            return simple.format(laDate).toUpperCase();
        else
            return "";
    }

    public static String dateToMoisXXX(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("MM");
        if (laDate != null)
            return simple.format(laDate).toUpperCase();
        else
            return "";
    }

    public static String dateToMMYYYY(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("MM-yyyy");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToDDMMYYYY(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToDDMMYYYYHMS(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("dd:MM:yyyy:H:m:ss");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String dateToDDMMYYYYSql(Date laDate) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        if (laDate != null)
            return simple.format(laDate);
        else
            return "";
    }

    public static String monthToString(int month) {
        String monthString;
        switch (month) {
            case 1:
                monthString = "Janvier";
                break;
            case 2:
                monthString = "F�vrier";
                break;
            case 3:
                monthString = "Mars";
                break;
            case 4:
                monthString = "Avril";
                break;
            case 5:
                monthString = "Mai";
                break;
            case 6:
                monthString = "Juin";
                break;
            case 7:
                monthString = "Juilllet";
                break;
            case 8:
                monthString = "Aout";
                break;
            case 9:
                monthString = "Septembre";
                break;
            case 10:
                monthString = "Octobre";
                break;
            case 11:
                monthString = "Novembre";
                break;
            case 12:
                monthString = "Decembre";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }


    public static Integer monthToInt(String month) {
        int monthInteger = 0;
        if (month.contains("Janv") || month.contains("jan") || month.contains("jan ")) {
            monthInteger = 01;
        }
        if (month.equalsIgnoreCase("Janvier") || month.equalsIgnoreCase("Janvier ")) {
            monthInteger = 01;
        }
        if (month.equalsIgnoreCase("01")) {
            monthInteger = 01;
        }
        if (month.equalsIgnoreCase("1")) {
            monthInteger = 01;
        }
        if (month.contains("F�v")) {
            monthInteger = 02;
        }
        if (month.contains("Fev") || month.contains("fev") || month.contains("fev ") || month.contains("f�v") || month.contains("f�v ")) {
            monthInteger = 02;
        }
        if (month.contains("FEV")) {
            monthInteger = 02;
        }
        if (month.equalsIgnoreCase("F�vrier ")) {
            monthInteger = 02;
        }
        if (month.equalsIgnoreCase("Fevrier ")) {
            monthInteger = 02;
        }
        if (month.equalsIgnoreCase("F�vrier")) {
            monthInteger = 02;
        }
        if (month.equalsIgnoreCase("Fevrier")) {
            monthInteger = 02;
        }
        if (month.equalsIgnoreCase("2") || month.equalsIgnoreCase("02")) {
            monthInteger = 02;
        }
        if (month.contains("Mar") || month.contains("mar") || month.contains("mar ")) {
            monthInteger = 03;
        }
        if (month.contains("MAR")) {
            monthInteger = 03;
        }
        if (month.equalsIgnoreCase("Mars") || month.equalsIgnoreCase("Mars ")) {
            monthInteger = 03;
        }
        if (month.equalsIgnoreCase("3") || month.equalsIgnoreCase("03")) {
            monthInteger = 03;
        }

        if (month.contains("Avr") || month.contains("Avr ")) {
            monthInteger = 04;
        }
        if (month.contains("AVR") || month.contains("AVR ") || month.contains("avr ")) {
            monthInteger = 04;
        }

        if (month.contains("Avri")) {
            monthInteger = 04;
        }
        if (month.contains("AVRI") || month.contains("avr")) {
            monthInteger = 04;
        }
        if (month.equalsIgnoreCase("Avril") || month.equalsIgnoreCase("Avril ")) {
            monthInteger = 04;
        }

        if (month.equalsIgnoreCase("4") || month.equalsIgnoreCase("04")) {
            monthInteger = 04;
        }
        //A FAIRE
        if (month.contains("Mai") || month.contains("mai") || month.contains("mai ")) {
            monthInteger = 05;
        }
        if (month.contains("MAI")) {
            monthInteger = 05;
        }
        if (month.equalsIgnoreCase("Mai") || month.equalsIgnoreCase("Mai ")) {
            monthInteger = 05;
        }
        if (month.equalsIgnoreCase("05") || month.equalsIgnoreCase("5")) {
            monthInteger = 05;
        }
        //A VOIR
        if (month.contains("juin") || month.contains("juin ")) {
            monthInteger = 06;
        }
        if (month.contains("JUIN") || month.contains("JUIN ")) {
            monthInteger = 06;
        }
        if (month.equalsIgnoreCase("Juin") || month.equalsIgnoreCase("Juin ")) {
            monthInteger = 06;
        }

        if (month.equalsIgnoreCase("6") || month.equalsIgnoreCase("06")) {
            monthInteger = 06;
        }
        if (month.contains("Juil") || month.contains("Juil ")) {
            monthInteger = 07;
        }
        if (month.contains("juil") || month.contains("juil ")) {
            monthInteger = 07;
        }
        if (month.contains("JUIL") || month.contains("JUIL ")) {
            monthInteger = 07;
        }
        if (month.equalsIgnoreCase("Juillet") || month.equalsIgnoreCase("Juillet ")) {
            monthInteger = 07;
        }
        if (month.equalsIgnoreCase("7") || month.equalsIgnoreCase("07")) {
            monthInteger = 07;
        }
        if (month.contains("Ao�t") || month.contains("Ao�t ")) {
            monthInteger = 8;
        }
        if (month.contains("Ao�t") || month.contains("Ao�t ")) {
            monthInteger = 8;
        }
        if (month.contains("AOU") || month.contains("AOUT ")) {
            monthInteger = 8;
        }

        if (month.equalsIgnoreCase("Ao�t") || month.equalsIgnoreCase("Ao�t ")) {
            monthInteger = 8;
        }
        if (month.equalsIgnoreCase("8") || month.equalsIgnoreCase("08")) {
            monthInteger = 8;
        }
        if (month.equalsIgnoreCase("Ao�t") || month.equalsIgnoreCase("Ao�t ")) {
            monthInteger = 8;
        }
        if (month.contentEquals("Aout") || month.contentEquals("Aout ")) {
            monthInteger = 8;
        }
        if (month.contains("Sept") || month.contains("sept") || month.contains("s�pt")) {
            monthInteger = 9;
        }
        if (month.contains("SEPT") || month.contains("SEPT ")) {
            monthInteger = 9;
        }
        if (month.contains("S�pt") || month.contains("S�pt ")) {
            monthInteger = 9;
        }
        if (month.contains("Sept ")) {
            monthInteger = 9;
        }
        if (month.equalsIgnoreCase("Septembre") || month.equalsIgnoreCase("Septembre ")) {
            monthInteger = 9;
        }
        if (month.equalsIgnoreCase("S�ptembre") || month.equalsIgnoreCase("S�ptembre ")) {
            monthInteger = 9;
        }
        if (month.equalsIgnoreCase("9") || month.equalsIgnoreCase("09")) {
            monthInteger = 9;
        }

        if (month.contains("Oct")) {
            monthInteger = 10;
        }
        if (month.contains("OCT") || month.contains("OCT ") || month.contains("oct") || month.contains("oct ")) {
            monthInteger = 10;
        }
        if (month.equalsIgnoreCase("Octobre") || month.equalsIgnoreCase("Octobre ")) {
            monthInteger = 10;
        }
        if (month.equalsIgnoreCase("10")) {
            monthInteger = 10;
        }
        if (month.contains("Nov") || month.contains("nov") || month.contains("nov ")) {
            monthInteger = 11;
        }
        if (month.contains("NOV") || month.contains("NOV ")) {
            monthInteger = 11;
        }
        if (month.equalsIgnoreCase("Novembre") || month.equalsIgnoreCase("Novembre ")) {
            monthInteger = 11;
        }
        if (month.equalsIgnoreCase("11")) {
            monthInteger = 11;
        }
        if (month.contains("D�c") || month.contains("D�c ") || month.contains("d�c") || month.contains("d�c ")) {
            monthInteger = 12;
        }
        if (month.contentEquals("Dec") || month.contentEquals("Dec ") || month.contentEquals("dec") || month.contentEquals("dec ")) {
            monthInteger = 12;
        }
        if (month.contentEquals("DEC") || month.contentEquals("DEC ")) {
            monthInteger = 12;
        }
        if (month.equalsIgnoreCase("D�cembre") || month.equalsIgnoreCase("D�cembre ")) {
            monthInteger = 12;
        }
        if (month.equalsIgnoreCase("Decembre") || month.equalsIgnoreCase("Decembre ")) {
            monthInteger = 12;
        }
        if (month.equalsIgnoreCase("12")) {
            monthInteger = 12;
        }

        if (month.equalsIgnoreCase("Vers")) {
            monthInteger = 01;
        }
        return monthInteger;
    }

    public static int getYears(Date d) {
        Calendar curr = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(d);
        int yeardiff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        curr.add(Calendar.YEAR, -yeardiff);
        if (birth.after(curr)) {
            yeardiff = yeardiff - 1;
        }
        return yeardiff;
    }

    public static long DifferenceDate(Date date1, Date date2, String r) {
        long resultat = 0;
        long msjour = 0;
        long heure = 0;

        if (r == "ss") {
            msjour = date1.getTime() - date2.getTime();
            resultat = Math.abs(msjour) / s_to_ms;

        }
        if (r == "mm") {
            msjour = date1.getTime() - date2.getTime();
            resultat = Math.abs(msjour) / mitune_to_ms;

        }
        if (r == "hh") {
            msjour = date1.getTime() - date2.getTime();
            heure = Math.abs(msjour) / heure_to_ms;
            resultat = heure;
        }
        if (r == "jj") {
            msjour = date1.getTime() - date2.getTime();
            heure = Math.abs(msjour) / heure_to_ms;
            resultat = heure / jour_to_heure;
        }
        if (r == "yy") {
            msjour = date1.getTime() - date2.getTime();
            heure = Math.abs(msjour) / heure_to_ms;
            resultat = heure / douze_mois;
        }

        return resultat;
    }
}
