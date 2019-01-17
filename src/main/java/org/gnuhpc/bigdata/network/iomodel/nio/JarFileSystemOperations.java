package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;

public class JarFileSystemOperations {

    public static void main(String[] args) {

        URI zip = URI.create("jar:file:///D:/temp/archive.zip");
        Map<String, String> options = new HashMap<>();
        options.put("create", "true");
        // encoding

        try (FileSystem zipFS = FileSystems.newFileSystem(zip, options);) {

            Path dir = zipFS.getPath("files/");
            zipFS.provider().createDirectory(dir);

            Path aesop = Paths.get("d:/temp/aesop.txt");
            Path target = zipFS.getPath("files/aesop-compressed.txt");

            Files.copy(aesop, target);

            Path binDir = zipFS.getPath("bin/");
            zipFS.provider().createDirectory(binDir);

            Path binFile = zipFS.getPath("bin/ints.bin");
            OutputStream os =
                    zipFS.provider().newOutputStream(binFile, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
            DataOutputStream dos = new DataOutputStream(os);

            dos.writeInt(10);
            dos.writeInt(20);
            dos.writeInt(30);
            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
