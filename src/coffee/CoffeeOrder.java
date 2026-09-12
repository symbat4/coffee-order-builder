package coffee;
import java.util.Collections;
import java.util.List;
public class CoffeeOrder {
    private final Size size;
    private final CoffeeType coffeeType;

    CoffeeOrder(Size size,
                CoffeeType coffeeType) {
        this.size = size;
        this.coffeeType = coffeeType;
    }

    public String toKitchenTicket() {
        StringBuilder ticket = new StringBuilder();
        ticket.append(size).append(" ").append(coffeeType);
        return ticket.toString();
    }

    public String toReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("--- Receipt ---\n");
        receipt.append("Size:        ").append(size).append("\n");
        receipt.append("Coffee:      ").append(coffeeType).append("\n");
        return receipt.toString();
    }

    @Override
    public String toString() {
        return toReceipt();
    }
}
