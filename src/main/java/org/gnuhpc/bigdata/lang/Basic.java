package org.gnuhpc.bigdata.lang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Basic {
    public static void main(String[] args) {
        LocalDate data1 = LocalDate.of(2014,6,20);
        LocalDate data2 = LocalDate.parse("2014-0dfsdaf6-20", DateTimeFormatter.ISO_DATE);

        System.out.println(data1);
        System.out.println(data2);
    }
}
