package coffee;
public class Main {
    public static void main(String[] args) {
        CoffeeOrder customOrder = new CoffeeOrderBuilder()
                .setSize(Size.LARGE)
                .setCoffeeType(CoffeeType.ESPRESSO)
                .build();

        System.out.println("=== Custom order (kitchen ticket) ===");
        System.out.println(customOrder.toKitchenTicket());
        System.out.println();

        System.out.println("=== Custom order (receipt) ===");
        System.out.println(customOrder.toReceipt());
        System.out.println();

        CoffeeOrderDirector director = new CoffeeOrderDirector();

        CoffeeOrder espresso = director.makeClassicEspresso(new CoffeeOrderBuilder());

        System.out.println("=== Classic Espresso ===");
        System.out.println(espresso.toKitchenTicket());
        System.out.println();

        System.out.println("=== Attempting an invalid order (missing coffeeType) ===");
        try {
            new CoffeeOrderBuilder()
                    .setSize(Size.MEDIUM)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Rejected as expected: " + e.getMessage());
        }
    }
}
