package ro.esock.model.converter;

public abstract class AbstractEntityConverter<DTO, ENTITY> {
	abstract public DTO toDto(ENTITY entity);
	abstract public ENTITY toEntity(DTO dto);
}
