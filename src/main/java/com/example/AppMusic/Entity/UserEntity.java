package com.example.AppMusic.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.DateTimeUtils;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private boolean isActv = true;
    private String birthDate;
    private String nationalId;



    

}
