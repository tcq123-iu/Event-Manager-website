import java.util.ArrayList;
import java.util.Comparator;

public class EventSorter {
    public static void mergeSort(ArrayList<Event> events, String attribute, boolean reverse) {
        if (events.size() < 2) {
            return;
        }

        int mid = events.size() / 2;
        ArrayList<Event> left = new ArrayList<>(events.subList(0, mid));
        ArrayList<Event> right = new ArrayList<>(events.subList(mid, events.size()));

        mergeSort(left, attribute, reverse);
        mergeSort(right, attribute, reverse);

        merge(events, left, right, attribute, reverse);
    }

    private static void merge(ArrayList<Event> events, ArrayList<Event> left, ArrayList<Event> right, String attribute, boolean reverse) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            int comparison = compareByAttribute(left.get(i), right.get(j), attribute);
            if (reverse) {
                comparison = -comparison;
            }

            if (comparison <= 0) {
                events.set(k++, left.get(i++));
            } else {
                events.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            events.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            events.set(k++, right.get(j++));
        }
    }

    private static int compareByAttribute(Event a, Event b, String attribute) {
        switch (attribute) {
            case "date":
                return a.date.compareTo(b.date);
            case "title":
                return a.title.compareTo(b.title);
            case "priority":
                return Integer.compare(a.priority, b.priority);
            default:
                return a.date.compareTo(b.date); // Default to date comparison
        }
    }

    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        // Add events to the list

        mergeSort(events, "date", false);
        for (Event event : events) {
            System.out.println(event.title + " " + event.date + " " + event.priority);
        }
    }
}
