package br.edu.ifsp.arq.exape_dw2.service.mapper;

import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryResource;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper implements Mapper<Entry, EntryResource> {
    @Override
    public Entry toModel(EntryResource resource) {
        return Entry.builder()
                .description(resource.getDescription())
                .dueDate(resource.getDueDate())
                .payDay(resource.getPayDay())
                .value(resource.getValue())
                .category(resource.getCategory())
                .type(resource.getType())
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
                .category(model.getCategory())
                .type(model.getType())
                .user(model.getUser())
                .build();
    }
}
