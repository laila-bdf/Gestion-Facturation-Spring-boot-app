package com.cigi.facturation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@Data
@Table(name = "loginUser")
@NoArgsConstructor
public class User {
    @Id
    private  String userId;
    private String password;
}
