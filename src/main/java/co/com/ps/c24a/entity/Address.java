package co.com.ps.c24a.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Address {

    @Id
    private Long id;
    private Long personId;
    private String city;

}
