package com.prueba_tecnica.bci.cl.config.mapper.generic;


public interface IGenericMapper<D, B> {
	public D toDTO(B bo);
	default B fromDTO(D dto) {
		throw new UnsupportedOperationException("El método toDTO no está implementado.");
	};
}
