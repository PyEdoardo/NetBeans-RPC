/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pyedoardo.netbeansrpc;
import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import de.jcm.discordgamesdk.activity.ActivityType;
import java.io.File;
import de.jcm.discordgamesdk.Core;
import java.io.IOException;
import java.time.Instant;
import javax.swing.JFrame;
/**
 *
 * @author edoar
 */
public class Discord {
    private Core core;

    public void iniciarRPC() throws IOException {
        Logger log = new Logger();
        
        try (CreateParams params = new CreateParams()) {
            params.setClientID(1280927336639889571L);
            params.setFlags(CreateParams.getDefaultFlags());

            core = new Core(params);

            new Thread(() -> {
                while (true) {
                    try {
                        NetBeans net = new NetBeans();
                        String arquivo = net.arquivoAberto();
                        String projeto = net.getProjetoAberto();
                        String extensaoArquivo = net.extensaoArquivo();
                        Arquivo imagemDescricao = net.tipoDeArquivoParaImagemDescricao.getOrDefault(extensaoArquivo, new Arquivo("ID_DEFAULT_IMAGE", "Descrição padrão"));
                        String idImagemLarge = imagemDescricao.getIdImagem();
                        String idImagemLargeDesc = imagemDescricao.getDescricao();
                        String tipo_projeto = net.tipoDeProjeto();
                        String idImagemSmall = net.tipoProjetoImg.get(tipo_projeto);
                        log.inicarLog("Projeto: " + projeto);
                        log.inicarLog("Arquivo: " + arquivo);

                        try (Activity activity = new Activity()) {
                            
                            activity.setDetails("Arquivo: " + arquivo + " ("+ NetBeans.linhaAtual()+"|"+NetBeans.linhasTotais()+")");
                            activity.setState("Projeto: " + projeto);
                            
                            activity.assets().setLargeImage(idImagemLarge);
                            activity.assets().setLargeText(idImagemLargeDesc);
                            
                            activity.assets().setSmallImage(idImagemSmall);
                            activity.assets().setSmallText("Gerenciador: " + net.tipoDeProjeto());

                            core.activityManager().updateActivity(activity);
                        }
                        core.runCallbacks();
                        Thread.sleep(2500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    } catch (Exception e) {
                        log.inicarLog("Erro ao atualizar atividade: " + e.getMessage());
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        } catch (Exception e) {
            log.inicarLog("Erro ao iniciar RPC: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopRPC() {
        if (core != null) {
            core.close();
        }
    }
}
