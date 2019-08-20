package org.v.v;

import java.util.function.BiFunction;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class V2<X1, X2, Y> implements V<Y> {
    public final X1 x1;
    public final X2 x2;
    public final Y y;
    public final BiFunction<X1, X2, Y> f;
    public final int h;

    public V<Y> eval() {
        int nh = x1.hashCode() ^ x2.hashCode();
        if (h != nh) {
            Y ny = f.apply(V.getOrEval(x1), V.getOrEval(x2));
            return new V2<X1, X2, Y>(x1, x2, ny, f, nh);
        } else {
            return this;
        }
    }

    @Override
    public Y value() {
        return y;
    }
}