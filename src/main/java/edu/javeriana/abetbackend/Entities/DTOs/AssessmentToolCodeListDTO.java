package edu.javeriana.abetbackend.Entities.DTOs;

import java.util.List;

public class AssessmentToolCodeListDTO {

    private List<String> codes;

    public AssessmentToolCodeListDTO(List<String> codes) {
        this.codes = codes;
    }

    public AssessmentToolCodeListDTO() {
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public void addCode(String code){
        this.codes.add(code);
    }

    public void removeCode(String code){
        this.codes.remove(code);
    }
}
