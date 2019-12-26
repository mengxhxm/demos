package com.zc.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * ����Excel�ĵ�������
 */
@SuppressWarnings("all")
public class FileUtil {

	public static void toWriteExcelFile(HttpServletResponse response, String fileName, List<Map<String, Object>> list,
			String[] keys, String[] names) {
		InputStream is = formatListDataToWorkBookInputStream(fileName, list, keys, names);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		File tempfile = null;
		try {
			// ��ȡ�ļ�·��
			String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../")
					.replaceAll("file:/", "").replaceAll("%20", " ").trim();
			if (path.indexOf(":") != 1) {
				path = File.separator + path;
			}
			// ������ʱ�ļ�
			tempfile = new File(path + System.currentTimeMillis() + fileName);
			FileUtils.copyInputStreamToFile(is, tempfile);
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			in = new BufferedInputStream(new FileInputStream(tempfile));
			out = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(new MimetypesFileTypeMap().getContentType(tempfile));// ����response���ݵ�����
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);// ����ͷ����Ϣ
			byte[] buffer = new byte[10240];
			int length = 0;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if (tempfile != null) {
					tempfile.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 2019��1��4�� ���� ��list����ת��Ϊ�ļ���
	 * 
	 * @param excelFileName
	 * @param type
	 * @param dataList
	 * @param keys
	 * @param columnNames
	 * @param response
	 * @return
	 */
	public static InputStream formatListDataToWorkBookInputStream(String excelFileName, List dataList, String[] keys,
			String[] columnNames) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			FileUtil.createExcel(excelFileName, dataList, keys, columnNames).write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			return is;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ѯ
	 * 
	 * @param excelFileName
	 * @param type
	 * @param dataList
	 * @param response
	 */
	public static Object reportExport(String excelFileName, int type, List dataList, String[] keys,
			String[] columnNames, HttpServletResponse response) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			FileUtil.createExcel(excelFileName, dataList, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// ����response���������Դ�����ҳ��
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(excelFileName.getBytes(), "iso8859-1") + ".xls");
			response.addHeader("Content-Length", "" + content.length);
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((excelFileName + ".xls").getBytes(), "iso-8859-1"));
			out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	/**
	 * ����excel�ĵ���
	 * 
	 * @param excelName   ����������
	 * @param list        ����
	 * @param keys        list��map��key���鼯��
	 * @param columnNames excel������
	 */
	public static Workbook createExcel(String excelName, List list, String[] keys, String columnNames[]) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (list == null || list.size() == 0) {

		} else {
			for (Object obj : list) {
				if (obj instanceof Map) {
					// ����������map������Ҫת��
					mapList = list;
					break;
				}
				mapList.add(bean2Map(obj));
			}
		}
		return createWorkBook(excelName, mapList, keys, columnNames);
	}

	public static Map<String, Object> bean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// ����class����
				if (!key.equals("class")) {
					// �õ�property��Ӧ��getter����
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	/**
	 * ����excel�ĵ���
	 * 
	 * @param list        ����
	 * @param keys        list��map��key���鼯��
	 * @param columnNames excel������
	 */
	private static Workbook createWorkBook(String excelName, List<Map<String, Object>> list, String[] keys,
			String columnNames[]) {
		// ����excel������
		HSSFWorkbook wb = new HSSFWorkbook();
		// ������һ��sheet��ҳ����������
		Sheet sheet = wb.createSheet(excelName);
		// �ֶ������п���һ��������ʾҪΪ�ڼ����裻���ڶ���������ʾ�еĿ�ȣ�nΪ�иߵ���������
		// ������һ��
		Row row = sheet.createRow((short) 0);

		// �������ֵ�Ԫ���ʽ
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// ������������
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// ������һ��������ʽ������������
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBold(true);

		// �����ڶ���������ʽ������ֵ��
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		// ���õ�һ�ֵ�Ԫ�����ʽ������������
		cs.setFont(f);
		cs.setBorderLeft(BorderStyle.THIN);
		cs.setBorderRight(BorderStyle.THIN);
		cs.setBorderTop(BorderStyle.THIN);
		cs.setBorderBottom(BorderStyle.THIN);
		cs.setAlignment(HorizontalAlignment.CENTER);
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// ����ɫ40%��ɫ
		cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		// ���õڶ��ֵ�Ԫ�����ʽ������ֵ��
		cs2.setFont(f2);
		cs2.setBorderLeft(BorderStyle.THIN);
		cs2.setBorderRight(BorderStyle.THIN);
		cs2.setBorderTop(BorderStyle.THIN);
		cs2.setBorderBottom(BorderStyle.THIN);
		cs2.setAlignment(HorizontalAlignment.LEFT);
		cs2.setDataFormat(wb.createDataFormat().getFormat("@"));// ��ʽ��Ϊ�ı�����
		// ��������
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		Map<Integer, Integer> maxWidth = new HashMap<Integer, Integer>();
		// ����ÿ��ÿ�е�ֵ
		for (short i = 0; i < list.size(); i++) {
			// Row ��,Cell ���� , Row �� Cell ���Ǵ�0��ʼ������
			// ����һ�У���ҳsheet��
			Row row1 = sheet.createRow((short) i + 1);
			row1.createCell(0).getCellStyle().setWrapText(true);
			row1.createCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);// ˮƽ����
			row1.createCell(0).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);// ��ֱ����
			// ��row���ϴ���һ������
			for (short j = 0; j < keys.length; j++) {
				String key = keys[j];
				Cell cell = row1.createCell(j);
				cell.setCellStyle(cs2);
				int pointIndex = key.indexOf(".");
				if (pointIndex == -1) {
					Object value = list.get(i).get(keys[j]);
					cell.setCellValue(value == null ? " " : value.toString());
				} else {
					Object value = list.get(i).get(key.substring(0, pointIndex));
					Object finalVal = null;
					// �����༶�ṹ,a.b
					String propertyName = key.substring(pointIndex + 1);
					try {
						PropertyDescriptor pd = new PropertyDescriptor(propertyName, value.getClass());
						Method methodGet = pd.getReadMethod();
						finalVal = methodGet.invoke(value);
					} catch (Exception e) {
						e.printStackTrace();
					}
					cell.setCellValue(finalVal == null ? "" : finalVal.toString());
				}
			}

		}
		return wb;
	}
}