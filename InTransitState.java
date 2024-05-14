public class InTransitState implements PackageState {
    public String getStatus() {
        return "Package is in transit.";
    }

    public void updateState(PackageContext context) {
        // Logic to check if package has arrived and update state
        context.setCurrentState(new DeliveredState());
    }
}