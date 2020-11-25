package br.edu.ifsp.arq.exape_dw2.domain.resources;

import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import br.edu.ifsp.arq.exape_dw2.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryResource {

    private Long id;

    private String description;

    private LocalDate dueDate;

    private LocalDate payDay;

    private BigDecimal value;

    private Category category;

    private EntryType type;

    private User user;

}
