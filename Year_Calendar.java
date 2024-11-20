import java.util.HashMap;
import java.util.ArrayList;

class Year_Calendar {
    private HashMap<Integer, HashMap<String, HashMap<Integer, ArrayList<Event>>>> yearCalendars;

    public Year_Calendar() {
        yearCalendars = new HashMap<>();
    }

    public void addYear(int year) {
        if (!yearCalendars.containsKey(year)) {
            yearCalendars.put(year, Calendar.createCalendar(year));
        }
    }

    public HashMap<String, HashMap<Integer, ArrayList<Event>>> getCalendar(int year) {
        if (!yearCalendars.containsKey(year)) {
            addYear(year);
        }
        return yearCalendars.get(year);
    }

    public void printCalendar(int year) {
        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = getCalendar(year);
        System.out.println("Calendar for year " + year + ":");
        for (String month : calendar.keySet()) {
            System.out.println(month + ": " + calendar.get(month).keySet());
        }
        System.out.println();
    }
}
