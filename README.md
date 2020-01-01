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
| ------ | ------ 
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
    e posteriomente implementado atráves do *Hibernate* com anotações *JPA*  tenologias que serão explicados mais especificamentes no       decorrer deste artigo.
+ Cadastro, controle e detalhamento de clientes, agendamentos, usuários. 
  - Cadastro de cliente, agendamento e usuários, seus respectivos CRUD's implementados nesta fase, **foi vetada a exclusão de qualquer registro pela interface do usuário** caso seja necessário realizar algum tipo de exclusão a mesma deve ser feita diretamente no banco de dados. Tambem foi implementado gráficos com gerenciamento de clientes ativos, inativos, inadimplentes... amostragem gráficas de cancelamentos de agendamento, periodiciade, e comparativos com o segmento do serviços escolhidos pelos clientes cadastrados no sistema.
+ Paginação principal
 ![IMAGEM 03 PRINT MENU ANINHADO DA APLICAÇÃO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MENU-ANINHADO.PNG)
  -Definido todos os setores e menus principais e seus respectivos *aninhamentos*, muitas páginas de formulários foram feitos em modal,   utilizando o recurso do *thymeleaf* de **fragments** que torna o código reutilizavel diversas vezes em  qualquer página atráves  de     somente um linha 
  ![IMAGEM 03 THYMELEAF COM FRAGMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-THYMELEAF-COM-FRAGMENTO.PNG)
+ Definição de Permissões
  - Foram criadas regras de acesso e implementados com *thymleaf* *sec:transaction* que define que usuário logado pode acessar    
    determinado conteúdo dentro do sistema.
### FASE 2
+ Controle e Fluxo de Caixa
  - Abertura diária do caixa com controles de entrada e saída de qualquer segmento de produto ou agendamento,
  - Adicionar ou remover crédito ao cliente que deseja guardar uma reserva financeira para consumir futuramente algum serviço prestado 
    no sistema.
  - Geração de relátorios, podendo realizar pesquisa por tempo estipulado pelo usuário do sistema ou gerar um relátorio de um mês em         especifico.
  ![DETALHAMENTO-FINANCEIRO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-DETALHAMENTO-FINANCEIRO.PNG)
+ Integração com API WhatsApp
  - Opção de enviar uma menssagem padrão para o usuário apartir do vencimento dos próximos agendamentos 
   ![CARD-DOS-PROXIMOS-AGENDAMENTOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CARD-PROXIMO-AGENDAMENTO.PNG)
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
  - Criação do relátorio final da aplicação para fins de consulta **(Markdown)**.
  - Passo à passo de uso para o Cliente.

## Exigências do Cliente 
  Durante as primeiras reuniões o cliente demonstrou interesse em um sistema que fosse auxiliar a ter controle de seus clientes como       contato, cadastro, e tambem um controle financeiro para sua movimentação.
  Baseado no desejo do cliente os requisitos foram pouco a pouco sendo levantados com interação e feedback.
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
    + Ambiente Físico
      - Pc/Notebook com no minimo 4GB de ram e processador com 2.0 GHZ (Para rodar o STS em boa performace)
    + Horas
      - Necessário ao menos 50h de trabalho para aprimoramento e execução das atividades pre definidas (funcionalidades extras não inseridas neste tempo).
## Procedimentos Passo à Passo
   Como utilizar a aplicação - passo à passo para o cliente [NOVO USUÁRIO]

## Login  
![IMAGEM - TELA LOGIN](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TELALOGIN.PNG)

Cada usuário ja deverá conter seu acesso, caso contrário entre em contato com o administrador do sistema junto ao gerente do espaço para definir seu **login** e **senha** e tipo de acesso.
OBS | Nem todos os usuários conseguem visualizar e alterar certas informações... varia de acordo com o tipo de usuário 
* ADMINISTRADOR
* GERENTE
* USUÁRIO
## Principal
A *Dashboard* principal contem as principais funcionalidades do sistemas resumidas e mais algumas adicionais como:
* Número total de Clientes Cadastrados
* Agendamentos do Dia
* Agendamentos da Semana
* Controle do Caixa
Tambem contem um avíso com os proximos 3 agendamentos:
**IMAGEM PROXIMOS 3 AGENDAMENTOS**
Onde contem infomações dos proximos agendamentos junto a infomações do cliente como telefone, nome e a opção de enviar mensagem para o cliente via o *whatsapp* (basta clicar sobre o  número do cliente), tambem contém o *Calendário Geral* que mostra por padrão a visão geral do mês e dos seus agendamentos por dia e categoria.
**IMAGEM CALENDARIO GERAL**

