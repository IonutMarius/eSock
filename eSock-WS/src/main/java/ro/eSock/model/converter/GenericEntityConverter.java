package ro.esock.model.converter;

public interface GenericEntityConverter<DTO, ENTITY> {
	public DTO toDto(ENTITY entity);
	public ENTITY toEntity(DTO dto);
}
