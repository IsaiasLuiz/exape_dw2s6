package br.edu.ifsp.arq.exape_dw2.controller;

import br.edu.ifsp.arq.exape_dw2.domain.resources.CategoryResource;
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
    public EntryResource save(@RequestBody EntryResource resource) {
        return entryService.save(resource);
    }

    @PatchMapping("/{id}")
    public EntryResource change(@PathVariable Long id, @RequestBody EntryResource resource) {
        return entryService.changeById(id, resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entryService.deleteById(id);
    }

}
