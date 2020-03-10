package org.gnuhpc.interview.designpattern.builder.basicexample;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//这个是写法二，builder类里面有一个要创建实体类的成员变量，在添加属性时直接添加到此成员变量实例上
// 由于是内部类，因此，private变量可以直接访问，相对于第一种写法省去了成员变量再定义一遍的痛苦，非常方便
// build的时候可以写必填检查的逻辑（本例简单进行了assert）然后直接返回这个实体类的成员变量实例即可。
@Getter
@ToString
public class EmailMessageV2 {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;  // optional

    private EmailMessageV2() {
    }

    public static Builder builder() {
        return new EmailMessageV2.Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {
        private final EmailMessageV2 instance = new EmailMessageV2();

        public Builder from(String from) {
            instance.from = from;
            return this;
        }

        public Builder to(String to) {
            instance.to = to;
            return this;
        }

        public Builder subject(String subject) {
            instance.subject = subject;
            return this;
        }

        public Builder content(String content) {
            instance.content = content;
            return this;
        }

        public Builder mimeType(String mimeTypeName) {
            instance.mimeType = mimeTypeName;
            return this;
        }

        public EmailMessageV2 build() {
            assert instance.from != null;
            assert instance.to != null;
            assert instance.subject != null;
            assert instance.content != null;
            return instance;
        }
    }

    public static void main(String[] args) {
        EmailMessageV2 email = EmailMessageV2.builder()
                .to("dasdasd")
                .from("dsadad")
                .content("asddasdad")
                .mimeType("dsadasd").build();

        System.out.println(email);
    }
}
