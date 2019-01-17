package org.gnuhpc.bigdata.designpattern.builder.advexample;


import lombok.Getter;
import lombok.ToString;

// 还有一种化简的写法是，Builder只有一个带有必选field的构造方法
@Getter
@ToString
public class EmailMessageV3Required {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;  // optional

    private EmailMessageV3Required() {}

    public static RequiredFrom from(String from) {
        return new EmailMessageV3Required.Builder(from);
    }

    public interface RequiredFrom {
        RequiredTo to(String to);
    }

    public interface RequiredTo {
        RequiredSubject subject(String subject);
    }

    public interface RequiredSubject {
        RequiredContent content(String content);
    }

    public interface RequiredContent {
        OptionalMimeType mimeType(String mimeTypeName);

        EmailMessageV3Required build();
    }

    public interface OptionalMimeType {
        EmailMessageV3Required build();
    }

    private static class Builder implements RequiredFrom, RequiredTo, RequiredSubject, RequiredContent, OptionalMimeType {
        private final EmailMessageV3Required instance = new EmailMessageV3Required();

        public Builder(String from) {
            instance.from = from;
        }

        @Override
        public RequiredTo to(String to) {
            instance.to = to;
            return this;
        }

        @Override
        public RequiredSubject subject(String subject) {
            instance.subject = subject;
            return this;
        }

        @Override
        public RequiredContent content(String content) {
            instance.content = content;
            return this;
        }

        @Override
        public OptionalMimeType mimeType(String mimeTypeName) {
            instance.mimeType = mimeTypeName;
            return this;
        }

        @Override
        public EmailMessageV3Required build() {
            return instance;
        }
    }

    public static void main(String[] args) {
        EmailMessageV3Required email = EmailMessageV3Required.from("gnuhpc@foxmail.com")
                .to("tina437213@163.com")
                .subject("Hello World!")
                .content("Hello World!")
                .mimeType("MIME")
                .build();

        System.out.println(email);
    }
}
