package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clinica.Ambulancia;
import medicos.IMedico;
import pacientes.Paciente;

public class VentanaClinica extends JFrame implements IVistaFacturacion, IVistaMedicos, KeyListener, IVistaAmbulancia {

	private JPanel contentPane;
	private JTabbedPane tabPanel;
	private JPanel panelFacturacion;
	private JPanel panelAmbulancia;
	private JPanel panelListas;
	private JPanel panelSalidaFactuacion;
	private JTextArea textAreaFacturacion;
	private JPanel panelAcciones;
	private JPanel panelCantidad;
	private JLabel lblCantidad;
	private JTextField textFieldCantidad;
	private JPanel panelContenedorTextFieldCantidad;
	private JPanel panelHabitacion;
	private JPanel panelContenedorLblCantidad;
	private JLabel lblTipoDeHabitacion;
	private JComboBox comboBoxTipoDeHabitacion;
	private JPanel panelContenedorLblTipoDeHabitacion;
	private JPanel panelPanelContenedorComboBoxTipoDeHabitacion;
	private JPanel panelBotonesAgregarPrestaciones;
	private JButton btnAgregarAtencionMedica;
	private JPanel panelContenedorBtnAgregarAtencionMedica;
	private JScrollPane scrollPaneListPacientes;
	private JScrollPane scrollPaneListMedicos;
	private JList<Paciente> listPacientesEnFacturacion;
	private JList<IMedico> listMedicosEnFacturacion;
	private DefaultListModel<IMedico> listaMedicos = new DefaultListModel<IMedico>();
	private DefaultListModel<Paciente> ListaPacientesEnAtencion = new DefaultListModel<Paciente>();
	private JPanel panelBtnFacturar;
	private JPanel panelContenedorFacturar;
	private JButton btnFacturar;
	private JButton btnAgregarInternacion;
	private JPanel panelContenedorAgregarInternacion;
	private JLabel lblPacientesEnAtencion;
	private JLabel lblMedicosEnFacturacion;
	private JScrollPane scrollPane;
	private JList<IMedico> listMedicosEnMedicos;
	private JPanel panelOperacionesConMedicos;
	private JPanel panelNombreMedico;
	private JLabel lblNombreMedico;
	private JTextField textFieldNombreMedico;
	private JPanel panelContenedorlblNombreMedico;
	private JPanel panelContenedorTextFieldNombreMedico;
	private JPanel panelApellidoMedico;
	private JPanel panelContenedorlblApellidoMedico;
	private JLabel lblApellido;
	private JPanel panelContenedorTextFieldApellidoMedico;
	private JTextField textFieldApellidoMedico;
	private JPanel panelDniMedico;
	private JPanel panelContenedorlblDniMedico;
	private JLabel lblDniMedico;
	private JPanel panelContenedorTextFieldDniMedico;
	private JTextField textFieldDniMedico;
	private JPanel panelTelefonoMedico;
	private JPanel panelContenedorlblTelefonoMedico;
	private JLabel lblTelefonoMedico;
	private JPanel panelContenedorTextFieldTelefonoMedico;
	private JTextField textFieldTelefonoMedico;
	private JPanel panelCiudadMedico;
	private JPanel panelContenedorlblCiudadMedico;
	private JLabel lblCiudadMedico;
	private JPanel panelContenedorTextFieldCiudadMedico;
	private JTextField textFieldCiudadMedico;
	private JPanel panelMatriculaMedico;
	private JPanel panelContenedorlblMatriculaMedico;
	private JLabel lblMatriculaMedico;
	private JPanel panelContenedorTextFieldMatriculaMedico;
	private JTextField textFieldMatriculaMedico;
	private JPanel panelDomicilioMedico;
	private JPanel panelContenedorlblDomicilioMedico;
	private JLabel lblCiudadDomicilioMedico;
	private JPanel panelContenedorTextFieldNumeroDeDomMedico;
	private JTextField textFieldNumeroDeDomicilioMedico;
	private JPanel panelContenedorTextFieldCalleMedico;
	private JTextField textFieldCalleMedico;
	private JPanel panelBotonesMedico;
	private JButton btnAgregarMedico;
	private JPanel panelContenedorBtnAgregarMedico;
	private JPanel panelInformacionMedico;
	private JComboBox comboBoxEspecialidad;
	private JPanel panelContenedorEspecialidad;
	private JPanel panelContenedorContratacion;
	private JComboBox comboBoxContratacion;
	private JPanel panelContenedorPosgrado;
	private JComboBox comboBoxPosgrado;
	private JPanel panelBotonesAmbulancia;
	private JPanel panelListaPacAmbulancia;
	private PanelAmbulancia panelEstadoAmbulancia;
	private JScrollPane scrollPaneListPac;
	private JList<Paciente> listPacien;
	private JTextArea textAreaEstadoAmbulancia;
	private JButton btnLlamaTranslado;
	private JButton btnSolReparacion;
	private JButton btnLlamaAtencion;
	private JPanel panelBtLlama;
	private JPanel panel_BtAtencion;
	private JPanel panel_BtReparacion;
	private DefaultListModel<Paciente> listaPacienteHistoricos = new DefaultListModel<Paciente>();

	

