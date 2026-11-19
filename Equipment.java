
class Equipment {

    int id;
    String name;
    String category;
    int totalQuantity;
    int availableQuantity;
    String condition;
    String location;
    String description;

    Equipment(
        int id,
        String name,
        String category,
        int totalQuantity,
        int availableQuantity,
        String condition,
        String location,
        String description
    ) {

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

        return condition.equalsIgnoreCase("NEW")
            || condition.equalsIgnoreCase("EXCELLENT")
            || condition.equalsIgnoreCase("GOOD")
            || condition.equalsIgnoreCase("FAIR");
    }

    void display() {

        System.out.println("\n===== EQUIPMENT DETAILS =====");
        System.out.println("Equipment ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Available Quantity: " + availableQuantity);
        System.out.println("Condition: " + condition);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
    }
}
