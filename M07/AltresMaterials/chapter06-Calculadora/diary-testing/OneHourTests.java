/**
 * Perform tests of the Day class that involve
 * making single-hour appointments.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class OneHourTests
{
    // The Day object being tested.
    private Day day;

    /**
     * Constructor for objects of class OneHourTests
     */
    public OneHourTests()
    {
        // Create a Day object that can be used in testing.
        // Individual methods might choose to create
        // their own instances.
        day = new Day(1);
    }

    /**
     * Test basic functionality by booking at either end
     * of a day, and in the middle.
     */
    public void makeThreeAppointments()
    {
        // Start with a fresh Day object.
        day = new Day(1);
        // Create three one-hour appointments.
        Appointment first = new Appointment("Java lecture", 1);
        Appointment second = new Appointment("Java class", 1);
        Appointment third = new Appointment("Meet John", 1);
        
        // Make each appointment at a different time.
        day.makeAppointment(9, first);
        day.makeAppointment(13, second);
        day.makeAppointment(17, third);
        
        day.showAppointments();
    }
    
    /**
     * Check that double-booking is not permitted.
     */
    public void testDoubleBooking()
    {
        // Set up the day with three legitimate appointments.
        makeThreeAppointments();
        Appointment badAppointment = new Appointment("Error", 1);
        day.makeAppointment(9, badAppointment);
        
        // Show that the badAppointment has not been made.
        day.showAppointments();
    }

    /**
     * Test basic functionality by filling a complete
     * day with appointments.
     */
    public void fillTheDay()
    {
        // Start with a fresh Day object.
        day = new Day(1);
        for(int time = Day.START_OF_DAY;
                    time <= Day.FINAL_APPOINTMENT_TIME;
                        time++) {
            day.makeAppointment(time,
                                new Appointment("Test " + time, 1));
        }
        
        day.showAppointments();
    }
}
