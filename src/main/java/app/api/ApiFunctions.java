package app.api;

public class ApiFunctions {

    @FunctionalInterface
    public interface ApiCreateCall<T> {
        T create();
    }

    @FunctionalInterface
    public interface ApiFindCall<T> {
        T find();
    }

    @FunctionalInterface
    public interface ApiValidateCall {
        boolean validate();
    }

}