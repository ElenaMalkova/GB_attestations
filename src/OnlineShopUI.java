import java.util.Scanner;

public class OnlineShopUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        try {
            double totalPrice = ShopManager.purchaseProduct(productId, quantity);
            System.out.println("Total price: $" + totalPrice);
        } catch (ProductNotFoundException | OutOfStockException | InvalidQuantityException e) {
            System.err.println(e.getMessage());
        }
    }
}

class ShopManager {
    public static double purchaseProduct(int productId, int quantity) throws ProductNotFoundException,
            OutOfStockException,
            InvalidQuantityException {
        Product product = ProductDatabase.getProduct(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        if (product.getAvailableQuantity() < quantity) {
            throw new OutOfStockException("Insufficient stock");
        }
        if (quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        double totalPrice = product.getPrice() * quantity;
        return totalPrice;
    }
}

class ProductDatabase {
    public static Product getProduct(int productId) {
        if (productId == 1) {
            return new Product("Product 1", 10, 5.99);
        }
        return null;
    }
}

class Product {
    private String name;
    private int availableQuantity;
    private double price;

    public Product(String name, int availableQuantity, double price) {
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public double getPrice() {
        return price;
    }
}

class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}