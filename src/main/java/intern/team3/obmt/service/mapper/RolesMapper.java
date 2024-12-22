package intern.team3.obmt.service.mapper;

import intern.team3.obmt.domain.Roles;
import intern.team3.obmt.service.dto.RolesDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring", uses = { PermissionsMapper.class })
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "idSet")
    RolesDTO toDto(Roles s);

    @Mapping(target = "removePermissions", ignore = true)
    Roles toEntity(RolesDTO rolesDTO);
}
