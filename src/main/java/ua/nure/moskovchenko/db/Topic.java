package ua.nure.moskovchenko.db;

public enum Topic {
    MATH(1, "math"),
    ENGLISH(2, "english");

    private int id;
    private String name;

    Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Topic getById(int id) {
        for (Topic e : Topic.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    public static Topic getByName(String text) {
        for (Topic r : Topic.values()) {
            if (r.name.equalsIgnoreCase(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
