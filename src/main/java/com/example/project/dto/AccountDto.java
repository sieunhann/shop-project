package com.example.project.dto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AccountDto(Long id, String name, String email, String phone, String address, String city, String roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        if (roles != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.roles = mapper.readValue((String) roles, new TypeReference<List<String>>(){});
            } catch (IOException e) {
                this.roles = null;
            }
        }
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}
