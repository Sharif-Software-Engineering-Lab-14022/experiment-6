import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the weight of the package: ");
        double weight = scanner.nextDouble();

        PackageContext packageContext = new PackageContext(weight);

        boolean packageDelivered = false;

        while (!packageDelivered) {

            System.out.print("Enter the shipping method (1 for Standard, 2 for Express): ");
            int shippingMethod = scanner.nextInt();

            if (shippingMethod == 1) {
                packageContext.setShippingStrategy(new StandardShippingStrategy());
            } else if (shippingMethod == 2) {
                packageContext.setShippingStrategy(new ExpressShippingStrategy());
            } else {
                System.out.println("Invalid shipping method. Please try again.");
                continue;
            }

            System.out.println("Shipping method updated.");

            double price = packageContext.calculatePrice();
            System.out.println("Price for the package with the selected shipping method: " + price);

            System.out.print("Do you want to change the shipping method? (yes/no): ");
            String changeMethod = scanner.next();
            if (changeMethod.equalsIgnoreCase("yes")) {
                continue; // Continue to the next iteration to re-enter shipping method
            }

            System.out.print("Do you want to change the package state to delivered? (yes/no): ");
            String changeState = scanner.next();
            if (changeState.equalsIgnoreCase("yes")) {
                packageContext.updatePackageState();
                System.out.println("Package state updated to delivered.");
                packageDelivered = true;
            } else {
                System.out.println("Package state remains in transit.");
            }
        }

        System.out.println("Package has been delivered. Program complete.");

        scanner.close();
    }
}
