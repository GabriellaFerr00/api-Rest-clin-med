package clin.med.apiRestclinmed.repositories;

import clin.med.apiRestclinmed.entities.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);
}
