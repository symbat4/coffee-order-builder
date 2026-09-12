package coffee;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderBuilder {
    private Size size;
    private CoffeeType coffeeType;

    public CoffeeOrderBuilder setSize(Size size) {
        this.size = size;
        return this;
    }

    public CoffeeOrderBuilder setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
        return this;
    }

    public CoffeeOrder build() {
        if (size == null) {
            throw new IllegalStateException("Cannot build CoffeeOrder: size must be set");
        }
        if (coffeeType == null) {
            throw new IllegalStateException("Cannot build CoffeeOrder: coffeeType must be set");
        }
        return new CoffeeOrder(size, coffeeType);
    }
}
