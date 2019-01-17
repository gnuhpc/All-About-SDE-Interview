package org.gnuhpc.bigdata.designpattern.builder.lombokexample;

import lombok.*;

import java.util.List;

@Builder
@ToString
public class EmailMessageV2Lombok {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;
    @Singular
    private List<String> tags;

    public static void main(String[] args) {
        EmailMessageV2Lombok.EmailMessageV2LombokBuilder emailBuilder = EmailMessageV2Lombok.builder()
                .to("dasdasd")
                .from("dsadad")
                .content("asddasdad")
                .mimeType("dsadasd");

        System.out.println(emailBuilder.tag("Tag1").tag("Tag2").build());

    }
}
