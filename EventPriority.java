import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EventPriority {
    // Method to calculate the priority of an event based on its date and description
    public static int calculatePriority(LocalDate date, String description) {
        int priority = 0;

        // Increase priority if the event is today
        if (date.isEqual(LocalDate.now())) {
            priority += 2;
        }

        // Keyword weights for different priorities
        Map<String, Integer> keywordWeights = new HashMap<>();
        keywordWeights.put("urgent", 3);
        keywordWeights.put("important", 3);
        keywordWeights.put("deadline", 2);
        keywordWeights.put("meeting", 1);
        keywordWeights.put("review", 1);

        // Analyze the description for keywords
        String lowerCaseDescription = description.toLowerCase();
        for (Map.Entry<String, Integer> entry : keywordWeights.entrySet()) {
            if (lowerCaseDescription.contains(entry.getKey())) {
                priority += entry.getValue();
            }
        }

        // Contextual weight adjustment
        int keywordCount = keywordWeights.keySet().stream()
            .mapToInt(keyword -> countOccurrences(lowerCaseDescription, keyword))
            .sum();
        priority += keywordCount;

        return Math.min(priority, 5); // Ensure priority is within 0-5 range
    }

    // Method to count occurrences of a keyword in the description
    private static int countOccurrences(String description, String keyword) {
        int count = 0;
        int index = 0;
        while ((index = description.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }
        return count;
    }
}