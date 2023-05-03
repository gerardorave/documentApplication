package co.mensclothing.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import co.mensclothing.controller.constant.ControllerConstants;
import co.mensclothing.document.OrderSaleReportService;
import co.mensclothing.document.ReportService;
import co.mensclothing.dto.response.OrderSaleConsolidateResponse;
import co.mensclothing.dto.response.OrderSaleResponse;
import co.mensclothing.service.OrderService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Asus
 */
@RestController()
@RequestMapping(ControllerConstants.ORDER_ROOT + "/report")
public class ReportController {

    private ReportService reportService;

    private OrderSaleReportService orderSaleReportService;


    private OrderService orderService;


    @Autowired
    public ReportController(ReportService reportService, OrderSaleReportService orderSaleReportService, OrderService orderService) {
        this.orderService = orderService;
        this.reportService = reportService;
        this.orderSaleReportService = orderSaleReportService;
    }

    @PostMapping("/consolidateCsvReport")
    public ResponseEntity<byte[]> consolidateCsvReport(@Valid @RequestBody(required = true) List<Long> orderIdsList)
            throws JRException, IOException {
        List<OrderSaleResponse> listDetailToPrint = orderService.getOrders(orderIdsList);
        List<OrderSaleConsolidateResponse> consolidate = orderService.getOrderConsolidateData(listDetailToPrint);
        final byte[] data = reportService.getReportCsv(orderSaleReportService.getConsolidateAndDetailReport(consolidate));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/detailedExcelReport")
    public ResponseEntity<byte[]> detailedExcelReport(@Valid @RequestBody(required = true) List<Long> orderIdsList)
            throws JRException, IOException {
        List<OrderSaleResponse> listDetailToPrint = orderService.getOrders(orderIdsList);
        final byte[] data = reportService.getReportXlsx(orderSaleReportService.getDetailReportExcelCsv(listDetailToPrint));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/consolidatePdfReport")
    public ResponseEntity<byte[]> consolidatePdfReport(@Valid @RequestBody(required = true) List<Long> orderIdsList)
            throws JRException {

        List<OrderSaleResponse> listDetailToPrint = orderService.getOrders(orderIdsList);

        final byte[] data = reportService.getReportPdf(orderSaleReportService.getConsolidateAndDetailReport(listDetailToPrint));

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @PostMapping("/detailedPdfReport")
    public ResponseEntity<byte[]> detailedPdfReport(@Valid @RequestBody(required = true) List<Long> orderIdsList)
            throws JRException {

        List<OrderSaleResponse> listDetailToPrint = orderService.getOrders(orderIdsList);

        final byte[] data = reportService.getReportPdf(orderSaleReportService.getReportDetail(listDetailToPrint));


        return new ResponseEntity<>(data, HttpStatus.OK);

    }

}