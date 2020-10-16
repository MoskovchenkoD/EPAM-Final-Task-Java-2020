package ua.nure.moskovchenko.db;

import ua.nure.moskovchenko.bean.User;

/**
 * State
 * @author D. Moskovchenko
 */

public enum State {
    FREE(1, "free"),
    BLOCKED(2, "blocked");

    private int id;
    private String name;

    State(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static State getById(int id) {
        for (State e : State.values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + " found");
    }

    public static State getByName(String text) {
        for (State s : State.values()) {
            if (s.name.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name().toLowerCase();
    }

//    public static Role getState(User user) {
//        int stateId = user.getStateId();
//        return Role.values()[stateId];
//    }
}
