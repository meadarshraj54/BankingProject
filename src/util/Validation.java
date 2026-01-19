package util;

import exceptions.ValidationException;

@FunctionalInterface
public interface Validation<T> {
    void validate(T s) throws ValidationException;
}
