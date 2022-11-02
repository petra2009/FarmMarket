package com.example.FarmMarket.DAO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

        @Column(name = "last_name", nullable = false, length = 45)
        private String last_name;

        @Column(name = "first_name", nullable = false, length = 45)
        private String first_name;
/*

        @OneToMany(mappedBy = "id")
        private List<Product> products;
*/

        @Column(unique = true, nullable = false)
        private String username;

        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Override
        public String toString() {
            return last_name + " " + first_name;
        }


}
