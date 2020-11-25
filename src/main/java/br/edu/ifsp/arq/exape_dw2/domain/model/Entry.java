package br.edu.ifsp.arq.exape_dw2.domain.model;

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
public class Entry {

    private Long id;

    private String description;

    private LocalDate dueDate;

    private LocalDate payDay;

    private BigDecimal value;

    private Category category;

    private EntryType type;

    private User user;

}
