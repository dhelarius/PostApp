package com.itla.postapp.preference;

public interface Preference<T> {

    void write(T value);

    T read();
}
