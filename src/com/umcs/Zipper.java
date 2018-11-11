package com.umcs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    public byte[] createZip(int[] body) {
        ByteArrayOutputStream baos;
        ZipOutputStream zos;
        try {
            baos = new ByteArrayOutputStream();
            zos = new ZipOutputStream(baos);
            ZipEntry entry = new ZipEntry("test");
            entry.setSize(body.length);
            zos.putNextEntry(entry);
            for (int i : body) {
                zos.write(i);
            }
            zos.closeEntry();
            return baos.toByteArray();
        } catch (IOException io) {
            // wyskoczyl blad (np. zapisywanie pliku, otwieranie pliku), ignorujemy go
            return null;
        }
    }
}
