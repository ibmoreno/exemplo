package br.com.exemplo.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);

    void updateFromDTO(UserDTO dto, @MappingTarget User entity);

    Credential toEntity(CredentialDTO dto);

}
