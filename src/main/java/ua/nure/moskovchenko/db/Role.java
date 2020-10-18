package ua.nure.moskovchenko.db;

/**
 * Represents "role" table from the database. The enums consists of all user roles.
 */
public enum Role {
    ADMIN(1, "admin"),
    STUDENT(2, "student"),
    LECTURER(3, "lecturer");

    private int id;
    private String name;

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Searches through all the enum values to find the one that matches.
     * @param id a String representation of a possible enum element
     * @return a Role element that matches the text string
     */
    public static Role getById(int id) {
        for (Role e : Role.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
        //throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    /**
     * Searches through all the enum values to find the one that matches.
     * @param text a String representation of a possible enum element
     * @return a Role element that matches the text string
     */
    public static Role getByName(String text) {
        for (Role r : Role.values()) {
            if (r.name.equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
        //throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

}
