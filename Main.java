import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Event Planner!");
        // Year_Calendar yearCalendar = new Year_Calendar();
        String fileName = "input_1000.txt";

        // Load events from file
        Year_Calendar yearCalendar = loadEventsFromFile(fileName);
        // interactive menu
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an event");
            System.out.println("2. View events");
            System.out.println("3. Sort events");
            System.out.println("4. Search for an event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the year: ");
                    int year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the month: ");
                    String month = input.nextLine();
                    System.out.print("Enter the day: ");
                    int day = input.nextInt();
                    input.nextLine(); // Consume newline

                    System.out.print("Enter the title: ");
                    String title = input.nextLine();
                    System.out.print("Enter the time: ");
                    String time = input.nextLine();
                    System.out.print("Enter the location: ");
                    String location = input.nextLine();
                    System.out.print("Enter the description: ");
                    String description = input.nextLine();

                    String monthNumber = getMonthNumber(month);
                    if (monthNumber == null) {
                        System.out.println("Invalid month. Please try again.");
                        continue;
                    }

                    // Check if the day map is properly initialized
                    if (!yearCalendar.getCalendar(year).containsKey(monthNumber)) {
                        yearCalendar.getCalendar(year).put(monthNumber, new HashMap<>());
                    }
                    if (!yearCalendar.getCalendar(year).get(monthNumber).containsKey(day)) {
                        yearCalendar.getCalendar(year).get(monthNumber).put(day, new ArrayList<>());
                    }

                    // Put the day, month, year in the format yyyy-MM-dd
                    String dateString = year + "-" + monthNumber + "-" + String.format("%02d", day);

                    Event event = new Event(title, dateString, time, location, description);
                    yearCalendar.getCalendar(year).get(monthNumber).get(day).add(event);
                    break;
                case 2:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the month: ");
                    month = input.nextLine();
                    System.out.print("Enter the day: ");
                    day = input.nextInt();
                    input.nextLine(); // Consume newline

                    String viewMonthNumber = getMonthNumber(month);
                    if (viewMonthNumber == null) {
                        System.out.println("Invalid month. Please try again.");
                        continue;
                    }

                    HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = yearCalendar.getCalendar(year);
                    if (calendar.containsKey(viewMonthNumber) && calendar.get(viewMonthNumber).containsKey(day)) {
                        for (Event e : calendar.get(viewMonthNumber).get(day)) {
                            System.out.println(e.getTitle() + " " + e.getDate() + " " + e.getLocation() + " " + e.getDescription());
                            System.out.println(e.generateSummary());
                        }
                    } else {
                        System.out.println("No events found for the specified date.");
                    }

                    System.out.println("Events displayed successfully!");
                    break;
                case 3:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the attribute to sort by (date, title, location): ");
                    String attribute = input.nextLine();
                    System.out.print("Sort in reverse order (true/false): ");
                    boolean reverse = input.nextBoolean();
                    input.nextLine(); // Consume newline

                    calendar = yearCalendar.getCalendar(year);

                    Calendar.sortEvents(calendar, attribute, reverse);
                    System.out.println("Events sorted successfully!");
                    break;
                case 4:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.println("Search by:");
                    System.out.println("1. Title");
                    System.out.println("2. Date");
                    System.out.println("3. Location");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    input.nextLine(); // Consume newline

                    Event foundEvent = null;
                    switch (searchChoice) {
                        case 1:
                            System.out.print("Enter the title: ");
                            String searchTitle = input.nextLine();
                            foundEvent = Calendar.searchEvent(yearCalendar.getCalendar(year), searchTitle);
                            break;
                        case 2:
                            System.out.print("Enter the date (yyyy-MM-dd): ");
                            String searchDate = input.nextLine();
                            foundEvent = Calendar.searchEvent(yearCalendar.getCalendar(year), LocalDate.parse(searchDate));
                            break;
                        case 3:
                            System.out.print("Enter the location: ");
                            String searchLocation = input.nextLine();
                            foundEvent = Calendar.searchEventLocation(yearCalendar.getCalendar(year), searchLocation);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }

                    if (foundEvent != null) {
                        System.out.println("Event found: " + foundEvent.title + " on " + foundEvent.date);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        input.close();
    }

    private static String getMonthNumber(String month) {
        switch (month) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            case "01":
                return "01";
            case "02":
                return "02";
            case "03":
                return "03";
            case "04":
                return "04";
            case "05":
                return "05";
            case "06":
                return "06";
            case "07":
                return "07";
            case "08":
                return "08";
            case "09":
                return "09";
            case "10":
                return "10";
            case "11":
                return "11";
            case "12":
                return "12";
            case "1":
                return "01";
            case "2":
                return "02";
            case "3":
                return "03";
            case "4":
                return "04";
            case "5":
                return "05";
            case "6":
                return "06";
            case "7":
                return "07";
            case "8":
                return "08";
            case "9":
                return "09";
            default:
                return null;
        }
    }
    private static Year_Calendar loadEventsFromFile(String fileName) {
        Year_Calendar yearCalendar = new Year_Calendar();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");


                if (parts.length != 5) {
                    System.out.println("Invalid event format in file.");
                    continue;
                }

                String title = parts[0];
                String date = parts[1];
                String time = parts[2];
                String location = parts[3];
                String description = parts[4];

                System.out.println("Title: " + title);
                System.out.println("Date: " + date);

                LocalDate eventDate = LocalDate.parse(date);
                int year = eventDate.getYear();
                String month = String.format("%02d", eventDate.getMonthValue());
                int day = eventDate.getDayOfMonth();

                Event event = new Event(title, date, time, location, description);

                yearCalendar.getCalendar(year).putIfAbsent(month, new HashMap<>());
                yearCalendar.getCalendar(year).get(month).putIfAbsent(day, new ArrayList<>());
                yearCalendar.getCalendar(year).get(month).get(day).add(event);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return null;
        }

        return yearCalendar;
    }
}