	/**
	 * Create the frame.
	 */
	public VentanaClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.tabPanel = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabPanel);

		this.panelFacturacion = new JPanel();
		this.tabPanel.addTab("Facturacion", null, this.panelFacturacion, null);
		this.panelFacturacion.setLayout(new BorderLayout(0, 0));

		this.panelListas = new JPanel();
		this.panelFacturacion.add(this.panelListas, BorderLayout.CENTER);
		this.panelListas.setLayout(new GridLayout(0, 2, 0, 0));

		this.scrollPaneListPacientes = new JScrollPane();
		this.panelListas.add(this.scrollPaneListPacientes);

		this.listPacientesEnFacturacion = new JList<Paciente>(this.ListaPacientesEnAtencion);
		this.scrollPaneListPacientes.setViewportView(this.listPacientesEnFacturacion);

		this.lblPacientesEnAtencion = new JLabel("Pacientes en atencion");
		this.lblPacientesEnAtencion.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPaneListPacientes.setColumnHeaderView(this.lblPacientesEnAtencion);

		this.scrollPaneListMedicos = new JScrollPane();
		this.panelListas.add(this.scrollPaneListMedicos);

		this.listMedicosEnFacturacion = new JList<IMedico>(this.listaMedicos);
		this.scrollPaneListMedicos.setViewportView(this.listMedicosEnFacturacion);

		this.lblMedicosEnFacturacion = new JLabel("Medicos ");
		this.lblMedicosEnFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPaneListMedicos.setColumnHeaderView(this.lblMedicosEnFacturacion);

		this.panelSalidaFactuacion = new JPanel();
		this.panelFacturacion.add(this.panelSalidaFactuacion, BorderLayout.SOUTH);
		this.panelSalidaFactuacion.setLayout(new GridLayout(0, 1, 0, 0));

		this.textAreaFacturacion = new JTextArea();
		this.textAreaFacturacion.setRows(10);
		this.textAreaFacturacion.setEditable(false);
		this.panelSalidaFactuacion.add(this.textAreaFacturacion);

		this.panelAcciones = new JPanel();
		this.panelFacturacion.add(this.panelAcciones, BorderLayout.EAST);
		this.panelAcciones.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelCantidad = new JPanel();
		this.panelAcciones.add(this.panelCantidad);
		this.panelCantidad.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblCantidad = new JPanel();
		this.panelCantidad.add(this.panelContenedorLblCantidad);

		this.lblCantidad = new JLabel("Cantidad");
		this.panelContenedorLblCantidad.add(this.lblCantidad);

		this.panelContenedorTextFieldCantidad = new JPanel();
		FlowLayout fl_panelContenedorTextFieldCantidad = (FlowLayout) this.panelContenedorTextFieldCantidad.getLayout();
		this.panelCantidad.add(this.panelContenedorTextFieldCantidad);

		this.textFieldCantidad = new JTextField();
		this.panelContenedorTextFieldCantidad.add(this.textFieldCantidad);
		this.textFieldCantidad.setColumns(10);

		this.panelHabitacion = new JPanel();
		this.panelAcciones.add(this.panelHabitacion);
		this.panelHabitacion.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblTipoDeHabitacion = new JPanel();
		this.panelHabitacion.add(this.panelContenedorLblTipoDeHabitacion);

		this.lblTipoDeHabitacion = new JLabel("Tipo de habitacion");
		this.panelContenedorLblTipoDeHabitacion.add(this.lblTipoDeHabitacion);

		this.panelPanelContenedorComboBoxTipoDeHabitacion = new JPanel();
		this.panelHabitacion.add(this.panelPanelContenedorComboBoxTipoDeHabitacion);

		this.comboBoxTipoDeHabitacion = new JComboBox();
		this.comboBoxTipoDeHabitacion.setModel(new DefaultComboBoxModel(
				new String[] { "Habitacion Compartida", "Habitacion Privada", "Terapia Intensiva" }));
		this.comboBoxTipoDeHabitacion.setName("Tipo de habitacion");
		this.comboBoxTipoDeHabitacion.setToolTipText("");
		this.panelPanelContenedorComboBoxTipoDeHabitacion.add(this.comboBoxTipoDeHabitacion);

		this.panelBotonesAgregarPrestaciones = new JPanel();
		this.panelAcciones.add(this.panelBotonesAgregarPrestaciones);
		this.panelBotonesAgregarPrestaciones.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorBtnAgregarAtencionMedica = new JPanel();
		this.panelBotonesAgregarPrestaciones.add(this.panelContenedorBtnAgregarAtencionMedica);

		this.btnAgregarAtencionMedica = new JButton("Agregar Atencion Medica");
		this.panelContenedorBtnAgregarAtencionMedica.add(this.btnAgregarAtencionMedica);

		this.panelContenedorAgregarInternacion = new JPanel();
		this.panelBotonesAgregarPrestaciones.add(this.panelContenedorAgregarInternacion);

		this.btnAgregarInternacion = new JButton("Agregar Internacion");
		this.panelContenedorAgregarInternacion.add(this.btnAgregarInternacion);

		this.panelBtnFacturar = new JPanel();
		this.panelAcciones.add(this.panelBtnFacturar);
		this.panelBtnFacturar.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContenedorFacturar = new JPanel();
		this.panelBtnFacturar.add(this.panelContenedorFacturar);

		this.btnFacturar = new JButton("Facturar");
		this.panelContenedorFacturar.add(this.btnFacturar);

		this.panelAmbulancia = new JPanel();
		this.tabPanel.addTab("Ambulancia", null, this.panelAmbulancia, null);
		this.panelAmbulancia.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.panelBotonesAmbulancia = new JPanel();
		this.panelAmbulancia.add(this.panelBotonesAmbulancia);
		this.panelBotonesAmbulancia.setLayout(new GridLayout(3, 0, 0, 0));
		
		this.panelBtLlama = new JPanel();
		this.panelBotonesAmbulancia.add(this.panelBtLlama);
		this.panelBtLlama.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.btnLlamaTranslado = new JButton("Llama Translado");
		this.panelBtLlama.add(this.btnLlamaTranslado);
		
		this.panel_BtAtencion = new JPanel();
		this.panelBotonesAmbulancia.add(this.panel_BtAtencion);
		
		this.btnLlamaAtencion = new JButton("Llama atencion");
		this.panel_BtAtencion.add(this.btnLlamaAtencion);
		
		this.panel_BtReparacion = new JPanel();
		this.panelBotonesAmbulancia.add(this.panel_BtReparacion);
		
		this.btnSolReparacion = new JButton("Solicitar Reparacion");
		this.panel_BtReparacion.add(this.btnSolReparacion);
		
		this.panelListaPacAmbulancia = new JPanel();
		this.panelAmbulancia.add(this.panelListaPacAmbulancia);
		this.panelListaPacAmbulancia.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneListPac = new JScrollPane();
		this.panelListaPacAmbulancia.add(this.scrollPaneListPac, BorderLayout.CENTER);
		
		this.listPacien = new JList<Paciente>(listaPacienteHistoricos);
		this.scrollPaneListPac.setViewportView(this.listPacien);
		
		this.panelEstadoAmbulancia = new PanelAmbulancia();
		this.panelAmbulancia.add(this.panelEstadoAmbulancia);
		this.panelEstadoAmbulancia.setLayout(new BorderLayout(0, 0));
		
		this.textAreaEstadoAmbulancia = new JTextArea();
		this.panelEstadoAmbulancia.add(this.textAreaEstadoAmbulancia);

		this.scrollPane = new JScrollPane();
		this.tabPanel.addTab("Medicos", null, this.scrollPane, null);

		this.listMedicosEnMedicos = new JList<IMedico>(this.listaMedicos);
		this.scrollPane.setViewportView(this.listMedicosEnMedicos);

		this.panelOperacionesConMedicos = new JPanel();
		this.scrollPane.setRowHeaderView(this.panelOperacionesConMedicos);
		this.panelOperacionesConMedicos.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelNombreMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelNombreMedico);
		this.panelNombreMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblNombreMedico = new JPanel();
		this.panelNombreMedico.add(this.panelContenedorlblNombreMedico);

		this.lblNombreMedico = new JLabel("Nombre");
		this.panelContenedorlblNombreMedico.add(this.lblNombreMedico);

		this.panelContenedorTextFieldNombreMedico = new JPanel();
		this.panelNombreMedico.add(this.panelContenedorTextFieldNombreMedico);

		this.textFieldNombreMedico = new JTextField();
		this.panelContenedorTextFieldNombreMedico.add(this.textFieldNombreMedico);
		this.textFieldNombreMedico.setColumns(10);
		this.textFieldNombreMedico.addKeyListener(this);

		this.panelApellidoMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelApellidoMedico);
		this.panelApellidoMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblApellidoMedico = new JPanel();
		this.panelApellidoMedico.add(this.panelContenedorlblApellidoMedico);

		this.lblApellido = new JLabel("Apellido");
		this.panelContenedorlblApellidoMedico.add(this.lblApellido);

		this.panelContenedorTextFieldApellidoMedico = new JPanel();
		this.panelApellidoMedico.add(this.panelContenedorTextFieldApellidoMedico);

		this.textFieldApellidoMedico = new JTextField();
		this.textFieldApellidoMedico.setColumns(10);
		this.panelContenedorTextFieldApellidoMedico.add(this.textFieldApellidoMedico);
		this.textFieldApellidoMedico.addKeyListener(this);

		this.panelDniMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelDniMedico);
		this.panelDniMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblDniMedico = new JPanel();
		this.panelDniMedico.add(this.panelContenedorlblDniMedico);

		this.lblDniMedico = new JLabel("DNI");
		this.panelContenedorlblDniMedico.add(this.lblDniMedico);

		this.panelContenedorTextFieldDniMedico = new JPanel();
		this.panelDniMedico.add(this.panelContenedorTextFieldDniMedico);

		this.textFieldDniMedico = new JTextField();
		this.textFieldDniMedico.setColumns(10);
		this.panelContenedorTextFieldDniMedico.add(this.textFieldDniMedico);
		this.textFieldDniMedico.addKeyListener(this);

		this.panelTelefonoMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelTelefonoMedico);
		this.panelTelefonoMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblTelefonoMedico = new JPanel();
		this.panelTelefonoMedico.add(this.panelContenedorlblTelefonoMedico);

		this.lblTelefonoMedico = new JLabel("Telefono");
		this.panelContenedorlblTelefonoMedico.add(this.lblTelefonoMedico);

		this.panelContenedorTextFieldTelefonoMedico = new JPanel();
		this.panelTelefonoMedico.add(this.panelContenedorTextFieldTelefonoMedico);

		this.textFieldTelefonoMedico = new JTextField();
		this.textFieldTelefonoMedico.setColumns(10);
		this.panelContenedorTextFieldTelefonoMedico.add(this.textFieldTelefonoMedico);

		this.panelDomicilioMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelDomicilioMedico);
		this.panelDomicilioMedico.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelContenedorlblDomicilioMedico = new JPanel();
		this.panelDomicilioMedico.add(this.panelContenedorlblDomicilioMedico);

		this.lblCiudadDomicilioMedico = new JLabel("Domicilio");
		this.panelContenedorlblDomicilioMedico.add(this.lblCiudadDomicilioMedico);

		this.panelContenedorTextFieldCalleMedico = new JPanel();
		this.panelDomicilioMedico.add(this.panelContenedorTextFieldCalleMedico);

		this.textFieldCalleMedico = new JTextField();
		this.textFieldCalleMedico.setColumns(10);
		this.panelContenedorTextFieldCalleMedico.add(this.textFieldCalleMedico);

		this.panelContenedorTextFieldNumeroDeDomMedico = new JPanel();
		this.panelDomicilioMedico.add(this.panelContenedorTextFieldNumeroDeDomMedico);

		this.textFieldNumeroDeDomicilioMedico = new JTextField();
		this.textFieldNumeroDeDomicilioMedico.setColumns(10);
		this.panelContenedorTextFieldNumeroDeDomMedico.add(this.textFieldNumeroDeDomicilioMedico);

		this.panelCiudadMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelCiudadMedico);
		this.panelCiudadMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblCiudadMedico = new JPanel();
		this.panelCiudadMedico.add(this.panelContenedorlblCiudadMedico);

		this.lblCiudadMedico = new JLabel("Ciudad");
		this.panelContenedorlblCiudadMedico.add(this.lblCiudadMedico);

		this.panelContenedorTextFieldCiudadMedico = new JPanel();
		this.panelCiudadMedico.add(this.panelContenedorTextFieldCiudadMedico);

		this.textFieldCiudadMedico = new JTextField();
		this.textFieldCiudadMedico.setColumns(10);
		this.panelContenedorTextFieldCiudadMedico.add(this.textFieldCiudadMedico);

		this.panelMatriculaMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelMatriculaMedico);
		this.panelMatriculaMedico.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorlblMatriculaMedico = new JPanel();
		this.panelMatriculaMedico.add(this.panelContenedorlblMatriculaMedico);

		this.lblMatriculaMedico = new JLabel("Matricula");
		this.panelContenedorlblMatriculaMedico.add(this.lblMatriculaMedico);

		this.panelContenedorTextFieldMatriculaMedico = new JPanel();
		this.panelMatriculaMedico.add(this.panelContenedorTextFieldMatriculaMedico);

		this.textFieldMatriculaMedico = new JTextField();
		this.textFieldMatriculaMedico.setColumns(10);
		this.panelContenedorTextFieldMatriculaMedico.add(this.textFieldMatriculaMedico);
		this.textFieldMatriculaMedico.addKeyListener(this);

		this.panelInformacionMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelInformacionMedico);
		this.panelInformacionMedico.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelContenedorEspecialidad = new JPanel();
		this.panelContenedorEspecialidad.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Especialidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelInformacionMedico.add(this.panelContenedorEspecialidad);

		this.comboBoxEspecialidad = new JComboBox();
		this.comboBoxEspecialidad
				.setModel(new DefaultComboBoxModel(new String[] { "Cirujano", "Pediatra", "Clinico" }));
		this.panelContenedorEspecialidad.add(this.comboBoxEspecialidad);

		this.panelContenedorContratacion = new JPanel();
		this.panelContenedorContratacion.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Contratacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelInformacionMedico.add(this.panelContenedorContratacion);

		this.comboBoxContratacion = new JComboBox();
		this.comboBoxContratacion.setModel(new DefaultComboBoxModel(new String[] { "Permanente", "Temporario" }));
		this.panelContenedorContratacion.add(this.comboBoxContratacion);

		this.panelContenedorPosgrado = new JPanel();
		this.panelContenedorPosgrado.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Posgrado",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelInformacionMedico.add(this.panelContenedorPosgrado);

		this.comboBoxPosgrado = new JComboBox();
		this.comboBoxPosgrado.setModel(new DefaultComboBoxModel(new String[] { "Magister", "Doctorado" }));
		this.panelContenedorPosgrado.add(this.comboBoxPosgrado);

		this.panelBotonesMedico = new JPanel();
		this.panelOperacionesConMedicos.add(this.panelBotonesMedico);
		this.panelBotonesMedico.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContenedorBtnAgregarMedico = new JPanel();
		this.panelBotonesMedico.add(this.panelContenedorBtnAgregarMedico);

		this.btnAgregarMedico = new JButton("Agregar Medico");
		this.panelContenedorBtnAgregarMedico.add(this.btnAgregarMedico);
		this.btnAgregarMedico.setEnabled(false);

		this.setVisible(true);
	}

	@Override
	public void actualizaListaMedicos(Iterator<IMedico> lista) {
		this.listaMedicos.clear();
		while (lista.hasNext())
			listaMedicos.addElement(lista.next());
		this.repaint();

	}

	@Override
	public void actualizaListaPacientesEnAtencion(Iterator<Paciente> lista) {
		this.ListaPacientesEnAtencion.clear();
		while (lista.hasNext())
			this.ListaPacientesEnAtencion.addElement(lista.next());
		this.repaint();

	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, "Error: " + mensaje);

	}

	@Override
	public String getCantidad() {
		return this.textFieldCantidad.getText();
	}

	@Override
	public Paciente getPaciente() {
		return this.listPacientesEnFacturacion.getSelectedValue();
	}

	@Override
	public IMedico getMedicoFacturacion() {
		return this.listMedicosEnFacturacion.getSelectedValue();
	}

	@Override
	public String getHabitacion() {
		return (String) this.comboBoxTipoDeHabitacion.getSelectedItem();
	}

	@Override
	public void mostrarFactura(String factura) {
		this.textAreaFacturacion.append(factura);
		this.textAreaFacturacion.append("\n");

	}

	@Override
	public void setActionListenerFacturacion(ActionListener listener) {
		this.btnAgregarAtencionMedica.addActionListener(listener);
		this.btnFacturar.addActionListener(listener);
		this.btnAgregarInternacion.addActionListener(listener);

	}

	public void mostrarCartelsatisfactorio(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje + " se ha realizado con exito");
	}

	public void limpiarCamposFacturacion() {
		this.listMedicosEnFacturacion.clearSelection();
		this.listPacientesEnFacturacion.clearSelection();
		this.textFieldCantidad.setText("");
	}

	@Override
	public String getNombreMedico() {
		return this.textFieldNombreMedico.getText();
	}

	@Override
	public String getApellidoMedico() {
		return this.textFieldApellidoMedico.getText();
	}

	@Override
	public int getMatricula() {
		return Integer.parseInt(this.textFieldMatriculaMedico.getText());
	}

	@Override
	public int getDniMedico() {
		return Integer.parseInt(this.textFieldDniMedico.getText());
	}

	@Override
	public String getCalleMedico() {
		return this.textFieldCalleMedico.getText();
	}

	@Override
	public String getNumeroDeDomicilioMedico() {
		return this.textFieldNumeroDeDomicilioMedico.getText();
	}

	@Override
	public String getCiudadMedico() {
		return this.textFieldCiudadMedico.getText();
	}

	@Override
	public String getTelefonoMedico() {
		return this.textFieldTelefonoMedico.getText();
	}

	@Override
	public void limpiarCamposMedicos() {
		this.textFieldApellidoMedico.setText("");
		this.textFieldCalleMedico.setText("");
		this.textFieldCiudadMedico.setText("");
		this.textFieldDniMedico.setText("");
		this.textFieldMatriculaMedico.setText("");
		this.textFieldNombreMedico.setText("");
		this.textFieldNumeroDeDomicilioMedico.setText("");
		this.textFieldTelefonoMedico.setText("");
		this.listMedicosEnMedicos.clearSelection();

	}

	public String getEspecialidad() {
		return (String) this.comboBoxEspecialidad.getSelectedItem();
	}

	public String getPosgrado() {
		return (String) this.comboBoxPosgrado.getSelectedItem();
	}

	public String getContratacion() {
		return (String) this.comboBoxContratacion.getSelectedItem();
	}

	@Override
	public void setActionListenerMedicos(ActionListener listener) {
		this.btnAgregarMedico.addActionListener(listener);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int DniMedico, MatriculaMedico;
		try {
			DniMedico = Integer.parseInt(this.textFieldDniMedico.getText());
			MatriculaMedico = Integer.parseInt(this.textFieldMatriculaMedico.getText());
			boolean nombreMedicoCorrecto, apellidoMedicoCorrecto, dniMedicoCorrecto, matriculaMedicoCorrecto,
					condicionBtnAgregaMedico;
			nombreMedicoCorrecto = !this.getNombreMedico().isBlank() && !this.getNombreMedico().isEmpty()
					&& this.getNombreMedico() != null;
			apellidoMedicoCorrecto = !this.getApellidoMedico().isBlank() && !this.getApellidoMedico().isEmpty()
					&& this.getApellidoMedico() != null;
			dniMedicoCorrecto = DniMedico > 0;
			matriculaMedicoCorrecto = MatriculaMedico > 0;
			condicionBtnAgregaMedico = nombreMedicoCorrecto && apellidoMedicoCorrecto && dniMedicoCorrecto
					&& matriculaMedicoCorrecto;
			this.btnAgregarMedico.setEnabled(condicionBtnAgregaMedico);
		} catch (NumberFormatException e1) {
			this.btnAgregarMedico.setEnabled(false);
		}

	}

	@Override
	public void setAmbulancia(Ambulancia a) {
		this.panelEstadoAmbulancia.setObservado(a);
		
	}

	@Override
	public void actualizaHistoricosAmbulancia(Iterator<Paciente> historicos) {
		this.listaPacienteHistoricos.clear();
		while(historicos.hasNext()) {
			this.listaPacienteHistoricos.addElement(historicos.next());
		}
		this.repaint();
		
	}

	@Override
	public Paciente getPacienteAmbulancia() {
		return this.listPacien.getSelectedValue();
	}

}
