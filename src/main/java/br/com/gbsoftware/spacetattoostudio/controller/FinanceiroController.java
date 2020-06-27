package br.com.gbsoftware.spacetattoostudio.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;
import br.com.gbsoftware.spacetattoostudio.service.EntradaCaixaService;
import br.com.gbsoftware.spacetattoostudio.service.SaidaCaixaService;
import br.com.gbsoftware.spacetattoostudio.service.VwArrecadacaoAnualTipoService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";

	@Autowired
	private VwArrecadacaoAnualTipoService vwArrecadacaoService;

	@Autowired
	private EntradaCaixaService servicoEntradaCaixa;

	@Autowired
	private SaidaCaixaService servicoSaidaCaixa;

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Model model) {
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	@RequestMapping(value = "/vw-arrecadacao-anual", method = RequestMethod.GET)
	public @ResponseBody String arrecadacaoAnual(String arrecadacaoAnual) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return arrecadacaoAnual = mapper.writeValueAsString(vwArrecadacaoService.buscarTodos());
	}

	@RequestMapping("relatorio-pdf")
	public void gerarPDF(HttpServletResponse response, @RequestParam("inicio") String inicio,
			@RequestParam("fim") String fim) {
		gerarRelatorioPDF(response, inicio, fim);
	}

	@RequestMapping("relatorio-pdf-mes-atual")
	public void gerarPdgMesAtual(HttpServletResponse response) {
		String inicio = LocalDateTime.now().withMonth(LocalDateTime.now().getMonthValue())
				.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		String fim = LocalDateTime.now().withMonth(LocalDateTime.now().getMonthValue())
				.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		gerarRelatorioPDF(response, inicio, fim);
	}

	private void gerarRelatorioPDF(HttpServletResponse response, String inicio, String fim) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		DateTimeFormatter formatterString = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		List<EntradaCaixa> entradas = servicoEntradaCaixa.buscarTodosIntervalo(inicioDate, fimDate);
		List<SaidaCaixa> saidas = servicoSaidaCaixa.buscarTodosIntervalo(inicioDate, fimDate);
		response.setContentType("application/pdf");
		Document relatorioMensalPDF = new Document();

		try {
			float[] pointColumnWidths = { 150F, 150F, 150F };
			PdfPTable tabela = new PdfPTable(pointColumnWidths);
			Font linhaComun = FontFactory.getFont(FontFactory.COURIER, 12);
			Font titulo = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
			PdfWriter.getInstance(relatorioMensalPDF, response.getOutputStream());
			response.getOutputStream().flush();
			relatorioMensalPDF.open();
			relatorioMensalPDF.addTitle("Financeiro STS");
			relatorioMensalPDF.add(new Paragraph("Relatório Financeiro Simplificado", titulo));
			relatorioMensalPDF.add(new Paragraph("\n"));
			tabela.addCell("Total Entradas");
			tabela.addCell("Total Saídas");
			tabela.addCell("Receita Líquida");
			tabela.addCell("R$ " + getValorTotalEntrada(entradas));
			tabela.addCell("R$ " + getValorTotalSaida(saidas));
			tabela.addCell("R$ " + getTotalLiquido(getValorTotalEntrada(entradas), getValorTotalSaida(saidas)));
			relatorioMensalPDF.add(tabela);
			relatorioMensalPDF.add(new Paragraph("Pesquisa efetuada no intervalo de "
					+ inicioDate.format(formatterString) + " até " + fimDate.format(formatterString), linhaComun));
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			relatorioMensalPDF.close();
		}
	}

	private BigDecimal getValorTotalSaida(List<SaidaCaixa> saidas) {
		return saidas.stream().map(SaidaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));
	}

	private BigDecimal getValorTotalEntrada(List<EntradaCaixa> entradas) {
		return entradas.stream().map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));
	}

	private BigDecimal getTotalLiquido(BigDecimal totalEntrada, BigDecimal totalSaida) {
		return totalEntrada.subtract(totalSaida);
	}

}
