package ua.nure.moskovchenko.db;

public enum Status {
    NEW(1, "new"),
    STARTED(2, "started"),
    FINISHED(3, "finished");

    private int id;
    private String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Status getById(int id) {
        for (Status e : Status.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    public static Status getByName(String text) {
        for (Status r : Status.values()) {
            if (r.name.equalsIgnoreCase(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public static boolean checkByName(String text) {
        for (Status r : Status.values()) {
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
