package art.java.arithmeticparser.view.swing;

import art.java.arithmeticparser.parser.Parser;
import art.java.arithmeticparser.parser.ParserException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SyntaxAnalizator extends javax.swing.JFrame{
	private static final long serialVersionUID = 2725777063968849644L;
	private javax.swing.JButton calculate;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea textArea;
	private javax.swing.JTextField resultTextField;
	
    public SyntaxAnalizator() {
        initComponents();
        //this.getRootPane().setDefaultButton(this.calculate);
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        calculate = new javax.swing.JButton();
        resultTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Арифметический анализатор выражений");
        setFont(new java.awt.Font("Times New Roman", 0, 14)); 
        setResizable(false);

        calculate.setFont(new java.awt.Font("Times New Roman", 0, 14)); 
        calculate.setText("Рассчитать");
        calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double result = new Parser().evaluate(textArea.getText());
					resultTextField.setText(result + "");
				} catch (ParserException ex) {
					showErrorMessage(ex.getMessage());
				}
			}
		});

        resultTextField.setFont(new java.awt.Font("Times New Roman", 0, 18));
        resultTextField.setText("");

        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Times New Roman", 2, 18));
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel2.setText("Полученное значение:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel3.setText("Введите выражение");
        
        initGroupLayout();
        

        pack();
    }
    private void initGroupLayout() {
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42)
                        .addComponent(resultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calculate))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calculate)
                    .addComponent(resultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
	}
    private void showErrorMessage(String text) {
		JOptionPane.showMessageDialog(this, text, "Error",
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(String args[]) {
        try {
        	//UIManager.setLookAndFeel(new AluminiumLookAndFeel());
            new SyntaxAnalizator().setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }                 
}
