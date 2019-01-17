package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;

public class DiskFileSystemOperations {

    public static void main(String[] args) throws IOException {

        List<FileSystemProvider> installedProviders = FileSystemProvider.installedProviders();
        installedProviders.forEach(System.out::println);

        FileSystemProvider windowsFSP = installedProviders.get(0);


        Path dir = Paths.get(URI.create("file:///D:/temp/tmp-dir"));
        windowsFSP.createDirectory(dir);

        //等价于
//		URI rootURI = URI.create("file:///");
//		FileSystem fileSystem = FileSystems.getFileSystem(rootURI);
        FileSystem fileSystem = FileSystems.getDefault();
        fileSystem.getPath("D:/temp/tmp-dir");

        Iterable<Path> rootDirectories = fileSystem.getRootDirectories();
        rootDirectories.forEach(System.out::println);

        Iterable<FileStore> fileStores = fileSystem.getFileStores();
        fileStores.forEach(fileStore -> System.out.println("type = " + fileStore.name() + " - " + fileStore.type()));
    }
}
