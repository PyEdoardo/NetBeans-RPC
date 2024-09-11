/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pyedoardo.netbeansrpc;

import java.util.HashMap;
import java.util.Map;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.cookies.EditorCookie;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.loaders.DataObject;
import org.openide.filesystems.FileObject;


/**
 *
 * @author edoar
 */
public class NetBeans {
    Logger log = new Logger();
    public String arquivoAberto(){
        TopComponent componente = WindowManager.getDefault().getRegistry().getActivated();
        if(componente != null){
            DataObject objeto = componente.getLookup().lookup(DataObject.class);
            if (objeto != null){
                FileObject arquivo = objeto.getPrimaryFile();
                log.inicarLog(arquivo.getNameExt());
                String retorno = arquivo.getNameExt();
                return retorno;
            }
        }
        return "Em repouso";
    }
    public String getProjetoAberto() {
        TopComponent activeComponent = WindowManager.getDefault().getRegistry().getActivated();
        if (activeComponent != null) {
            DataObject dataObject = activeComponent.getLookup().lookup(DataObject.class);
            if (dataObject != null) {
                FileObject file = dataObject.getPrimaryFile();
                Project project = FileOwnerQuery.getOwner(file);
                if (project != null) {
                    return project.getProjectDirectory().getName();
                }
            }
        }
        return "Nenhum projeto aberto";
    }
    public Map<String, Arquivo> tipoDeArquivoParaImagemDescricao = new HashMap<>();

{
    tipoDeArquivoParaImagemDescricao.put("java", new Arquivo("iconjava", "Classe Java"));
    tipoDeArquivoParaImagemDescricao.put("xml", new Arquivo("iconxml", "Arquivo XML"));
    tipoDeArquivoParaImagemDescricao.put("txt", new Arquivo("icontxt", "Arquivo de Texto"));
    tipoDeArquivoParaImagemDescricao.put("json", new Arquivo("iconjson", "Dicionário JSON"));
    tipoDeArquivoParaImagemDescricao.put("yml", new Arquivo("iconyml", "Arquivo YML"));
    tipoDeArquivoParaImagemDescricao.put("proprieties", new Arquivo("iconproprieties", "Arquivo de Propriedades"));
    tipoDeArquivoParaImagemDescricao.put("md", new Arquivo("iconmd", "Arquivo de Markdown"));
    tipoDeArquivoParaImagemDescricao.put("kt", new Arquivo("iconkt", "Código Kotlin"));
    tipoDeArquivoParaImagemDescricao.put("default", new Arquivo("logo", "Nenhum arquivo aberto"));
}
    public String extensaoArquivo() {
    TopComponent componente = WindowManager.getDefault().getRegistry().getActivated();
    if (componente != null) {
        DataObject objeto = componente.getLookup().lookup(DataObject.class);
        if (objeto != null) {
            FileObject arquivo = objeto.getPrimaryFile();
            String extensao = arquivo.getExt(); 
            log.inicarLog(extensao);
            return extensao;
        }
    }
    return "default";
    }
    public String tipoDeProjeto() {
        TopComponent componente = WindowManager.getDefault().getRegistry().getActivated();
        if (componente != null) {
            DataObject objeto = componente.getLookup().lookup(DataObject.class);
            if (objeto != null) {
                FileObject arquivo = objeto.getPrimaryFile();
                FileObject pastaAtual = arquivo.getParent();

                while (pastaAtual != null) {
                    if (pastaAtual.getFileObject("pom.xml") != null) {
                        return "Maven";
                    }
                    if (pastaAtual.getFileObject("build.gradle") != null || pastaAtual.getFileObject("build.gradle.kts") != null || pastaAtual.getFileObject("gradlew.bat") != null) {
                        return "Gradle";
                    }
                    if (pastaAtual.getFileObject("build.xml") != null){
                        return "Ant";
                    }
                    pastaAtual = pastaAtual.getParent(); 
                }
            }
        }
        return "";
    }
    public Map<String, String> tipoProjetoImg = new HashMap<>();
    
    {
        tipoProjetoImg.put("Maven", "iconmaven");
        tipoProjetoImg.put("Gradle", "icongradle");
        tipoProjetoImg.put("Ant", "iconant");
    }
    public static int linhaAtual() {
        TopComponent tc = WindowManager.getDefault().getRegistry().getActivated();
        if (tc != null) {
            EditorCookie editorCookie = tc.getLookup().lookup(EditorCookie.class);
            if (editorCookie != null) {
                JTextComponent textComponent = editorCookie.getOpenedPanes()[0];
                if (textComponent != null) {
                    Caret caret = textComponent.getCaret();
                    if (caret != null) {
                        int caretPosition = caret.getDot();
                        try {
                            Document doc = textComponent.getDocument();
                            Element rootElement = doc.getDefaultRootElement();
                            int line = rootElement.getElementIndex(caretPosition) + 1; // +1 para linha baseada em 1
                            return line;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return 0;
    }
    public static int linhasTotais() {
        TopComponent tc = WindowManager.getDefault().getRegistry().getActivated();
        if (tc != null) {
            EditorCookie editorCookie = tc.getLookup().lookup(EditorCookie.class);
            if (editorCookie != null) {
                JTextComponent textComponent = editorCookie.getOpenedPanes()[0];
                if (textComponent != null) {
                    Document doc = textComponent.getDocument();
                    if (doc != null) {
                        Element rootElement = doc.getDefaultRootElement();
                        return rootElement.getElementCount();
                    }
                }
            }
        }
        return 0; 
    }
}
