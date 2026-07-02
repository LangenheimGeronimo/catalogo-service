package com.example.catalogoservice.repository;

import com.example.catalogoservice.model.entity.Producto;
import com.example.catalogoservice.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

    // Se apoya en el índice sobre 'categoria_id' definido en la entidad.
    List<Producto> findByCategoriaId(Long categoriaId);

    boolean existsByNombre(String nombre);

    // Se apoya en el índice sobre 'state' definido en la entidad.
    List<Producto> findByState(State state);
}
