package br.edu.ifsp.arq.exape_dw2.service;

import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryResource;
import br.edu.ifsp.arq.exape_dw2.repository.EntryRepository;
import br.edu.ifsp.arq.exape_dw2.service.mapper.EntryMapper;
import br.edu.ifsp.arq.exape_dw2.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private Mapper<Entry, EntryResource> entryMapper;

    public List<EntryResource> getAll() {
        List<Entry> list = entryRepository.findAll();
        if(list != null) {
            return list.stream()
                    .map(entryMapper::toResource)
                    .collect(Collectors.toList());
        }
        return Arrays.asList();
    }

    public EntryResource getById(Long id) {
        return entryMapper.toResource(entryRepository.findById(id));
    }

    public EntryResource save(EntryResource resource) {
        return entryMapper.toResource(entryRepository.save(entryMapper.toModel(resource)));
    }

    public EntryResource updateById(Long id, EntryResource resource) {
        return null;
    }

    public EntryResource changeById(Long id, EntryResource resource) {
        return null;
    }

    public void deleteById(Long id) {

    }

    private void validate(EntryResource resource) {

    }

}
