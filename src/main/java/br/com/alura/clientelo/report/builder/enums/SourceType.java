package br.com.alura.clientelo.report.builder.enums;

public enum SourceType {
    JSON(".json"), CSV(".csv");

    private final String fileExtension;

    SourceType(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
