
class BorrowRequest {

    int requestId;
    int priority;

    Student student;
    Equipment equipment;

    String status;

    BorrowRequest(
        int requestId,
        int priority,
        Student student,
        Equipment equipment
    ) {

        this.requestId = requestId;
        this.priority = priority;
        this.student = student;
        this.equipment = equipment;

        this.status = "PENDING";
    }

    void display() {

        System.out.println("\nRequest ID: " + requestId);
        System.out.println("Student ID: " + student.id);
        System.out.println("Student Name: " + student.name);
        System.out.println("Equipment ID: " + equipment.id);
        System.out.println("Equipment Name: " + equipment.name);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + status);
    }
}
