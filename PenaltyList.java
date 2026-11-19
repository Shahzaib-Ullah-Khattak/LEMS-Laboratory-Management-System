
class PenaltyList {

    PenaltyNode head;
    PenaltyNode tail;

    PenaltyList() {
        head = null;
        tail = null;
    }

    void add(double amount, String reason) {

        PenaltyNode newNode = new PenaltyNode(amount, reason);

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    void display() {

        System.out.println("\n===== STUDENT PENALTIES =====");

        if (head == null) {
            System.out.println("No penalties found!");
            return;
        }

        displayRecursive(head);

        System.out.println("-------------------------");
        System.out.println("Total Penalty: " + calculateTotal(head));
    }

    void displayRecursive(PenaltyNode current) {

        if (current == null) {
            return;
        }

        System.out.println("Reason: " + current.reason);
        System.out.println("Amount: " + current.amount);
        System.out.println("-------------------------");

        displayRecursive(current.next);
    }

    double calculateTotal(PenaltyNode current) {

        if (current == null) {
            return 0;
        }

        return current.amount + calculateTotal(current.next);
    }
}
