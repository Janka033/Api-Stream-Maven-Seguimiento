import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;
import mapping.Mapper.ToyTypeMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToyTypeMapperTest {

    @Test
    void testMapFrom() {
        ToyTypes toyTypes = new ToyTypes(1, "TestToy", ToyType.UNISEX, 9.99, 15);
        ToyTypeDto dto = ToyTypeMapper.mapFrom(toyTypes);
        assertEquals(toyTypes.getName(), dto.getName());
        assertEquals(toyTypes.getType(), dto.getType());
        assertEquals(toyTypes.getPrice(), dto.getPrice());
        assertEquals(toyTypes.getQuantityInStock(), dto.getQuantityInStock());
    }

    @Test
    void testMapFromDto() {
        ToyTypeDto dto = new ToyTypeDto(2, "AnotherToy", ToyType.FEMALE, 19.99, 7);
        ToyTypes toyTypes = ToyTypeMapper.mapFrom(dto);
        assertEquals(dto.getName(), toyTypes.getName());
        assertEquals(dto.getType(), toyTypes.getType());
        assertEquals(dto.getPrice(), toyTypes.getPrice());
        assertEquals(dto.getQuantityInStock(), toyTypes.getQuantityInStock());
    }

}

