
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * This is like TableDemo, except that it substitutes a
 * Favorite Color column for the Last Name column and specifies
 * a custom cell renderer and editor for the color data.
 */
public class ColorTest extends JPanel {
    private boolean DEBUG = false;

    public ColorTest() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up renderer and editor for the Favorite Color column.
        table.setDefaultRenderer(Color.class,
                                 new ColorRenderer(true));
        table.setDefaultEditor(Color.class,
                               new ColorEditor());

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Courses",
                                        "Projected Difficulty",
                                        "Year",
                                        "Total Hours per Week",
                                        "Generate new schedule?"};
        private Object[][] data = {
            {"Math0100, CSCI0170, "
            		+ "RUSS150E, ECON0110", new Color(124, 252, 0),
             "First-Year S1", new Integer(45), new Boolean(false)},
            {"Math0180, CSCI0180, COLT0340, CS0220", new Color(124, 252, 0),
            	 "First-Year S2", new Integer(51), new Boolean(true)},
            {"Math0520, CSCI0320, ENGN0090, CS0330", new Color(255, 0, 0),
                    	 "Sophomore S1", new Integer(53), new Boolean(true)},
            {"Math1010, CSCI1010, ENGN0030, ECON0210", new Color(124, 252, 0),
             "Sophomore S2", new Integer(55), new Boolean(false)},
            {"Math1230, APMA1650, ENGN0040, APMA0350", new Color(255, 165, 0),
            	 "Junior S1", new Integer(60), new Boolean(true)},
            {"Math1530, CSCI1420, CSCI1410, PHIL1590", new Color(255, 215, 0),
                	 "Junior S2", new Integer(56), new Boolean(true)},
            {"Math1690, CSCI1690, APMA1740, Math2000B", new Color(255, 165, 0),
                    	 "Senior S1", new Integer(49), new Boolean(true)},
            {"Math2520, CSCI2090, APMA1920, SPAN0600", new Color(255, 0, 0),
                        	 "Senior S2", new Integer(50), new Boolean(true)}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("TableDialogEditDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorTest();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
}
