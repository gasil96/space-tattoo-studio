package br.com.gbsoftware.spacetattoostudio.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;
import br.com.gbsoftware.spacetattoostudio.service.EntradaCaixaService;
import br.com.gbsoftware.spacetattoostudio.service.VwArrecadacaoAnualTipoService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";

	@Autowired
	private VwArrecadacaoAnualTipoService vwArrecadacaoService;

	@Autowired
	private EntradaCaixaService servicoEntradaCaixa;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime inicioDate = LocalDateTime.parse(inicio, formatter);
		LocalDateTime fimDate = LocalDateTime.parse(fim, formatter);
		List<EntradaCaixa> entradas = servicoEntradaCaixa.buscarTodosIntervalo(inicioDate, fimDate);
		response.setContentType("application/pdf");
		Document relatorioMensalPDF = new Document();
		
		// TODO - MONTAR O DOCUMENTO
		try {
			PdfWriter.getInstance(relatorioMensalPDF, response.getOutputStream());
			response.getOutputStream().flush();
			relatorioMensalPDF.open();
			relatorioMensalPDF
					.add(new Paragraph("Relatório Financeiro Mensal ( " + LocalDateTime.now().getMonth() + " )"));
			relatorioMensalPDF.add(new Paragraph("Total Entradas: R$ " + getValorTotalEntrada(entradas),
					FontFactory.getFont(FontFactory.COURIER, 12)));
//			relatorioMensalPDF.add(new Paragraph("Total Arrecadado no Crédito: R$ " + relatorio.get(1),
//					FontFactory.getFont(FontFactory.COURIER, 12)));
//			relatorioMensalPDF.add(new Paragraph("Total Arrecadado no Débito: R$ " + relatorio.get(2),
//					FontFactory.getFont(FontFactory.COURIER, 12)));
//			relatorioMensalPDF.add(new Paragraph("Total Arrecadado À Vista: R$ " + relatorio.get(3),
//					FontFactory.getFont(FontFactory.COURIER, 12)));
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
