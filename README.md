## Passo a passo criação de sistema do começo até a entrega ao cliente.
### Back-End 
| Tecnologia | Descrição |
| ------ | ------ |
| Java 8 | Linguagem de programação orientada a objetos |
| SpringBoot | Spring Boot é um projeto da Spring que veio para facilitar o processo de configuração e publicação de nossas aplicações. Você escolhe os módulos que deseja através dos starters que inclui no pom.xml do seu projeto. Eles, basicamente, são dependências que agrupam outras dependências. |
| Spring MVC | O framework Spring, é um dos frameworks Java mais conhecido e utilizado. Esse framework implementa um grande número de funções, como injeção de dependência, persistência de dados e uma implementação para o padrão MVC para a criação de aplicações WEB|
| Hibernate | Hibernate é o framework para persistência de dados mais utilizado em projetos Java. Sendo uma das primeiras opções a implementar o conceito de mapeamento objeto-relacional (ORM), em pouco tempo se tornou referência entre os desenvolvedores, tendo influenciado, inclusive, a criação da especificação JPA.|
| JPA | JPA é a especificação do Java que dita como os frameworks ORM devem ser implementados. Ela foi criada com o intuito de padronizar essas soluções. Antes de sua criação existiam diversos frameworks e bibliotecas que abstraiam os desafios da persistência com ORM em Java.|
| Spring Security | O Spring Security é uma estrutura Java / Java EE que fornece autenticação, autorização e outros recursos de segurança para aplicativos corporativos. |
| MySQL | É um sistema de gerenciamento de banco de dados, que utiliza a linguagem SQL como interface. |

### Front-End 
| Tecnologia | Descrição |
| ------ | ------ |
| Thymeleaf | Thymeleaf é um mecanismo de modelo Java XML / XHTML / HTML5 que pode funcionar tanto em ambientes web como fora da web. É mais adequado para servir XHTML / HTML5 na camada de visualização de aplicativos da web baseados em MVC, mas pode processar qualquer arquivo XML mesmo em ambientes off-line. |
| Javascript | JavaScript é uma linguagem de programação interpretada estruturada, de script em alto nível com tipagem dinâmica fraca e multi-paradigma. Juntamente com HTML e CSS, o JavaScript é uma das três principais tecnologias da World Wide Web. |
| Jquery | jQuery é uma biblioteca de funções JavaScript que interage com o HTML, desenvolvida para simplificar os scripts interpretados no navegador do cliente. |
| Bootstrap | Bootstrap é um framework web com código-fonte aberto para desenvolvimento de componentes de interface e front-end para sites e aplicações web usando HTML, CSS e JavaScript, baseado em modelos de design para a tipografia, melhorando a experiência do usuário em um site amigável e responsivo. |

### Ferramentas
| Ferramenta | Descrição |
| ------ | ------ |
| Spring Tool Suite 4 (STS) | É uma derivação do eclipse que ja contem diversas modificações para lidar com as facilidades e implementações do *Spring Boot* |
| Mysql Workbench | É uma ferramenta visual de design de banco de dados que integra desenvolvimento, administração, design, criação e manutenção de SQL em um único ambiente de desenvolvimento integrado para o sistema de banco de dados MySQL. |
### Principais Bibliotecas
- DataTable
- TouchSping
- Input Masks
- Full Calendar IO
- Typehead
- MommentJS
- Mensseger HubSpot
- Entre outras...

