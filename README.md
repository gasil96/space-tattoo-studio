# Criação de um sistema do processo de modelagem e requisitos até a entrega ao cliente.
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
+ Definição da interface principal (TEMPLATE)
  - A interface principal foi feita encima de um template R$(PREMIUM) produzido por uma empresa especializada, ja carregando diversos componentes necessários para serem utilizados no front-end (alguns componentes foram atualizados para suas versões mais recentes) [TEMPLATE](https://demo.bootstrapious.com/dark-admin-premium/1-4-5/index.html)
+ Modelagem e Construção do Banco de Dados
  - O Banco de dados escolhido foi o **MYSQL**, incialmente modelado através da ferramenta *Workbench* 
    e posteriomente implementado atráves do *Hibernate* com anotações *JPA*  tecnologias que serão explicados de melhor forma no       decorrer deste artigo.
+ Cadastro, controle e detalhamento de clientes, agendamentos, usuários. 
  - Cadastro de cliente, agendamentos e usuários esses respectivos CRUD's estão implementados nesta fase, **foi vetada a exclusão de qualquer registro pela interface do usuário** caso seja necessário realizar algum tipo de exclusão, deve ser feito diretamente no banco de dados. Tambem foram implementados gráficos com gerenciamento de clientes ativos, inativos, inadimplentes... cancelamentos de agendamentos, periodicidade, e comparativos com os segmentos dos serviços escolhidos pelos clientes cadastrados no sistema.
+ Paginação principal
  -Definido todos os setores e menus principais e seus respectivos *aninhamentos*, muitas páginas de formulários foram feitos em *modal* (recurso que abre uma nova tela sem sair da anterior),   utilizando o recurso do *thymeleaf* de **fragments** que torna o código reutilizavel diversas vezes em  qualquer página atráves de somente uma linha.
  
  **PAGINAÇÃO**
  
   ![IMAGEM 03 PRINT MENU ANINHADO DA APLICAÇÃO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MENU-ANINHADO.PNG)
 
 **THYMELEAF**
 
 ![IMAGEM 03 THYMELEAF COM FRAGMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-THYMELEAF-COM-FRAGMENTO.PNG)

+ Definição de Permissões
  - Foram criadas regras de acesso e implementados com *thymeleaf* *sec:transaction* que define que o usuário logado pode acessar    
    determinado conteúdo dentro do sistema se possuir a *transação/regra* correta.
  - Como nos menus demarcados em *azul* com acesso somente para o **Gerente** e **Desenvolvedor** e *preto* somente para **Desenvolvedor**
### FASE 2
+ Controle e Fluxo de Caixa
  - Abertura diária do caixa com controle de entrada e saída de produto ou agendamento,
  - Adicionar ou remover crédito ao cliente que deseja guardar uma reserva financeira para consumir futuramente algum serviço prestado 
    no sistema. (ou algum caso que venha ser necessário o pagamento adiantado)
  - Geração de relátorios, podendo realizar pesquisa por tempo estipulado pelo usuário ou gerar um relátorio de um mês em especifico.
  
  ![DETALHAMENTO-FINANCEIRO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-DETALHAMENTO-FINANCEIRO.PNG)
  
+ Integração com API WhatsApp
  - Opção de enviar uma menssagem padrão para o usuário a partir do vencimento dos próximos agendamentos 
  
   ![CARD-DOS-PROXIMOS-AGENDAMENTOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CARD-PROXIMO-AGENDAMENTO.PNG)
  
  - Enviar menssagem para qualquer cliente localizado no detalhamento geral de clientes a partir de um click no número listado.
### FASE 3
+ Processo de **Homogação**
  - Fase onde o cliente ja pode utilizar a aplicação em um versão a ser *homologada/testada* por ele mesmo e todos os usuários que irão futuramente utilizar o sistema final, onde o feedback interfere as novas atualizações, com modificações, correções de erros, inclusão de especificações solicitadas.
+ Build e Deploy em nuvem
  - A nuvem escolhida para armazenamento do sistema foi a [HEROKU](https://www.heroku.com/) uma plataforma internacional.
  - Versionamento de acordo com alterações feitas 
  - Contem dois ambientes **PRODUÇÃO** e **HOMOLOGAÇÃO** onde respectivamente um serve para a versão final do usuário, e outro para         versão de testes ( melhorias, e novas implementações ).
+ Entrega
  - Aplicação em ambiente de produção.
  - Criação do relátorio final da aplicação para fins de consulta **(Markdown)**.
  - Passo à passo de uso para o Cliente.

## Exigências do Cliente 
  Durante as primeiras reuniões o cliente demonstrou interesse em um sistema que fosse auxiliar a ter controle de seus clientes como       contato, cadastro, e tambem um controle financeiro para sua movimentação.
  Baseado no desejo do cliente os requisitos foram pouco a pouco sendo levantados com o feedback.
## Requisitos
   Com a ideia principal ja estabelecida e alinhada junto ao cliente, conseguimos definir oque vai ser necessário para a elaboração do projeto.
   #### Conhecimento
   + Requisitos de Conhecimento
     - Domínio em *Java* com *SpringBoot* para back-end e *Javascript* com *Bootstrap* para front-end
     - Template padrão para ser utizado como base
     - Bibliotecas de auxilio para funcionalidades específicas como, MommentJs, FullCalendarIO
     - Padronização MVC
     - Versionamento com Git/Github
   #### Softwares  
   + Softwares
     - Spring Tool Suite 4
     - Mysql com Workbench
     - Heroku (Plataforma de Nuvem Online)
     - Git (Versionamento com github)
     - Trello (Plataforma Online)
     - Maven (Gerenciamento de Dependências)
   #### Ambiente Físico e Horas
    + Ambiente Físico
      - Pc/Notebook com no mínimo 4GB de ram e processador com 2.0 GHZ (Para rodar o STS em boa performace)
    + Horas
      - Necessário ao menos 50h de trabalho para aprimoramento e execução das atividades pré-definidas (funcionalidades extras não inseridas neste tempo).
# Procedimentos Passo à Passo
   Como utilizar a aplicação - passo à passo para o cliente e/ou [NOVO USUÁRIO]

## Login  

![IMAGEM - TELA LOGIN](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TELALOGIN.PNG)

Cada usuário deverá conter suas credenciais de acesso (login e senha), caso contrário entre em contato com o administrador do sistema junto ao gerente do espaço para definir seu **login** e **senha** e tipo de acesso.
OBS | Nem todos os usuários conseguem visualizar e alterar certas informações... varia de acordo com o tipo de usuário 
* ADMINISTRADOR
* GERENTE
* USUÁRIO
## Principal
A **Dashboard** principal contem as principais funcionalidades do sistemas resumidas e mais algumas adicionais como:
* Número total de Clientes Cadastrados
* Agendamentos do Dia
* Agendamentos da Semana
* Controle do Caixa
Tambem contem um avíso com os proximos 3 agendamentos:

![IMAGEM-PROXIMOS-TRES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-PROXIMOS-TRES.PNG)

Onde contem infomações dos proximos agendamentos junto a infomações do cliente, como: telefone, nome e a opção de enviar uma mensagem padronizada para o cliente via o *whatsapp* (basta clicar sobre o  icone de celular com uma setinha), tambem contém o *Calendário Geral* que mostra por padrão a visão geral do mês e dos seus agendamentos por dia definidos com a categoria do agendamento e uma breve descrição caso você clique sobre a *tag*.

![IMAGEM-CALENDARIO-GERAL](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CALENDARIO-GERAL.PNG)

## Caixa
O caixa é sub-menu referente ao tipo de controle de fluxo de caixa, por padrão ja irá carregar as informações de * R$ Entrada*, *R$ Saída* e *R$ Saldo Atual*.

![IMAGEM-CAIXA-SALDOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CAIXA-SALDOS.PNG)

É possivel **abrir** o caixa e visualizar, incluir e *excluir( somente se o seu login tiver as permissões necessárias )*.

![IMAGEM-ADICIONAR-LANCAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ADICIONAR-LANCAMENTO.PNG)

os lançamentos do dia, e ao final do uso ao **fechar o caixa** sera feito um cálculo baseado em entrada e saída e mostrará o **LUCRO DIÁRIO**. No mesmo sub-menu tambem contém o **crédito do cliente** podem adicionar um fundo de crédito ao cliente para possíveis agendamentos, falta de troco ou pagamento antecipado.

OBS | A opção **Simular Desconto** irá calcular o novo valor encima do desconto aplicado. *(O desconto é opcional)*


![IMAGEM-DESCONTO-SIMULADO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-DESCONTO-SIMULADO.PNG)

OBS | Caso a opção para lançamento no **caixa** seja **saída** não é necessário incluir o **cliente**

![IMAGEM-CAIXA-ADD-CREDITO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CAIXA-ADD-CREDITO.PNG)

Lembre-se sempre que após o encerramento do dia é necessário fechar ou caixa.

![IMAGEM-FECHAR-CAIXA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-FECHAR-CAIXA.PNG)

## Clientes
### Como adicionar um novo cliente:
É possivel adicionar um novo cliente de 2 formas diferentes através da **dashboard** clicando no ícone.

![IMAGEM-ICON-ADD-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ICON-ADD-CLIENTE.PNG)

ou diretamente no sub-menu de **Clientes** clicando no mesmo ícone.

![IMAGEM-MODAL-NOVO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MODAL-NOVO-CLIENTE.PNG)

O campo **instagram** e **telefone** são opcionais.
### Como consumir crédito:
No mesmo sub-menu se encontra a opção de CONSUMIR os créditos de clientes que possuem crédito.
------------------todo---------------------todo------------------------------------todo--------------------------todo---------------
![IMAGEM-CONSUMIR-CREDITO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

Na listagem de **TODOS**  é possivel visualizar o valor de crédito que o cliente possui (verde) ou esta devendo (vermelho).

![IMAGEM-CREDITO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

![IMAGEM-CREDITO-CLIENTE-VERDE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CREDITO-CLIENTE-VERDE.PNG)

### Nesse mesmo item **TODOS** voce pode:

![IMAGEM-TODOS-CLIENTES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

1 - Gerar uma planilha, um pdf, um imprimir diretamente todas suas informações pertinentes a **Clientes** ou simplesmente a listagem de números para **whtasapp** ou todos **instagrams**
2 - Filtrar a pesquisa geral de **TODOS** por nome, número, instagram, etc...
3 - Abrir o modal/campo de editar dados dos clientes 

![IMAGEM-ALTERAR-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

4 - Abrir o modal/campo para agendar um serviço para o cliente escolhido.

![IMAGEM-AGENDAMENTO-PARA-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

Ao final desse sub-menu encontraremos informações pertinentes aos clientes cadastrados, como: comparação do ultimo mês com o mês atual, quantos clientes se encontram **INADIMPLENTES**...

![IMAGEM-INFORMACOES-DE-CLIENTES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

## Agendamentos
Assim como **Clientes** os **Agendamentos** possuem duas formas de serem feitos, na **dashboard** pelo ícone.

![IMAGEM-ICONE-ADD-NOVO-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

ou diretamente no sub-menu **Agendamentos** clicando no mesmo ícone. 
### Modal/Campo agendamento:

![IMAGEM-NOVO-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

### Item TODOS poderemos realizar algumas atividades como:

![IMAGEM-TODOS-AGENDAMENTOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

1 - Gerar planilha, pdf ou impressão da listagem de agendamentos associadas aos clientes
2 - Realizar pesquisa filtrando, nome cliente, tipo agendamento, categoria, status, etc...
3 - Alterar Agendamento

![IMAGEM-ALTERAR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

4 - Encerrar Agendamento

![IMAGEM-ENCERRAR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

4.1 Reabrir Agendamento

![IMAGEM-REABRIR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

Obs: Na lista **TODOS** se o relogio de um agendamento se encontra vermelho significa que você pode encerrar caso encontre-se laranja o agendamento ja foi encerrado e agora pode ser reaberto em uma nova data.
### Informações Visuais Agendamentos
Apos a listagem geral de **TODOS** agendamentos neste sub-menu você ira encontrar vários gráficos que informam detalhes financeiros, comparativos, quantitativos e qualitativos sobre os agendamentos.

![IMAGEM-GRAFICOS-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

## Detalhamento
Informa com mais detalhes alguns items do sistema
### Financeiro
Para ter acesso ao detalhamento financeiro é necessário que o caixa esteja fechado, pois alguns calculos so podem ser realizados após todos os lançamentos do dia serem encerrados.
#### Histórico Caixa Por Tempo Determinado
Você pode determinar um intervalo de tempo entre dias, meses, anos que desejar e realizar a pesquisa dos lançamentos diários e tambem gerar gráficos comparativos.

![IMAGEM-PESQUISA-HISTORICO-CAIXA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

Gráficos E Históricos Caixa

![IMAGEM-GRAFICO-HISTORICO-FINANCEIRO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

#### Relátorio Geral Por Mês Escolhido
Aqui você pode gerar um relátorio para um mes específico.

![IMAGEM-RELATORIO-GERAL](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

com a opção de gerar um *pdf* deste relátorio.

![IMAGEM-RELATORIO-GERAL-POR-MES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

## **Administração**
Este sub-menu é voltado somente para o uso do administrador do sistema, para observar todos os usuários criados e suas respectivas permissões.

![IMAGEM-MENU-ADM](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

tambem é possivel criar novos usuários e definir o nível de visualização de cada um.

![IMAGEM-NOVO-USUARIO-SISTEMA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-.PNG)

1 - **Nome** e Sobrenome do Usuário
2 - **Login** que o usuário usará para se conectar ao sistema geralmente usado *nome.sobrenome*
3 - **Senha** pessoal para cada usuário, contem criptografia então no caso de esquecimeneto sera necessário contatar o adminsitrador para cadastro de uma nova senha.
4 - **Confirmar Senha** confirmar senha anterior para impedir erros de digitação.
5 - **Cargo** necessário para saber qual tipo de permissão de acesso deve ser dada
6 - **Permissão de Acesso** - Defini quais sub-menus e ações o usuário poderar acessar/executar dentro do sistema
### Entenda o Grau de Permissão de Acesso

|CARGO|PRINCIPAL|CAIXA|CLIENTE|AGENDAMENTO|FINANCEIRO|ADMINISTRAÇÃO|PODE EXCLUIR|
|-----|-----|-----|-----|-----|-----|-----|-----|
|DESENVOLVEDOR|SIM|SIM|SIM|SIM|SIM|SIM|SIM|
|GERENTE|SIM|SIM|SIM|SIM|SIM|NÃO|SIM|
|FUNCIONÁRIO|SIM|SIM|SIM|SIM|NÃO|NÃO|NÃO|
