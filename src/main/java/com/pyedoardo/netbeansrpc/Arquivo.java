
package com.pyedoardo.netbeansrpc;

/**
 *
 * @author edoar
 */
public class Arquivo {
    //Classe que serve de modelo pra o ícone e descrição de um tipo de arquivo.
    private final String idImagem;
    private final String descricao;

    public Arquivo(String idImagem, String descricao) {
        this.idImagem = idImagem;
        this.descricao = descricao;
    }
    //Exatamente não existe nenhum setter na classe, pois apenas recebe pelo construtor, e po, essa classe não faz NADA, apenas um modelo........
    public String getIdImagem() {
        return idImagem;
    }

    public String getDescricao() {
        return descricao;
    }
    //Puramente pra debug, tão útil quanto político no brasil.
    @Override
    public String toString(){
        return idImagem + descricao;
    }
}
