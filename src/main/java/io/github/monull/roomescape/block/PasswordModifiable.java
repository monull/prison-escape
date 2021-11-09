package io.github.monull.roomescape.block;

import java.util.ArrayList;

public interface PasswordModifiable {
    public default void setPassword(ArrayList<Integer> password) {}

    public default boolean checkPassword(ArrayList<Integer> password) {
        return true;
    }
}
