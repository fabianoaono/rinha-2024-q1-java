package br.com.fabianoaono.rinha.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @JsonIgnore
    public Long id;
    public Integer limite;
    public Integer saldo;
}
