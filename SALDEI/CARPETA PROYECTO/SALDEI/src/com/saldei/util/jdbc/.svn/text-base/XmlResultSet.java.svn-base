/**
 * Proyecto: Tesis
 * Desarrollado: Will
 * Desarrollado para trabajo de graduacion Ciclo 01 - 2008
 */
package com.saldei.util.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlResultSet {
		
		private static final long serialVersionUID = 1L;
		private static String MAIN = "root"; 
		private static String COLUMNS = "columns";
		private static String COL_ATT_COUNT = "columnCount";
		private static String COLUMN = "column";
		private static String CLASS = "class";
		private static String ROWS = "rows";
		private static String ROWS_ATT_COUNT = "rowCount";
		private static String ROW = "row";	
		private static String COL_ID = "id";
			
		private String xml;
			
		public XmlResultSet(ResultSet rs){
			if (rs == null) {
				throw new NullPointerException();
			}
			Document document = build(rs);
			xml = document.asXML();			
		}		
		
		protected Document build(ResultSet rs){
			Document doc = null;
			try{
				ResultSetMetaData rsmd = rs.getMetaData();
				int intColCount = rsmd.getColumnCount();
				doc = DocumentHelper.createDocument();
		        Element root = doc.addElement(MAIN);
				Element columns = root.addElement(COLUMNS);
				Element rows = root.addElement(ROWS);
					
				int rowCount = 0;					
				String[] columnName = new String[intColCount];
				for (int index = 0; index < intColCount; index++) {
					columnName[index] = rsmd.getColumnName(index + 1);
					Element column = columns.addElement(COLUMN);
					column.addText(columnName[index]);
					column.addAttribute(CLASS, rsmd.getColumnClassName(index + 1));
				}
				columns.addAttribute(COL_ATT_COUNT, String.valueOf(intColCount));
				while (rs.next()) {
					Element row = rows.addElement(ROW);
					for (int col = 1; col <= intColCount; col++) {
						String name = columnName[col - 1];
						String value = rs.getString(col);
						Element node = row.addElement(COLUMN);
						node.addAttribute(COL_ID, name);
						if (!rs.wasNull()) {
							node.addText(value.toString());
						}
					}
					rowCount++;
				}
				rows.addAttribute(ROWS_ATT_COUNT, String.valueOf(rowCount));
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			return doc;
		}	

			/**
			 * 
			 * @return String que posee el Xml del ResultSet
			 */
			public String getXml() {		
				return xml;
			}//getXml

}//clase
