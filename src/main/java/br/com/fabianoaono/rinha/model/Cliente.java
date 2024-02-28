package br.com.fabianoaono.rinha.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente extends PanacheEntity {

    public Integer limite;
    public Integer saldo;
}
