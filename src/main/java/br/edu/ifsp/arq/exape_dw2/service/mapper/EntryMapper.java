package br.edu.ifsp.arq.exape_dw2.service.mapper;

import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import br.edu.ifsp.arq.exape_dw2.domain.resources.CategoryResource;
import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryResource;
import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryTypeResource;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper implements Mapper<Entry, EntryResource> {
    @Override
    public Entry toModel(EntryResource resource) {
        EntryType type = EntryType.valueOf(resource.getType().getType());
        type.setId(resource.getType().getId());
        return Entry.builder()
                .description(resource.getDescription())
                .dueDate(resource.getDueDate())
                .payDay(resource.getPayDay())
                .value(resource.getValue())
                .category(Category.builder()
                        .id(resource.getCategory().getId())
                        .name(resource.getCategory().getName())
                        .build())
                .type(type)
                .user(resource.getUser())
                .build();
    }

    @Override
    public EntryResource toResource(Entry model) {
        return EntryResource.builder()
                .id(model.getId())
                .description(model.getDescription())
                .dueDate(model.getDueDate())
                .payDay(model.getPayDay())
                .value(model.getValue())
                .category(CategoryResource.builder()
                        .id(model.getCategory().getId())
                        .name(model.getCategory().getName())
                        .build())
                .type(EntryTypeResource.builder()
                        .type(model.getType().name())
                        .id(model.getType().getId())
                        .build()
                )
                .user(model.getUser())
                .build();
    }
}
