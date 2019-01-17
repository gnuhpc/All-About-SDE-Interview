package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class VisitingDirectories {

    static class CustomFileVisitor implements FileVisitor<Path> {

        private long emptyDirs = 0L;
        private Map<String, Long> fileTypes = new HashMap<>();


        public long getEmptyDirs() {
            return emptyDirs;
        }

        public Map<String, Long> getFileTypes() {
            return fileTypes;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);

            boolean dirIsNotEmpty =
                    StreamSupport.stream(directoryStream.spliterator(), false).findFirst().isPresent();

            if (dirIsNotEmpty) {
                return FileVisitResult.CONTINUE;
            } else {
                emptyDirs++;
                return FileVisitResult.SKIP_SUBTREE;
            }
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            String fileType = Files.probeContentType(file);
            fileTypes.merge(fileType, 1L, (l1, l2) -> l1 + l2);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {

        // count the # of empty directories
        // count the # of files / type
        CustomFileVisitor fileVisitor = new CustomFileVisitor();
        Path dir = Paths.get(URI.create("file:///D://BaiduNetdiskDownload"));
        Files.walkFileTree(dir, fileVisitor);
        System.out.println("# of empty dirs = " + fileVisitor.getEmptyDirs());
        System.out.println("AbstractFile types:");
        fileVisitor.getFileTypes()
                .forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
