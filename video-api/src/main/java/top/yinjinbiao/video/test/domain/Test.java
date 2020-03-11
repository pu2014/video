package top.yinjinbiao.video.test.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String sex;

}