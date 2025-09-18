package com.immutable.internal.adapter;

import com.immutable.Function;
import com.immutable.Traversable;
import com.immutable.internal.base.Break;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 *
 */
public class Adapters {
    public static <E> boolean containsAll(@NotNull Traversable<E> traversable, @NotNull Collection<?> c) {
        for (Object e : c) {
            if (!contains(traversable, e)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <E> boolean contains(@NotNull Traversable<E> traversable, final Object o) {
        try {
            traversable.forEach(new Function<E, Object>() {
                @Override
                public Object invoke(E e) {
                    if (e.equals(o)) {
                        throw Break.instance;
                    }
                    return null;
                }
            });
            return false;
        } catch (Break e) {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, E> T[] toArray(@NotNull Traversable<E> traversable, T[] a) {
        // Estimate size of array; be prepared to see more or fewer elements
        int size = traversable.size();
        final T[] result = a.length >= size ? a : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        final int[] count = new int[]{0};

        traversable.forEach(new Function<E, Object>() {
            @Override
            public Object invoke(E e) {
                result[count[0]] = (T) e;
                count[0] = count[0] + 1;
                return null;
            }
        });

        return result;
    }
}
