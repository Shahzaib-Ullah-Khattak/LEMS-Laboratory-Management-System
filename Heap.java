
class RequestHeap {

    HeapNode root;
    int size;

    RequestHeap() {
        root = null;
        size = 0;
    }

    HeapNode getNode(int index) {

        if (index < 1 || root == null) {
            return null;
        }

        HeapNode current = root;

        int highestBit = Integer.highestOneBit(index);
        highestBit = highestBit >> 1;

        while (highestBit > 0 && current != null) {

            if ((index & highestBit) == 0) {
                current = current.left;
            } else {
                current = current.right;
            }

            highestBit = highestBit >> 1;
        }

        return current;
    }

    boolean hasHigherPriority(BorrowRequest first, BorrowRequest second) {

        if (first.priority != second.priority) {
            return first.priority > second.priority;
        }

        return first.requestId < second.requestId;
    }

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

    void heapifyUp(HeapNode node) {

        while (node.parent != null
            && hasHigherPriority(node.data, node.parent.data)) {

            BorrowRequest temp = node.data;
            node.data = node.parent.data;
            node.parent.data = temp;

            node = node.parent;
        }
    }

    BorrowRequest peek() {

        if (root == null) {
            return null;
        }

        return root.data;
    }

    BorrowRequest removeMax() {

        if (root == null) {
            return null;
        }

        BorrowRequest max = root.data;

        if (size == 1) {
            root = null;
            size = 0;
            return max;
        }

        HeapNode lastNode = getNode(size);

        root.data = lastNode.data;

        HeapNode parent = lastNode.parent;

        if (parent.left == lastNode) {
            parent.left = null;
        } else {
            parent.right = null;
        }

        size--;

        heapifyDown(root);

        return max;
    }

    void heapifyDown(HeapNode node) {

        while (node != null) {

            HeapNode highest = node;

            if (node.left != null
                && hasHigherPriority(node.left.data, highest.data)) {

                highest = node.left;
            }

            if (node.right != null
                && hasHigherPriority(node.right.data, highest.data)) {

                highest = node.right;
            }

            if (highest == node) {
                break;
            }

            BorrowRequest temp = node.data;
            node.data = highest.data;
            highest.data = temp;

            node = highest;
        }
    }

    void displayAll() {

        if (root == null) {
            System.out.println("No requests in Heap!");
            return;
        }

        displayLevel(root, 1);
    }

    void displayLevel(HeapNode node, int level) {

        if (node == null) {
            return;
        }

        System.out.println(
            "Level " + level
            + " | Request ID: " + node.data.requestId
            + " | Priority: " + node.data.priority
        );

        displayLevel(node.left, level + 1);
        displayLevel(node.right, level + 1);
    }

    int height(HeapNode node) {

        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
