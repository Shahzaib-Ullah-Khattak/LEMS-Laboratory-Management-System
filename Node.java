
class EquipmentNode {

    Equipment data;
    EquipmentNode left;
    EquipmentNode right;
    int height;

    EquipmentNode(Equipment data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

class StudentNode {

    Student data;
    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class HeapNode {

    BorrowRequest data;
    HeapNode left;
    HeapNode right;
    HeapNode parent;

    HeapNode(BorrowRequest data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

class RequestNode {

    BorrowRequest data;
    RequestNode next;

    RequestNode(BorrowRequest data) {
        this.data = data;
        this.next = null;
    }
}

class PenaltyNode {

    double amount;
    String reason;
    PenaltyNode next;

    PenaltyNode(double amount, String reason) {
        this.amount = amount;
        this.reason = reason;
        this.next = null;
    }
}
