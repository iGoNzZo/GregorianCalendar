/**
 * MyCalendar class defines the data structure hash map that holds 
 * events. uses the event date as a key to store the event object 
 * 
 * @author Steven Gonzalez SSID: 009387092
 * @copyRight - ? hasnt beem published
 * version1
 * October 1, 2015
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCalendar {
	public static HashMap<String, ArrayList<Event>> map;
	
	/**
	 * sets up MyCalendar by making a hash map to store information
	 */
	public MyCalendar()
	{
		map = new HashMap<String, ArrayList<Event>>();
	}
	
	/**
	 * adds an object of type Event to the hash map
	 * 
	 * @param d the date of the event used as a key for the hash map
	 * @param e the event to add
	 */
	public static void add(String d,Event e)
	{
		ArrayList<Event> value = map.get(d);
		if(value == null)
		{
			value = new ArrayList<Event>();
			map.put(d, value);
		}
		value.add(e);
		Collections.sort(value, Event.compareTime);
	}
	
	/**
	 * deletes the events stored within a selected date
	 * 
	 * @param key location of the event in the hash map
	 */
	public static void deleteSelected(String key)
	{
		map.remove(key);
		Menu.mainMenu();
	}
	 
	/**
	 * deletes all events in the hash map
	 */
	public static void deleteAll()
	{
		map.clear();
		Menu.mainMenu();
	}
	
	
	public static void sortMap()
	{
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<Event> events = new ArrayList<Event>();
		
		for(Entry<String, ArrayList<Event>> keyList: map.entrySet())
		{
			 keys.add(keyList.getKey());
		}
		
		for(int i =0; i < keys.size(); i++)
		{
			ArrayList<Event> value = map.get(keys.get(i));
			for(int j = 0; j < value.size(); j++)
			{
				events.add(value.get(j));
			}
		}
		Collections.sort(events, Event.compareDate);
		printEventList(events);
	}
	/**
	 * prints the entire hash map of event objects in sorted order
	 */
	public static void printEventList(ArrayList<Event> eventList) 
	{
		//sortMap();
		
		for(int j = 0; j < eventList.size(); j++)
		{
			Event hold = eventList.get(j);
			System.out.println(hold.toString());
		}
		System.out.println();
		Menu.mainMenu();
	}	 

	/**
	 * prints the events on a given day sorted by time
	 * 
	 * @param d the hash map key for the location of the event
	 */
	public static void print(String d)
	 {
		 System.out.println("Events scheduled for: " + d);
		 ArrayList<Event> val = map.get(d);
		 if(val == null)
		 {
			 System.out.println("No events scheduled on this day");
		 }
		 else
		 {
			 for(int j = 0; j < val.size(); j++)
			 	{
				 	System.out.println(map.get(d).get(j).toString2());
			 	}
		 }
		 System.out.println();
	 }
	
	/**
	 * creates a new file called "events.txt" and writes all events in the
	 * hash map to the .txt file and then terminates the program
	 */
	public static void exitStatus()
	{
		try{
			File file = new File("events.txt");
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Set<String> keys = map.keySet();
			for(String s : keys)
			{
				for(int i = 0; i < map.get(s).size(); i++)
				{
					String e = map.get(s).get(i).getEvent() + "\n";
					String d = map.get(s).get(i).getDate() + "\n";
					String sT = map.get(s).get(i).getStartT() + "\n";
					String eT = map.get(s).get(i).getEndT() + "\n";
					String blank = "\n";
					bw.write(e);
					bw.write(d);
					bw.write(sT);
					bw.write(eT);
					bw.write(blank);
				}
			}
			bw.write("end.");
			bw.close();
			System.out.println("Session ended...");
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("Could not write events.txt");
		}
	}
}
