/*
 * Copyright (C) 2024 [Edoardo Tombolesi]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.pyedoardo.netbeansrpc;

public class Arquivo {
    //Classe que serve de modelo pra o ícone e descrição de um tipo de arquivo.
    private String idImagem;
    private String descricao;

    public Arquivo(){
    }
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
