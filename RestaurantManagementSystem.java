import java.util.Scanner;

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Food prices
        int burger = 80, pizza = 200, pasta = 150, noodles = 110;
        // Quantity ordered for each food item
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        boolean[] table = new boolean[6];   // false = available, true = booked
        int myTable = 0, total = 0;
        int choice = 0, t, item, q, subtotal, discount, pay;
        String key, mode;

        System.out.println("=== WELCOME TO OUR RESTAURANT ===");
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        while (choice != 8) {
            System.out.println("\n------ MAIN MENU ------");
            System.out.println("1. View Menu");
            System.out.println("2. Book Table");
            System.out.println("3. Order Food");
            System.out.println("4. Search Food");
            System.out.println("5. Kitchen Order");
            System.out.println("6. Generate Bill");
            System.out.println("7. Payment");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:   // View Menu
                    System.out.println("\n------ FOOD MENU ------");
                    System.out.println("1. Burger  - Rs." + burger);
                    System.out.println("2. Pizza   - Rs." + pizza);
                    System.out.println("3. Pasta   - Rs." + pasta);
                    System.out.println("4. Noodles - Rs." + noodles);
                    break;

                case 2:   // Book Table
                    for (int i = 0; i < 6; i++) {
                        if (table[i])
                            System.out.println("Table " + (i + 1) + " - Booked");
                        else
                            System.out.println("Table " + (i + 1) + " - Available");
                    }
                    System.out.print("Enter table number: ");
                    t = sc.nextInt();
                    if (myTable != 0)
                        System.out.println("You have already booked table " + myTable);
                    else if (t >= 1 && t <= 6 && !table[t - 1]) {
                        table[t - 1] = true;
                        myTable = t;
                        System.out.println("Table " + t + " booked successfully.");
                    } else
                        System.out.println("Invalid or already booked table.");
                    break;

                case 3:   // Order Food
                    if (myTable == 0) {
                        System.out.println("Please book a table first.");
                        break;
                    }
                    System.out.print("Enter item number (1-4): ");
                    item = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    q = sc.nextInt();
                    if (item >= 1 && item <= 4 && q > 0) {
                        if (item == 1) q1 += q;
                        else if (item == 2) q2 += q;
                        else if (item == 3) q3 += q;
                        else q4 += q;
                        total = 0;   // bill has to be generated again
                        System.out.println("Item added to your order.");
                    } else
                        System.out.println("Invalid item or quantity.");
                    break;

                case 4:   // Search Food
                    System.out.print("Enter food name: ");
                    key = sc.next().toLowerCase();
                    if (key.equals("burger")) System.out.println("Burger - Rs." + burger);
                    else if (key.equals("pizza")) System.out.println("Pizza - Rs." + pizza);
                    else if (key.equals("pasta")) System.out.println("Pasta - Rs." + pasta);
                    else if (key.equals("noodles")) System.out.println("Noodles - Rs." + noodles);
                    else System.out.println("Food item not found.");
                    break;

                case 5:   // Kitchen Order
                    System.out.println("\n------ KITCHEN ORDER ------");
                    if (q1 + q2 + q3 + q4 == 0) {
                        System.out.println("No food ordered.");
                        break;
                    }
                    System.out.println("Table No : " + myTable);
                    if (q1 > 0) System.out.println("Burger x " + q1);
                    if (q2 > 0) System.out.println("Pizza x " + q2);
                    if (q3 > 0) System.out.println("Pasta x " + q3);
                    if (q4 > 0) System.out.println("Noodles x " + q4);
                    break;

                case 6:   // Generate Bill
                    subtotal = q1 * burger + q2 * pizza + q3 * pasta + q4 * noodles;
                    if (subtotal == 0) {
                        System.out.println("No items ordered.");
                        break;
                    }
                    discount = 0;
                    if (subtotal >= 1000) discount = subtotal * 10 / 100;
                    else if (subtotal >= 500) discount = subtotal * 5 / 100;
                    total = subtotal - discount;

                    System.out.println("\n========== BILL ==========");
                    System.out.println("Customer : " + name);
                    System.out.println("Table No : " + myTable);
                    if (q1 > 0) System.out.println("Burger x " + q1 + " = Rs." + (q1 * burger));
                    if (q2 > 0) System.out.println("Pizza x " + q2 + " = Rs." + (q2 * pizza));
                    if (q3 > 0) System.out.println("Pasta x " + q3 + " = Rs." + (q3 * pasta));
                    if (q4 > 0) System.out.println("Noodles x " + q4 + " = Rs." + (q4 * noodles));
                    System.out.println("--------------------------");
                    System.out.println("Subtotal : Rs." + subtotal);
                    System.out.println("Discount : Rs." + discount);
                    System.out.println("Total    : Rs." + total);
                    break;

                case 7:   // Payment
                    if (total == 0) {
                        System.out.println("Please generate the bill first.");
                        break;
                    }
                    System.out.println("1. Cash  2. Card  3. UPI");
                    System.out.print("Select payment: ");
                    pay = sc.nextInt();
                    if (pay >= 1 && pay <= 3) {
                        if (pay == 1) mode = "Cash";
                        else if (pay == 2) mode = "Card";
                        else mode = "UPI";
                        System.out.println("Rs." + total + " paid by " + mode + ".");
                        System.out.println("Thank you for visiting!");
                        // Clear everything for the next customer
                        table[myTable - 1] = false;
                        myTable = 0;
                        total = 0;
                        q1 = 0; q2 = 0; q3 = 0; q4 = 0;
                    } else
                        System.out.println("Invalid payment choice.");
                    break;

                case 8:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
