
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
