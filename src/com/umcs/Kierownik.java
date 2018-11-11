package com.umcs;

public class Kierownik {
    private PlikBuilder plikBuilder;

    public Kierownik(PlikBuilder plikBuilder) {
        this.plikBuilder = plikBuilder;
    }

    /**
    PlikBuilder builderPlusHeader = plikBuilder.budujHeader();
    PlikBuilder builderPlusTag = builderPlusHeader.budujTagi();
    PlikBuilder builderPlusBody = builderPlusTag.budujBody(tab);
    Plik plik = builderPlusBody.build();
    String napis = plik.toString();
    return napis;
    */
    public String wygenerujNapis(int[] tab) {
        return plikBuilder // PlikBuilder
                .budujHeader() // PlikBuilder
                .budujTagi() // PlikBuilder
                .budujBody(tab) // PlikBuilder
                .build() // Plik
                .toString();  // String
    }
}