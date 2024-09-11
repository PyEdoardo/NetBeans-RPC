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
import java.io.IOException;
import org.openide.loaders.DataObjectNotFoundException;

//Maior parte feita com a ajuda do mano chatgpt pq o site da apache é o maior lixo que vi na minha vida e a documentação é triste....
//Os métodos que fazem pesquisa por arquivos são os mais lentos, recomendo não mexer com eles e nem diminuir a velocidade da Thread do discord.
public class NetBeans {
    Logger log = new Logger();
    public String arquivoAberto() throws IOException {
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
    public String getProjetoAberto() throws IOException {
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
    //Esse Map guarda a extensão de um arquivo, e o relaciona com um ícone e uma descrição.
    tipoDeArquivoParaImagemDescricao.put("java", new Arquivo("iconjava", "Classe Java"));
    tipoDeArquivoParaImagemDescricao.put("xml", new Arquivo("iconxml", "Arquivo XML"));
    tipoDeArquivoParaImagemDescricao.put("txt", new Arquivo("icontxt", "Arquivo de Texto"));
    tipoDeArquivoParaImagemDescricao.put("json", new Arquivo("iconjson", "Dicionário JSON"));
    tipoDeArquivoParaImagemDescricao.put("yml", new Arquivo("iconyml", "Arquivo YML"));
    tipoDeArquivoParaImagemDescricao.put("proprieties", new Arquivo("iconproprieties", "Arquivo de Propriedades"));
    tipoDeArquivoParaImagemDescricao.put("md", new Arquivo("iconmd", "Arquivo de Markdown"));
    tipoDeArquivoParaImagemDescricao.put("kt", new Arquivo("iconkt", "Código Kotlin"));
    tipoDeArquivoParaImagemDescricao.put("default", new Arquivo("iconjava", "Nenhum arquivo aberto"));
}
    public String extensaoArquivo() {
        //Obviamente pega a extensão do arquivo que está sendo editado..
        //Feito com tratamento de excessão, pois é provavelmente o método que mais me dá dor de cabeça xdddddddddddd
        TopComponent componente = WindowManager.getDefault().getRegistry().getActivated();
        if (componente != null) {
            DataObject objeto = componente.getLookup().lookup(DataObject.class);
            if (objeto != null) {
                FileObject arquivo = objeto.getPrimaryFile();
                if (arquivo != null) {
                    String extensao = arquivo.getExt(); 
                    try {
                        log.inicarLog(extensao);
                    } catch (Exception e) {
                        System.err.println("Erro ao registrar log: " + e.getMessage());
                    }
                    if (tipoDeArquivoParaImagemDescricao.containsKey(extensao)){
                        return extensao;
                    }
                    else{
                        return "default";
                    }
                } else {
                    System.err.println("Erro: arquivo é null.");
                }
            } else {
                System.err.println("Erro: DataObject é null.");
            }
        } else {
            System.err.println("Erro: TopComponent é null.");
        }
        return "default";
    }
    //Esse método procura algum arquivo de gerenciamento de projetos como o pom.xml do maven ou o gradlew pra o gradle, ou o build.xml do ant
    //Pra que? não sei, só é bonitinho mesmo.
    public String tipoDeProjeto() {
        TopComponent componente = WindowManager.getDefault().getRegistry().getActivated();
        if (componente != null) {
            DataObject objeto = componente.getLookup().lookup(DataObject.class);
            if (objeto != null) {
                FileObject arquivo = objeto.getPrimaryFile();
                if (arquivo == null) {
                    System.err.println("Erro: arquivo é null.");
                    return "";
                }

                FileObject pastaAtual = arquivo.getParent();
                if (pastaAtual == null) {
                    System.err.println("Erro: pastaAtual é null.");
                    return "";
                }

                while (pastaAtual != null) {
                    if (pastaAtual.getFileObject("pom.xml") != null) {
                        return "Maven";
                    }
                    if (pastaAtual.getFileObject("build.gradle") != null ||
                        pastaAtual.getFileObject("build.gradle.kts") != null ||
                        pastaAtual.getFileObject("gradlew.bat") != null) {
                        return "Gradle";
                    }
                    if (pastaAtual.getFileObject("build.xml") != null) {
                        return "Ant";
                    }
                    pastaAtual = pastaAtual.getParent(); 
                }
            } else {
                System.err.println("Erro: DataObject é null.");
            }
        } else {
            System.err.println("Erro: TopComponent é null.");
        }
        return "";
    }
    public Map<String, String> tipoProjetoImg = new HashMap<>();
    
    {
        tipoProjetoImg.put("Maven", "iconmaven");
        tipoProjetoImg.put("Gradle", "icongradle");
        tipoProjetoImg.put("Ant", "iconant");
    }
    
    //Método MAIS BUGADO que tem, método radiativo e perigoso!
    //Tive que adicionar esse monte de tratamento de excessões que eu nem sabia que existiam, é de fato o método mais problemático, apagar da terra.
    public static int linhaAtual() {
    TopComponent tc = WindowManager.getDefault().getRegistry().getActivated();
    if (tc != null) {
        EditorCookie editorCookie = tc.getLookup().lookup(EditorCookie.class);
        if (editorCookie != null) {
            JTextComponent[] openedPanes = editorCookie.getOpenedPanes();
            if (openedPanes != null && openedPanes.length > 0) {
                JTextComponent textComponent = openedPanes[0];
                if (textComponent != null) {
                    Caret caret = textComponent.getCaret();
                    if (caret != null) {
                        int caretPosition = caret.getDot();
                        try {
                            Document doc = textComponent.getDocument();
                            if (doc != null) {
                                Element rootElement = doc.getDefaultRootElement();
                                if (rootElement != null) {
                                    int line = rootElement.getElementIndex(caretPosition) + 1; // +1 para linha baseada em 1
                                    return line;
                                } else {
                                    System.err.println("Erro: rootElement é null.");
                                }
                            } else {
                                System.err.println("Erro: Document é null.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Erro: Caret é null.");
                    }
                } else {
                    System.err.println("Erro: JTextComponent é null.");
                }
            } else {
                System.err.println("Erro: openedPanes é null ou está vazio.");
            }
        } else {
            System.err.println("Erro: EditorCookie é null.");
        }
    } else {
        System.err.println("Erro: TopComponent é null.");
    }
    return 0;
}
    
    public static int linhasTotais() {
    TopComponent tc = WindowManager.getDefault().getRegistry().getActivated();
    if (tc != null) {
        EditorCookie editorCookie = tc.getLookup().lookup(EditorCookie.class);
        if (editorCookie != null) {
            JTextComponent[] openedPanes = editorCookie.getOpenedPanes();
            if (openedPanes != null && openedPanes.length > 0) {
                JTextComponent textComponent = openedPanes[0];
                if (textComponent != null) {
                    Document doc = textComponent.getDocument();
                    if (doc != null) {
                        Element rootElement = doc.getDefaultRootElement();
                        if (rootElement != null) {
                            return rootElement.getElementCount();
                        } else {
                            System.err.println("Erro: rootElement é null.");
                        }
                    } else {
                        System.err.println("Erro: Document é null.");
                    }
                } else {
                    System.err.println("Erro: JTextComponent é null.");
                }
            } else {
                System.err.println("Erro: openedPanes é null ou está vazio.");
            }
        } else {
            System.err.println("Erro: EditorCookie é null.");
        }
    } else {
        System.err.println("Erro: TopComponent é null.");
    }
    return 0;
}
}
