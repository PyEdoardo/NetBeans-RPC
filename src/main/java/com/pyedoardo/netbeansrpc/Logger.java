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

import java.time.LocalDate;
import java.time.LocalTime;

public class Logger {
    private String data;
    private String horario;
    //Extremamente orientado a objetos, Steve jobs teria até inveja.
    public void inicarLog(String texto){
        data = String.valueOf(LocalDate.now());
        horario = String.valueOf(LocalTime.now());
        //Pra que regex se tem esse tipo de gambiarra?
        String retorno = "[NetBeans-RPC] "
                                +
                                "["
                                +
                                data
                                +
                                "] "
                                +
                                "["
                                +
                                horario
                                +
                                "] "
                                +
                                "["
                                +
                                texto
                                +
                                "]";
        System.out.println(retorno);
    }
}
