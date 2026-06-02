# Lombok y MapStruct

## pom.xml
```xml
    <properties>
    <!-- Otras propeties -->
		<org.mapstruct.version>1.6.3</org.mapstruct.version>
    <!-- Otras propeties -->
	</properties>

    <dependencies>
    ...
    <!-- otras dependencias -->
        <dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>${org.mapstruct.version}</version>
		</dependency>
    <!-- Otras dependiencias -->
    </dependencies>

    <build>
    <!-- Otros plugins -->
        <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.mapstruct</groupId>
							<artifactId>mapstruct-processor</artifactId>
							<version>1.6.3</version>
						</path>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
							<version>1.18.32</version>
						</path>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok-mapstruct-binding</artifactId>
							<version>0.2.0</version>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
            <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
            <!-- Otros plugins -->
    </build>
```

## Class abstracta o Interface
Se crea una clase abstracta o una interface. Yo la cree en la carpeta service

```java
import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring") // Para que Spring lo inyecte como un bean
public abstract class RoomMapperStruct {
    @Autowired
    protected RoomTypeRepository roomTypeRepository;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDto(RoomDTO dto, @MappingTarget Room entity);

    @Mapping(source = "type", target = "type") // Mapea el campo 'type' del DTO al 'type' de la entidad
    @Mapping(target = "id", ignore = true) // Generalmente el ID se genera automáticamente para una nueva entidad
    @Mapping(target = "bookings", ignore = true) // Ignora bookings si no se mapea directamente desde DTO
    public abstract Room toRoom(RoomDTO roomDTO);

    // Este metodo se crea  porque el RoomDTO esta tiene el tipo de "type" como Integer y la entidad Room tiene como tipo de datos 
    // RoomType en el campo "type"
    // Méthod de mapeo personalizado para convertir Integer a RoomType
    public RoomType map(Integer typeId) {
        if (typeId == null) {
            return null; // O lanza una excepción si el ID de tipo es obligatorio
        }
        // Busca el RoomType por su ID. Si no lo encuentra, puedes lanzar una excepción.
        return roomTypeRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("RoomType with ID " + typeId + " not found"));
    }
}
```

## Service

```java
    @Service
    public class RoomService {
        private final RoomMapperStruct roomMapperStruct;
        
        public RoomService(RoomRepository repository, RoomTypeRepository roomTypeRepository, RoomMapper mapper, RoomMapperStruct roomMapperStruct) {
            this.repository = repository;
            this.roomTypeRepository = roomTypeRepository;
            this.mapper = mapper;
            this.roomMapperStruct = roomMapperStruct;
    }

        // Otros metodos

    public RoomResponseDTO updateRoom(RoomDTO roomDTO, Integer id){
            Room roomDB = this.repository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Entity not found")
            );

            // Llamamos al "updateEntityFromDto" de "RoomMapperStruct" para actualizar la entidad en la base de datos con solo
            // los campos que tienen valor. Los campos que tienen valor null(los que envia el cliente)  
            // se le asignan los valores que estan en la bd, asi no se sobre escriben los valores que no se 
            // quieren modificar con el valor null de los campos vacios (los que envia el cliente). 
            roomMapperStruct.updateEntityFromDto(roomDTO, roomDB);

            return  this.mapper.toRoomResponseDTO(this.repository.save(roomDB));
        }

        // Otros metodos
    }
```