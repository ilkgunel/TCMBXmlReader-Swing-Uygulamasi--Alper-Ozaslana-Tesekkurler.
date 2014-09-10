/**
 * @author Alper
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class CurrencyPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JScrollPane pane = null;
	
	private JTable table = null;

	public CurrencyPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(830, 600);
		this.setLayout(null);
		this.add(getPane(),null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("TCMB Reader");
		getContentPane().setBackground(Color.orange);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	
	private JScrollPane getPane(){
		if(pane == null){
			table = new JTable(new TableModel(),new TableColumnModel());
			ArrayList<Currency> list = TCMBReader.getInstance().getCurrencies();
			pane = new JScrollPane(table);
			pane.setBounds(10, 10, 800, 540);
			fillTable(list);
			table.setFillsViewportHeight(true);
			JTableHeader header = table.getTableHeader();
			header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
			header.setBackground(Color.DARK_GRAY);
			header.setForeground(Color.yellow);
			table.setTableHeader(header);
			table.setAutoCreateRowSorter(true);
		}
		return pane;
	}
	
	private void fillTable(ArrayList<Currency> list) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(Currency currceny:list){
			Vector<Object> row = new Vector<Object>();
			row.add(currceny.getCurrencyCode());
			row.add(currceny.getUnit());
			row.add(currceny.getIsim()+" - "+currceny.getCurrencyName());
			row.add(currceny.getForexBuying());
			row.add(currceny.getForexSelling());
			row.add(currceny.getBanknoteBuying());
			row.add(currceny.getBanknoteSelling());
			model.addRow(row);
		}
		table.setModel(model);
	}

	class TableModel extends DefaultTableModel{

		private static final long serialVersionUID = 1L;

		public TableModel() {
			addColumn("Döviz Kodu");
			addColumn("Birim");
			addColumn("Döviz Adý");
			addColumn("Döviz Alýþ");
			addColumn("Döviz Satýþ");
			addColumn("Efektif Alýþ");
			addColumn("Efektif Satýþ");
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	class TableColumnModel extends DefaultTableColumnModel{
		
		private static final long serialVersionUID = 1L;

		public TableColumnModel() {
			addColumn(new TableColumn(0));
			addColumn(new TableColumn(1));
			addColumn(new TableColumn(2));
			addColumn(new TableColumn(3));
			addColumn(new TableColumn(4));
			addColumn(new TableColumn(5));
			addColumn(new TableColumn(6));
			getColumn(0).setHeaderValue("Döviz Kodu");
			getColumn(1).setHeaderValue("Birim");
			getColumn(2).setHeaderValue("Döviz Adý");
			getColumn(3).setHeaderValue("Döviz Alýþ");
			getColumn(4).setHeaderValue("Döviz Satýþ");
			getColumn(5).setHeaderValue("Efektif Alýþ");
			getColumn(6).setHeaderValue("Efektif Satýþ");
			getColumn(2).setPreferredWidth(290);
			getColumn(2).setMaxWidth(290);
			getColumn(2).setMinWidth(290);
			getColumn(1).setPreferredWidth(70);
			getColumn(1).setMaxWidth(70);
			getColumn(1).setMinWidth(70);
		}
	}
	
	

}
