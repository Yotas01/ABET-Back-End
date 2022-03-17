package edu.javeriana.abetbackend.Entities.DTOs;

import com.google.common.base.Objects;

public class NameDTO {
    private String name;

    public NameDTO(String name) {
        this.name = name;
    }

    public NameDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameDTO nameDTO = (NameDTO) o;
        return Objects.equal(name, nameDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
