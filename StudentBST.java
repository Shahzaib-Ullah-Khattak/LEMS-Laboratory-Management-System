
class StudentBST {

    StudentNode root;

    void insert(Student student) {
        root = insertRecursive(root, student);
    }

    StudentNode insertRecursive(StudentNode node, Student student) {

        if (node == null) {
            return new StudentNode(student);
        }

        int comparison = student.id.compareTo(node.data.id);

        if (comparison < 0) {
            node.left = insertRecursive(node.left, student);
        } else if (comparison > 0) {
            node.right = insertRecursive(node.right, student);
        }

        return node;
    }

    Student search(String id) {
        return searchRecursive(root, id);
    }

    Student searchRecursive(StudentNode node, String id) {

        if (node == null) {
            return null;
        }

        int comparison = id.compareTo(node.data.id);

        if (comparison == 0) {
            return node.data;
        }

        if (comparison < 0) {
            return searchRecursive(node.left, id);
        }

        return searchRecursive(node.right, id);
    }
}
