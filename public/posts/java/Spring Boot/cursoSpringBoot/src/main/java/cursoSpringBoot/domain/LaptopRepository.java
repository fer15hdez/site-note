package cursoSpringBoot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<ElectronicDevices, Integer> {
}
