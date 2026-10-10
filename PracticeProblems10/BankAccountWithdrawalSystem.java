
import java.util.*;

abstract class BankAccount {
    String id;
    int balance;

    BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract String withdraw(int amount);
}

class SavingsAccount extends BankAccount {
    SavingsAccount(String id, int balance) {
        super(id, balance);
    }

    String withdraw(int amount) {
        if (balance - amount < 1000)
            return id + " rejected: minimum balance 1000";
        balance -= amount;
        return id + " balance " + balance;
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(String id, int balance) {
        super(id, balance);
    }

    String withdraw(int amount) {
        if (balance - amount < -5000)
            return id + " rejected: overdraft limit 5000";
        balance -= amount;
        return id + " balance " + balance;
    }
}

public class BankAccountWithdrawalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        while (sc.hasNext()) {
            String op = sc.next();
            if (op.equals("Savings") || op.equals("Current")) {
                String id = sc.next();
                int balance = sc.nextInt();
                if (op.equals("Savings"))
                    accounts.put(id, new SavingsAccount(id, balance));
                else
                    accounts.put(id, new CurrentAccount(id, balance));
            } else if (op.equals("WITHDRAW")) {
                String id = sc.next();
                int amount = sc.nextInt();
                if (!accounts.containsKey(id))
                    System.out.println("Account not found");
                else
                    System.out.println(accounts.get(id).withdraw(amount));
            }
        }
        sc.close();
    }
}
