package com.example.FarmMarket.DAO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

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

        @Column(unique = true, nullable = false)
        private String username;

        @Column(name = "password", nullable = false)
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password " +
                "must be minimum eight characters, including at least one letter and one number")
        private String password;

        @Column (name = "email")
        private String email;

        @Column (name = "phone_number")
        private String phone_number;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Override
        public String toString() {
            return last_name + " " + first_name;
        }


}
