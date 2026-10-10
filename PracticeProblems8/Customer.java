
import java.util.*;

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    Product product;
    int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Credit Card payment successful");
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("PayPal payment failed");
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Bank Transfer payment successful");
        return true;
    }
}

class Order {
    String id;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        System.out.println("Order created for " + customer.name);
    }

    void addProduct(Product p, int quantity) {
        if (quantity > 0) {
            items.add(new OrderItem(p, quantity));
        }
    }

    double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    String getStatus() {
        return status;
    }

    void pay(PaymentMethod method, String methodName) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (status.equals("Paid")) {
            System.out.println("Order is already paid");
            return;
        }

        System.out.println("Payment initiated via " +
                methodName + " for Order " + id + ".");

        if (method.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment for Order " + id + " successful.");
        } else {
            System.out.println("Payment for Order " + id + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Order orderX = new Order("X", x);

        orderX.addProduct(new Product("Product A", 100), 2);
        orderX.addProduct(new Product("Product B", 200), 1);
        orderX.pay(new CreditCardPayment(), "Credit Card");

        Customer y = new Customer("Customer Y");
        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment(), "Credit Card");

        Customer z = new Customer("Customer Z");
        Order orderZ = new Order("Z", z);

        orderZ.addProduct(new Product("Product C", 300), 1);
        orderZ.pay(new PayPalPayment(), "PayPal");
    }
}
