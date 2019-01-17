package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

public class FindingFiles {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(URI.create("file:///D://BaiduNetdiskDownload"));

        boolean exists = Files.exists(path);
        System.out.println("exists = " + exists);

        Stream<Path> find1 =
                Files.find(path, Integer.MAX_VALUE, (p, attr) -> true);

        System.out.println("count = " + find1.count());

        Stream<Path> find2 =
                Files.find(path, Integer.MAX_VALUE, (p, attr) -> p.toString().endsWith(".jpg"));
        System.out.println("count = " + find2.count());

        Calendar c = GregorianCalendar.getInstance();
        c.set(2017, 0, 1, 0, 0);
        long date = c.getTimeInMillis();

        Stream<Path> find3 =
                Files.find(path, Integer.MAX_VALUE, (p, attr) -> attr.creationTime().toMillis() < date);
        System.out.println("count = " + find3.count());
    }
}
