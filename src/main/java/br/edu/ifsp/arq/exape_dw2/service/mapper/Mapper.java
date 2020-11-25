package br.edu.ifsp.arq.exape_dw2.service.mapper;

public interface Mapper<T, K> {

   T toModel(K resource);

   K toResource(T model);

}
