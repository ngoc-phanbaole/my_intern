package intern.team3.obmt.service.mapper;

import intern.team3.obmt.domain.AppUser;
import intern.team3.obmt.service.dto.AppUserDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppUser} and its DTO {@link AppUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { RolesMapper.class })
public interface AppUserMapper extends EntityMapper<AppUserDTO, AppUser> {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "idSet")
    AppUserDTO toDto(AppUser s);

    @Mapping(target = "removeRoles", ignore = true)
    AppUser toEntity(AppUserDTO appUserDTO);
}
