package br.edu.ifsp.arq.exape_dw2.domain.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResource {

    private Long id;

    private String name;

}
