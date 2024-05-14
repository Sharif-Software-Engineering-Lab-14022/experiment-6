public class DeliveredState implements PackageState {
    public String getStatus() {
        return "Package has been delivered.";
    }

    public void updateState(PackageContext context) {
        // Already delivered, do nothing
    }
}