package intern.team3.obmt.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PermissionsMapperTest {

    private PermissionsMapper permissionsMapper;

    @BeforeEach
    public void setUp() {
        permissionsMapper = new PermissionsMapperImpl();
    }
}
