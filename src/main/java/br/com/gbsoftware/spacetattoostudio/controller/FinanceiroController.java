package br.com.gbsoftware.spacetattoostudio.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gbsoftware.spacetattoostudio.domain.enums.CategoriaEntradaEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.service.CaixaService;
import br.com.gbsoftware.spacetattoostudio.service.EntradaCaixaService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";
	private static final String MODAL_DETALHAMENTO_MENSAL = "modal/modal-detalhamento-financeiro-mensal";

	@Autowired
	private CaixaService servicoCaixa;

	@Autowired
	private EntradaCaixaService servicoEntradaSaida;

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Caixa caixa, Model model) {
		caixa = servicoCaixa.getDiaAtual();
		if (caixa == null) {
			model.addAttribute("caixaAberto", false);
		} else {
			model.addAttribute("caixaAberto", servicoCaixa.getDiaAtual().getAberto());
		}
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	@RequestMapping("pesquisar-caixa")
	public String pesquisarCaixaTeste(@RequestParam(value = "dataInicial", required = true) String dataInicial,
			@RequestParam(value = "dataFinal", required = true) String dataFinal, Model model)
			throws JsonProcessingException {
		List<Caixa> caixas = servicoCaixa.buscarPorIntervalo(dataInicial, dataFinal);
		if (caixas.isEmpty()) {
			model.addAttribute("vazio", true);
		} else {
			model.addAttribute("caixas", caixas);
			model.addAttribute("consultado", caixas.isEmpty() ? false : true);
			model.addAttribute("dataInicial", dataInicial);
			model.addAttribute("dataFinal", dataFinal);
		}
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	@RequestMapping("relatorio-geral-mensal")
	public String pesquisarRelatorioGeralMes(
			@RequestParam(value = "relGeralMensal", required = true) String relGeralMensal,
			HttpServletResponse response, Model model) {

		List<Caixa> listaRelatorioGeralMensal = servicoCaixa.buscarTodosMes(relGeralMensal);

		if (!listaRelatorioGeralMensal.isEmpty()) {
			List<Object> relatorio = servicoCaixa.relatorio(relGeralMensal);
			model.addAttribute("totalGeral", relatorio.get(0));
			model.addAttribute("totalCredito", relatorio.get(1));
			model.addAttribute("totalDebito", relatorio.get(2));
			model.addAttribute("totalAvista", relatorio.get(3));
			model.addAttribute("relGeralMensal", relGeralMensal);
			return MODAL_DETALHAMENTO_MENSAL;

		} else {
			model.addAttribute("msgSemRelatorio", true);
			return PAGINA_DETALHAMENTO_FINANCEIRO;
		}

	}

	@RequestMapping("relatorio-mensal-pdf")
	public void gerarPDF(@RequestParam(value = "relGeralMensal", required = true) String relGeralMensal,
			HttpServletResponse response) {
		List<Caixa> listaRelatorioGeralMensal = servicoCaixa.buscarTodosMes(relGeralMensal);
		if (!listaRelatorioGeralMensal.isEmpty()) {
			List<Object> relatorio = servicoCaixa.relatorio(relGeralMensal);
			response.setContentType("application/pdf");
			Document relatorioPDF = new Document();
			try {
				PdfWriter.getInstance(relatorioPDF, response.getOutputStream());
				response.getOutputStream().flush();
				relatorioPDF.open();
				relatorioPDF.add(new Paragraph("Relatório Financeiro Mensal ( " + relGeralMensal + " )"));
				relatorioPDF.add(new Paragraph("Total Geral: R$ " + relatorio.get(0),
						FontFactory.getFont(FontFactory.COURIER, 12)));
				relatorioPDF.add(new Paragraph("Total Arrecadado no Crédito: R$ " + relatorio.get(1),
						FontFactory.getFont(FontFactory.COURIER, 12)));
				relatorioPDF.add(new Paragraph("Total Arrecadado no Débito: R$ " + relatorio.get(2),
						FontFactory.getFont(FontFactory.COURIER, 12)));
				relatorioPDF.add(new Paragraph("Total Arrecadado À Vista: R$ " + relatorio.get(3),
						FontFactory.getFont(FontFactory.COURIER, 12)));
			} catch (DocumentException de) {
				System.err.println(de.getMessage());
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			} finally {
				relatorioPDF.close();
			}
		} else {
			System.err.println("erro");
		}
	}

	@RequestMapping(value = "/pesquisa-intervalo", method = RequestMethod.GET)
	public @ResponseBody String pesquisaIntervalo(String dataInicial, String dataFinal) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		List<Caixa> caixas = servicoCaixa.buscarPorIntervalo(dataInicial, dataFinal);
		String lista = mapper.writeValueAsString(caixas);
		return lista;
	}

	@RequestMapping(value = "/pesquisa-intervalo-gasto", method = RequestMethod.POST)
	public @ResponseBody String pesquisaIntervaloGasto(String dataInicial, String dataFinal)
			throws JsonProcessingException {
		List<EntradaCaixa> entradaSaida = servicoEntradaSaida.buscarPorIntervalo(dataInicial, dataFinal);

		BigDecimal gasTotalPercieng = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.PIERCING)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal gasTotalTattoo = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.TATTOO)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal gasTotalBarbearia = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.BARBEARIA)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal gasTotalProduto = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.PRODUTO)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.SAIDA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gasTotalPercieng", gasTotalPercieng);
		map.put("gasTotalTattoo", gasTotalTattoo);
		map.put("gasTotalBarbearia", gasTotalBarbearia);
		map.put("gasTotalProduto", gasTotalProduto);
		map.put("dataInicial", dataInicial);
		map.put("dataFinal", dataFinal);
		String listaGasto = new Gson().toJson(map);
		System.err.println(listaGasto);
		return listaGasto;
	}

	@RequestMapping(value = "/pesquisa-intervalo-arrecadacao", method = RequestMethod.POST)
	public @ResponseBody String pesquisaIntervaloArrecadacao(String dataInicial, String dataFinal)
			throws JsonProcessingException {
		List<EntradaCaixa> entradaSaida = servicoEntradaSaida.buscarPorIntervalo(dataInicial, dataFinal);

		BigDecimal arrTotalPercieng = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.PIERCING)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal arrTotalTattoo = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.TATTOO)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal arrTotalBarbearia = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.BARBEARIA)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));

		BigDecimal arrTotalProduto = entradaSaida.stream()
				.filter(x -> x.getCategoriaEntrada().equals(CategoriaEntradaEnum.PRODUTO)
						&& x.getTipoOperacao().equals(TipoOperacaoEnum.ENTRADA))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("arrTotalPercieng", arrTotalPercieng);
		map.put("arrTotalTattoo", arrTotalTattoo);
		map.put("arrTotalBarbearia", arrTotalBarbearia);
		map.put("arrTotalProduto", arrTotalProduto);
		map.put("dataInicial", dataInicial);
		map.put("dataFinal", dataFinal);
		String listaArrecadacao = new Gson().toJson(map);
		System.err.println(listaArrecadacao);
		return listaArrecadacao;
	}

}
