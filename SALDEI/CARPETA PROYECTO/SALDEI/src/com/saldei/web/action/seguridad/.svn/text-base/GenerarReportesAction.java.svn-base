/**
 * 
 */
package com.saldei.web.action.seguridad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.saldei.util.jdbc.JdbcHelper;


/**
 * @author Carlos
 *
 */
public class GenerarReportesAction extends DispatchAction {
	JdbcHelper jdbcCon = new JdbcHelper();
	
	public ActionForward rptUsuario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,String formato,String ruta,HashMap parameters) throws JRException, IOException, SQLException{		
		String format=formato;		
		switch(Integer.parseInt(format)){
			case 1:
				return this.imprimirReportePDF(mapping,form,request,response,ruta,parameters);
				case 2:
				return this.imprimirReporteEXCEL(mapping,form,request,response,ruta,parameters);				
			case 3: 
				return this.imprimirReportHTML(mapping,form,request,response,ruta,parameters);
							
		}
		return null;
				
	}
	private ActionForward imprimirReportHTML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String ruta, HashMap parameters) throws JRException, IOException, SQLException  {
		String htmlFile=this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgUsuariosActivos.html");
		Connection con = jdbcCon.getCon();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(ruta);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,con);
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);			
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,htmlFile);
		exporter.exportReport();
		File f = new File (this.getServlet().getServletContext().getRealPath("reportesJasper/rpt_sgUsuariosActivos.html"));
		String name = f.getName().substring(f.getName().lastIndexOf("/") + 1,f.getName().length());
		response.setHeader("Content-Disposition", "attachment; filename=\" " + name + "\"");
		InputStream in = new FileInputStream(f);
		ServletOutputStream outs = response.getOutputStream();

		int bit = 256;

		while ((bit) >= 0) {
		bit = in.read();
		outs.write(bit);
		}

		outs.flush();
		outs.close();
		in.close();
		con.close();

		return null;
	}
	private ActionForward imprimirReporteEXCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String ruta, HashMap parameters) throws JRException, IOException, SQLException {
		Connection con = jdbcCon.getCon();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(ruta);
		ByteArrayOutputStream bytesw = new ByteArrayOutputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,con);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bytesw);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.exportReport();
		byte[] bytes = bytesw.toByteArray();
		response.setHeader("Content-type", "application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=reporte"+ ".xls");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
        con.close(); 
		return null;
	}
	public ActionForward imprimirReportePDF(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,String ruta,HashMap parameters) throws JRException, IOException, SQLException{
		Connection con = jdbcCon.getCon();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(ruta);
		byte[] fichero = JasperRunManager.runReportToPdf(jasperReport, parameters, con);
		if ((fichero != null) && (fichero.length > 0)) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=ok.pdf");
			response.setHeader("Cache-Control", "max-age=30");
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(fichero.length);
			ServletOutputStream out = response.getOutputStream();
			out.write(fichero, 0, fichero.length);
			out.flush();
			out.close();
		}
		con.close();
		
		return null;		
	}
	

}
