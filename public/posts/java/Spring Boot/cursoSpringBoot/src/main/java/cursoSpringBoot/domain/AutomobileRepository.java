package cursoSpringBoot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
}
