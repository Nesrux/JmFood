# JMFood

<!---Esses são exemplos. Veja https://shields.io para outras pessoas ou para personalizar este conjunto de escudos. Você pode querer incluir dependências, status do projeto e informações de licença aqui--->
<div align="center">
  <img src="/docs/jmfoodLogo.png" alt="exemplo imagem" width=300px>
</div>
> Uma API Rest feita com Java e spring para simular um sistema de delivey de entrega de comida

### Ajustes e melhorias

- [x] Criar o projeto e adicionar dependencias.
- [x] Criar classes de dominio.
- [x] Criar as classes de controle
- [x] mapear as entidades com JPA
- [x] Criar as migrations com o flyway
- [x] tratar exceptions
- [x] fazer as validações com bean validation
- [x] envio de PDF pronto
- [x] implementando o envio de email
- [x] implementação do serviço de envio de imagens
- [x] Implementação de cache
- [x] documentação da API
- [x] Implementação de hateoas
- [x] Versionando a API
## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
<!---Estes são apenas requisitos de exemplo. Adicionar, duplicar ou remover conforme necessário--->
* Você instalou a versão `JDK 17`
* Você instalou a versão de `MySql 8.0`
* Você tem uma máquina `Windows`
* Você instalou Uma `IDE` em seu computador
* Você instalou o `Lombok` em sua IDE

## 🚀 Instalando JmFood

Para instalar o JmFood, siga estas etapas:

Git:
```
$ git clone https://github.com/Nesrux/JmFood.git
```
## Configurações importantes para a execução do projeto
Além das configurações que ja estão no projeto, você precisará adicionar e modificar algumas, tais como
### Configuração do banco de dados
```
spring.datasource.username= `Username do banco de dados`
spring.datasource.password= `Senha do banco de dados`
```
### Configuração da pasta local
```
jmfood.storage.tipo= "recebe dois valores, Local ou S3, se for local precisará indicar o caminho da pasta"				
jmfood.storage.local.diretorio-fotos= "caminho absoluto da pasta onde vai ser salvo os arquivos."
```
### Configuração de Envio de email
```
jmfood.email.remetente= de qual email será enviado os emails da jmfood
jmfood.email.host= host Smtp
jmfood.email.port= porta de serviço
jmfood.email.impl= Tipo de imlementação, prod, mock ou sandbox
jmfood.email.sandbox.destinatario= Email para testes sandbox
jmfood.email.password= {senha} Colocar a senha de 16 caracteres do gmail aqui
```
Caso possua alguma duvida, o arquivo application.properties tem exemplos de configurações, clique <a href="https://github.com/Nesrux/JmFood/blob/main/src/main/resources/application.properties"> aqui </a> para ver

`Por fim, execute o arquivo baixado na sua IDE de preferencia para poder aproveitar tudo que o JmFood tem a oferecer`
## 🫡 Vídeos do funcionamento da API
* Criação da Api : https://www.linkedin.com/posts/joaomarcosdev_java-data-security-activity-7069045829155663872-Lp_n
* Adicionando novas features : https://www.linkedin.com/posts/joaomarcosdev_java-springboot-desenvolvimento-activity-7076613656939044864-Otlt/
* implementação de hatoas : https://www.linkedin.com/posts/joaomarcosdev_opensource-git-github-activity-7084653583190634496-fEke

## 📫 Contribuindo para o JmFood
<!---Se o seu README for longo ou se você tiver algum processo ou etapas específicas que deseja que os contribuidores sigam, considere a criação de um arquivo CONTRIBUTING.md separado--->
Para contribuir com JmFood, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <jmlog> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/112022434?s=400&u=35c5908d4696605c944211f4ea0e15a7bfcb6263&v=4" width="100px;" alt="Foto do joao marcos no GitHub"/><br>
        <sub>
          <b>João Marcos</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/102268237?v=4" width="100px;" alt="Foto do joao marcos no GitHub"/><br>
        <sub>
          <b>Lucas Ferreira</b>
        </sub>
      </a>
    </td>
    <td align="center">
</table>

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.

[⬆ Voltar ao topo](https://github.com/Nesrux/Jmlog)<br>
