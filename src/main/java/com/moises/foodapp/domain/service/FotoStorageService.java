package com.moises.foodapp.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }

    }

    default String gerarNomeArquivo(String nomeOriginal) {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy-hhmmss");

        String initialFileName = dateTimeFormatter.format(localDateTime);

        return initialFileName + "_" + nomeOriginal;

    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
        private Long size;

    }

    @Builder
    @Getter
    class FotoRecuperada {

        private InputStream inputStream;
        private String url;

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != null;
        }

    }

}
