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

import java.io.IOException;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {
    Discord discord = new Discord();
    Logger log = new Logger();
    @Override
    public void restored() {
        //Netbeans......pq vc não mostra os logs em view ide logs? pq........
        //Vou deixar o log só de maldade e pesar mais o módulo.
            log.inicarLog("Iniciando Módulo");
                try{
                    discord.iniciarRPC();
                }catch(IOException e){
                    System.err.println(e);
            };
    }
    //Espero que funcione 🤡
    @Override
    public boolean closing(){
        log.inicarLog("Encerrando Módulo");
        discord.stopRPC();
        return true;
    }
}
