package br.edu.ifsp.arq.exape_dw2.domain.resources;

import br.edu.ifsp.arq.exape_dw2.domain.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntryResource {

    private Long id;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate payDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal value;

    @JsonProperty("category")
    private CategoryResource category;

    private EntryTypeResource type;

    @JsonProperty("user")
    private UserResource userResource;

}
