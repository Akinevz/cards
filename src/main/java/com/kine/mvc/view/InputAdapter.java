package com.kine.mvc.view;

class InputAdapter<T> {

    T value;
    private boolean isException;
    InputAdapter(ThrowingSupplier<T> in) {
        try {
            this.value = in.get();
        } catch (Exception e) {
            this.value = null;
            this.isException = true;
        }
    }

    InputAdapter(Exception e) {
        this.value = null;
        this.isException = true;
    }

    <R> InputAdapter<R> map(ThrowingFunction<T, R> func) {
        try {
            return new InputAdapter<>(() -> func.apply(this.value));
        } catch (Exception e) {
            return new InputAdapter<>(e);
        }
    }

    boolean isException() {
        return this.isException;
    }

    boolean isValueEqual(T v) {
        return v.equals(this.value);
    }

}