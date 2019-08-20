package org.v.v;

import java.util.function.Function;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class V1<X, Y> implements V<Y> {
    public final X X;
    public final Y y;
    public final Function<X, Y> f;
    public final int h;

    public V<Y> eval() {
        int nh = X.hashCode();
        if (h != nh) {
            Y ny = f.apply(X);
            return new V1<X, Y>(X, ny, f, nh);
        } else {
            return this;
        }
    }

    @Override
    public Y value() {
        return y;
    }
}