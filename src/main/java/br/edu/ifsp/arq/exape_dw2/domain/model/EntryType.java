package br.edu.ifsp.arq.exape_dw2.domain.model;

public enum EntryType {

    RECIPE("receita"), EXPENSE("despesa");

    private String type;

    private Long id;

    private EntryType(String type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
