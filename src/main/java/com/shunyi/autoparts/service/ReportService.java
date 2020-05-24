package com.shunyi.autoparts.service;


import com.shunyi.autoparts.dao.*;
import com.shunyi.autoparts.exception.ExportReportToHtmlFileException;
import com.shunyi.autoparts.exception.ExportReportToPdfStreamException;
import com.shunyi.autoparts.exception.ExportReportToPngFileException;
import com.shunyi.autoparts.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author chens
 *
 */
@Service
public class ReportService {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
	/** pdf文件类型 */
	private static final String PDF = "pdf";
	/** html文件类型 */
	private static final String HTML = "html";
	/** png文件类型 */
	private static final String PNG = "png";

	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
	@Autowired
	private PurchaseReturnOrderDao purchaseReturnOrderDao;
	@Autowired
	private SalesOrderDao salesOrderDao;
	@Autowired
	private SalesReturnOrderDao salesReturnOrderDao;
	@Autowired
	private PriceAdjustmentOrderDao priceAdjustmentOrderDao;
	@Autowired
	private StocktakingOrderDao stocktakingOrderDao;

	/**
	 * 查找模板文件列表
	 *
	 * @return
	 */
	public List<String> findTempletes() {
		File removableDir = new File("ireport/templates");
		if (removableDir.exists()) {
			File[] files = removableDir.listFiles();
			return Arrays.asList(files).stream().map(e -> e.getName()).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	/**
	 * 删除报表
	 *
	 * @param orderNo
	 * @param reportFileType
	 */
	public void delete(String orderNo, String reportFileType) {
		File removableFile = new File("ireport/generated/"+orderNo+"."+reportFileType);
		if (removableFile.exists()) {
			try {
				FileUtils.forceDelete(removableFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 准备生成报表
	 *
	 * @param orderNo
	 * @param templateName
	 * @param reportFileType
	 */
	public void generate(String orderNo, String templateName, String reportFileType) {
		if(orderNo.startsWith(OrderCodeFactory.PURCHASE_CODE)) {
			List<PurchaseOrder> purchaseOrderList = purchaseOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
			if (purchaseOrderList.size() > 0) {
				List<PurchaseOrderItem> itemList = purchaseOrderItemDao.findAllByPurchaseOrderIdOrderByIdAsc(purchaseOrderList.get(0).getId());
				System.out.println(itemList);

				if(reportFileType.equals(PDF)) {
					jasperToPdf(itemList, orderNo, templateName);
				} else if(reportFileType.equals(HTML)) {
					jasperToHtml(itemList, orderNo, templateName);
				} else if(reportFileType.equals(PNG)) {
					jasperToPng(itemList, orderNo, templateName);
				}
			}

		} else if(orderNo.startsWith(OrderCodeFactory.PURCHASE_RETURN_ORDER)) {
//			List<PurchaseReturnOrder> list = purchaseReturnOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
//			if(reportFileType.equals(PDF)) {
//				jasperToPdf(list, orderNo, templateName);
//			} else if(reportFileType.equals(HTML)) {
//				jasperToHtml(list, orderNo, templateName);
//			} else if(reportFileType.equals(PNG)) {
//				jasperToPng(list, orderNo, templateName);
//			}

		} else if(orderNo.startsWith(OrderCodeFactory.SALES_CODE)) {
//			List<SalesOrder> list = salesOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
//			if(reportFileType.equals(PDF)) {
//				jasperToPdf(list, orderNo, templateName);
//			} else if(reportFileType.equals(HTML)) {
//				jasperToHtml(list, orderNo, templateName);
//			} else if(reportFileType.equals(PNG)) {
//				jasperToPng(list, orderNo, templateName);
//			}

		} else if(orderNo.startsWith(OrderCodeFactory.SALES_RETURN_CODE)) {
//			List<SalesReturnOrder> list = salesReturnOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
//			if(reportFileType.equals(PDF)) {
//				jasperToPdf(list, orderNo, templateName);
//			} else if(reportFileType.equals(HTML)) {
//				jasperToHtml(list, orderNo, templateName);
//			} else if(reportFileType.equals(PNG)) {
//				jasperToPng(list, orderNo, templateName);
//			}

		} else if(orderNo.startsWith(OrderCodeFactory.PRICE_ADJUSTMENT_CODE)) {
//			List<PriceAdjustmentOrder> list = priceAdjustmentOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
//			if(reportFileType.equals(PDF)) {
//				jasperToPdf(list, orderNo, templateName);
//			} else if(reportFileType.equals(HTML)) {
//				jasperToHtml(list, orderNo, templateName);
//			} else if(reportFileType.equals(PNG)) {
//				jasperToPng(list, orderNo, templateName);
//			}

		} else if(orderNo.startsWith(OrderCodeFactory.STOCKTAKING_CODE)) {
//			List<StocktakingOrder> list = stocktakingOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
//			if(reportFileType.equals(PDF)) {
//				jasperToPdf(list, orderNo, templateName);
//			} else if(reportFileType.equals(HTML)) {
//				jasperToHtml(list, orderNo, templateName);
//			} else if(reportFileType.equals(PNG)) {
//				jasperToPng(list, orderNo, templateName);
//			}
		}
	}

	private void jasperToHtml(List<?> list, String orderNo, String templateName) {
		try {
			JRDataSource data = new JRBeanCollectionDataSource(list);
			FileInputStream inputStream = new FileInputStream("ireport/templates/" + templateName);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, data);
			File dir = new File("ireport/generated");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String reportFileName = dir.getPath() + "/"+orderNo+".html";
			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportFileName);
			inputStream.close();
			logger.info("Report(" + orderNo + ") has generated successfully.");
		} catch (Exception e) {
			throw new ExportReportToHtmlFileException("Export report to html file failed.");
		}
	}

	private void jasperToPdf(List<?> list, String orderNo, String templateName) {
		try {
			JRDataSource data= new JRBeanCollectionDataSource(list);
			FileInputStream inputStream = new FileInputStream("ireport/templates/"+templateName);
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, data);
			File dir = new File("ireport/generated");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String pdf = dir.getPath() + "/"+orderNo+".pdf";
			FileOutputStream outputStream = new FileOutputStream(pdf);
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			outputStream.close();
			inputStream.close();
			logger.info("Report(" + orderNo + ") has generated successfully.");
		} catch (Exception e) {
			throw new ExportReportToPdfStreamException("Export report to pdf stream failed.");
		}
	}

	private void jasperToPng(List<?> list, String orderNo, String templateName) {
		try {
			JRDataSource data= new JRBeanCollectionDataSource(list);
			FileInputStream inputStream = new FileInputStream("ireport/templates/"+templateName);
			JasperReport jasperReport= (JasperReport)JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, data);
			File dir = new File("ireport/generated");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String png = dir.getPath() + "/"+orderNo+".png";
			File file = new File(png);
			OutputStream outputStream = new FileOutputStream(file);
			BufferedImage rendered_image = (BufferedImage)JasperPrintManager.printPageToImage(jasperPrint, 0, 1.4f);
	        ImageIO.write(rendered_image, "png", outputStream);
			logger.info("Report(" + orderNo + ") has generated successfully.");
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			throw new ExportReportToPngFileException("Export report to png file failed.");
		}
	}


	public static void main(String[] args) {

		ReportService d = new ReportService();
		d.jasperToPdf(createPurchaseOrderItemCollection(), "CG2020052323525096394287362311041", "采购单.jasper");
	}

	public static List<PurchaseOrderItem> createPurchaseOrderItemCollection() {
		Supplier supplier = new Supplier();
		supplier.setName("大连聚鑫伟业");
		PurchaseOrder po = new PurchaseOrder();
		po.setOrderDate(new Date());
		po.setOrderNo("CG00012312313123");
		po.setInvoiceNo("112233445");
		po.setSupplier(supplier);
		po.setOperator("陈顺谊");
		po.setUserName("无视了");

		Product product = new Product();
		Unit unit = new Unit();
		unit.setName("个");
		product.setUnit(unit);
		Car car = new Car();
		car.setCode("FKS");
		car.setName("福克斯");
		product.setCar(car);

		SKU sku = new SKU();
		sku.setSkuCode("05MDO2.0-GT");
		sku.setSkuName("缸套");
		sku.setProduct(product);

		List<PurchaseOrderItem> listItems = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			PurchaseOrderItem item = new PurchaseOrderItem();
			item.setPurchaseOrder(po);
			item.setSku(sku);
			item.setQuantity(2*i);
			item.setPriceExcludingTax(new BigDecimal(56.22+i).setScale(2, RoundingMode.HALF_UP));
			item.setAmountExcludingTax(new BigDecimal(112.4+i).setScale(2, RoundingMode.HALF_UP));
			item.setTaxAmount(new BigDecimal(i).setScale(2, RoundingMode.HALF_UP));
			item.setNotes("无备注"+i);
			listItems.add(item);
		}
		return listItems;
	}
}
