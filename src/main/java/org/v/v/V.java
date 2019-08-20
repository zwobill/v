package org.v.v;

public interface V<T> {
    V<T> eval();
    T value();

    @SuppressWarnings("unchecked")
    static <X> X getOrEval(X x) {
        if (x instanceof V) {
            return (X) ((V<X>) x).eval();
        } else {
            return x;
        }
    }
}