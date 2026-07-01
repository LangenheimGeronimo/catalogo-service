package com.example.catalogoservice.model.entity;

import com.example.catalogoservice.model.enums.State;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "categorias", indexes = {
    @Index(name = "idx_categoria_nombre", columnList = "nombre")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE categorias SET state = 'DELETED' WHERE id = ?")
@SQLRestriction("state != 'DELETED'")
public class Categoria extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = true, length = 255)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private State state = State.ACTIVE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return id != null && id.equals(categoria.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}