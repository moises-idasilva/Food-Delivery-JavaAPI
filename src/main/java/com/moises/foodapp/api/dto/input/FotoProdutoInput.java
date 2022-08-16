package com.moises.foodapp.api.dto.input;

import com.moises.foodapp.core.validation.FileContentType;
import com.moises.foodapp.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max = "5MB")
    @FileContentType( allowed = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;

}
