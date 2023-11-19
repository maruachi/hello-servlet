package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member() {
    }

    public Member(String uesrname, int age) {
        this.username = uesrname;
        this.age = age;
    }
}
