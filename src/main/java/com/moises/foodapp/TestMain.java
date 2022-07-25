package com.moises.foodapp;

import java.util.UUID;

public class TestMain {

    public static void main(String[] args) {


        String clientCodigoId = gerarCodigo();

        System.out.println("clientCodigoId: " + clientCodigoId);

    }

    public static String gerarCodigo() {

        String codigo = UUID.randomUUID().toString();

        // System.out.println("Codigo Original: " + codigo);

        StringBuilder novoCodigoId = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            String tempChar = String.valueOf(codigo.charAt(i));

            if (tempChar.equalsIgnoreCase("o")) {
                tempChar = "w";

            } else if (tempChar.equalsIgnoreCase("0")) {
                tempChar = "m";
            }

            novoCodigoId.append(tempChar);
        }

        return novoCodigoId.toString().toUpperCase();

    }

}
