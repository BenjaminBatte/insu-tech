package com.edian.edian_backend.utility;

import java.util.function.Consumer;

public class UpdateUtility {
    public static <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}