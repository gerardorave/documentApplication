package co.mensclothing.document;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportService {

	byte[] getReportPdf(final JasperPrint jp) throws JRException;

	byte[] getReportCsv(final JasperPrint jp) throws JRException, IOException;

	byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException;
}
