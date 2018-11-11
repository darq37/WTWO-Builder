package com.umcs;

public interface PlikBuilder {
    Plik build();

    PlikBuilder budujHeader();

    PlikBuilder budujTagi();

    PlikBuilder budujBody(int[] tab);
}