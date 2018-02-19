/**
 * Menu class holds the methods that define the main menu. asks uers for prompts and supports options:
 * 	[G]oto goes to the entered date
 *  [V]iew mode user selects weather to view day mode or month mode 
 *  [D]elete deletes events on an entered date or deletes all events in the event list
 *  [L]oad loads an event list
 *  [E]ventList prints all the events in the event list
 *  [C]reate creates and adds an event to the event list
 *  [Q]uit saves all current events in the hash map to a txt and exits the application
 * 
 * @author Steven Gonzalez SSID: 009387092
 * @copyRight - ? hasnt beem published
 * version1
 * October 1, 2015
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
	public static String[] months = {"January", "February", "March", "April","May", "June" , "July", "August", "September" , "October", "November", "December"};
	public static String[] days = {"Su ", "Mo ", "Tu ", "We ", "Th ", "Fr ", "Sa "};
	public static GregorianCalendar cal;
	public static String currentMM = "";
	public static int currentDD = 0;
	public static MyCalendar myCal = new MyCalendar();
	public static HashMap<String, ArrayList<Event>> map;
	
	/**
	 * creates a main menu object makes a call to display the menu 
	 */
	public Menu()
	{
		cal = new GregorianCalendar();
		currentMM = months[cal.get(cal.MONTH)];
		currentDD = cal.get(cal.DAY_OF_MONTH)+1;
		CalendarView view = new CalendarView(0);
		mainMenu();
	} 
	
	/**
	 * main menu interface. user decides and enters the actions of the application. Options: 
	 *  [G]oto goes to the entered date
	 *  [V]iew mode user selects weather to view day mode or month mode 
	 *  [D]elete deletes events on an entered date or deletes all events in the event list
	 *  [L]oad loads an event list
	 *  [E]ventList prints all the events in the event list
	 *  [C]reate creates and adds an event to the event list
	 *  [Q]uit saves all current events in the hash map to a txt and exits the application
	 */
	public static void mainMenu()
	{
		System.out.println("Select one of the following options: ");
		System.out.println("[L]oad,  [V]iew by,  [C]reate,  [G]o to,  [E]vent list,  [D]elete,  [Q]uit");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		if(input.equals("q") | input.equals("Q"))
		{
			MyCalendar.exitStatus();
		}
		else if(input.equals("l") | input.equals("L"))
		{
			String event = null;
			String date = null;
			String sTime = null;
			String eTime = null;
			
			try {
				FileReader file= new FileReader(new File("events.txt"));
				BufferedReader reader = new BufferedReader(file);
				
				String line = reader.readLine();
				while(!line.equals("end."))
				{
					if(line.equals(""))
					{
						line = reader.readLine();
					}
					if(line.equals("end."))
					{
						break;
					}
						if(event == null)
						{
							event = line;
						}
						else if(date == null)
						{
							date = line;
						}
						else if(sTime == null)
						{
							sTime = line;
						}
						else if(eTime == null)
						{
							eTime = line;
						}
						
						if(event != null && date != null && sTime != null && eTime != null)
						{
							Event newEvent = new Event(event, date, sTime, eTime);
							MyCalendar.add(newEvent.getDate(), newEvent);
							event = null;
							date = null;
							sTime = null;
							eTime = null;
						}
						line = reader.readLine();
				}
				mainMenu();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("First run of this prgoram therefor no file yet exists");
			}
			catch(IOException e)
			{
				System.out.println("IOException occured");
			}
		}
		else if(input.equals("v") | input.equals("V"))
		{
			CalendarView cal = new CalendarView(1);
		}
		else if(input.equals("c") | input.equals("C"))
		{
			in = new Scanner(System.in);
			System.out.println("Enter the name of the event you wish to add...");
			String eventName = in.nextLine();
			in = new Scanner(System.in);
			System.out.println("Enter the date for the event to add. Use Format MM/DD/YY. Ex. 10/05/2015");
			String eventDate = in.nextLine();
			in = new Scanner(System.in);
			System.out.print("Enter a start time for this event to add...");
			String  startTime = in.nextLine();
			in = new Scanner(System.in);
			System.out.println("Enter an ending time for this event...");
			String endTime = in.nextLine();
			
			Event eventToAdd = new Event(eventName, eventDate, startTime, endTime);
			MyCalendar.add(eventDate, eventToAdd);
			System.out.println("Event Created and stored in calendar.");
			mainMenu();
		}
		else if(input.equals("g") | input.equals("G"))
		{
			in = new Scanner(System.in);
			System.out.println("Enter the date to go to. Use Format MM/DD/YY. Ex. 10/05/2015");
			String eventDate = in.nextLine();
			MyCalendar.print(eventDate);
			mainMenu();
		}
		else if(input.equals("e") | input.equals("E"))
		{
			MyCalendar.sortMap();
		}
		else if(input.equals("d") | input.equals("D"))
		{
			in = new Scanner(System.in);
			System.out.println("Delete [A]ll or [S]elected ?");
			String option = in.nextLine();
			if(option.equals("a") | option.equals("A"))
			{
				MyCalendar.deleteAll();
			}
			else if(option.equals("s") | option.equals("S"))
			{
				in = new Scanner(System.in);
				System.out.println("Enter a date in which you would like to clear");
				String dateToClear = in.nextLine();
				MyCalendar.deleteSelected(dateToClear);
			}
		}
	}

}