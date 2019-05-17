package com.lucascolaco.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucascolaco.curso.domain.PagamentoComBoleto;
import com.lucascolaco.curso.domain.PagamentoComCartao;

@Configuration // esse codigo é padrao, oq vai mudar eh as sub-classes que voce tem que resistrar
public class JacksonConfig { // essa classe possui um metodo que esta disponivel no sistema e vai ser configurada no inicio da executacao da aplicacao
	
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
