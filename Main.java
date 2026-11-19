
import java.util.Scanner;

// =====================================================
//                       MAIN CLASS
// =====================================================

public class Main {

    static Scanner input = new Scanner(System.in);

    static EquipmentAVL equipmentTree = new EquipmentAVL();
    static StudentBST studentTree = new StudentBST();
    static RequestHeap requestHeap = new RequestHeap();

    static int requestCounter = 1;

    public static void main(String[] args) {

        loadEquipment();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     LABORATORY EQUIPMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Student Login");
            System.out.println("2. Student Registration");
            System.out.println("0. Exit");

            int choice = readInt("Enter Your Choice: ");

            switch (choice) {

                case 1:
                    studentLogin();
                    break;

                case 2:
                    studentRegistration();
                    break;

                case 0:
                    System.out.println("System Closed!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // =================================================
    //                  INPUT FUNCTION
    // =================================================

    static int readInt(String message) {

        System.out.print(message);

        while (!input.hasNextInt()) {

            System.out.print("Enter a valid number: ");
            input.next();
        }

        int value = input.nextInt();
        input.nextLine();

        return value;
    }

    // =================================================
    //                  SAMPLE EQUIPMENT
    // =================================================

    static void loadEquipment() {

        equipmentTree.insert(
            new Equipment(101, "Laptop", "Computer",
                10, 10, "GOOD", "Computer Lab",
                "Dell laptops")
        );

        equipmentTree.insert(
            new Equipment(102, "Keyboard", "Peripheral",
                20, 20, "EXCELLENT", "Computer Lab",
                "USB keyboard")
        );

        equipmentTree.insert(
            new Equipment(103, "Mouse", "Peripheral",
                30, 30, "GOOD", "Computer Lab",
                "Optical mouse")
        );

        equipmentTree.insert(
            new Equipment(104, "Monitor", "Display",
                8, 8, "FAIR", "Computer Lab",
                "LED monitor")
        );

        equipmentTree.insert(
            new Equipment(105, "Projector", "Display",
                5, 5, "GOOD", "Seminar Room",
                "Multimedia projector")
        );

        equipmentTree.insert(
            new Equipment(106, "Arduino", "Electronics",
                15, 15, "EXCELLENT", "Electronics Lab",
                "Arduino development board")
        );

        equipmentTree.insert(
            new Equipment(107, "Oscilloscope", "Electronics",
                4, 4, "UNDER_MAINTENANCE", "Electronics Lab",
                "Digital oscilloscope")
        );
    }

    // =================================================
    //                  REGISTRATION
    // =================================================

    static void studentRegistration() {

        System.out.println("\n===== STUDENT REGISTRATION =====");

        int id = readInt("Enter Student ID: ");

        if (studentTree.search(id) != null) {

            System.out.println("Student ID Already Exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Department: ");
        String department = input.nextLine();

        System.out.print("Enter Semester: ");
        String semester = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Student student = new Student(
            id, name, department, semester, password
        );

        studentTree.insert(student);

        System.out.println("\nRegistration Successful!");
    }

    // =================================================
    //                  STUDENT LOGIN
    // =================================================

    static void studentLogin() {

        System.out.println("\n===== STUDENT LOGIN =====");

        int id = readInt("Enter Student ID: ");

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Student student = studentTree.search(id);

        if (student == null) {

            System.out.println("Student Not Found!");
            return;
        }

        if (!student.password.equals(password)) {

            System.out.println("Incorrect Password!");
            return;
        }

        System.out.println("\nLogin Successful!");

        studentPortal(student);
    }

    // =================================================
    //                  STUDENT PORTAL
    // =================================================

    static void studentPortal(Student student) {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("           STUDENT PORTAL");
            System.out.println("====================================");

            System.out.println("Welcome, " + student.name);

            System.out.println("\n1. Search Equipment By ID");
            System.out.println("2. Search Equipment By Name");
            System.out.println("3. Display All Available Equipment");
            System.out.println("4. Send Borrowing Request");
            System.out.println("5. View Pending Requests");
            System.out.println("6. View Approved Requests");
            System.out.println("7. View Currently Borrowed Equipment");
            System.out.println("8. View Returned Equipment");
            System.out.println("9. View Penalties");
            System.out.println("10. View Personal Profile");
            System.out.println("11. Exit Portal");

            int choice = readInt("\nEnter Your Choice: ");

            switch (choice) {

                case 1:
                    searchEquipmentByID();
                    break;

                case 2:
                    searchEquipmentByName();
                    break;

                case 3:
                    displayAvailableEquipment();
                    break;

                case 4:
                    sendBorrowingRequest(student);
                    break;

                case 5:
                    student.requests.displayStatus("PENDING");
                    break;

                case 6:
                    student.requests.displayStatus("APPROVED");
                    break;

                case 7:
                    student.requests.displayStatus("BORROWED");
                    break;

                case 8:
                    student.requests.displayStatus("RETURNED");
                    break;

                case 9:
                    student.penalties.display();
                    break;

                case 10:
                    student.displayProfile();
                    break;

                case 11:
                    System.out.println("Student Logged Out!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // =================================================
    //             SEARCH EQUIPMENT BY ID
    // =================================================

    static void searchEquipmentByID() {

        int id = readInt("Enter Equipment ID: ");

        Equipment equipment = equipmentTree.search(id);

        if (equipment == null) {

            System.out.println("Equipment Not Found!");

        } else {

            System.out.println("\n===== EQUIPMENT FOUND =====");

            equipment.display();
        }
    }

    // =================================================
    //             SEARCH EQUIPMENT BY NAME
    // =================================================

    static void searchEquipmentByName() {

        System.out.print("Enter Equipment Name: ");

        String name = input.nextLine();

        boolean found = equipmentTree.searchByName(name);

        if (!found) {

            System.out.println("Equipment Not Found!");
        }
    }

    // =================================================
    //             DISPLAY AVAILABLE EQUIPMENT
    // =================================================

    static void displayAvailableEquipment() {

        System.out.println("\n===== AVAILABLE EQUIPMENT =====");

        int count = equipmentTree.displayAvailable();

        if (count == 0) {

            System.out.println("No Available Equipment!");
        }
    }

    // =================================================
    //             SEND BORROWING REQUEST
    // =================================================

    static void sendBorrowingRequest(Student student) {

        System.out.println("\n===== BORROWING REQUEST =====");

        int id = readInt("Enter Equipment ID: ");

        Equipment equipment = equipmentTree.search(id);

        if (equipment == null) {

            System.out.println("Equipment Not Found!");
            return;
        }

        // Check available quantity

        if (equipment.availableQuantity <= 0) {

            System.out.println("Equipment Is Currently Unavailable!");
            return;
        }

        // Check equipment condition

        if (!equipment.isIssuable()) {

            System.out.println(
                "Request Cannot Be Submitted Due To Equipment Condition!"
            );

            System.out.println("Current Condition: " + equipment.condition);

            return;
        }

        // Prevent duplicate active requests

        if (student.requests.hasActiveRequest(id)) {

            System.out.println(
                "You Already Have An Active Request For This Equipment!"
            );

            return;
        }

        System.out.println("\n===== EQUIPMENT DETAILS =====");

        equipment.display();

        System.out.print("\nConfirm Request? (yes/no): ");

        String confirm = input.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {

            System.out.println("Request Cancelled!");
            return;
        }

        // Priority is assigned by the system.
        // Students cannot manipulate their priority.

        int priority = 1;

        BorrowRequest request = new BorrowRequest(
            requestCounter++,
            student,
            equipment,
            priority
        );

        // Insert into student's linked list

        student.requests.add(request);

        // Insert into manual Max Heap

        requestHeap.insert(request);

        System.out.println("\nRequest Submitted Successfully!");

        System.out.println("Request ID: " + request.requestId);

        System.out.println("Equipment: " + equipment.name);

        System.out.println("Status: PENDING");

        System.out.println("Priority: Assigned By System");
    }
}


// =====================================================
//                  EQUIPMENT CLASS
// =====================================================

class Equipment {

    int id;

    String name;
    String category;

    int totalQuantity;
    int availableQuantity;

    String condition;
    String location;
    String description;

    Equipment(int id, String name, String category,
              int totalQuantity, int availableQuantity,
              String condition, String location,
              String description) {

        this.id = id;

        this.name = name;
        this.category = category;

        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;

        this.condition = condition;
        this.location = location;
        this.description = description;
    }

    boolean isIssuable() {

        return condition.equals("NEW")
            || condition.equals("EXCELLENT")
            || condition.equals("GOOD")
            || condition.equals("FAIR");
    }

    void display() {

        System.out.println("\nEquipment ID: " + id);

        System.out.println("Name: " + name);

        System.out.println("Category: " + category);

        System.out.println("Total Quantity: " + totalQuantity);

        System.out.println("Available Quantity: " + availableQuantity);

        System.out.println("Condition: " + condition);

        System.out.println("Location: " + location);

        System.out.println("Description: " + description);

        System.out.println(
            "Availability: " +
            (availableQuantity > 0 && isIssuable()
                ? "AVAILABLE" : "UNAVAILABLE")
        );
    }
}


// =====================================================
//                  EQUIPMENT AVL NODE
// =====================================================

class EquipmentNode {

    Equipment data;

    EquipmentNode left;
    EquipmentNode right;

    int height;

    EquipmentNode(Equipment data) {

        this.data = data;

        this.height = 1;
    }
}


// =====================================================
//                  EQUIPMENT AVL TREE
// =====================================================

class EquipmentAVL {

    EquipmentNode root;

    // HEIGHT

    int height(EquipmentNode node) {

        if (node == null)
            return 0;

        return node.height;
    }

    // MAXIMUM

    int max(int a, int b) {

        return a > b ? a : b;
    }

    // BALANCE FACTOR

    int balanceFactor(EquipmentNode node) {

        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    // RIGHT ROTATION

    EquipmentNode rightRotate(EquipmentNode y) {

        EquipmentNode x = y.left;

        EquipmentNode temp = x.right;

        x.right = y;

        y.left = temp;

        y.height = 1 + max(
            height(y.left),
            height(y.right)
        );

        x.height = 1 + max(
            height(x.left),
            height(x.right)
        );

        return x;
    }

    // LEFT ROTATION

    EquipmentNode leftRotate(EquipmentNode x) {

        EquipmentNode y = x.right;

        EquipmentNode temp = y.left;

        y.left = x;

        x.right = temp;

        x.height = 1 + max(
            height(x.left),
            height(x.right)
        );

        y.height = 1 + max(
            height(y.left),
            height(y.right)
        );

        return y;
    }

    // INSERT

    void insert(Equipment equipment) {

        root = insertRecursive(root, equipment);
    }

    EquipmentNode insertRecursive(
        EquipmentNode node, Equipment equipment) {

        if (node == null) {

            return new EquipmentNode(equipment);
        }

        if (equipment.id < node.data.id) {

            node.left = insertRecursive(node.left, equipment);

        } else if (equipment.id > node.data.id) {

            node.right = insertRecursive(node.right, equipment);

        } else {

            System.out.println("Duplicate Equipment ID!");

            return node;
        }

        // UPDATE HEIGHT

        node.height = 1 + max(
            height(node.left),
            height(node.right)
        );

        // CHECK BALANCE

        int balance = balanceFactor(node);

        // LL CASE

        if (balance > 1 &&
            equipment.id < node.left.data.id) {

            return rightRotate(node);
        }

        // RR CASE

        if (balance < -1 &&
            equipment.id > node.right.data.id) {

            return leftRotate(node);
        }

        // LR CASE

        if (balance > 1 &&
            equipment.id > node.left.data.id) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        // RL CASE

        if (balance < -1 &&
            equipment.id < node.right.data.id) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    // SEARCH BY ID

    Equipment search(int id) {

        return searchRecursive(root, id);
    }

    Equipment searchRecursive(EquipmentNode node, int id) {

        if (node == null)
            return null;

        if (node.data.id == id)
            return node.data;

        if (id < node.data.id) {

            return searchRecursive(node.left, id);
        }

        return searchRecursive(node.right, id);
    }

    // SEARCH BY NAME

    boolean searchByName(String name) {

        return searchNameRecursive(root, name);
    }

    boolean searchNameRecursive(
        EquipmentNode node, String name) {

        if (node == null)
            return false;

        boolean found = false;

        if (node.data.name.equalsIgnoreCase(name)) {

            node.data.display();

            found = true;
        }

        boolean left = searchNameRecursive(node.left, name);

        boolean right = searchNameRecursive(node.right, name);

        return found || left || right;
    }

    // DISPLAY ALL AVAILABLE EQUIPMENT

    int displayAvailable() {

        return displayAvailableRecursive(root);
    }

    int displayAvailableRecursive(EquipmentNode node) {

        if (node == null)
            return 0;

        int count = 0;

        count += displayAvailableRecursive(node.left);

        if (node.data.availableQuantity > 0 &&
            node.data.isIssuable()) {

            node.data.display();

            count++;
        }

        count += displayAvailableRecursive(node.right);

        return count;
    }

    // INORDER TRAVERSAL

    void display() {

        if (root == null) {

            System.out.println("Equipment Tree Is Empty!");

            return;
        }

        inorder(root);
    }

    void inorder(EquipmentNode node) {

        if (node == null)
            return;

        inorder(node.left);

        node.data.display();

        inorder(node.right);
    }
}


// =====================================================
//                      STUDENT CLASS
// =====================================================

class Student {

    int id;

    String name;
    String department;
    String semester;
    String password;

    StudentRequestList requests;

    PenaltyList penalties;

    Student(int id, String name, String department,
            String semester, String password) {

        this.id = id;

        this.name = name;
        this.department = department;
        this.semester = semester;

        this.password = password;

        requests = new StudentRequestList();

        penalties = new PenaltyList();
    }

    void displayProfile() {

        System.out.println("\n===== PERSONAL PROFILE =====");

        System.out.println("Student ID: " + id);

        System.out.println("Name: " + name);

        System.out.println("Department: " + department);

        System.out.println("Semester: " + semester);

        System.out.println("Total Penalty: Rs. " +
            penalties.totalPenalty());
    }
}


// =====================================================
//                      STUDENT BST NODE
// =====================================================

class StudentNode {

    Student data;

    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {

        this.data = data;
    }
}


// =====================================================
//                      STUDENT BST
// =====================================================

class StudentBST {

    StudentNode root;

    // INSERT STUDENT

    void insert(Student student) {

        root = insertRecursive(root, student);
    }

    StudentNode insertRecursive(
        StudentNode node, Student student) {

        if (node == null) {

            return new StudentNode(student);
        }

        if (student.id < node.data.id) {

            node.left = insertRecursive(node.left, student);

        } else if (student.id > node.data.id) {

            node.right = insertRecursive(node.right, student);
        }

        return node;
    }

    // SEARCH STUDENT

    Student search(int id) {

        return searchRecursive(root, id);
    }

    Student searchRecursive(StudentNode node, int id) {

        if (node == null)
            return null;

        if (node.data.id == id)
            return node.data;

        if (id < node.data.id) {

            return searchRecursive(node.left, id);
        }

        return searchRecursive(node.right, id);
    }
}


// =====================================================
//                  BORROW REQUEST CLASS
// =====================================================

class BorrowRequest {

    int requestId;

    int priority;

    Student student;

    Equipment equipment;

    String status;

    BorrowRequest(int requestId, Student student,
                  Equipment equipment, int priority) {

        this.requestId = requestId;

        this.student = student;

        this.equipment = equipment;

        this.priority = priority;

        this.status = "PENDING";
    }

    void display() {

        System.out.println("\nRequest ID: " + requestId);

        System.out.println("Equipment ID: " + equipment.id);

        System.out.println("Equipment: " + equipment.name);

        System.out.println("Priority: " + priority);

        System.out.println("Status: " + status);
    }
}


// =====================================================
//                      HEAP NODE
// =====================================================

class HeapNode {

    BorrowRequest data;

    HeapNode left;
    HeapNode right;
    HeapNode parent;

    HeapNode(BorrowRequest data) {

        this.data = data;
    }
}


// =====================================================
//                  MANUAL MAX HEAP
// =====================================================

class RequestHeap {

    HeapNode root;

    int size;

    // GET NODE USING COMPLETE TREE INDEX

    HeapNode getNode(int index) {

        if (root == null || index < 1 || index > size)
            return null;

        HeapNode current = root;

        int bit = Integer.highestOneBit(index) >> 1;

        while (bit > 0) {

            if ((index & bit) == 0) {

                current = current.left;

            } else {

                current = current.right;
            }

            bit >>= 1;
        }

        return current;
    }

    // INSERT

    void insert(BorrowRequest request) {

        HeapNode newNode = new HeapNode(request);

        size++;

        if (root == null) {

            root = newNode;

            return;
        }

        HeapNode parent = getNode(size / 2);

        newNode.parent = parent;

        if (size % 2 == 0) {

            parent.left = newNode;

        } else {

            parent.right = newNode;
        }

        heapifyUp(newNode);
    }

    // HEAPIFY UP

    void heapifyUp(HeapNode node) {

        while (node.parent != null &&
               higherPriority(node.data, node.parent.data)) {

            swap(node, node.parent);

            node = node.parent;
        }
    }

    // COMPARE PRIORITIES

    boolean higherPriority(BorrowRequest a, BorrowRequest b) {

        if (a.priority != b.priority) {

            return a.priority > b.priority;
        }

        return a.requestId < b.requestId;
    }

    // SWAP DATA

    void swap(HeapNode a, HeapNode b) {

        BorrowRequest temp = a.data;

        a.data = b.data;

        b.data = temp;
    }

    // PEEK

    BorrowRequest peek() {

        if (root == null)
            return null;

        return root.data;
    }

    // REMOVE MAX

    BorrowRequest removeMax() {

        if (root == null)
            return null;

        BorrowRequest removed = root.data;

        if (size == 1) {

            root = null;

            size = 0;

            return removed;
        }

        HeapNode lastNode = getNode(size);

        root.data = lastNode.data;

        HeapNode parent = lastNode.parent;

        if (parent.left == lastNode) {

            parent.left = null;

        } else {

            parent.right = null;
        }

        lastNode.parent = null;

        size--;

        heapifyDown(root);

        return removed;
    }

    // HEAPIFY DOWN

    void heapifyDown(HeapNode node) {

        while (node != null) {

            HeapNode largest = node;

            if (node.left != null &&
                higherPriority(node.left.data, largest.data)) {

                largest = node.left;
            }

            if (node.right != null &&
                higherPriority(node.right.data, largest.data)) {

                largest = node.right;
            }

            if (largest == node)
                break;

            swap(node, largest);

            node = largest;
        }
    }

    // DISPLAY HIGHEST PRIORITY

    void displayHighest() {

        BorrowRequest request = peek();

        if (request == null) {

            System.out.println("No Pending Requests!");

            return;
        }

        request.display();
    }

    // DISPLAY HEAP LEVEL BY LEVEL

    void displayAll() {

        if (root == null) {

            System.out.println("Heap Is Empty!");

            return;
        }

        int h = height(root);

        for (int i = 1; i <= h; i++) {

            displayLevel(root, i);
        }
    }

    void displayLevel(HeapNode node, int level) {

        if (node == null)
            return;

        if (level == 1) {

            node.data.display();

        } else if (level > 1) {

            displayLevel(node.left, level - 1);

            displayLevel(node.right, level - 1);
        }
    }

    // HEIGHT USING RECURSION

    int height(HeapNode node) {

        if (node == null)
            return 0;

        int leftHeight = height(node.left);

        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}


// =====================================================
//                  REQUEST LINKED LIST NODE
// =====================================================

class RequestNode {

    BorrowRequest data;

    RequestNode next;

    RequestNode(BorrowRequest data) {

        this.data = data;
    }
}


// =====================================================
//                  STUDENT REQUEST LINKED LIST
// =====================================================

class StudentRequestList {

    RequestNode head;

    // INSERT AT END

    void add(BorrowRequest request) {

        RequestNode newNode = new RequestNode(request);

        if (head == null) {

            head = newNode;

            return;
        }

        RequestNode current = head;

        while (current.next != null) {

            current = current.next;
        }

        current.next = newNode;
    }

    // SEARCH BY REQUEST ID

    BorrowRequest search(int requestId) {

        return searchRecursive(head, requestId);
    }

    BorrowRequest searchRecursive(
        RequestNode node, int requestId) {

        if (node == null)
            return null;

        if (node.data.requestId == requestId)
            return node.data;

        return searchRecursive(node.next, requestId);
    }

    // CHECK ACTIVE REQUEST

    boolean hasActiveRequest(int equipmentId) {

        return hasActiveRequestRecursive(head, equipmentId);
    }

    boolean hasActiveRequestRecursive(
        RequestNode node, int equipmentId) {

        if (node == null)
            return false;

        BorrowRequest request = node.data;

        if (request.equipment.id == equipmentId &&
            (request.status.equals("PENDING") ||
             request.status.equals("APPROVED") ||
             request.status.equals("BORROWED"))) {

            return true;
        }

        return hasActiveRequestRecursive(node.next, equipmentId);
    }

    // DISPLAY ALL

    void displayAll() {

        if (head == null) {

            System.out.println("No Requests Found!");

            return;
        }

        displayRecursive(head);
    }

    void displayRecursive(RequestNode node) {

        if (node == null)
            return;

        node.data.display();

        displayRecursive(node.next);
    }

    // DISPLAY REQUESTS BY STATUS

    void displayStatus(String status) {

        boolean found = displayStatusRecursive(head, status);

        if (!found) {

            System.out.println("No " + status + " Requests Found!");
        }
    }

    boolean displayStatusRecursive(
        RequestNode node, String status) {

        if (node == null)
            return false;

        boolean found = false;

        if (node.data.status.equals(status)) {

            node.data.display();

            found = true;
        }

        boolean nextFound =
            displayStatusRecursive(node.next, status);

        return found || nextFound;
    }
}


// =====================================================
//                      PENALTY NODE
// =====================================================

class PenaltyNode {

    double amount;

    String reason;

    PenaltyNode next;

    PenaltyNode(double amount, String reason) {

        this.amount = amount;

        this.reason = reason;
    }
}


// =====================================================
//                      PENALTY LINKED LIST
// =====================================================

class PenaltyList {

    PenaltyNode head;

    // INSERT PENALTY

    void add(double amount, String reason) {

        PenaltyNode newNode = new PenaltyNode(amount, reason);

        if (head == null) {

            head = newNode;

            return;
        }

        PenaltyNode current = head;

        while (current.next != null) {

            current = current.next;
        }

        current.next = newNode;
    }

    // DISPLAY PENALTIES

    void display() {

        if (head == null) {

            System.out.println("No Penalties Found!");

            return;
        }

        System.out.println("\n===== PENALTY HISTORY =====");

        displayRecursive(head);

        System.out.println(
            "Total Penalty: Rs. " + totalPenalty()
        );
    }

    void displayRecursive(PenaltyNode node) {

        if (node == null)
            return;

        System.out.println("\nReason: " + node.reason);

        System.out.println("Amount: Rs. " + node.amount);

        displayRecursive(node.next);
    }

    // CALCULATE TOTAL PENALTY

    double totalPenalty() {

        return totalRecursive(head);
    }

    double totalRecursive(PenaltyNode node) {

        if (node == null)
            return 0;

        return node.amount + totalRecursive(node.next);
    }
}
