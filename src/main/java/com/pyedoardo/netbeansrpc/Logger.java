/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pyedoardo.netbeansrpc;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author edoar
 */
public class Logger {
    private String data;
    private String horario;
    
    public void inicarLog(String texto){
        data = String.valueOf(LocalDate.now());
        horario = String.valueOf(LocalTime.now());
        
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
