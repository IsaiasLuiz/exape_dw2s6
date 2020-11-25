package br.edu.ifsp.arq.exape_dw2.service;

import br.edu.ifsp.arq.exape_dw2.domain.exception.BadRequestException;
import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.resources.EntryResource;
import br.edu.ifsp.arq.exape_dw2.repository.EntryRepository;
import br.edu.ifsp.arq.exape_dw2.service.mapper.EntryMapper;
import br.edu.ifsp.arq.exape_dw2.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
        try {
            return entryMapper.toResource(entryRepository.save(entryMapper.toModel(resource)));
        } catch (Exception e) {
             throw new BadRequestException("Verifique seus Dados e tente novamente", e);
        }
    }

    public EntryResource changeById(Long id, EntryResource resource) {
        try {
            return entryMapper.toResource(entryRepository.update(id, entryMapper.toModel(resource)));
        } catch (SQLException e) {
            throw new BadRequestException("Verifique seus Dados e tente novamente", e);
        } catch (NullPointerException e) {
            throw new BadRequestException("Verifique seus Dados e tente novamente", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new BadRequestException("Verifique seus Dados e tente novamente", e);
        }
    }

    public void deleteById(Long id) {
        try {
            entryRepository.delete(id);
        } catch (SQLException e) {
            throw new BadRequestException("Verifique seus Dados e tente novamente", e);
        }
    }

}
