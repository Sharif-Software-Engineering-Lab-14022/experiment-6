public class ExpressShippingStrategy implements ShippingStrategy {
    public double calculatePrice(double weight) {
        return weight * 3.5;
    }
}