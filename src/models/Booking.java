package models;

/**
 * Booking class
 *
 * Model for single bookings
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Booking {

    private String bookingTime;
    private String customerName;
    private Integer tableNumber;
    private Double bookingDuration;
    private Integer numberOfGuests;
    private Boolean isCurrent = false;

    public Booking(
            String customerName,
            Integer numberOfGuests,
            String bookingTime,
            Double bookingDuration
    ) {
        this.setCustomerName(customerName);
        this.setNumberOfGuests(numberOfGuests);
        this.setBookingTime(bookingTime);
        this.setBookingDuration(bookingDuration);
    }

    public Booking(
            String customerName,
            Integer numberOfGuests,
            Integer tableNumber,
            String bookingTime,
            Double bookingDuration
    ) {
        this.setCustomerName(customerName);
        this.setNumberOfGuests(numberOfGuests);
        this.setTableNumber(tableNumber);
        this.setBookingTime(bookingTime);
        this.setBookingDuration(bookingDuration);
    }

    /**
     * Get the name of the customer that a booking is filed under
     *
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set the name of the customer making a booking
     *
     * @param customerName the name of the customer
     */
    private void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Get the time of the booking
     *
     * @return the booking time
     */
    public String getBookingTime() {
        return bookingTime;
    }

    /**
     * Set the time of the booking
     *
     * @param bookingTime the time of the reservation
     */
    private void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    /**
     * Get the table number for the booking
     *
     * @return the table number
     */
    public Integer getTableNumber() {
        return tableNumber;
    }

    /**
     * Set the table number for the booking
     *
     * @param tableNumber the table number of the booking
     */
    protected void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * Get the number of guests to be seated for the booking,
     * including the customer making the booking
     *
     * @return number of guests to be seated
     */
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Set the number of guests for the booking,
     * including the customer making the booking
     *
     * @param numberOfGuests number of guests being seated for booking
     */
    private void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Get the duration of the booking
     *
     * @return duration of the booking
     */
    public Double getBookingDuration() {
        return bookingDuration;
    }

    /**
     * Set the duration of the booking
     *
     * @param bookingDuration duration of booking
     */
    private void setBookingDuration(Double bookingDuration) {
        this.bookingDuration = bookingDuration;
    }

    /**
     * Check if a booking is checked in or not
     *
     * @return if the booking is current or not
     */
    public boolean isCurrent() { return isCurrent; }

    /**
     * Toggle the status of the current booking, i.e. active/current or not
     *
     * @param current boolean value of if the booking is current or not
     */
    public void setCurrent(boolean current) { isCurrent = current; }

}
