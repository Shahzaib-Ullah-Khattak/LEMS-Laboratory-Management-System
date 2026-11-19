
class EquipmentAVL {

    EquipmentNode root;

    int height(EquipmentNode node) {

        if (node == null) {
            return 0;
        }

        return node.height;
    }

    int getBalance(EquipmentNode node) {

        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    EquipmentNode rightRotate(EquipmentNode y) {

        EquipmentNode x = y.left;
        EquipmentNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + max(height(y.left), height(y.right));
        x.height = 1 + max(height(x.left), height(x.right));

        return x;
    }

    EquipmentNode leftRotate(EquipmentNode x) {

        EquipmentNode y = x.right;
        EquipmentNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + max(height(x.left), height(x.right));
        y.height = 1 + max(height(y.left), height(y.right));

        return y;
    }

    void insert(Equipment equipment) {
        root = insertRecursive(root, equipment);
    }

    EquipmentNode insertRecursive(
        EquipmentNode node,
        Equipment equipment
    ) {

        if (node == null) {
            return new EquipmentNode(equipment);
        }

        if (equipment.id < node.data.id) {
            node.left = insertRecursive(node.left, equipment);
        } else if (equipment.id > node.data.id) {
            node.right = insertRecursive(node.right, equipment);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && equipment.id < node.left.data.id) {
            return rightRotate(node);
        }

        // RR Case
        if (balance < -1 && equipment.id > node.right.data.id) {
            return leftRotate(node);
        }

        // LR Case
        if (balance > 1 && equipment.id > node.left.data.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && equipment.id < node.right.data.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Equipment searchByID(int id) {
        return searchByIDRecursive(root, id);
    }

    Equipment searchByIDRecursive(EquipmentNode node, int id) {

        if (node == null) {
            return null;
        }

        if (node.data.id == id) {
            return node.data;
        }

        if (id < node.data.id) {
            return searchByIDRecursive(node.left, id);
        }

        return searchByIDRecursive(node.right, id);
    }

    boolean searchByName(String name) {
        return searchByNameRecursive(root, name);
    }

    boolean searchByNameRecursive(EquipmentNode node, String name) {

        if (node == null) {
            return false;
        }

        boolean found = false;

        if (node.data.name.equalsIgnoreCase(name)) {
            node.data.display();
            found = true;
        }

        boolean left = searchByNameRecursive(node.left, name);
        boolean right = searchByNameRecursive(node.right, name);

        return found || left || right;
    }

    void displayAvailable() {

        System.out.println("\n===== AVAILABLE EQUIPMENT =====");

        boolean found = displayAvailableRecursive(root);

        if (!found) {
            System.out.println("No available equipment found!");
        }
    }

    boolean displayAvailableRecursive(EquipmentNode node) {

        if (node == null) {
            return false;
        }

        boolean found = false;

        if (node.data.availableQuantity > 0 && node.data.isIssuable()) {
            node.data.display();
            found = true;
        }

        boolean left = displayAvailableRecursive(node.left);
        boolean right = displayAvailableRecursive(node.right);

        return found || left || right;
    }
}
