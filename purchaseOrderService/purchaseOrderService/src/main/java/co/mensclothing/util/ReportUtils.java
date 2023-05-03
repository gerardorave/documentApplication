/**
 * 
 */
package co.mensclothing.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Map;

public class ReportUtils {

	/**
	 * @param jasperStream
	 * @return
	 * @throws JRException
	 */
	public JasperReport getReportBase(InputStream jasperStream) throws JRException {
		return JasperCompileManager.compileReport(jasperStream);
	}
	
	/**
	 * Obtiene el reporte para imprimir
	 * @param jasperReport
	 * @param params
	 * @param collecTionDs
	 * @return
	 * @throws JRException
	 */
	public JasperPrint getReportToPrint(JasperReport jasperReport, Map<String, Object> params,JRBeanCollectionDataSource collecTionDs) throws JRException{
		return JasperFillManager.fillReport(jasperReport, params, collecTionDs);
	}
	
}
