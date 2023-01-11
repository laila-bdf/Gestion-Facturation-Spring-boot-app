package com.cigi.facturation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor@NoArgsConstructor
public class FournProId implements Serializable {

    private long fournId;
    private long prodId;
}
