package br.edu.ifsp.arq.exape_dw2.controller;

import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryResource;
import br.edu.ifsp.arq.exape_dw2.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping
    public List<EntryResource> getAll() {
        return entryService.getAll();
    }

    @GetMapping("/{id}")
    public EntryResource getById(@PathVariable Long id) {
        return entryService.getById(id);
    }

    @PostMapping
    public EntryResource save(EntryResource resource) {
        return entryService.save(resource);
    }

    @PutMapping
    public EntryResource update(@PathVariable Long id, EntryResource resource) {
        return entryService.updateById(id, resource);
    }

    @PatchMapping
    public EntryResource change(@PathVariable Long id, EntryResource resource) {
        return entryService.changeById(id, resource);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        entryService.deleteById(id);
    }

}
