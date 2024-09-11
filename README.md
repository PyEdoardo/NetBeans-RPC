# Projeto NetBeans-RPC

O projeto **netbeans-rpc** integra o NetBeans IDE com o Discord por meio do Rich Presence (RPC), utilizando o **Discord Game SDK**. Essa integração permite exibir informações personalizadas sobre o que você está fazendo no NetBeans diretamente no seu perfil do Discord.

## Funcionalidades

- **Status do Editor**: Mostra o arquivo, linhas totais do código, o gerenciador do projeto e o próprio nome do projeto do NetBeans no seu Discord.
- **Atualização em Tempo Real**: Atualiza automaticamente o status do Discord conforme você navega entre arquivos ou modifica o projeto.
- **Personalização**: Configurações para exibir imagens e descrições específicas no status do Discord usando o Game SDK.

## Instalação

1. **Baixe o Arquivo `.nbm`**: Obtenha o arquivo de módulo do NetBeans (`netbeans-rpc.nbm`) na sessão de [Release](https://github.com/PyEdoardo/NetBeans-RPC/releases) ou no [Actions](https://github.com/PyEdoardo/NetBeans-RPC/actions)

2. **Instale o Módulo**:
   - Abra o NetBeans IDE.
   - Vá para **"Tools"** > **"Plugins"**.
   - Na aba **"Downloaded"**, clique em **"Add Plugins..."**.
   - Selecione o arquivo `.nbm` e clique em **"Install"**.
   - Reinicie o NetBeans para aplicar as mudanças.

## Configuração

1. **Abra as Configurações do Plugin**:
   - Vá para **"Tools"** > **"Options"**.
   - Selecione a aba **"NetBeans RPC"**.

2. **Configure o RPC**:
   - Crie uma aplicação na parte de desenvolvedor do Discord
   - Copie o ID do Cliente e coloque na classe **"Discord.java"**
   - Após isso, só definir na classe **"NetBeans"**, os nomes de ícones de cada arquivo, pois eles não estão no repositório, apenas na pag de dev.
   - Defina as imagens e descrições que serão exibidas no status do Discord usando o Discord Game SDK.
   - Ajuste outras preferências conforme necessário.

## Contribuição

Se você deseja contribuir para o projeto, envie suas sugestões e melhorias através de pull requests ou abra uma issue no repositório do GitHub.

## Contato

Para mais informações ou suporte, entre em contato com o desenvolvedor através do [GitHub](https://github.com/PyEdoardo/NetBeans-RPC) ou envie um e-mail para [edoardotombolesi8@gmail.com](mailto:edoardotombolesi8@gmail.com).

Obrigado por usar o **netbeans-rpc**!
