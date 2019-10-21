package br.com.gbsoftware.spacetattoostudio.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.service.CaixaService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";
	private static final String MODAL_DETALHAMENTO_MENSAL = "modal/modal-detalhamento-financeiro-mensal";

	@Autowired
	private CaixaService servicoCaixa;

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
			@RequestParam(value = "dataFinal", required = true) String dataFinal, Model model) {

		List<Caixa> caixas = servicoCaixa.buscarPorIntervalo(dataInicial, dataFinal);

		if (caixas.isEmpty()) {
			model.addAttribute("vazio", true);
		}

		model.addAttribute("caixas", caixas);
		model.addAttribute("consultado", caixas.isEmpty() ? false : true);

		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	// TODO - MÉTODO EM ANDAMENTO
	@RequestMapping("relatorio-geral-mensal")
	public String pesquisarRelatorioGeralMes(
			@RequestParam(value = "relGeralMensal", required = true) String relGeralMensal, Model model) {

		List<Caixa> listaRelatorioGeralMensal = servicoCaixa.buscarTodosMes(relGeralMensal);

		if (!listaRelatorioGeralMensal.isEmpty()) {
			List<Object> relatorio = servicoCaixa.relatorio(relGeralMensal);
			model.addAttribute("totalGeral", relatorio.get(0));
			model.addAttribute("totalCredito", relatorio.get(1));
			model.addAttribute("totalDebito", relatorio.get(2));
			model.addAttribute("totalAvista", relatorio.get(3));

			Document relatorioPDF = new Document();
			try {

//				PdfWriter.getInstance(relatorioPDF,
//						new FileOutputStream("Relatorio_Mensal_Space_Tattoo_Studio.pdf"));
				PdfWriter.getInstance(relatorioPDF, new FileOutputStream(relGeralMensal));
				relatorioPDF.open();

				// adicionando um parágrafo ao documento
				relatorioPDF.add(new Paragraph("Relatório Mensal (nomeDoMes)"));

				// adicionando um parágrafo com fonte diferente ao arquivo
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

			return MODAL_DETALHAMENTO_MENSAL;

		} else {
			model.addAttribute("msgSemRelatorio", true);
			return PAGINA_DETALHAMENTO_FINANCEIRO;
		}

	}

//	// TODO - MÉTODO EM ANDAMENTO
//	@RequestMapping("gerar-pdf")
//	public String gerarPdf(@RequestParam(value = "relGeralMensal", required = true) String relGeralMensal,
//			Model model) {
//		List<Caixa> listaRelatorioGeralMensal = servicoCaixa.buscarTodosMes(relGeralMensal);
//
//		if (!listaRelatorioGeralMensal.isEmpty()) {
//			List<Object> relatorio = servicoCaixa.relatorio(relGeralMensal);
//			Document relatorioPDF = new Document();
//			try {
//
//				PdfWriter.getInstance(relatorioPDF,
//						new FileOutputStream("C:\\Users\\14882\\Downloads\\pdf_ReceitasDeCodigo.pdf"));
//				relatorioPDF.open();
//
//				// adicionando um parágrafo ao documento
//				relatorioPDF.add(new Paragraph("Relatório Mensal (nomeDoMes)"));
//
//				// adicionando um parágrafo com fonte diferente ao arquivo
//				relatorioPDF.add(new Paragraph("Total Geral: R$ " + relatorio.get(0),
//						FontFactory.getFont(FontFactory.COURIER, 12)));
//				relatorioPDF.add(new Paragraph("Total Arrecadado no Crédito: R$ " + relatorio.get(1),
//						FontFactory.getFont(FontFactory.COURIER, 12)));
//				relatorioPDF.add(new Paragraph("Total Arrecadado no Débito: R$ " + relatorio.get(2),
//						FontFactory.getFont(FontFactory.COURIER, 12)));
//				relatorioPDF.add(new Paragraph("Total Arrecadado À Vista: R$ " + relatorio.get(3),
//						FontFactory.getFont(FontFactory.COURIER, 12)));
//
//			} catch (DocumentException de) {
//				System.err.println(de.getMessage());
//			} catch (IOException ioe) {
//				System.err.println(ioe.getMessage());
//			} finally {
//				relatorioPDF.close();
//			}
//			return MODAL_DETALHAMENTO_MENSAL;
//
//		} else {
//			model.addAttribute("msgSemRelatorio", true);
//			return PAGINA_DETALHAMENTO_FINANCEIRO;
//		}
//	}
}
