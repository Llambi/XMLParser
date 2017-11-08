package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import logic.Receta;
import main.ParserManager;
import net.miginfocom.swing.MigLayout;

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCerrar;
	private JPanel pnBotonera;
	private JPanel pnBody;
	private JButton btnSeleccionarRecetario;
	private JScrollPane sPnRecetario;
	private JList<Receta> recetarioList;
	private DefaultListModel<Receta> recetarioModel;
	private JPanel pnAux1;
	private JButton btnSeleccionar;
	private JScrollPane sPnCompra;
	private JList<Receta> compraList;
	private DefaultListModel<Receta> compraModel;
	private JButton btnGenerarListaCompra;

	private ParserManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		recetarioModel = new DefaultListModel<Receta>();
		compraModel = new DefaultListModel<Receta>();

		setTitle("Generrador Lista de la Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 429);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnBotonera(), BorderLayout.SOUTH);
		contentPane.add(getPnBody(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}

	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnCerrar.setMnemonic('C');
		}
		return btnCerrar;
	}

	private void cerrar() {
		this.dispose();
	}

	private JPanel getPnBotonera() {
		if (pnBotonera == null) {
			pnBotonera = new JPanel();
			pnBotonera.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) pnBotonera.getLayout();
			flowLayout.setAlignment(FlowLayout.TRAILING);
			pnBotonera.add(getBtnCerrar());
		}
		return pnBotonera;
	}

	private JPanel getPnBody() {
		if (pnBody == null) {
			pnBody = new JPanel();
			pnBody.setBackground(Color.WHITE);
			pnBody.setLayout(new MigLayout("", "[36.23%,grow][103.00,center][34.59%,grow]", "[][grow][]"));
			pnBody.add(getBtnSeleccionarRecetario(), "cell 0 0,alignx center,aligny center");
			pnBody.add(getSPnRecetario(), "cell 0 1,grow");
			pnBody.add(getPnAux1(), "cell 1 1,grow");
			pnBody.add(getScrollPane_1(), "cell 2 1,grow");
			pnBody.add(getBtnGenerarListaCompra(), "cell 2 2,alignx center,aligny center");
		}
		return pnBody;
	}

	private JButton getBtnSeleccionarRecetario() {
		if (btnSeleccionarRecetario == null) {
			btnSeleccionarRecetario = new JButton("Seleccionar Recetario");
			btnSeleccionarRecetario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cleanRecetario();
					selectRecetarioXML();
					loadRecetarioModel();
					btnGenerarListaCompra.setEnabled(true);
				}
			});
		}
		return btnSeleccionarRecetario;
	}

	private void selectRecetarioXML() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Seleccione el recetario a cargar");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println("Selected file: " + selectedFile);
			try {
				manager = new ParserManager(new File(selectedFile));
			} catch (DocumentException e) {
				JOptionPane.showMessageDialog(this, "Error en el documento.", "ERROR CON EL DOCUMENTO",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		}

	}

	private JScrollPane getSPnRecetario() {
		if (sPnRecetario == null) {
			sPnRecetario = new JScrollPane();
			sPnRecetario.setBorder(
					new TitledBorder(null, "Recetario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			sPnRecetario.setViewportView(getList());
		}
		return sPnRecetario;
	}

	private JList<Receta> getList() {
		if (recetarioList == null) {
			recetarioList = new JList<Receta>();
			recetarioList.setBorder(null);
			recetarioList.setModel(recetarioModel);
		}
		return recetarioList;
	}

	private void loadRecetarioModel() {
		ArrayList<Receta> recetario = manager.getRecetario().getRecetas();
		for (Receta receta : recetario) {
			recetarioModel.addElement(receta);
		}
		recetarioList.setModel(recetarioModel);
	}

	private JPanel getPnAux1() {
		if (pnAux1 == null) {
			pnAux1 = new JPanel();
			pnAux1.setBackground(Color.WHITE);
			pnAux1.setLayout(new MigLayout("", "[115px]", "[29px,grow]"));
			pnAux1.add(getBtnSeleccionar(), "cell 0 0,alignx center,aligny center");
		}
		return pnAux1;
	}

	private JButton getBtnSeleccionar() {
		if (btnSeleccionar == null) {
			btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadCompraModel();
				}
			});
		}
		return btnSeleccionar;
	}

	private void loadCompraModel() {
		int[] indices = recetarioList.getSelectedIndices();
		for (int i = 0; i < indices.length; i++) {
			Receta receta = recetarioModel.getElementAt(indices[i]);
			if(!manager.getCompra().isInCompra(receta))
			{
				manager.getCompra().addReceta(receta);
				compraModel.addElement(receta);
			}
			
		}
		compraList.setModel(compraModel);
	}

	private void cleanCompra() {
		compraModel.removeAllElements();
		btnGenerarListaCompra.setEnabled(false);
	}

	private void cleanRecetario() {
		recetarioModel.removeAllElements();
	}

	private JScrollPane getScrollPane_1() {
		if (sPnCompra == null) {
			sPnCompra = new JScrollPane();
			sPnCompra.setBorder(new TitledBorder(null, "Lista de la Compra", TitledBorder.LEADING, TitledBorder.TOP,
					null, Color.BLACK));
			sPnCompra.setViewportView(getList_1());
		}
		return sPnCompra;
	}

	private JList<Receta> getList_1() {
		if (compraList == null) {
			compraList = new JList<Receta>();
			compraList.setBorder(null);
			compraList.setModel(compraModel);
			compraList.setEnabled(false);
		}
		return compraList;
	}

	private JButton getBtnGenerarListaCompra() {
		if (btnGenerarListaCompra == null) {
			btnGenerarListaCompra = new JButton("Generar Lista de la Compra");
			btnGenerarListaCompra.setEnabled(false);
			btnGenerarListaCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					URI uri = selectDestinoCompra();
					manager.printCompra(uri);
					cleanCompra();
					JOptionPane.showMessageDialog(null, "Lista de la compra generada correctamente", "CARRO DE LA COMPRA CORRECTO",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return btnGenerarListaCompra;
	}

	private URI selectDestinoCompra() {
		JFileChooser DirectoryChooser = new JFileChooser();
		URI selectedDirectory = null;
		DirectoryChooser.setMultiSelectionEnabled(false);
		DirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		DirectoryChooser.setDialogTitle("Seleccione el destino de la lista de la compra.");
		DirectoryChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = DirectoryChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String selectedFile = DirectoryChooser.getSelectedFile().getAbsolutePath();
			System.out.println("Selected file: " + selectedFile);
			try {
				selectedDirectory = new URI(selectedFile);
			} catch (URISyntaxException | IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, "Error con el destino", "ERROR CON EL DESTINO",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		}
		return selectedDirectory;

	}
}
