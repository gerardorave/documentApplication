package co.mensclothing.document.impl;

import co.mensclothing.document.OrderSaleReportService;
import co.mensclothing.dto.response.OrderSaleResponse;
import co.mensclothing.util.ReportUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class OrderSaleReportServiceImpl implements OrderSaleReportService {

	public static final String CONSOLIDATE_ORDER_SALE_REPORT_JRXML = "/reports/reportResume.jrxml";
	public static final String ORDER_SALE_DETAILS_REPORT_JRXML = "/reports/reportDetail.jrxml";
	public static final String ORDER_SALE_DETAILS_REPORT_EXCEL_JRXML = "/reports/reportDetailExcel.jrxml";
	
	private ReportUtils reportUtils = new ReportUtils();

	@Override
	public JasperPrint getReportDetail(List<OrderSaleResponse> OrderSaleList) throws JRException{

		InputStream jasperStreamReportDetail = this.getClass().getResourceAsStream(ORDER_SALE_DETAILS_REPORT_JRXML);
		Map<String, Object> params = new HashMap<>();
		JasperReport jasperReport = reportUtils.getReportBase(jasperStreamReportDetail);
		JRBeanCollectionDataSource collectionDs = new JRBeanCollectionDataSource((OrderSaleList));
		return reportUtils.getReportToPrint(jasperReport, params, collectionDs);
	}

	@Override
	public JasperPrint getDetailReportExcelCsv(List<OrderSaleResponse> OrderSaleList) throws JRException {

		InputStream jasperStreamDetailReport = this.getClass().getResourceAsStream(ORDER_SALE_DETAILS_REPORT_EXCEL_JRXML);
		Map<String, Object> params = new HashMap<>();
		JasperReport jasperReport = reportUtils.getReportBase(jasperStreamDetailReport);
		JRBeanCollectionDataSource collectionDs = new JRBeanCollectionDataSource((OrderSaleList));
		return reportUtils.getReportToPrint(jasperReport, params, collectionDs);
	}

	@Override
	public JasperPrint getConsolidateAndDetailReport(Collection<?> listReport) throws JRException {
		InputStream jasperStream = this.getClass().getResourceAsStream(CONSOLIDATE_ORDER_SALE_REPORT_JRXML);
		Map<String, Object> params = new HashMap<>();
		JasperReport jasperReport = reportUtils.getReportBase(jasperStream);

		JRBeanCollectionDataSource collectionDs = new JRBeanCollectionDataSource((Arrays.asList(listReport).get(0)));
		return reportUtils.getReportToPrint(jasperReport, params, collectionDs);
	}

}
