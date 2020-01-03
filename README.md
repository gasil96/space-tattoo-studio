# Criando uma aplicação web do processo de modelagem/requisitos até a entrega ao cliente.

# Índice 
+ [Tecnologias Utilizadas](#tecnologias-utilizadas)
    - [Back-End](#back-end)
    - [Front-End](#front-end)
    - [Ferramentas](#ferramentas)
    - [Principais Bibliotecas](#principais-bibliotecas)
+ [Metodologia Ágil](#metodologia-ágil)
    - [Fase 1](#fase-1)
    - [Fase 2](#fase-2)
    - [Fase 3](#fase-3)
+ [Exigências do Cliente](#exigências-do-cliente)
+ [Requisitos](#requisitos)
    - [Conhecimento](#conhecimento)
    - [Softwares](#softwares)
    - [Ambiente Físico e Horas](#ambiente-físico-e-horas)
+ [Código Fonte](#código-fonte)    
+ [Guia Usuário](#guia-usuário)    
    - [Login](#login)
    - [Principal](#principal)
    - [Caixa](#caixa)
    - [Clientes](#clientes)
       + [Adicionar Novo Cliente](#adicionar-novo-cliente)
       + [Consumir Crédito](#consumir-crédito)
       + [Tabela Todos Clientes](#tabela-todos-clientes)
    - [Agendamentos](#agendamentos)
       + [Adicionar Novo Agendamento](#adicionar-novo-agendamento)
       + [Tabela Todos Agendamentos](#tabela-todos-agendamentos)
       + [Informações Agendamentos](informações-agendamentos)
    - [Detalhamento](#detalhamento)
        + [Financeiro](#financeiro)
           - [Histórico Caixa Por Tempo Determinado](histórico-caixa-por-tempo-determinado)
           - [Relatório Geral Por Mês Escolhido](relatório-geral-por-mês-escolhido)
    + [Administração](#administração)
       - [Grau de Permissão de Acesso](grau-de-permissão-de-acesso)   
---

## Tecnologias Utilizadas

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

O sistema foi subdivido em 3 fases

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

Obs | Todas as *fases* foram organizadas e gerenciadas através do [TRELLO](http://trello.com/) utilizando o *Kanban*.

Confira um exemplo do método *Kanban* de execução de processos na **Fase 2**

![IMAGEM-TRELLO-FASE-2](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TRELLO-FASE-2.PNG)

Alguns processos podem ter sido adiantados ou atrasados conforme a necessidade tenha surgido.

![IMAGEM-TRELLO-FASE-GERAL](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TRELLO-FASE-GERAL.PNG)

## Exigências do Cliente 
  Durante as primeiras reuniões o cliente demonstrou interesse em um sistema que fosse auxiliar a ter controle de seus clientes como       contato, cadastro, e tambem um controle financeiro para sua movimentação.
  Baseado no desejo do cliente os requisitos foram pouco a pouco sendo levantados com o feedback.
## Requisitos
   Com a ideia principal ja estabelecida e alinhada junto ao cliente, conseguimos definir oque vai ser necessário para a elaboração do projeto.
### Conhecimento
   + Requisitos de Conhecimento
     - Domínio em *Java* com *SpringBoot* para back-end e *Javascript* com *Bootstrap* para front-end
     - Template padrão para ser utizado como base
     - Bibliotecas de auxilio para funcionalidades específicas como, MommentJs, FullCalendarIO
     - Padronização MVC
     - Versionamento com Git/Github
### Softwares  
   + Requisitos de Softwares
     - Spring Tool Suite 4
     - Mysql com Workbench
     - Heroku (Plataforma de Nuvem Online)
     - Git (Versionamento com github)
     - Trello (Plataforma Online)
     - Maven (Gerenciamento de Dependências)
### Ambiente Físico e Horas
   + Ambiente Físico
     - Pc/Notebook com no mínimo 4GB de ram e processador com 2.0 GHZ (Para rodar o STS em boa performace)
   + Horas
     - Necessário ao menos 50h de trabalho para aprimoramento e execução das atividades pré-definidas (funcionalidades extras não inseridas neste tempo).

## Código Fonte

Veja à seguir alguns dos principais trechos de código da aplicação com comentários mostrando a necessidade e/ou objetivo da implementação

### Padrão MVC 
Utilizamos o padrão de projetos *MVC* - **Model View Controller**.

>MVC é nada mais que um padrão de arquitetura de software, separando sua aplicação em 3 camadas.
>A camada de interação do usuário(view), a camada de manipulação dos dados(model) e a camada de controle(controller).

![IMAGEM-MVC](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MVC.png)

### Versionamento Com Git
Nossa plataforma em nuvem para armazenar o código-fonte foi o [Github](github.com), onde criamos 3 *branches*.

1. **Master** Gerada automaticamente ao criar o projeto, é onde a versão final, estavel da aplicação fica armazenada (deploy e build), versão de **PRODUÇÃO**
2. **HML** Versão para testes de mudanças, novas funcionalidades etc.. pelos usuários antes de ser implatada em **produção**
3. **DSV** Versão de desenvolvimento, primeira camada a ser implementada, todas novas funcionalidades ou atualizações são primeiramente colocadas aqui.

![IMAGEM-BRANCHES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-BRANCHES.PNG)

### Java com SpringBoot 
A anotação *@Controller* transforma a classe em um *bean* do Spring e faz com ela seja reconhecida pelo framework como camada de controller, existem várias outras anotações importantes que usamos nessa implementação, como @RequestMapping que define o *path* de acesso desse controller, @GetMapping, @PutMapping e todos os outros métodos *Http's*.

Outra anotação muito utilizada em nossa aplicação é a *@Autowired* que define uma injeção de depedência.

~~~java
@Controller
@RequestMapping("cliente")
public class ClienteController {

	private static final String PAGINA_CLIENTE_DETALHADO = "detalhamento/cliente-detalhado";
	private static final String ATUALIZAR_PAGINA = "redirect:detalhamento";
	private static final String MODAL_EDITAR_CLIENTE = "modal/modal-editar-cliente";
	private static final String MODAL_NOVO_AGENDAMENTO_CLIENTE = "modal/modal-novo-agendamento-cliente";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoServico;

	@GetMapping("agendar/{id}")
	public String preAgendar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoServico.buscarPorId(id));
		model.addAttribute("id_cliente_referente", id);
		model.addAttribute("clienteNome", servicoCliente.buscarPorId(id).get().getNome());
		return MODAL_NOVO_AGENDAMENTO_CLIENTE;
	}
~~~


### Java Persistence API com Hibernate 
Utilizamos *JPA* como modelagem para nossa *entidade*  

~~~java
package br.com.gbsoftware.spacetattoostudio.domain.model;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
// Outros 'imports' foram omitidos.

@Entity // Jpa Anotação
@SuppressWarnings("serial")
@Table(name = "CLIENTE")
public class Cliente extends EntidadeBase<Long> {

	@Column(length = 50, nullable = false)
	@JsonProperty(value = "nome")
	private String nome;

	@Column(length = 20)
	@JsonProperty(value = "telefone") // Jackson anotação
	private String telefone;

	@Column(name = "credito_cliente", precision = 12, scale = 2)
	private BigDecimal creditoCliente;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30, nullable = false)
	private StatusClienteEnum statusCliente;

	@JsonIgnore
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@Column(length = 30)
	@JsonProperty(value = "instagram")
	private String instagram;

	@JsonIgnore
	@Column(precision = 12, scale = 2)
	private BigDecimal saldo;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Servico> servicos;

	public Cliente() {
	}

	public Cliente( String nome, String telefone, BigDecimal creditoCliente,
			 StatusClienteEnum statusCliente, LocalDateTime dataCadastro, String instagram, BigDecimal saldo,
			List<Servico> servicos) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.creditoCliente = creditoCliente;
		this.statusCliente = statusCliente;
		this.dataCadastro = dataCadastro;
		this.instagram = instagram;
		this.saldo = saldo;
		this.servicos = servicos;
	}
~~~

Para acesso a Banco de Dados, utilizamos *@Repository* e extendemos a classe *JpaRepository* com todos os métodos de **CRUD** ja carregados.

Obs | Mesmo **JpaRepository** sendo uma extensão podemos criar *Querys* porsonalizadas com anotação *@Query* nativa ou não. 

~~~java
	// QUERY NATIVA
	@Query(value = "select clt.id, clt.instagram, clt.nome, clt.telefone from cliente clt", nativeQuery = true)
	List<Cliente> getClienteIdInstaNome();

	Object save(Optional<Cliente> cliente);

	// QUERY
	@Transactional
	@Modifying
	@Query("UPDATE Cliente SET credito_cliente = ?1 WHERE id = ?2")
	void updateCredito(BigDecimal valorCredito, Long idCliente);
~~~

### Spring Security

A autenticação de usuário foi implementadas partir do Spring Security.

>O Spring Security é uma estrutura Java / Java EE que fornece autenticação, autorização e outros recursos de segurança para aplicativos >corporativos.

~~~java
@Configuration
@EnableWebSecurity // HABILITA O SECURITY
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired // INJEÇÃO DE DEPENDÊNCIA
	private ImplementsUserDetailsService userDetailsService;
	
	@Override // DEFINE QUAIS PATHS PODEM SER ACESSADO POR DETERMINADO GRUPO DE ACESSO
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/financeiro/detalhamento").hasAnyRole("ADMIN","GERENTE","USUARIO")
//		.antMatchers(HttpMethod.GET, "/caixa/excluir").hasAnyRole("ADMIN","GERENTE")
//		.antMatchers(HttpMethod.GET, "/caixa/excluir/{id}").hasAnyRole("ADMIN","GERENTE")
//		.antMatchers(HttpMethod.GET, "/caixa/editar").hasAnyRole("ADMIN","GERENTE")
//		.antMatchers(HttpMethod.GET, "/caixa/editar/{id}").hasAnyRole("ADMIN","GERENTE")
		.antMatchers(HttpMethod.GET, "/promocional/promo").hasAnyRole("ADMIN","GERENTE","USUARIO")
		.antMatchers(HttpMethod.GET, "/adm/administracao").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		auth.inMemoryAuthentication().withUser("OMITIDO").password("OMITIDO").roles("ADMIN");
	}
	 
	@Override // DEFINE QUIAS PASTAM DEVEM SER IGNORADAS PELO SECURITY (GERALMENTE ARQUIVOS DE IMAGENS E CSS)
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**","/data/**","/fonts/**","/icons-reference/**","/img/**","/js/**","/vendor/**");
	}
}
~~~
O método sobrescrito *configure* com **.auth** define duas formas de logar e se manter autenticado na aplicação. via memória com o *user* e *password* fixados para uso restrito do administrador e via JPA com os mesmos  armazenados no banco de dados, podendo alterar, criar novos ou excluir.

### Arquivo Properties
>A aplicação com Spring Boot converte as propriedades da linha de comandos em propriedades do Spring Boot Environment. 
> As propriedades da linha de comando têm precedência sobre as outras fontes de propriedade.

Nesse arquivo iremos colocar a *url*, *login*, *senha* do banco de dados. Tambem, informações como porta que desejamos pra iniciar, configurações de cache para o *Thymeleaf* e configurações do *JPA*.

~~~properties
#MYSQL
spring.datasource.url= jdbc:mysql://localhost:3306/spacetattoo_dsv?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=America/Belem
spring.datasource.username=root
spring.datasource.password=mysql
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

#PORTA PARA INICIAR LOCALMENTE
server.port=8085

#JPA
spring.jpa.hibernate.ddl-auto= update
spring.jpa.show-sql= true
spring.jpa.open-in-view=true

# THYMELEAF (ThymeleafAutoConfiguration)
spring.thymeleaf.cache= false 

project.version = @project.version@
~~~

Note que temos *dois* arquivos *properties* um que usamos para **desenvolvimento local** e outro com dados de acesso para o banco em produção.

~~~properties
#MYSQL			       {----login---} {-pass-} {--------------url------------_} {-----database-------}	      	   						
spring.datasource.url= mysql://##############:########@us-cdbr-iron-eas#############net/heroku_7bd6###########?reconnect=true
#JPA
spring.jpa.hibernate.ddl-auto= update
spring.jpa.show-sql= false
spring.jpa.open-in-view=true

# THYMELEAF (ThymeleafAutoConfiguration)
spring.thymeleaf.cache= true

# Transforma a versão atual do projeto (anotada no maven) em uma variavel global na aplicação
project.version = @project.version@
~~~

### Pom.xml
>Project Object Model (literalmente "projeto modelo de objeto"), ou POM, é a peça fundamental de um projeto do Apache Maven.
>Um POM possui as infomações básicas de um projeto, bem como as diretivas de como o artefato final deste projeto deve ser construido.
>A versão 1.0 do Maven utiliza o arquivo project.xml para definição do POM. Na versão 2.0, este arquivo passa a se chamar pom.xml. 

Neste arquivo carregamos a versão do nosso projeto como:
Versão 1.0.2 onde:
**1** Representa a 1ª versão estavel.
**0** Indica que não houve nenhuma grande alteração nesta versão.
**2** Houve 2 updates ou correções.

~~~xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>spacetattoostudio</groupId>
	<artifactId>spacetattoostudio</artifactId>
	<version>1.0.2</version>
	<packaging>jar</packaging>

	<name>spacetattoostudio</name>
	<description>Projeto SpaceTatto Studio</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.7.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEnconding>UTF-8</project.build.sourceEnconding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.bouncycastle</groupId>
			<artifactId>bcprov-jdk15on</artifactId>
			<version>1.56</version>
		</dependency>
		<dependency>
			<groupId>org.apache.pdfbox</groupId>
			<artifactId>pdfbox</artifactId>
			<version>2.0.17</version>
		</dependency>
~~~

### Thymeleaf

>O Thymeleaf não é um projeto Spring, mas uma biblioteca que foi criada para facilitar a criação da camada de view com uma forte >integração com o Spring, e uma boa alternativa ao JSP.

>O principal objetivo do Thymeleaf é prover uma forma elegante e bem formatada para criarmos nossas páginas. O dialeto do Thymeleaf é >bem poderoso como você verá no desenvolvimento da aplicação, mas você também pode estendê-lopara customizar de acordo com suas necessidades

Utilizamos o thymeleaf para fazer a maior parte da transferencia de objeto entre back e front end, tambem utilizamos muito o recurso de fragmentação do thymeleaf que otimiza o uso de código html evitando repetição de código

**Fragments**

Fragmentação:
~~~html
<div id="novoCliente" tabindex="-1" role="dialog"	aria-labelledby="exampleModalLabel" aria-hidden="true"	class="modal fade text-left" th:fragment="modalNovoCliente">
		<div role="document" class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<strong id="titvuloModalNovoCliente" class="modal-title">Novo Cliente</strong>
					<button type="button" data-dismiss="modal" aria-label="Close"
						class="close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				O código foi encurtado.
				</div>
			</div>
		</div>
	</div>
~~~

Forma de Reuso:
~~~html
	<div th:replace="fragments/modal-novo-cliente :: modalNovoCliente"></div>
~~~
basta apenas uma linha de código para reutilizar todo aquele formulário em modal visto no highlight anterior.

Tambem utilizamos o thymeleaf para definir as transações(permissões) necessárias para renderização de alguns componentes ou menus.
Como no exemplo:
~~~html
     <li sec:authorize="hasAnyRole('ADMIN','GERENTE', 'USUARIO')" th:class="${classActiveCaixa}"><a th:href="@{/caixa/fluxo}"><i class="icon-grid"></i>Caixa</a></li>
~~~
Só ira ser renderizado a *tag* para os usuários autenticados que possuirem as transações/permissões especificadas.

### Bootstrap
Como dito anteriormente o bootstrap tratou quase toda a parte visual do projeto perdendo pouco lugar para o *materializer* da google, utilizamos um template premium pronto e as customizações foram implementadas encima do projeto base. bibliotecas **javascript** foram necessárias, alguns foram incluidas outras apenas atualizadas.

~~~~html
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
	<!-- 	preloader -->
	<script th:src="@{/js/components-preloader.js}"> </script>
	<!-- Bootstrap TypeAhead-->
    <script th:src="@{/vendor/bootstrap-3-typeahead/bootstrap3-typeahead.min.js}"></script>
    <!-- TypeAhead init-->
    <script th:src="@{/js/forms-autocomplete.js}"></script>
	<!-- 	 script's gerais -->
	<script type="text/javascript">
~~~~

A maioria das customizações de css foram implementadas no arquivo **custom.css** como podemos ver em:

~~~css

.texto-login {
  font-family:  font-login-LemonJelly;
  font-size: 95px;
  background: -webkit-linear-gradient(#aaa	, #aaa);
  -webkit-background-clip: text;
/*   -webkit-text-fill-color: transparent; */
  color: white;
 transform: rotate(-11deg);
}

~~~

## Guia Usuário
 
 Este guia irá mostrar todas as telas da aplicação e o passo à passo de como utiliza-la de forma correta.

### Login  

![IMAGEM - TELA LOGIN](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TELALOGIN.PNG)

Cada usuário deverá conter suas credenciais de acesso (login e senha), caso contrário entre em contato com o administrador do sistema junto ao gerente do espaço para definir seu **login** e **senha** e tipo de acesso.
OBS | Nem todos os usuários conseguem visualizar e alterar certas informações... varia de acordo com o tipo de usuário 
* ADMINISTRADOR
* GERENTE
* USUÁRIO
### Principal
A **Dashboard** principal contem as principais funcionalidades do sistema resumidas e mais algumas adicionais como:
* Número total de Clientes Cadastrados
* Agendamentos do Dia
* Agendamentos da Semana
* Controle do Caixa
Tambem contém um avíso com os próximos 3 agendamentos:

![IMAGEM-PROXIMOS-TRES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-PROXIMOS-TRES.PNG)

Onde contem infomações dos proximos agendamentos junto a infomações do cliente, como: telefone, nome e a opção de enviar uma mensagem padronizada para o cliente via o *whatsapp* (basta clicar sobre o  icone de celular com uma setinha), tambem contém o *Calendário Geral* que mostra por padrão a visão geral do mês e dos seus agendamentos por dia definidos com a categoria do agendamento e uma breve descrição caso você clique sobre a *tag*.

![IMAGEM-CALENDARIO-GERAL](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CALENDARIO-GERAL.PNG)

### Caixa
O caixa é sub-menu referente ao controle de fluxo de caixa, por padrão ja irá carregar as informações de * R$ Entrada*, *R$ Saída* e *R$ Saldo Atual*.

![IMAGEM-CAIXA-SALDOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CAIXA-SALDOS.PNG)

É possivel **abrir** o caixa e visualizar, incluir e *excluir (somente se o seu login tiver as permissões necessárias)*.

![IMAGEM-ADICIONAR-LANCAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ADICIONAR-LANCAMENTO.PNG)

Os lançamentos do dia, e ao final do uso ao **fechar o caixa** sera feito um cálculo baseado em entrada e saída e mostrará o **LUCRO DIÁRIO**. No mesmo sub-menu tambem contém o **crédito do cliente** podem adicionar um fundo de crédito ao cliente para possíveis agendamentos, falta de troco ou pagamento antecipado.

OBS | A opção **Simular Desconto** irá calcular o novo valor encima do desconto aplicado. *(O desconto é opcional)*


![IMAGEM-DESCONTO-SIMULADO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-DESCONTO-SIMULADO.PNG)

OBS | Caso a opção para lançamento no **caixa** seja **saída** não é necessário incluir o **cliente**

![IMAGEM-CAIXA-ADD-CREDITO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CAIXA-ADD-CREDITO.PNG)

Lembre-se sempre que após o encerramento do dia é necessário fechar ou caixa.

![IMAGEM-FECHAR-CAIXA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-FECHAR-CAIXA.PNG)

### Clientes
#### Adicionar Novo cliente:
É possivel adicionar um novo cliente de 2 formas diferentes através da **dashboard** clicando no ícone.

![IMAGEM-ICON-ADD-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ICON-ADD-CLIENTE.PNG)

Ou diretamente no sub-menu de **Clientes** clicando no mesmo ícone.

![IMAGEM-MODAL-NOVO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MODAL-NOVO-CLIENTE.PNG)

O campo **instagram** e **telefone** são opcionais.
#### Consumir crédito:
No mesmo sub-menu se encontra a opção de CONSUMIR os créditos de clientes que possuem crédito.

![IMAGEM-CONSUMIR-CREDITO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CONSUMIR-CREDITO-CLIENTE.PNG)

Na listagem de **TODOS**  é possivel visualizar o valor de crédito que o cliente possui (verde) ou esta devendo (vermelho).

![IMAGEM-CREDITO-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CREDITO-CLIENTE.png)

![IMAGEM-CREDITO-CLIENTE-VERDE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-CREDITO-CLIENTE-VERDE.png)

#### Tabela TODOS Clientes

![IMAGEM-TODOS-CLIENTES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TODOS-CLIENTES.PNG)

- 1 - Gerar uma planilha, um pdf, um imprimir diretamente todas suas informações pertinentes a **Clientes** ou simplesmente a listagem de números para **whtasapp** ou todos **instagrams**
- 2 - Filtrar a pesquisa geral de **TODOS** por nome, número, instagram, etc...
- 3 - Abrir o campo de editar dados dos clientes 

![IMAGEM-ALTERAR-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ALTERAR-CLIENTE.PNG)

- 4 - Abrir o campo para agendar um serviço para o cliente escolhido.

![IMAGEM-AGENDAMENTO-PARA-CLIENTE](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-AGENDAMENTO-PARA-CLIENTE.PNG)

Ao final desse sub-menu encontraremos informações pertinentes aos clientes cadastrados, como: comparação do ultimo mês com o mês atual, quantos clientes se encontram **INADIMPLENTES**...

![IMAGEM-INFORMACOES-DE-CLIENTES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-INFORMACOES-DE-CLIENTES.PNG)

### Agendamentos
Assim como em **Clientes** os **Agendamentos** possuem duas formas de serem feitos, na **dashboard** pelo ícone.

![IMAGEM-ICONE-ADD-NOVO-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ICONE-ADD-NOVO-AGENDAMENTO.PNG)

Ou diretamente no sub-menu **Agendamentos** clicando no mesmo ícone. 
#### Adicionar Novo agendamento:

![IMAGEM-NOVO-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-NOVO-AGENDAMENTO.PNG)

#### Tabela TODOS Agendamentos:

![IMAGEM-TODOS-AGENDAMENTOS](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-TODOS-AGENDAMENTOS.PNG)

- 1 - Gerar planilha, pdf ou impressão da listagem de agendamentos associadas aos clientes
- 2 - Realizar pesquisa filtrando, nome cliente, tipo agendamento, categoria, status, etc...
- 3 - Alterar Agendamento

![IMAGEM-ALTERAR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ALTERAR-AGENDAMENTO.PNG)

- 4 Reabrir Agendamento 

![IMAGEM-REABRIR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-REABRIR-AGENDAMENTO.PNG)

- 4.1 - Encerrar Agendamento 

![IMAGEM-ENCERRAR-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-ENCERRAR-AGENDAMENTO.PNG)

Obs: Na lista **TODOS** se o relogio de um agendamento se encontra vermelho significa que você pode encerrar caso encontre-se laranja o agendamento ja foi encerrado e agora pode ser reaberto em uma nova data.
#### Informações Agendamentos
Apos a listagem geral de **TODOS** agendamentos neste sub-menu você ira encontrar vários gráficos que informam detalhes financeiros, comparativos, quantitativos e qualitativos sobre os agendamentos.

![IMAGEM-GRAFICOS-AGENDAMENTO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-GRAFICOS-AGENDAMENTO.PNG)

### Detalhamento
Informa com mais detalhes alguns items do sistema
#### Financeiro
Para ter acesso ao detalhamento financeiro é necessário que o caixa esteja fechado, pois alguns calculos so podem ser realizados após todos os lançamentos do dia serem encerrados.
##### Histórico Caixa Por Tempo Determinado
Você pode determinar um intervalo de tempo entre dias, meses, anos que desejar e realizar a pesquisa dos lançamentos diários e tambem gerar gráficos comparativos.

![IMAGEM-PESQUISA-HISTORICO-CAIXA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-PESQUISA-HISTORICO-CAIXA.PNG)

Gráficos e Históricos do Caixa

![IMAGEM-GRAFICO-HISTORICO-FINANCEIRO](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-GRAFICO-HISTORICO-FINANCEIRO.PNG)

##### Relátorio Geral Por Mês Escolhido
Aqui você pode gerar um relátorio para um mes específico.

![IMAGEM-RELATORIO-GERAL](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-RELATORIO-GERAL.PNG)

Com a opção de gerar um *pdf* deste relátorio.

![IMAGEM-RELATORIO-GERAL-POR-MES](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-RELATORIO-GERAL-POR-MES.PNG)

### **Administração**
Este sub-menu é voltado somente para o uso do administrador do sistema, para observar todos os usuários criados e suas respectivas permissões.

![IMAGEM-MENU-ADM](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-MENU-ADM.PNG)

Tambem é possivel criar novos usuários e definir o nível de visualização de cada um.

![IMAGEM-NOVO-USUARIO-SISTEMA](https://github.com/gasil96/spacetattoostudio/blob/hml/src/main/resources/static/img/img-readme/IMAGEM-NOVO-USUARIO-SISTEMA.PNG)

- 1 - **Nome** e Sobrenome do Usuário
- 2 - **Login** que o usuário usará para se conectar ao sistema geralmente usado *nome.sobrenome*
- 3 - **Senha** pessoal para cada usuário, contem criptografia então no caso de esquecimeneto sera necessário contatar o adminsitrador para cadastro de uma nova senha.
- 4 - **Confirmar Senha** confirmar senha anterior para impedir erros de digitação.
- 5 - **Cargo** necessário para saber qual tipo de permissão de acesso deve ser dada
- 6 - **Permissão de Acesso** - Defini quais sub-menus e ações o usuário poderar acessar/executar dentro do sistema
#### Grau de Permissão de Acesso

|CARGO|PRINCIPAL|CAIXA|CLIENTE|AGENDAMENTO|FINANCEIRO|ADMINISTRAÇÃO|PODE EXCLUIR|
|-----|-----|-----|-----|-----|-----|-----|-----|
|DESENVOLVEDOR|SIM|SIM|SIM|SIM|SIM|SIM|SIM|
|GERENTE|SIM|SIM|SIM|SIM|SIM|NÃO|SIM|
|FUNCIONÁRIO|SIM|SIM|SIM|SIM|NÃO|NÃO|NÃO|

---
Para outras informações sobre o uso do sistema entre em contato com o *suporte* pelo número:
**+55 (91) 9 8217-9201** WhatsApp e Ligação

---

**Created and developed by *Gabriel Silva* &copy; *Gabriel S. Software* 2019**
