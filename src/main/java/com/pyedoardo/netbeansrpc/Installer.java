/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/moduleInstall.java to edit this template
 */
package com.pyedoardo.netbeansrpc;

import java.io.IOException;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {
    Discord discord = new Discord();
    Logger log = new Logger();
    @Override
    public void restored() {
            log.inicarLog("Iniciando Módulo");
                try{
                    discord.iniciarRPC();
                }catch(IOException e){
                    System.err.println(e);
            };
    }
    @Override
    public boolean closing(){
        log.inicarLog("Encerrando Módulo");
        return true;
    }
}
