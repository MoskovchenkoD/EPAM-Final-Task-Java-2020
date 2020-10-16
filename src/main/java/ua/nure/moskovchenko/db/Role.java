package ua.nure.moskovchenko.db;

import ua.nure.moskovchenko.bean.User;

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

    public static Role getById(int id) {
        for (Role e : Role.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    public static Role getByName(String text) {
        for (Role r : Role.values()) {
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

//    public static Role getRole(User user) {
//        int roleId = user.getRoleId();
//        return Role.values()[roleId];
//    }

//    public String getName() {
//        return name().toLowerCase();
//    }
}
