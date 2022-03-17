package edu.javeriana.abetbackend.Entities.DTOs;

import com.google.common.base.Objects;

public class ValueDTO {
    private Integer value;

    public ValueDTO(Integer value) {
        this.value = value;
    }

    public ValueDTO() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueDTO valueDTO = (ValueDTO) o;
        return Objects.equal(value, valueDTO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
