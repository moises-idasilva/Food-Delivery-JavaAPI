package com.moises.foodapp.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("foodapp.email")
public class EmailProperties {

    @NotNull
    private String remetente;

    private Sandbox sandbox = new Sandbox();

    // Atribuimos FAKE como padrão
    // Isso evita o problema de enviar e-mails de verdade caso você esqueça de definir a propriedade
    private Implementacao impl = Implementacao.FAKE;

    public enum Implementacao {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {

        private String destinatario;

    }

}
