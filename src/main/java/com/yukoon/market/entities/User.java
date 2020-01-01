package com.yukoon.market.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id,String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Getter
    @Setter
    private Integer id;

    @Column(name = "USERNAME")
    @Getter
    @Setter
    private String username;

    @Column(name = "PASSOWRD")
    @Getter
    @Setter
    private String password;

    @JoinColumn(name = "ROLE_ID")
    @OneToOne
    @Getter
    @Setter
    private Role role;
}
