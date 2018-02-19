/**
 * Calendar view mode offers two types of viewing options
 * -[D]ay view displays a date and any events that may be scheduled on that
 * 		day, also supports the traversal of days
 * - [M]onth view displays the current month with brackets[] around the curre
 * 		day, also supports traversal of months
 * 
 * @author Steven Gonzalez SSID: 009387092
 * @copyRight - ? hasnt beem published
 * version1
 * October 1, 2015
 */
import java.awt.*;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarView {
	public static String[] months = {"January", "February", "March", "April","May", "June" , "July", "August", "September" , "October", "November", "December"};
	public static String[] days = {"Su ", "Mo ", "Tu ", "We ", "Th ", "Fr ", "Sa "};
	public static String[] fullDays = {"Su ", "Mo ", "Tu ", "We ", "Th ", "Fr ", "Sa "};
	public static GregorianCalendar cal;
	public static String currentMM = "";
	public static int currentDD = 0;
			
	/**
	 * - creates a calendar object and sets the current month and current day
	 * - asks user to display day view or monthView
	 * 
	 * @param mode display the initial current month and date if mode = 0 or month view with traversal if mode = 1
	 */
	public CalendarView(int mode)
	{
		cal = new GregorianCalendar();
		currentMM = months[cal.get(cal.MONTH)];
		currentDD = cal.get(cal.DAY_OF_MONTH);
			
		if(mode == 0 )
		{
			monthViewCalendar(0);
		}
		else
		{
			Scanner in = new Scanner(System.in);
			System.out.println("[D]ay view or [M]onth View?");
			String a = in.nextLine();
			
			if(a.equals("d") | a.equals("D"))
			{
				dayViewCalendar();
				dayViewer();
			}
			else if(a.equals("m") | a.equals("M"))
			{
				monthViewCalendar(1);
				monthViewer();
			}
		}	
	}
			
	/**
	 * options for traversal of day view mode. Allows user to see next day or 
	 * 	previous day. also sets the day and makes a call for the changes to be
	 *  displayed. user can also choose to go back to the main menu 
	 */
	public void dayViewer()
	{
		System.out.println("[P]revious, [N]ext or [M]ain menu");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		if(input.equals("p") | input.equals("P"))
		{
			cal.add(cal.DATE, -1);
			dayViewCalendar();
			dayViewer();
		}
		else if(input.equals("n") | input.equals("N"))
		{
			cal.add(cal.DATE, 1);
			dayViewCalendar();
			dayViewer();
		}
		else if(input.equals("m") | input.equals("M"))
		{
			Menu.mainMenu();
			return;
		}
	}
		
	/**
	 * options for traversal of monthView mode. Allows user to see next month or 
	 * 	previous month. also sets the month and makes a call for the changes to be
	 *  displayed. user can also choose to go back to the main menu 
	 */
	public static void monthViewer()
	{
		System.out.println("[P]revious, [N]ext or [M]ain menu");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		if(input.equals("p") | input.equals("P"))
		{
			cal.add(cal.MONTH, -1);
			monthViewCalendar(1);
		}
		else if(input.equals("n") | input.equals("N"))
		{
			cal.add(cal.MONTH, 1);
			monthViewCalendar(1);
		}
		else if(input.equals("m") | input.equals("M"))
		{
			Menu.mainMenu();
			return;
		}
	}
		
	/**
	 * displays a day with any events that may be scheduled on that day
	 */
	public void dayViewCalendar()
	{
		int currentDayName = cal.get(cal.DAY_OF_WEEK)-1;
		int currentMonth = cal.get(cal.MONTH);
		int currentDay = cal.get(cal.DAY_OF_MONTH);
		String date = currentMonth+1 + "/" + currentDay +"/" + cal.get(cal.YEAR);
		System.out.println(days[currentDayName] + ", " + months[currentMonth] + " " + currentDay + ", " + cal.get(cal.YEAR));
		MyCalendar.print(date);
		dayViewer();
	}
			
	/**
	 * displays a month
	 * 
	 * @param cal the current calendar to display
	 * @param mode tells the method if this should run as an initial screen or as a monthView
	 */
	public static void monthViewCalendar(int mode)
	{
		cal.set(cal.DATE, 1);
		int max = cal.getActualMaximum(cal.DAY_OF_MONTH);
		int firstDayOfMonth = cal.get(cal.DAY_OF_WEEK)-1;
				
		System.out.println("   " + months[cal.get(cal.MONTH)] + " " + cal.get(cal.YEAR));
		for(int i =  0; i < days.length; i++)
		{
			System.out.print(days[i]);
		}
		System.out.print("\n");
			
		int j = 0;
		int k = 0;
		if(firstDayOfMonth == 0)
		{
			k = 0;
		}
		else
		{
			k = 1;
		}
				
		for(int i = k; i < 42; i++)
		{
			if(j != firstDayOfMonth)
			{
				System.out.print("   ");
				j++;
			}
			if(i >= (max+j))
			{
				System.out.print(" ");
			}
			else if(j == firstDayOfMonth)
			{
				if((i-j+1) == currentDD && months[cal.get(cal.MONTH)].equals(currentMM))
				{
					if(i % 7 == 0)
					{
						System.out.print("[" + (i-j+1) + "]");
						System.out.print("\n");
					}
					else if((i+1) % 7 == 0)
					{
						System.out.print("[" + (i-j+1) + "]");	
						System.out.print("\n");
					}
					else
					{
						System.out.print("[" + (i-j+1) + "]");
					}
				}
				else if((i+1) % 7 == 0)
				{
					System.out.print((i-j+1) + " \n");
				}
				else
				{
					if((i-j+1) <= 9)
					{
						System.out.print(" " +(i-j+1) + " ");
					}
					else if((i-j+1) <= 20)
					{
						System.out.print(i-j+1 + " ");
					}
					else
					{
						System.out.print(i-j+1 + " ");
					}		
				}
			}
		}
		System.out.println("\n");
			
		if(mode == 1)
		{
			monthViewer();
		}
	}
}
  