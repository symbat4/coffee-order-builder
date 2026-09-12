# Coffee Order Builder

A Java implementation of the **Builder** design pattern, modeled around a coffee shop order. Instead of one giant constructor with many parameters, a `CoffeeOrder` is assembled step by step through a fluent
builder API, and a `Director` provides ready-made "recipes" for the shop's most common drinks.

## Structure

| Class                  | Role                                                            |
|-------------------------|------------------------------------------------------------------|
| `CoffeeOrder`            | **Product** — the finished, immutable order.                    |
| `CoffeeOrderBuilder`     | **Builder** — fluent, chainable methods to configure an order.  |
| `CoffeeOrderDirector`    | **Director** — known recipes (Espresso, Caramel Latte, etc).   |
| `Main`                   | **Client** — demonstrates both custom and director-built orders.|
| `Size`, `CoffeeType`| Enums used instead of raw strings/ints.                |

## How to run

```bash
javac -d out src/coffee/*.java
java -cp out coffee.Main
```

## Clean Code principles applied

### 1. Meaningful, intention-revealing names

Every class, method, and field states exactly what it does or holds —
`Size.LARGE`, `toKitchenTicket()` — no abbreviations like `sz`, `ct`, or `mk()`.

### 2. Small methods, each doing one thing

Every builder setter does exactly one job: set one field and return
`this`. Validation logic lives only in `build()`, not scattered across
setters.

```java
// Before (bad): one setter doing two jobs
public CoffeeOrderBuilder setSizeAndValidate(Size size) {
    if (size == null) throw new IllegalArgumentException("bad size");
    this.size = size;
    checkAllFieldsSoFar(); // unrelated responsibility mixed in
    return this;
}

// After (this project): one job per method
public CoffeeOrderBuilder setSize(Size size) {
    this.size = size;
    return this;
}
```

### 3. No magic numbers/strings

Coffee size, type, and milk are `enum`s (`Size`, `CoffeeType`)
instead of raw strings like `"large"` or `"small"`, which would allow typos
(`"lrage"`) that the compiler could never catch.

```java
// Before (bad): magic strings, no compile-time safety
builder.setSize("large"); // typo compiles fine, fails at runtime

// After (this project): compiler catches invalid values
builder.setSize(Size.LARGE);
```

### 4. Validated construction — `build()` fails fast on invalid state

An order without a `size` or `coffeeType` isn't a real order, so
`build()` throws a clear, descriptive exception instead of silently
returning a broken object.

```java
public CoffeeOrder build() {
    if (size == null) {
        throw new IllegalStateException("Cannot build CoffeeOrder: size must be set");
    }
    if (coffeeType == null) {
        throw new IllegalStateException("Cannot build CoffeeOrder: coffeeType must be set");
    }
    return new CoffeeOrder(size, coffeeType);
}
```

### 5. Small, focused classes (Single Responsibility)

`CoffeeOrderBuilder` only knows how to *assemble* an order.
`CoffeeOrderDirector` only knows *which combinations* are popular recipes.
`CoffeeOrder` only knows how to *represent itself* (ticket / receipt).
None of these responsibilities are mixed into one class.

## Multiple representations (Product)

`CoffeeOrder` exposes two different views of the same built object:
- `toKitchenTicket()` — short line for the barista.
- `toReceipt()` — detailed, customer-facing summary.
