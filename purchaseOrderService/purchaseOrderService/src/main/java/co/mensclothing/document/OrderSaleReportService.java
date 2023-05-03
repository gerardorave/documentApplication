package co.mensclothing.document;

import java.util.Collection;
import java.util.List;


import co.mensclothing.dto.response.OrderSaleResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface OrderSaleReportService {
	JasperPrint getReportDetail(List<OrderSaleResponse> OrderSaleList) throws JRException;

	JasperPrint getDetailReportExcelCsv(List<OrderSaleResponse> OrderSaleList) throws JRException;

	/* (non-Javadoc)
	 * @see com.carvajal.avisodevolucion.dominio.servicios.reporte.DevolucionReporteService#getConsolidadoYDetalleReporte(java.util.Collection, java.lang.String)
	 */
	JasperPrint getConsolidateAndDetailReport(Collection<?> listReport) throws JRException;

}
