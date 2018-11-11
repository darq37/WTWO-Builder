package com.umcs;

import java.util.Arrays;
import java.util.Base64;


public class Plik {
    private String header;
    private String tag;
    private String body; //deklaracja

    /**
     * TODO
     * co to jest metoda toString?
     * metoda toString tworzy nam napis według podanej instrukcji
     * @return Header + body + tagi = plik
     */
    public String toString(){
        return "HEADER: " + header + '\n' + "TAG: " + tag + '\n' + "BODY: " + body;
    }

    /**
     * Konstruktor klasy Plik
     * @param header - napis (String) odzwierciedlający nagłówek
     * @param tag ...
     * @param body ...
     */
    public Plik(String header, String tag, String body) {
        this.header = header;
        this.tag = tag;
        this.body = body;
    }

    /**
     * Statyczna funkcja mp3Builder przypisana do klasy Plik
     * @return nowy obiekt klasy Mp3PlikBuilder implemntujący interfejs PlikBuilder
     */
    public static PlikBuilder mp3Builder() {
        return new Mp3PlikBuilder();
    }

    /**
     * Statyczna funkcja (taka, do której można się odwołać bez używania konkretnego obiektu)
     * Aby jej użyć, używamy samej klasy Plik:
     * Plik.wavBuilder()
     * @return nowy obiekt - builder plików Wav
     */
    public static PlikBuilder wavBuilder() {
        return new WavPlikBuilder();
    }

    /**
     * Publiczna funckaj oggBuilder (publiczna - taka, która jest dostępna w całym programie)
     * @return nowy obiekt oggBuilder klasy OggPlikBuilder
     */
    public static PlikBuilder oggBuilder() {
        return new OggPlikBuilder();
    }

    /**
     * Klasa wewnętrzna w klasie Plik, definiująca builder plików Wav.
     * WavPlikBuilder implementuje interfejs PlikBuilder, co oznacza że musi implementować jego wszystkie metody
     * static - klasa WavPlikBuilder jest przypisdana do klasy Plik, a nie do konkretnego obiektu Klasy Plik
     * final - klasa WavPlikBuilder jest ostateczna, żadna klasa nie może po niej dziedziczyć
     */
    public static final class WavPlikBuilder implements PlikBuilder {
        private String header;
        private String tag;
        private String body;

        /**
         * TODO
         * Dlaczego ten konstruktor jest prywatny?
         * Dlatego, żeby nie można było się do niego dostać spoza klasy Plik
         */
        private WavPlikBuilder() {
        }


        public PlikBuilder budujHeader() {
            this.header = "WavHeader";
            return this;
        }

        public PlikBuilder budujTagi() {
            this.tag = "";
            return this;
        }

        public PlikBuilder budujBody(int[] body) {
            this.body = Arrays.toString(body);
            return this;
        }

        /** TODO
         * Co to za metoda?
         * metoda build() zwracająca nowy obiekt typu Plik, skłądający się z obiektów header, tag i body
         * Co jej używa i co zwraca?
         */
        public Plik build() {
            return new Plik(header, tag, body);
        }
    }

    public static final class Mp3PlikBuilder implements PlikBuilder {
        private String header;
        private String tag;
        private String body;

        private Mp3PlikBuilder() {
        }

        public PlikBuilder budujHeader() {
            this.header = "Mp3Header";
            return this;
        }

        public PlikBuilder budujTagi() {
            this.tag = "Mp3Tags";
            return this;
        }

        public PlikBuilder budujBody(int[] body) {
            Zipper zipper = new Zipper();
            byte[] bajtyPlikuZIP = zipper.createZip(body);
            this.body = new String(Base64.getEncoder().encode(bajtyPlikuZIP));
            return this;
        }

        public Plik build() {
            return new Plik(header, tag, body);
        }
    }

    public static final class OggPlikBuilder implements PlikBuilder {
        private String header;
        private String tag;
        private String body;

        private OggPlikBuilder() {
        }

        public PlikBuilder budujHeader() {
            this.header = "OggHeader";
            return this;
        }

        public PlikBuilder budujTagi() {
            this.tag = "OggTags";
            return this;
        }

        public PlikBuilder budujBody(int[] body) {
            for (int i = 0; i < body.length; i++) {
                if (body[i] > 100) {
                    body[i] = 100;
                } else if (body[i] < -100) {
                    body[i] = -100;
                }
            }
            Zipper zipper = new Zipper();
            byte[] bajtyPlikuZIP = zipper.createZip(body);
            this.body = new String(Base64.getEncoder().encode(bajtyPlikuZIP));
            return this;
        }

        public Plik build() {
            return new Plik(header, tag, body);
        }
    }
}