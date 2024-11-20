import java.time.LocalDate;
import java.util.ArrayList;

public class SearchingEvent {
    /**
     * Search an event by title
     * @param events
     * @param title
     * @return
     */
    public static Event searchEventByTitle(ArrayList<Event> events, String title) {
        // Sort the events by title before searching
        EventSorter.mergeSort(events, "title", false);
        int index = binarySearchByTitle(events, title);
        if (index >= 0) {
            return events.get(index);
        }
        return null;
    }

    /**
     * Search for an event by date
     * @param events
     * @param date
     * @return
     */
    public static Event searchEventByDate(ArrayList<Event> events, LocalDate date) {
        // Sort the events by date before searching
        EventSorter.mergeSort(events, "date", false);
        int index = binarySearchByDate(events, date);
        if (index >= 0) {
            return events.get(index);
        }
        return null;
    }

    public static Event searchEventByLocation(ArrayList<Event> events, String location) {
        // Sort the events by location before searching
        // Note: You may need to adjust the sorting method to include "location" if not already supported
        EventSorter.mergeSort(events, "location", false);
        int index = binarySearchByLocation(events, location);
        if (index >= 0) {
            return events.get(index);
        }
        return null;
    }

    public static int binarySearchByDate(ArrayList<Event> events, LocalDate date) {
        int low = 0;
        int high = events.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            LocalDate midDate = events.get(mid).getDate();
            if (midDate.equals(date)) {
                return mid;
            } else if (midDate.isBefore(date)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchByTitle(ArrayList<Event> events, String title) {
        int low = 0;
        int high = events.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midTitle = events.get(mid).getTitle().toLowerCase();
            int comparison = midTitle.compareTo(title.toLowerCase());
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchByLocation(ArrayList<Event> events, String location) {
        int low = 0;
        int high = events.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midLocation = events.get(mid).getLocation().toLowerCase();
            int comparison = midLocation.compareTo(location.toLowerCase());
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
