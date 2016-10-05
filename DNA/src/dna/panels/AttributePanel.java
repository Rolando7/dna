package dna.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTextField;

import dna.Dna;
import dna.dataStructures.AttributeVector;
import dna.dataStructures.StatementType;
import dna.renderer.AttributeCellRenderer;
import dna.renderer.AttributeTableModel;
import dna.renderer.ColorChooserEditor;
import dna.renderer.StatementTypeComboBoxModel;
import dna.renderer.StatementTypeComboBoxRenderer;

@SuppressWarnings("serial")
public class AttributePanel extends JPanel {
	
	JToggleButton attributeToggleButton, searchToggleButton, recodeToggleButton;
	public AttributeTableModel attributeTableModel;
	public JTable attributeTable;
	public JComboBox typeComboBox, entryBox;
	TableRowSorter<AttributeTableModel> sorter;
	AttributeCellRenderer renderer;
	
	public AttributePanel() {
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(new EmptyBorder(0,5,0,5));
		
		// toggle buttons, top right corner
		searchToggleButton = new JToggleButton(new ImageIcon(getClass().getResource("/icons/find.png")));
		searchToggleButton.setPreferredSize(new Dimension(24, 18));
		searchToggleButton.setSelected(false);
		searchToggleButton.setName("searchToggle");
		searchToggleButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) Dna.dna.gui.textPanel.bottomCardPanel.getLayout();
				cl.show(Dna.dna.gui.textPanel.bottomCardPanel, "searchPanel");
				searchToggleButton.setSelected(true);
				recodeToggleButton.setSelected(false);
				attributeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.searchToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.recodeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.attributeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.searchToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.recodeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.attributeToggleButton.setSelected(false);
			}
		});
		recodeToggleButton = new JToggleButton(new ImageIcon(getClass().getResource("/icons/table_edit.png")));
		recodeToggleButton.setPreferredSize(new Dimension(24, 18));
		recodeToggleButton.setSelected(true);
		recodeToggleButton.setName("recodeToggle");
		recodeToggleButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) Dna.dna.gui.textPanel.bottomCardPanel.getLayout();
				cl.show(Dna.dna.gui.textPanel.bottomCardPanel, "recodePanel");
				searchToggleButton.setSelected(false);
				recodeToggleButton.setSelected(true);
				attributeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.searchToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.recodeToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.attributeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.searchToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.recodeToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.attributeToggleButton.setSelected(false);
			}
		});
		attributeToggleButton = new JToggleButton(new ImageIcon(getClass().getResource("/icons/tag_purple.png")));
		attributeToggleButton.setPreferredSize(new Dimension(24, 18));
		attributeToggleButton.setSelected(false);
		attributeToggleButton.setName("attributeToggle");
		attributeToggleButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) Dna.dna.gui.textPanel.bottomCardPanel.getLayout();
				cl.show(Dna.dna.gui.textPanel.bottomCardPanel, "attributePanel");
				searchToggleButton.setSelected(false);
				recodeToggleButton.setSelected(false);
				attributeToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.searchToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.recodeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.recodePanel.attributeToggleButton.setSelected(true);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.searchToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.recodeToggleButton.setSelected(false);
				Dna.dna.gui.textPanel.bottomCardPanel.searchWindow.attributeToggleButton.setSelected(true);
			}
		});
		JPanel switchPanel = new JPanel();
		switchPanel.add(searchToggleButton);
		switchPanel.add(recodeToggleButton);
		switchPanel.add(attributeToggleButton);
		topPanel.add(switchPanel, BorderLayout.EAST);
		
		attributeTableModel = new AttributeTableModel();
		attributeTable = new JTable(attributeTableModel);
		JScrollPane sp = new JScrollPane(attributeTable);
		sp.setPreferredSize(new Dimension(300, 150));

		attributeTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 150 );
		attributeTable.getColumnModel().getColumn( 1 ).setPreferredWidth( 25 );
		attributeTable.getColumnModel().getColumn( 2 ).setPreferredWidth( 150 );
		attributeTable.getColumnModel().getColumn( 3 ).setPreferredWidth( 150 );
		attributeTable.getColumnModel().getColumn( 4 ).setPreferredWidth( 150 );
		renderer = new AttributeCellRenderer();
		attributeTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
		ColorChooserEditor cce = new ColorChooserEditor();
		attributeTable.getColumnModel().getColumn(1).setCellEditor(cce);
		
		// combo boxes and buttons, top left
		StatementTypeComboBoxRenderer renderer = new StatementTypeComboBoxRenderer();
		StatementTypeComboBoxModel model = new StatementTypeComboBoxModel();
		typeComboBox = new JComboBox(model);
		typeComboBox.setRenderer(renderer);
		typeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ArrayList<String> variables = new ArrayList<String>();
				Iterator<String> it = ((StatementType) typeComboBox.getSelectedItem()).getVariables().keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					String type = ((StatementType) typeComboBox.getSelectedItem()).getVariables().get(key);
					if (type.equals("short text") || type.equals("long text")) {
						variables.add(key);
					}
				}
				String[] varArray = new String[variables.size()];
				varArray = variables.toArray(varArray);
				entryBox.setModel(new DefaultComboBoxModel(varArray));
				variableFilter((String) entryBox.getSelectedItem(), ((StatementType) typeComboBox.getSelectedItem()).getId());
			}
		});
		typeComboBox.setPreferredSize(new Dimension(200, 30));
		entryBox = new JComboBox();
		entryBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				variableFilter((String) entryBox.getSelectedItem(), ((StatementType) typeComboBox.getSelectedItem()).getId());
			}
		});
		entryBox.setPreferredSize(new Dimension(200, 30));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(typeComboBox);
		buttonPanel.add(entryBox);
		topPanel.add(buttonPanel, BorderLayout.WEST);
		
		sorter = new TableRowSorter<AttributeTableModel>(attributeTableModel);
		attributeTable.setRowSorter(sorter);
		emptyFilter();
		
		//panel with field and buttons at the bottom
		JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JXTextField newField = new JXTextField("New value...");
		newField.setColumns(14);
		lowerPanel.add(newField);
		Icon addIcon = new ImageIcon(getClass().getResource("/icons/table_row_insert.png"));
		JButton addButton = new JButton("Add entry", addIcon);
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width, newField.getPreferredSize().height));
		lowerPanel.add(addButton);
		Icon deleteIcon = new ImageIcon(getClass().getResource("/icons/table_row_delete.png"));
		JButton deleteButton = new JButton("Delete entry", deleteIcon);
		deleteButton.setPreferredSize(new Dimension(deleteButton.getPreferredSize().width, newField.getPreferredSize().height));
		lowerPanel.add(deleteButton);
		Icon cleanUpIcon = new ImageIcon(getClass().getResource("/icons/table_delete.png"));
		JButton cleanUpButton = new JButton("Clean up", cleanUpIcon);
		cleanUpButton.setPreferredSize(new Dimension(cleanUpButton.getPreferredSize().width, newField.getPreferredSize().height));
		lowerPanel.add(cleanUpButton);
		Icon addMissingIcon = new ImageIcon(getClass().getResource("/icons/table_add.png"));
		JButton addMissingButton = new JButton("Add missing", addMissingIcon);
		addMissingButton.setPreferredSize(new Dimension(addMissingButton.getPreferredSize().width, newField.getPreferredSize().height));
		lowerPanel.add(addMissingButton);
		
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = newField.getText();
				int id = Dna.data.generateNewId("attributes");
				int statementTypeId = ((StatementType) typeComboBox.getSelectedItem()).getId();
				String variable = (String) entryBox.getSelectedItem();
				boolean exists = false;
				for (int i = 0; i < Dna.data.getAttributes().size(); i++) {
					if (Dna.data.getAttributes().get(i).getValue().equals(value) && Dna.data.getAttributes().get(i).getVariable().equals(variable) 
							&& Dna.data.getAttributes().get(i).getStatementTypeId() == statementTypeId) {
						exists = true;
						break;
					}
				}
				if (!exists) {
					AttributeVector av = new AttributeVector(id, value, new Color(0, 0, 0), "", "", "", "", statementTypeId, variable);
					attributeTableModel.addRow(av);
				}
			}
		});
		
		deleteButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int modelRow = attributeTable.convertRowIndexToModel(attributeTable.getSelectedRow());
				if (!attributeTableModel.get(modelRow).isInDataset()) {
					attributeTableModel.deleteRow(modelRow);
				} else {
					System.err.println("Entry cannot be deleted because it is still present in the dataset.");
				}
			}
		});
		
		cleanUpButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		addMissingButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);
	}
	
	private void variableFilter(String variable, int statementTypeId) {
		RowFilter<AttributeTableModel, Integer> attributeFilter = new RowFilter<AttributeTableModel, Integer>() {
			public boolean include(Entry<? extends AttributeTableModel, ? extends Integer> entry) {
				AttributeTableModel atm = entry.getModel();
				AttributeVector av = atm.get(entry.getIdentifier());
				int stId = av.getStatementTypeId();
				String var = av.getVariable();
				if (variable.equals(var) && stId == statementTypeId) {
					return true;
				} else {
					return false;
				}
			}
		};
		sorter.setRowFilter(attributeFilter);
	}
	
	private void emptyFilter() {
		RowFilter<AttributeTableModel, Integer> attributeFilter = new RowFilter<AttributeTableModel, Integer>() {
			public boolean include(Entry<? extends AttributeTableModel, ? extends Integer> entry) {
				return false;
			}
		};
		sorter.setRowFilter(attributeFilter);
	}
}
