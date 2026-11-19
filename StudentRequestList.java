
class StudentRequestList {

    RequestNode head;
    RequestNode tail;

    StudentRequestList() {
        head = null;
        tail = null;
    }

    void add(BorrowRequest request) {

        RequestNode newNode = new RequestNode(request);

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    boolean hasActiveRequest(int equipmentID) {

        return hasActiveRequestRecursive(head, equipmentID);
    }

    boolean hasActiveRequestRecursive(
        RequestNode current,
        int equipmentID
    ) {

        if (current == null) {
            return false;
        }

        if (current.data.equipment.id == equipmentID
            && (current.data.status.equals("PENDING")
            || current.data.status.equals("APPROVED")
            || current.data.status.equals("BORROWED"))) {

            return true;
        }

        return hasActiveRequestRecursive(current.next, equipmentID);
    }

    BorrowRequest searchRequest(int requestID) {

        return searchRequestRecursive(head, requestID);
    }

    BorrowRequest searchRequestRecursive(
        RequestNode current,
        int requestID
    ) {

        if (current == null) {
            return null;
        }

        if (current.data.requestId == requestID) {
            return current.data;
        }

        return searchRequestRecursive(current.next, requestID);
    }

    void displayStatus(String status) {

        System.out.println("\n===== " + status + " REQUESTS =====");

        boolean found = displayStatusRecursive(head, status);

        if (!found) {
            System.out.println("No " + status + " requests found!");
        }
    }

    boolean displayStatusRecursive(
        RequestNode current,
        String status
    ) {

        if (current == null) {
            return false;
        }

        boolean found = false;

        if (current.data.status.equalsIgnoreCase(status)) {
            current.data.display();
            found = true;
        }

        boolean nextFound = displayStatusRecursive(current.next, status);

        return found || nextFound;
    }
}
