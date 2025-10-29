import java.time.*;
import java.util.*;

public class CalendarPrinter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Step 1: Get year and month
        System.out.print("Enter year: ");
        int year = input.nextInt();

        System.out.print("Enter month (1-12): ");
        int month = input.nextInt();

        input.close();

        // Step 2: Create LocalDate for the 1st day of that month
        LocalDate firstDay = LocalDate.of(year, month, 1);

        // Step 3: Get total days in the month
        int daysInMonth = firstDay.lengthOfMonth();

        // Step 4: Find which day of week the 1st day is
        DayOfWeek startDay = firstDay.getDayOfWeek();
        int start = startDay.getValue();  // 1=Monday ... 7=Sunday

        // Step 5: Print calendar header
        System.out.println("\nCalendar for the month of " + firstDay.getMonth() + ", " + year + "\n");
        System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

        // Step 6: Adjust start position (convert Monday=1..Sunday=7 to Sunday=0..Saturday=6)
        int dayIndex = (start % 7);  // makes Sunday=0

        // Step 7: Print leading spaces
        for (int i = 0; i < dayIndex; i++) {
            System.out.print("    ");
        }

        // Step 8: Print days
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%2d  ", day);
            dayIndex++;
            if (dayIndex % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}