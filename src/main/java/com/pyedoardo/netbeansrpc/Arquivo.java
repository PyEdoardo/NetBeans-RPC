/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pyedoardo.netbeansrpc;

/**
 *
 * @author edoar
 */
public class Arquivo {
    private final String idImagem;
    private final String descricao;

    public Arquivo(String idImagem, String descricao) {
        this.idImagem = idImagem;
        this.descricao = descricao;
    }

    public String getIdImagem() {
        return idImagem;
    }

    public String getDescricao() {
        return descricao;
    }
}
