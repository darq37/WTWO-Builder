package com.umcs;

public class Main {

    public static void main(String[] args) {
        int[] arr = {-500, 0, 500};

        Kierownik wav = new Kierownik(Plik.wavBuilder());
        Kierownik mp3 = new Kierownik(Plik.mp3Builder());
        Kierownik ogg = new Kierownik(Plik.oggBuilder());
        System.out.println(wav.wygenerujNapis(arr));
        System.out.println(mp3.wygenerujNapis(arr));
        System.out.println(ogg.wygenerujNapis(arr));
    }
}
