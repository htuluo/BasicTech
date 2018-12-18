package basic.tech;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ListRemove {
	public static void main(String[] args) {

		Date date = new Date(2, 0, 21);
		java.sql.Timestamp timestamp2 = new java.sql.Timestamp(date.getTime());
		// java.sql.Date date2 = timestamp2;
		System.out.println(date.getYear());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(sqlDate.toString());
		// Timestamp timestamp = new Timestamp(date, ce);
		System.out.println(timestamp2.toString());
		System.out.println(timestamp2.getTime());
		System.out.println(timestamp2.getDate());

		/*
		 * List<String> list = new ArrayList<>(); for (Integer i = 0; i < 10;
		 * i++) { list.add(i.toString()); }
		 *
		 * Iterator<String> iterator = list.iterator(); while
		 * (iterator.hasNext()) { String string = iterator.next(); if
		 * (string.equals("3")) { iterator.remove(); } //
		 * System.out.println(iterator.next()); // } for (String string : list)
		 * { System.out.println(string); }
		 */
	}

}
