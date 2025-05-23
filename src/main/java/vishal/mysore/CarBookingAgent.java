package vishal.mysore;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log
@Service
@Agent(groupName = "car booking", groupDescription = "actions related to car booking")
public class CarBookingAgent {

    @PreAuthorize("hasRole('USER')")
    @Action(description = "Book a car for the given details")
    public String bookCar(String carType, String pickupLocation, String dropLocation) {
        log.info("Booking car of type: " + carType+
                ", pickup location: " + pickupLocation +
                ", drop location: " + dropLocation);
        return "Car of type " + carType + " has been booked from " + pickupLocation + " to " + dropLocation;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Action(description = "Cancel a car booking")
    public String cancelCarBooking(String bookingId) {
        log.info("Cancelling car booking with ID: " + bookingId);
        return "Car booking with ID " + bookingId + " has been cancelled";
    }

    /**
     * This is unsecured method
     * @param bookingId
     * @return
     */
    @Action(description = "Get booking status of a car")
    public String getBookingStatus(String bookingId) {
        return "The status of booking ID " + bookingId + " is confirmed";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Action(description = "Block a car for maintenance")
    public String blockCarForMaintenance(String carId, String reason) {
        log.info("Blocking car " + carId + " for maintenance");
        return "Car " + carId + " blocked for maintenance: " + reason;
    }

    /**
     * This is unsecured method
     */
    @Action(description = "View available car types")
    public String listCarTypes() {
        return "Available car types: Economy, Compact, SUV, Luxury";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Action(description = "Update pickup location for booking")
    public String updatePickupLocation(String bookingId, String newLocation) {
        log.info("Updating pickup location for booking " + bookingId);
        return "Pickup location updated to " + newLocation + " for booking " + bookingId;
    }

    /**
     * This is unsecured method
     */
    @Action(description = "Get pricing for car type")
    public String getCarPricing(String carType) {
        return "Pricing for " + carType + " is $50 per day";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Action(description = "Generate booking report")
    public String generateBookingReport(String dateRange) {
        log.info("Generating booking report for " + dateRange);
        return "Booking report generated for period: " + dateRange;
    }

}
