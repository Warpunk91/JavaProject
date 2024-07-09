package co.com.ps.c24a.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Person {
    @Id
    private Long id;
    private String name;

}
