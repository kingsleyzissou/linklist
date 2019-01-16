package utilities;

public class Utilities {

    /**
     * Method to convert booking times from a string to the
     * corresponding slot in the booking slots array
     *
     * @param time the time being converted to a booking slot
     * @return the index of the booking slot corresponding to the time
     */
    public static int lookupBookingTime(String time) {
        switch (time) {
            case "17:00": return 0;
            case "17:30": return 1;
            case "18:00": return 2;
            case "18:30": return 3;
            case "19:00": return 4;
            case "19:30": return 5;
            case "20:00": return 6;
            case "20:30": return 7;
            case "21:00": return 8;
            case "21:30": return 9;
            default: return -1;
        }
    }

    /**
     * This method takes a double and rounds it to the nearest 0.5
     *
     * Reference:
     * https://stackoverflow.com/questions/23449662/java-round-to-nearest-5
     *
     * @param hours the duration to be rounded
     * @return double rounded to the nearest 0.5
     */
    public static int getNumberOfSlotsByBookingDuration(double hours) {
        return (int) (( Math.round(hours * 2) / 2.0) * 60 / 30);
    }

}
