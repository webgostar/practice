package com.snap.practice.contact;

import com.snap.practice.contact.models.ContactDTO;
import com.snap.practice.contact.models.ContactEntity;
import com.snap.practice.contact.models.ContactSearchDTO;
import com.snap.practice.contact.models.RepositoryEntity;
import com.snap.practice.github.RepositoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    ContactEntity toEntity(ContactDTO contactDTO);
    @Mapping(target = "id", ignore = true)
    ContactEntity toEntity(ContactDTO contactDTO, List<RepositoryEntity> githubRepositories);
    ContactSearchDTO toSearchDTO(ContactDTO contactDTO);
    ContactEntity toEntity(ContactSearchDTO contactSearchDTO);
    ContactDTO toDTO(ContactEntity contactEntity);
    List<ContactDTO> toDTO(List<ContactEntity> contactEntities);
}
