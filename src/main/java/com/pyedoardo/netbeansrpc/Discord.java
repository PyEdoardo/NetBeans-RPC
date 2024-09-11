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

//Classe que faz o RPC (Remote procedure call) com o discord, passando os status da atividade e atualizando de acordo com o tempo em "Thread.sleep(TEMPO);"
public class Discord {
    private Core core;
    public void iniciarRPC() throws IOException {
        Logger log = new Logger();
        
        try (CreateParams params = new CreateParams()) {
            //Não mexa no ID, a não ser que queira criar com outro nome ou usar pra outro afim, lembre-se de trocar na classe NetBeans.java os nomes do ícones
            params.setClientID(1280927336639889571L);
            params.setFlags(CreateParams.getDefaultFlags());

            core = new Core(params);

            new Thread(() -> {
                while (true) {
                    try {
                        NetBeans net = new NetBeans();
                        //Isso pega o nome do arquivo pela classe NetBeans.class
                        Arquivo imagemDescricao = net.tipoDeArquivoParaImagemDescricao.getOrDefault(net.extensaoArquivo(), new Arquivo("ID_DEFAULT_IMAGE", "Descrição padrão"));
                                            
                        log.inicarLog("Projeto: " + net.getProjetoAberto());
                        log.inicarLog("Arquivo: " + net.arquivoAberto());

                        try (Activity activity = new Activity()) {
                            //Isso basicamente diz ao discord o campos de texto que ele consegue receber, porém por algum motivo o Enum ActivityType não funciona
                            // Isso abaixo
                            //activity.setType(ActivityType.CUSTOM); não funciona de jeito nenhum, ele até para de soltar a log :(
                            activity.setDetails("Arquivo: " + net.arquivoAberto() + " ("+ NetBeans.linhaAtual()+"|"+NetBeans.linhasTotais()+")");
                            activity.setState("Projeto: " + net.getProjetoAberto());
                            
                            activity.assets().setLargeImage(imagemDescricao.getIdImagem());
                            activity.assets().setLargeText(imagemDescricao.getDescricao());
                            
                            activity.assets().setSmallImage(net.tipoProjetoImg.get(net.tipoDeProjeto()));
                            activity.assets().setSmallText("Gerenciador: " + net.tipoDeProjeto());

                            core.activityManager().updateActivity(activity);
                        }
                        //Esse método força a troca de campos de texto e atualiza com oq recebeu no try.
                        core.runCallbacks();
                        //Esse método faz com que a thread rode a cada 1500ms, o suficiente pra não quebrar os métodos de procurar as ext dos arquivos;
                        //Pode deixar abaixo disso, porém pode quebrar o módulo.
                        Thread.sleep(1500);

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
            //Pra tratar excessões e avisar na log, mas por algum motivo não aparece KKKKKKKKKKKKKKKKKKKKK
            log.inicarLog("Erro ao iniciar RPC: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopRPC() {
        //Método pra desligar o RPC, feito pra quando o netbeans fechar, ele parar com a comunicação.
        if (core != null) {
            core.close();
        }
    }
}