## Caixa
O caixa é sub-menu referente ao tipo de controle de fluxo de caixa, por padrão ja irá carregar as informações de * R$ Entrada*, *R$ Saída* e *R$ Saldo Atual*
**IMAGEM CAIXA SALDOS**
É possivel **abrir** o caixa e visualizar, incluir e *excluir( somente se o seu login tiver as permissões necessárias )*
**IMAGEM ADICIONAR LANCAMENTO** os lançamentos do dia, e ao final do uso ao **fechar o caixa** sera feito um calculo baseado em entrada e saida e mostrará o **LUCRO DIÁRIO**. No mesmo sub-menu tambem contém o **crédito do cliente** podem adicionar um fundo de crédito ao cliente para possíveis agendamentos, falta de troco ou pagamento antecipado.
**IMAGEM CAIXA ADD CRÉDITO**
Lembre-se sempre que apos o encerramento do dia é necessário fechar ou caixa.
**IMAGEM FECHAR CAIXA**
## Clientes
### Como adicionar um novo cliente:
É possivel adicionar um novo cliente de 2 formas diferentes através da **dashboard** clicando no ícone
**IMAGEM ICONE ADD NOVO CLIENTE**
ou diretamente no sub-menu de **Clientes** clicando no mesmo ícone 
**IMAGEM MODAL NOVO CLIENTE**
O campo **instagram** e **telefone** são opcionais.
### Como consumir crédito:
No mesmo sub-menu se encontra a opção de CONSUMIR os créditos de clientes que possuem crédito.
**IMAGEM CONSUMIR CREDITO CLIENTE**
Na listagem de **TODOS**  é possivel visualizar o valor de crédito que o cliente possui (verde) ou esta devendo (vermelho).
**IMAGEM CREDITO CLIENTE**
### Nesse mesmo item **TODOS** voce pode:
**IMAGEM TODOS CLIENTES**
1 - Gerar uma planilha, um pdf, um imprimir diretamente todas suas informações pertinentes a **Clientes** ou simplesmente a listagem de números para **whtasapp** ou todos **instagrams**
2 - Filtrar a pesquisa geral de **TODOS** por nome, número, instagram, etc...
3 - Abrir o modal/campo de editar dados dos clientes 
**IMAGEM ALTERAR CLIENTE**
4 - Abrir o modal/campo para agendar um serviço para o cliente escolhido.
**IMAGEM AGENDAMENTO PARA CLIENTE**
Ao final desse sub-menu encontraremos informações pertinentes aos clientes cadastrados, como: comparação do ultimo mês com o mês atual, quantos clientes se encontram **INADIMPLENTES**...
**IMAGEM INFORMAÇÕES DE CLIENTES**
## Agendamentos
Assim como **Clientes** os **Agendamentos** possuem duas formas de serem feitos, na **dashboard** pelo ícone
**IMAGEM ICONE ADD NOVO AGENDAMENTO**
ou diretamente no sub-menu **Agendamentos** clicando no mesmo icone. 
### Modal/Campo agendamento:
**IMAGEM NOVO AGENDAMENTO**
### Item TODOS poderemos realizar algumas atividades como:
**IMAGEM TODOS AGENDAMENTOS***
1 - Gerar planilha, pdf ou impressão da listagem de agendamentos associadas aos clientes
2 - Realizar pesquisa filtrando, nome cliente, tipo agendamento, categoria, status, etc...
3 - Alterar Agendamento
**IMAGEM ALTERAR AGENDAMENTO**
4 - Encerrar Agendamento
**IMAGEM ENCERRAR AGENDAMENTO**
4.1 Reabrir Agendamento
**IMAGEM REABRIR AGENDAMENTO**
Obs: Na lista **TODOS** se o relogio de um agendamento se encontra vermelho significa que você pode encerrar caso encontre-se laranja o agendamento ja foi encerrado e agora pode ser reaberto em uma nova data.
### Informações Visuais Agendamentos
Apos a listagem geral de **TODOS** agendamentos neste sub-menu você ira encontrar vários gráficos que informam detalhes financeiros, comparativos, quantitativos e qualitativos sobre os agendamentos.
**IMAGEM GRÁFICOS AGENDAMENTOS**
## Detalhamento
Informa com mais detalhes alguns items do sistema
### Financeiro
Para ter acesso ao detalhamento financeiro é necessário que o caixa esteja fechado, pois alguns calculos so podem ser realizados após todos os lançamentos do dia serem encerrados.
#### Histórico Caixa Por Tempo Determinado
Você pode determinar um intervalo de tempo entre dias, meses, anos que desejar e realizar a pesquisa dos lançamentos diários e tambem gerar gráficos comparativos.
**IMAGEM PESQUISA HISTORICO CAIXA**
Gráficos E Históricos Caixa
**IMAGEM GRÁFICO E HISTORICO FINANCEIRO**
#### Relátorio Geral Por Mês Escolhido
Aqui você pode gerar um relátorio para um mes específico
**IMAGEM RELATORIO GERAL**
com a opção de gerar um *pdf* deste relátorio.
**IMAGEM RELATORIO GERAL POR MES**
## **Administração**
Este sub-menu é voltado somente para o uso do administrador do sistema, para observar todos os usuários criados e suas respectivas permissões.
**IMAGEM MENU ADM**
tambem é possivel criar novos usuários e definir o nível de visualização de cada um
**IMAGEM NOVO USUÁRIO DO SISTEMA**
1 - **Nome** e Sobrenome do Usuário
2 - **Login** que o usuário usará para se conectar ao sistema geralmente usado *nome.sobrenome*
3 - **Senha** pessoal para cada usuário, contem criptografia então no caso de esquecimeneto sera necessário contatar o adminsitrador para cadastro de uma nova senha.
4 - **Confirmar Senha** confirmar senha anterior para impedir erros de digitação.
5 - **Cargo** necessário para saber qual tipo de permissão de acesso deve ser dada
6 - **Permissão de Acesso** - Defini quais sub-menus e ações o usuário poderar acessar/executar dentro do sistema
### Entenda o Grau de Permissões de Acesso
|CARGO|PRINCIPAL|CAIXA|CLIENTE|AGENDAMENTO|FINANCEIRO|ADMINISTRAÇÃO|PODE EXCLUIR|
|-----|-----|-----|-----|-----|-----|-----|-----|
|DESENVOLVEDOR|SIM|SIM|SIM|SIM|SIM|SIM|SIM|
|GERENTE|SIM|SIM|SIM|SIM|SIM|NÃO|SIM|
|FUNCIONÁRIO|SIM|SIM|SIM|SIM|NÃO|NÃO|NÃO|
