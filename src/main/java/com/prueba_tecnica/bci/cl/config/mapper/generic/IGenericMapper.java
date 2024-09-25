package com.prueba_tecnica.bci.cl.config.mapper.generic;


/**
 * Interfaz genérica para el mapeo entre objetos de transferencia de datos (DTO) y
 * objetos de negocio (BO).
 *
 * <p>
 * Esta interfaz define métodos para convertir entre dos tipos de objetos, permitiendo la
 * implementación de lógica de mapeo específica en clases concretas. La conversión de
 * DTO a BO no está soportada por defecto y debe ser implementada en las clases que
 * extiendan esta interfaz si es necesario.
 * </p>
 *
 * @param <D> El tipo de objeto de transferencia de datos (DTO).
 * @param <B> El tipo de objeto de negocio (BO).
 *
 * @version 1.0
 * @since 25/09/2024
 * @author itocto
 */
public interface IGenericMapper<D, B> {

	/**
	 * Convierte un objeto de negocio (BO) a un objeto de transferencia de datos (DTO).
	 *
	 * @param bo El objeto de negocio a convertir.
	 * @return El objeto de transferencia de datos correspondiente.
	 */
	public D toDTO(B bo);

	/**
	 * Convierte un objeto de transferencia de datos (DTO) a un objeto de negocio (BO).
	 *
	 * <p>
	 * Este método lanza una excepción {@link UnsupportedOperationException} de manera
	 * predeterminada, indicando que la conversión no está soportada. Las implementaciones
	 * de la interfaz pueden optar por sobreescribir este método si se requiere la
	 * conversión en la dirección opuesta.
	 * </p>
	 *
	 * @param dto El objeto de transferencia de datos a convertir.
	 * @return El objeto de negocio correspondiente.
	 * @throws UnsupportedOperationException si no se implementa la conversión.
	 */
	default B fromDTO(D dto) {
		throw new UnsupportedOperationException("El método toDTO no está implementado.");
	};
}
