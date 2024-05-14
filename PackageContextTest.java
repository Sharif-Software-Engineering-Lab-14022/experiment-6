import org.junit.Test;
import static org.junit.Assert.*;

public class PackageContextTest {

    @Test
    public void testInitialStateAndStandardShipping() {
        PackageContext packageContext = new PackageContext(2.0); // Package weight is 2.0
        assertEquals("Package is in transit.", packageContext.getCurrentState().getStatus());
        assertEquals(5.0, packageContext.calculatePrice(), 0.01); // Standard shipping price: 2.0 * 2.5 = 5.0
    }

    @Test
    public void testUpdateStateToDelivered() {
        PackageContext packageContext = new PackageContext(2.0); // Package weight is 2.0
        assertEquals("Package is in transit.", packageContext.getCurrentState().getStatus());

        // Update state to Delivered
        packageContext.updatePackageState();
        assertEquals("Package has been delivered.", packageContext.getCurrentState().getStatus());
    }

    @Test
    public void testChangeShippingStrategy() {
        PackageContext packageContext = new PackageContext(2.0); // Package weight is 2.0
        assertEquals(5.0, packageContext.calculatePrice(), 0.01); // Default: Standard shipping price

        // Change shipping strategy to Express
        packageContext.setShippingStrategy(new ExpressShippingStrategy());
        assertEquals(7.0, packageContext.calculatePrice(), 0.01); // Express shipping price: 2.0 * 3.5 = 7.0
    }

    @Test
    public void testFullDeliveryProcess() {
        PackageContext packageContext = new PackageContext(2.0); // Package weight is 2.0
        assertEquals("Package is in transit.", packageContext.getCurrentState().getStatus());
        assertEquals(5.0, packageContext.calculatePrice(), 0.01); // Default: Standard shipping price

        // Update state to Delivered
        packageContext.updatePackageState();
        assertEquals("Package has been delivered.", packageContext.getCurrentState().getStatus());

        // Change shipping strategy to Express
        packageContext.setShippingStrategy(new ExpressShippingStrategy());
        assertEquals(7.0, packageContext.calculatePrice(), 0.01); // Express shipping price: 2.0 * 3.5 = 7.0

        // Package already delivered, no state change should occur
        packageContext.updatePackageState();
        assertEquals("Package has been delivered.", packageContext.getCurrentState().getStatus());
    }

    @Test
    public void testMultipleStateTransitions() {
        PackageContext packageContext = new PackageContext(2.0); // Package weight is 2.0
        assertEquals("Package is in transit.", packageContext.getCurrentState().getStatus());

        // Update state to Delivered
        packageContext.updatePackageState();
        assertEquals("Package has been delivered.", packageContext.getCurrentState().getStatus());

        // Change shipping strategy to Express
        packageContext.setShippingStrategy(new ExpressShippingStrategy());
        assertEquals(7.0, packageContext.calculatePrice(), 0.01); // Express shipping price: 2.0 * 3.5 = 7.0

        // Change state back to in transit (simulate new delivery attempt)
        packageContext.setCurrentState(new InTransitState());
        assertEquals("Package is in transit.", packageContext.getCurrentState().getStatus());
    }
}