## Metodologia Ágil
#### O sistema foi subdivido em 3 fases
### FASE 1
+ Definição da interfaace prinpal (TEMPLATE)
  - A interface principal foi feita encima de um template R$(PREMIUM) produzido por uma empresa especializada ja carregando diversos componentes necessários para serem utilizados no front-end (alguns componentes foram atualizados para suas versões mais recentes) [TEMPLATE](https://demo.bootstrapious.com/dark-admin-premium/1-4-5/index.html)
+ Modelagem e Construção do Banco de Dados
  - O Banco de dados escolhido foi o **MYSQL**, incialmente modelado atraves da ferramenta Workbench 
    **IMAGEM 01 - WORKBENCH MODELANDO O BD**
    e posteriomente implementado atráves do *Hibernate* com anotações *JPA*  tenologias que serão explicados mais especificamentes no       decorrer deste artigo.
+ Cadastro, controle e detalhamento de clientes, agendamentos, usuários. 
  - Cadastro de cliente, agendamento e usuários, seus respectivos CRUD's implementados nesta fase, **foi vetada a exclusão de qualquer registro pela interface do usuário** caso seja necessário realizar algum tipo de exclusão a mesma deve ser feita diretamente no banco de dados. Tambem foi implementado gráficos com gerenciamento de clientes ativos, inativos, inadimplentes... amostragem gráficas de cancelamentos de agendamento, periodiciade, e comparativos com o segmento do serviços escolhidos pelos clientes cadastrados no sistema.
+ Paginação principal</br>
 ![Menu principal](https://user-images.githubusercontent.com/48265863/67949769-128ffa00-fbc7-11e9-8b15-f43f84338d57.PNG)
 </br>
  - Definido todos os setores e menus principais e seus respectivos *aninhamentos*, muitas páginas de formulários foram feitos em modal,   utilizando o recurso do *thymeleaf* de **fragments** que torna o código reutilizavel diversas vezes em  qualquer página atráves  de     somente um linha </br>
  ``` html
    <div th:replace="fragments/modal-novo-cliente :: modalNovoCliente"></div>
  ``` 
</br>
+ Definição de Permissões
  - Foram criadas regras de acesso e implementados com *thymleaf* *sec:transaction* que define que usuário logado pode acessar    
    determinado conteúdo dentro do sistema.
### FASE 2
+ Controle e Fluxo de Caixa
  - Abertura diária do caixa com controles de entrada e saída de qualquer segmento de produto ou agendamento,
  - Adicionar ou remover crédito ao cliente que deseja guardar uma reserva financeira para consumir futuramente algum serviço prestado 
    no sistema.
  - Geração de relátorios, podendo realizar pesquisa por tempo estipulado pelo usuário do sistema ou gerar um relátorio de um mês em         especifico.
  </br>
  ![detalhamento-financeiro-pesquisado](https://user-images.githubusercontent.com/48265863/67947826-45d08a00-fbc3-11e9-9baa-     df9a56e1a120.PNG)
  </br>
+ Integração com API WhatsApp
  - Opção de enviar uma menssagem padrão para o usuário apartir do vencimento dos próximos agendamentos 
  </br>
  ![Card Proximo Agendamento](https://user-images.githubusercontent.com/48265863/67949138-ed4ebc00-fbc5-11e9-84ac-45e482c66de5.PNG)
  </br>
  - Poder enviar menssagem para qualquer cliente localizado no detalhamento geral de clientes apartir de um click no número listado.
### FASE 3
+ Processo de **Homogação**
  - Fase onde o cliente ja pode utilizar a aplicação em um versão a ser homologada/testada por ele mesmo onde o feedback infere as novas     atualizações com modificações, correções de erros, inclusão de especificações solicitadas pelos usuários do sistema
+ Build e Deploy em nuvem
  - A nuvem escolhida para armazenamento do sistema foi a [HEROKU](https://www.heroku.com/)
  - Mysqldump em para nuvem do banco de dados local
  - Versionamento de acordo com alterações feitas 
  - Contem dois ambientes **PRODUÇÃO** E **HOMOLOGAÇÃO** onde respectivamente um serve para a versão final do usuário, e outro para         versão de testes ( melhorias, e novas implementações ).
+ Entrega
  - Criação do relátorio final da aplicação para fins de consulta **(MarkDown)**.
  - Ensinamento do passo a passo de uso para o cliente.

## Exigências do Cliente 
  Durante as primeiras reuniões o cliente demonstrou interesse em um sistema que fosse auxiliar a ter controle de seus clientes como contato, cadastro, e tambem um controle financeiro para sua movimentação.
  Baseado no desejo do cliente os requisitos foram pouco a pouco sendo levantado com muita conversa e feedback.
## Requisitos
   Com a ideia principal ja estabelecida e alinhada junto ao cliente, chega a hora de definirmos oque vai ser necessário para a elaboração do projeto.
   ##### Conhecimento
   + Requisitos de Conhecimento
     - Domínio aprofundado em *Java* com *SpringBoot* para back-end e *Javascript* com *Bootstrap* para front-end
     - Template padrão para ser utizado como base
     - Bibliotecas de auxilio para funcionalidades específicas como, MommentJs, FullCalendarIO
     - Padronização MVC
   #### Softwares  
   + Softwares
     - Spring Tool Suite 4
     - Mysql Workbench
     - Heroku (Plataforma Online)
     - Git (versionamento com github)
     - Trello (Plataforma Online)
     - Maven (Gerenciamento de Dependencias)
   #### Ambiente Físico e Horas
   + Ambiente
      - Pc/Notebook com no minimo 4GB de ram e processador com 2.0 GHZ (Para rodar o STS em boa qualidade)
      - Espaço neutro para trabalho
   + Horas
      - Necessário ao menos 200h de trabalho para aprimoramento e execução das atividades pre definidas (funcionalidades extras não inseridas neste tempo).
## Procedimentos Passo à Passo
#### 1 - Login 
 Essa é a primeira tela do sistema para um usuário que ainda não esta logado, você pode logar usando seu usuário e senha cadastrados préviamentes pelo administrador do sistema, *caso seja o primeiro uso da aplicação* o administrador contem um usuário padrão para gerar novos usuários.
 ![Login](https://user-images.githubusercontent.com/48265863/67949236-22f3a500-fbc6-11e9-92af-bca6ef86736a.PNG)</br>
#### 2 - Pagina Principal (HOME)
