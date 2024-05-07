import java.util.Scanner;

public class OnlineShopUI {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product ID: ");
            int productId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            double totalPrice = ShopManager.purchaseProduct(productId, quantity);
            System.out.println("Total price: $" + totalPrice);

        } catch (ProductNotFoundException e) {
            System.out.println("Товар с этим Идентификатором не найден");
        } catch (InsufficientQuantityException e) {
            System.out.println("Недостаточное количество товара");
        }
    }
}

public class ShopManager {
    public static double purchaseProduct(int productId, int quantity) throws ProductNotFoundException, InsufficientQuantityException {
        Product product = ProductDatabase.getProduct(productId);

        if (product.getAvailableQuantity() < quantity) {
            throw new InsufficientQuantityException();
        }

        double totalPrice = product.getPrice() * quantity;
        return totalPrice;
    }
}

public class ProductDatabase {
    public static Product getProduct(int productId) throws ProductNotFoundException {
        if (productId == 1) {
            return new Product("Product 1", 10, 5.99);
        }

        throw new ProductNotFoundException(); // Если товара нет, бросаем исключение
    }
}

public class Product {
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

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Товар не найден");
    }
}

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException() {
        super("Недостаточное количество товара");
    }
}