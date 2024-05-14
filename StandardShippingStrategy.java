public class StandardShippingStrategy implements ShippingStrategy {
    public double calculatePrice(double weight) {
        return weight * 2.5;
    }
}