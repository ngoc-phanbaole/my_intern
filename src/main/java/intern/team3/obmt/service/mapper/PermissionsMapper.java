package intern.team3.obmt.service.mapper;

import intern.team3.obmt.domain.Permissions;
import intern.team3.obmt.service.dto.PermissionsDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Permissions} and its DTO {@link PermissionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PermissionsMapper extends EntityMapper<PermissionsDTO, Permissions> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<PermissionsDTO> toDtoIdSet(Set<Permissions> permissions);
}
