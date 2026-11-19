
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    static EquipmentAVL equipmentTree = new EquipmentAVL();
    static StudentBST studentTree = new StudentBST();
    static RequestHeap requestHeap = new RequestHeap();

    static int requestCounter = 1001;

    public static void main(String[] args) {

        loadSampleEquipment();

        while (true) {

            System.out.println("\n===== LABORATORY EQUIPMENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Student Login");
            System.out.println("2. Student Registration");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = readInteger();

            switch (choice) {

                case 1:
                    login();
                    break;

                case 2:
                    registerStudent();
                    break;

                case 3:
                    System.out.println("Thank you for using LEMS!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
// i use this dummy methord for the searching and of equipment make this in admin (safdar`s part ) so that these equipment are entered by the admin.
    static int readInteger() {
        while (!input.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            input.nextLine();
        }

        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    static void loadSampleEquipment() {

        equipmentTree.insert(new Equipment(
            101, "Laptop", "Computer",
            10, 8, "GOOD",
            "Computer Laboratory", "Dell laboratory laptop"
        ));

        equipmentTree.insert(new Equipment(
            102, "Keyboard", "Computer Accessories",
            20, 15, "EXCELLENT",
            "Computer Laboratory", "USB keyboard"
        ));

        equipmentTree.insert(new Equipment(
            103, "Mouse", "Computer Accessories",
            20, 18, "GOOD",
            "Computer Laboratory", "USB mouse"
        ));

        equipmentTree.insert(new Equipment(
            104, "Monitor", "Computer",
            10, 6, "FAIR",
            "Computer Laboratory", "LCD monitor"
        ));

        equipmentTree.insert(new Equipment(
            105, "Projector", "Presentation",
            5, 3, "GOOD",
            "Multimedia Laboratory", "Multimedia projector"
        ));

        equipmentTree.insert(new Equipment(
            106, "Arduino", "Electronics",
            15, 12, "EXCELLENT",
            "Electronics Laboratory", "Arduino development board"
        ));

        equipmentTree.insert(new Equipment(
            107, "Oscilloscope", "Electronics",
            3, 0, "UNDER_MAINTENANCE",
            "Electronics Laboratory", "Digital oscilloscope"
        ));
    }

    static void registerStudent() {

        System.out.println("\n===== STUDENT REGISTRATION =====");

        System.out.print("Enter Student ID (000-00-0000): ");
        String id = input.nextLine().trim();

        if (!isValidStudentID(id)) {
            System.out.println("Invalid Student ID format!");
            System.out.println("Required format: 000-00-0000");
            return;
        }

        if (studentTree.search(id) != null) {
            System.out.println("Student ID already registered!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter University Email: ");
        String email = input.nextLine().trim();

        if (!isValidUniversityEmail(email)) {
            System.out.println("Invalid university email!");
            System.out.println("Only @iba-suk.edu.pk emails are accepted.");
            return;
        }

        if (!verifyStudentInCMS(id)) {
            System.out.println("Student not found in University CMS!");
            return;
        }

        System.out.print("Create Password: ");
        String password = input.nextLine();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            return;
        }

        Student student = new Student(id, name, email, password);

        studentTree.insert(student);

        System.out.println("\nRegistration successful!");
        System.out.println("You can now login to your account.");
    }

    static boolean isValidStudentID(String id) {
        return id.matches("\\d{3}-\\d{2}-\\d{4}");
    }

    static boolean isValidUniversityEmail(String email) {
        return email.matches(
            "^[A-Za-z0-9._%+-]+@iba-suk\\.edu\\.pk$"
        );
    }

    static boolean verifyStudentInCMS(String studentID) {

        /*
         * FUTURE CMS INTEGRATION:
         *
         * This method will connect to the official
         * Sukkur IBA University CMS.
         *
         * 1. Send the student ID to the authorized CMS API.
         * 2. Search for the student record.
         * 3. Verify that the student exists and is active.
         * 4. Return true if the student is verified.
         * 5. Return false if the student does not exist.
         *
         * Actual verification requires authorized CMS
         * API access or an official university integration.
         */

        return true; // Temporary placeholder for testing.
    }

    static void login() {

        System.out.println("\n===== STUDENT LOGIN =====");

        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Student student = studentTree.search(id);

        if (student == null || !student.password.equals(password)) {
            System.out.println("Invalid Student ID or Password!");
            return;
        }

        System.out.println("\nLogin successful!");
        studentPortal(student);
    }

    static void studentPortal(Student student) {

        while (true) {

            System.out.println("\n===== STUDENT PORTAL =====");
            System.out.println("1. Search Equipment by ID");
            System.out.println("2. Search Equipment by Name");
            System.out.println("3. Display All Available Equipment");
            System.out.println("4. Send a Borrowing Request");
            System.out.println("5. View Pending Requests");
            System.out.println("6. View Approved Requests");
            System.out.println("7. View Currently Borrowed Equipment");
            System.out.println("8. View Returned Equipment");
            System.out.println("9. View Penalties");
            System.out.println("10. View Personal Profile");
            System.out.println("11. Exit the Portal");

            System.out.print("Enter choice: ");
            int choice = readInteger();

            switch (choice) {

                case 1:
                    searchEquipmentByID();
                    break;

                case 2:
                    searchEquipmentByName();
                    break;

                case 3:
                    equipmentTree.displayAvailable();
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
                    System.out.println("Exiting Student Portal...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void searchEquipmentByID() {

        System.out.print("Enter Equipment ID: ");
        int id = readInteger();

        Equipment equipment = equipmentTree.searchByID(id);

        if (equipment == null) {
            System.out.println("Equipment not found!");
        } else {
            equipment.display();
        }
    }

    static void searchEquipmentByName() {

        System.out.print("Enter Equipment Name: ");
        String name = input.nextLine();

        boolean found = equipmentTree.searchByName(name);

        if (!found) {
            System.out.println("Equipment not found!");
        }
    }

    static void sendBorrowingRequest(Student student) {

        System.out.print("Enter Equipment ID: ");
        int id = readInteger();

        Equipment equipment = equipmentTree.searchByID(id);

        if (equipment == null) {
            System.out.println("Equipment not found!");
            return;
        }

        if (!equipment.isIssuable()) {
            System.out.println("This equipment is not in an issuable condition!");
            return;
        }

        if (equipment.availableQuantity <= 0) {
            System.out.println("No available quantity!");
            return;
        }

        if (student.requests.hasActiveRequest(equipment.id)) {
            System.out.println("You already have an active request for this equipment!");
            return;
        }

        BorrowRequest request = new BorrowRequest(
            requestCounter++,
            1,
            student,
            equipment
        );

        student.requests.add(request);
        requestHeap.insert(request);

        System.out.println("\nBorrowing request submitted successfully!");
        System.out.println("Request ID: " + request.requestId);
        System.out.println("Equipment: " + equipment.name);
        System.out.println("Status: " + request.status);
    }
}

class Student {

    String id;
    String name;
    String email;
    String password;

    StudentRequestList requests;
    PenaltyList penalties;

    Student(String id, String name, String email, String password) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

        this.requests = new StudentRequestList();
        this.penalties = new PenaltyList();
    }

    void displayProfile() {

    System.out.println("\n===== STUDENT PROFILE =====");

    System.out.println("Student ID: " + id);
    System.out.println("Name: " + name);
    System.out.println("Email: " + email);

    System.out.println("Total Penalty Amount: Rs. " + penalties.getTotalPenalty());
}

}
