package com.Auth.service.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    @NotEmpty(message = "This field Cannot be Empty")
    private String username;
    @NotEmpty(message = "This field Cannot be Empty")
    private String password;
    private Integer Score;
    @NotEmpty(message = "This field Cannot be Empty")
    private String role;
}
