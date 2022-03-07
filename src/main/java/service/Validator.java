package service;

public interface Validator<T> {
    boolean validate(T t);
}
