package com.immutable.internal.adapter;

import com.immutable.Function;
import com.immutable.Map;
import com.immutable.Pair;
import com.immutable.internal.base.MappedIterable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MapAdapter<K, V> extends java.util.AbstractMap<K, V> {
    private final Map<K, V> map;

    public MapAdapter(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        try {
            return map.containsKey((K) key);
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value) {
        return Adapters.contains(map.values(), value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        try {
            return map.get((K) key);
        } catch (ClassCastException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(@NotNull java.util.Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public int size() {
                return map.size();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return containsKey(o);
            }

            @NotNull
            @Override
            public Iterator<K> iterator() {
                return map.keys().iterator();
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return map.keys().toArray();
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return Adapters.toArray(map.keys(), a);
            }

            @Override
            public boolean add(K k) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return Adapters.containsAll(map.keys(), c);
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends K> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return new AbstractCollection<V>() {
            @Override
            public int size() {
                return map.size();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return containsValue(o);
            }

            @NotNull
            @Override
            public Iterator<V> iterator() {
                return map.values().iterator();
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return map.values().toArray();
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return Adapters.toArray(map.values(), a);
            }

            @Override
            public boolean add(V v) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return Adapters.containsAll(map.values(), c);
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends V> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public int size() {
                return map.size();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                try {
                    @SuppressWarnings("unchecked")
                    Entry<K, V> entry = (Entry<K, V>) o;
                    Pair<K, V> pair = new Pair<K, V>(entry.getKey(), entry.getValue());
                    return Adapters.contains(map, pair);
                } catch (ClassCastException e) {
                    return false;
                }
            }

            @NotNull
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return getEntries().iterator();
            }

            private MappedIterable<Entry<K, V>, Pair<K, V>> getEntries() {
                return new MappedIterable<Entry<K, V>, Pair<K, V>>(map, new Function<Pair<K, V>, Entry<K, V>>() {
                    @Override
                    public Entry<K, V> invoke(final Pair<K, V> pair) {
                        return new Entry<K, V>() {
                            @Override
                            public K getKey() {
                                return pair.component1();
                            }

                            @Override
                            public V getValue() {
                                return pair.component2();
                            }

                            @Override
                            public V setValue(@NotNull V value) {
                                throw new UnsupportedOperationException();
                            }

                            @Override
                            public boolean equals(Object o) {
                                if (!(o instanceof Entry))
                                    return false;

                                Entry e = (Entry) o;
                                return !(getKey() != null ? !getKey().equals(e.getKey()) : e.getKey() != null)
                                        && !(getValue() != null ? !getValue().equals(e.getValue()) : e.getValue() != null);
                            }

                            @Override
                            public int hashCode() {
                                return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
                            }

                            @Override
                            public String toString() {
                                return getKey() + "=" + getValue();
                            }
                        };
                    }
                });
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return getEntries().toArray();
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return Adapters.toArray(getEntries(), a);
            }

            @Override
            public boolean add(Entry<K, V> kvEntry) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends Entry<K, V>> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
