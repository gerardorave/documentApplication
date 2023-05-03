package co.mensclothing.document.impl;

import co.mensclothing.document.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
public class ReportServiceImpl implements ReportService {

	private JRCsvExporter exporterCsv = new JRCsvExporter();
	private JRXlsxExporter exporterXlsx = new JRXlsxExporter();


	@Override
	public byte[] getReportPdf(final JasperPrint jp) throws JRException {
		return JasperExportManager.exportReportToPdf(jp);
	}


	@Override
	public byte[] getReportCsv(final JasperPrint jp) throws JRException, IOException {
		final byte[] rawBytes;

		try (ByteArrayOutputStream csvReport = new ByteArrayOutputStream()) {
			exporterCsv.setExporterInput(new SimpleExporterInput(jp));
			exporterCsv.setExporterOutput(new SimpleWriterExporterOutput(csvReport));
			SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
			configuration.setWriteBOM(Boolean.TRUE);
			configuration.setRecordDelimiter("\r\n");
			exporterCsv.setConfiguration(configuration);
			exporterCsv.exportReport();
			rawBytes = csvReport.toByteArray();
		}
		return rawBytes;
	}

	@Override
	public byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException {
		final byte[] rawBytes;

		try (ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()) {
			exporterXlsx.setExporterInput(new SimpleExporterInput(jp));
			exporterXlsx.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
			exporterXlsx.exportReport();

			rawBytes = xlsReport.toByteArray();
		}

		return rawBytes;
	}
}
