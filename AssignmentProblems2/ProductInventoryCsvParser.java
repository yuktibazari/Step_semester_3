import java.util.Scanner;

public class ProductInventoryCsvParser {

    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println(
                "Product: " + fields[0] +
                " | SKU: " + fields[1] +
                " | Qty: " + fields[2]
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter inventory record: ");
        String csvLine = scanner.nextLine();

        parseInventoryRecord(csvLine);

        scanner.close();
    }
}