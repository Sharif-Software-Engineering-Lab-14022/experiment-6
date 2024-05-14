public class PackageContext {
    private PackageState currentState;
    private ShippingStrategy shippingStrategy;
    private final double weight;

    public PackageContext(double weight) {
        this.weight = weight;
        this.currentState = new InTransitState();
        this.shippingStrategy = new StandardShippingStrategy(); // Default to Standard
    }

    public void setCurrentState(PackageState state) {
        this.currentState = state;
    }

    public void setShippingStrategy(ShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }

    public void updatePackageState() {
        currentState.updateState(this);
    }

    public double calculatePrice() {
        return shippingStrategy.calculatePrice(weight);
    }

    public PackageState getCurrentState() {
        return currentState;
    }
}