import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representing an event with details such as title, date, time, location, and description.
 * Automatically generating a unique ID for each new event.
 */
public class Event {
    private static int idCounter = 1; // Static counter for generating unique IDs

    int id;
    int priority = 0; 
    String title;
    LocalDate date;
    String time;
    String location;
    String description;

    private static final List<DateTimeFormatter> DATE_FORMATS = new ArrayList<>();

    static {
        // Adding common date formats
        DATE_FORMATS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // ISO format
        DATE_FORMATS.add(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // Slash separator
        DATE_FORMATS.add(DateTimeFormatter.ofPattern("MM/dd/yyyy")); // US format
        DATE_FORMATS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Day-Month-Year format
        DATE_FORMATS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Alternative Day-Month-Year format
        // Adding more formats as needed
    }

    /**
     * Creating an event with the provided details and automatically generating a unique ID.
     *
     * @param title The title of the event.
     * @param dateString The date of the event in a string format, which is being parsed using various date formats.
     * @param time The time of the event.
     * @param location The location where the event is taking place.
     * @param description A description of the event.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Event(String title, String dateString, String time, String location, String description) {
        this.id = generateId();
        this.title = title;
        this.date = parseDate(dateString);
        this.time = time;
        this.location = location;
        this.description = description;
        this.priority = EventPriority.calculatePriority(this.date, this.description);

        if (this.date == null) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }


    //getter methods 
    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public LocalDate getDate() { return this.date; }
    public String getLocation() { return this.location; }
    public String getDescription() { return this.description; }
    public int getPriority() { return this.priority; }

    //setter methods 
    public void setTitle(String title) { this.title = title; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(int priority) { this.priority = priority; }

    /**
     * Generating a unique ID for each event.
     *
     * @return The next unique ID.
     */
    private static int generateId() {
        return idCounter++; // Incrementing and returning the unique ID
    }

    /**
     * Parsing a date string using a list of supported date formats.
     *
     * @param dateString The date string to parse.
     * @return The parsed LocalDate, or null if no format matches.
     */
    private static LocalDate parseDate(String dateString) {
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continuing to next format
            }
        }
        // Returning null if no formats match
        return null;
    }

    //generate summary of the event
    public String generateSummary() {
        if (this.description == null || this.description.isEmpty()) {
            return "No description available.";
        }
        else {
            try{
                if (this.description.length() > 512) {
                    return HuggingFaceAPI.generateSummary(this.description);
                }
                else {
                    return this.description;
                }
            } catch (Exception e) {
                return "Error generating summary: " + e.getMessage();
            }
    }
}

    /**
     * Providing a string representation of the event.
     *
     * @return A string describing the event with its details.
     */
    @Override
    public String toString() {
        return "Event{" +
                "id=" + this.id +
                ", priority='" + this.priority + '\'' +
                ", title='" + this.title + '\'' +
                ", date=" + this.date +
                ", time='" + this.time + '\'' +
                ", location='" + this.location + '\'' +
                ", description='" + this.description + '\'' +
                '}';
    }
}
