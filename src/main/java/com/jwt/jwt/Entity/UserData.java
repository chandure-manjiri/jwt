package com.jwt.jwt.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_data")
@Data
public class UserData implements Serializable, GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 5926468583005150707L;

    @Id
    @GeneratedValue
    private Integer id;
    private String username = "";
    private String password = "";
    private String role = "";
    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public String toString() {
        return "AuthData{" +
                "Id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
