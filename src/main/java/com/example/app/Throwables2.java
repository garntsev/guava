package com.example.app;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class Throwables2 {
    public static void main(String[] args) throws IOException, SQLException {
        try {
            someMethodThatCouldThrowAnything();
        } catch (IKnowWhatToDoWithThisException e) {
            handle(e);
        } catch (Throwable t) {
            Throwables.throwIfInstanceOf(t, IOException.class);
            Throwables.throwIfInstanceOf(t, SQLException.class);
            Throwables.throwIfUnchecked(t);
            throw new RuntimeException(t);
        }
    }

    private static void handle(IKnowWhatToDoWithThisException e) {
    }

    private static void someMethodThatCouldThrowAnything() throws IKnowWhatToDoWithThisException {
        if (new Random().nextBoolean()) {
            throw new IKnowWhatToDoWithThisException();
        }
    }
}
