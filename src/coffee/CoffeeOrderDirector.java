package coffee;
public class CoffeeOrderDirector {
    public CoffeeOrder makeClassicEspresso(CoffeeOrderBuilder builder) {
        return builder
                .setSize(Size.SMALL)
                .setCoffeeType(CoffeeType.ESPRESSO)
                .build();
    }
}
