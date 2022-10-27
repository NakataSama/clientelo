package br.com.alura.clientelo.dataprocessor;

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
