class CirculationBook {
    private int copiesTotal;
    private int copiesAvailable;

    CirculationBook(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException();
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookCirculationGuard {
    public static void main(String[] args) {
        CirculationBook b = new CirculationBook(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}