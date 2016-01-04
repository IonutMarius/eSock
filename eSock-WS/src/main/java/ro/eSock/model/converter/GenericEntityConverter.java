package ro.esock.model.converter;

public abstract class GenericEntityConverter<DTO, ENTITY> {
	abstract public DTO toDto(ENTITY entity);
	abstract public ENTITY toEntity(DTO dto);
}
