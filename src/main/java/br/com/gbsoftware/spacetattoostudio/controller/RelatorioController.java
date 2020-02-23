package br.com.gbsoftware.spacetattoostudio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gbsoftware.spacetattoostudio.domain.Employee;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@PostMapping
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response)
			throws JRException, SQLException, IOException {

		List<Employee> empList = Arrays.asList(
				new Employee(1, "Sandeep", "Data Matrix", "Front-end Developer", 20000),
				new Employee(2, "Prince", "Genpact", "Consultant", 40000),
				new Employee(3, "Gaurav", "Silver Touch ", "Sr. Java Engineer", 47000),
				new Employee(4, "Abhinav", "Akal Info Sys", "CTO", 700000));
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;

		// Pega o arquivo .jasper localizado em resources
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/livros.jasper");

		// Cria o objeto JaperReport com o Stream do arquivo jasper
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		// Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no
		// caso uma conexão ao banco de dados
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, jrBeanCollectionDataSource);

		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		// Define que o arquivo pode ser visualizado no navegador e também nome final do
		// arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "inline; filename=livros.pdf");

		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
}
