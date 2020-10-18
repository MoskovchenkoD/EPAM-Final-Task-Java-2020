package ua.nure.moskovchenko.db;

/**
 * Represents "topic" table from the database. The enum consists of all course topics.
 */
public enum Topic {
    MATH(1, "math"),
    ENGLISH(2, "english");

    private int id;
    private String name;

    Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Searches through all the enum values to find the one that matches.
     * @param id an int representation of a possible enum element
     * @return a Role element that matches the id
     */
    public static Topic getById(int id) {
        for (Topic e : Topic.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    /**
     * Searches through all the enum values to find the one that matches.
     * @param text a String representation of a possible enum element
     * @return a Role element that matches the text string
     */
    public static Topic getByName(String text) {
        for (Topic r : Topic.values()) {
            if (r.name.equalsIgnoreCase(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    /**
     * Searches through all the enum values to find the one that matches.
     * @param text a String representation of a possible enum element
     * @return a boolean values that shows whether such an enum element exists
     */
    public static boolean checkByName(String text) {
        for (Topic r : Topic.values()) {
            if (r.name.equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
