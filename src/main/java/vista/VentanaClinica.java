package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
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

import asociado.Asociado;
import medicos.IMedico;
import pacientes.Paciente;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import javax.swing.ListModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;

public class VentanaClinica extends JFrame implements IVistaFacturacion, IVistaMedicos, KeyListener, IVistaAmbulancia,
		IVistaPacientes, IVistaConfiguraciones {

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
	private JScrollPane scrollPaneMedicos;
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
	private JPanel panelDatosAmbulancia;
	private JPanel panelListaPacAmbulancia;
	private JScrollPane scrollPaneListPac;
	private JList<Asociado> listPacien;
	private JTextArea textAreaEstadoAmbulancia;
	private JButton btnSolReparacion;
	private JPanel panel_BtReparacion;
	private DefaultListModel<Asociado> listaAsociados = new DefaultListModel<Asociado>();
	private JPanel panelConfiguraciones;
	private JPanel panelCondicionesActuales;
	private JPanel panelDatosActualClinica;
	private JPanel panelCostosActualClinica;
	private JLabel lblNombreActualClinica;
	private JLabel lblTelefonoActualClinica;
	private JLabel lblDireccionActualClinica;
	private JLabel lblSueldoBasicoActualMedicos;
	private JLabel lblCostoHabPrivadaActual;
	private JLabel lblCostoHabCompartidaActual;
	private JLabel lblCostoTerapiaIntensivaActual;
	private JPanel panelActualizarConfiguraciones;
	private JPanel panelActualizarDatosClinica;
	private JPanel panelActualizarCostosClinica;
	private JPanel panelPersistencia;
	private JButton btnRestaurarClinica;
	private JButton btnAlmacenarClinica;
	private JPanel panelContenedorBtnRestaurarClinica;
	private JPanel panelContenedorBtnAlmacenarClinica;
	private JPanel panelContenedorNombreDeLaClinica;
	private JPanel panelContenedorLblNombreDeLaClinica;
	private JLabel lblNombreDeLaClinica;
	private JPanel panelContenedorTextFielNombreDeLaClinica;
	private JTextField textFieldNombreClinica;
	private JPanel panelContenedorTelefonoDeLaClinica;
	private JPanel panelContenedorLblTelefonoDeLaClinica;
	private JLabel lblTelefonoDeLaClinica;
	private JPanel panelContenedorTextFielTelefonoDeLaClinica;
	private JTextField textFieldTelefonoClinica;
	private JPanel panelContenedorDireccionDeLaClinica;
	private JPanel panelContenedorLblDireccionDeLaClinica;
	private JLabel lblDireccionDeLaClinica;
	private JPanel panelContenedorTextFielCalleDeLaClinica;
	private JTextField textFieldCalleClinica;
	private JButton btnActualizarDatosClinica;
	private JPanel panelContenedorBtnActualizarDatos;
	private JPanel panelContenedorTextFielNumeroDeCalleDeLaClinica;
	private JTextField textFieldNumeroDeCalleDeLaClinica;
	private JPanel panelContenedorSueldoBasicoMedicos;
	private JLabel lblSueldoBasicoMedicos;
	private JPanel panelContenedorLblSueldoBasicoMedicos;
	private JTextField textFieldSueldoBasicoMedicos;
	private JPanel panelContenedorTextFieldSueldoBasicoMedicos;
	private JPanel panelContenedorCostoPrivada;
	private JLabel lblCostoPrivada;
	private JTextField textFieldCostoPrivada;
	private JPanel panelContenedorLblCostoPrivada;
	private JPanel panelContenedorTextFieldCostoPrivada;
	private JPanel panelContenedorCompartida;
	private JLabel lblCostoCompartida;
	private JTextField textFieldCostoCompartida;
	private JPanel panelContenedorLblCostoCompartida;
	private JPanel panelContenedorTextFieldCostoCompartida;
	private JPanel panelContenedorTerapia;
	private JLabel lblCostoTerapia;
	private JTextField textFieldCostoTerapia;
	private JPanel panelContenedorLblCostoTerapia;
	private JPanel panelContenedorTextFieldCostoTerapia;
	private JButton btnActualizarMontosClinica;
	private JPanel panelActualizarMontos;
	private JPanel panelContenedorCiudadDeLaClinica;
	private JLabel lblNuevaCiudadDeLaClinica;
	private JTextField textFieldNuevaCiudadDeLaClinica;
	private JPanel panelContenedorLblNuevaCiudadDeLaClinica;
	private JPanel panelContenedorTextFieldNuevaCiudadDeLaClinica;
	private JLabel lblCiudadActualDeLaClinica;
	private JPanel panelEstadoAmbulancia;
	private JTextArea textAreaAmbulancia;

	private JPanel panelMedicosEnMedicos;
	private JList<IMedico> listMedicosEnMedicos;
	private JPanel panelListaMedicosEnMedicos;
	private JPanel panelPacientes;
	private JPanel panelListasPacientes;
	private JPanel panelAccionesPacientes;
	private JPanel panelContenedorListaPacientesHistoricos;
	private JPanel PanelContenedorListaPacientesEnAtencion;
	private JPanel PanelContenedorListaDeEspera;
	private JPanel PanelContenedorTiposDeEspera;
	private JLabel lblTituloPacientesHistoricosEnPacientes;
	private JList listPacientesHistoricosEnPacientes;
	private JLabel lblTituloPacientesEnAtencionEnPacientes;
	private JList listPacientesEnAtencionEnPacientes;
	private JLabel lblTituloPacientesEnEsperaEnPacientes;
	private JList listPacientesEnEsperaEnPacientes;
	private JPanel panelSalaDeEsperaPrivada;
	private JPanel panel_1;
	private JButton btnAtiendeSiguiente;
	private JLabel lblCartelSalaPrivada;
	private JLabel lblPacienteSalaDeEsperaprivadaEnPacientes;
	private JPanel panelNombrePaciente;
	private JPanel PanelApellidoPaciente;
	private JPanel PanelDniPaciente;
	private JPanel panelDireccionPaciente;
	private JPanel panelTelefonoPaciente;
	private JPanel panelCiudadPaciente;
	private JPanel panelNroHistoriaClinica;
	private JLabel lblNombrePaciente;
	private JTextField textFieldNombrePaciente;
	private JPanel panelContenedorLblNombrePaciente;
	private JPanel panelContenedorTextFieldNombrePaciente;
	private JLabel lblApellidoPaciente;
	private JTextField textFieldApellidoPaciente;
	private JPanel panelContenedorLblPaciente;
	private JPanel panelContenedorTextFieldApellidoPaciente;
	private JLabel lblDniPaciente;
	private JTextField textFieldDniPaciente;
	private JPanel panelContenedorLblDniPaciente;
	private JPanel panelContenedorTextFieldDniPaciente;
	private JLabel lblDireccionPaciente;
	private JTextField textFieldCallePaciente;
	private JTextField textFieldNumeroDeCallePaciente;
	private JPanel panelContenedorLblDireccionPaciente;
	private JPanel panelContenedorTextFieldCallePaciente;
	private JPanel panelContenedorTextFieldNumeroCallePaciente;
	private JLabel lblTelefonoPaciente;
	private JTextField textFieldTelefonoPaciente;
	private JPanel panelContenedorLblTelefonoPaciente;
	private JPanel panelContenedorTextFieldTelefonoPaciente;
	private JLabel lblCiudadPaciente;
	private JTextField textFieldCiudadPaciente;
	private JPanel panelContenedorLblCiuadPaciente;
	private JPanel panelContenedorTextFieldCiudadPaciente;
	private JPanel panelContenedorBtnAgregarPaciente;
	private JButton btnAgregarPaciente;
	private JLabel lblNroDeHistoriaClinicaPaciente;
	private JTextField textFieldNroDeHistoriaClinicaPaciente;
	private JPanel panelContenedorComboBoxRangoEtareo;
	private JComboBox comboBoxRangoEtareo;
	private JPanel panelContenedorLblNroDeHistoriaClinicaPaciente;
	private JPanel panelContenedorTextFieldNroDeHistoriaClinicaPaciente;

	private JButton btnLlamaAtencion;
	private JButton btnLlamaTranslado;
	private JPanel panelCargaAsociado;
	private JLabel lblNombreAsociado;
	private JTextField textFieldNombreAsociado;
	private JLabel lblTelefonoAsociado;
	private JLabel lblDomicilioAsociado;
	private JLabel lblDNIAsociado;
	private JTextField textFieldTelefonoAsociado;
	private JTextField textFieldDNIAsociado;
	private JTextField textFieldDomicilioCalleAsociado;
	private JPanel panelNombreApellidoAsociado;
	private JPanel panelCamposNombreApellidoAsociado;
	private JTextField textFieldApellidoAsociado;
	private JPanel panelDomicilioAsociado;
	private JPanel panelCampoDomicilioAsociado;
	private JPanel panelTelefonoAsociado;
	private JPanel panelCampoTelefonoAsociado;
	private JTextField textFieldDomicilioNumeroAsociado;
	private JPanel panelDniAsociado;
	private JPanel panelCampoDNIAsociado;
	private JButton btnAgregarAsociado;
	private DefaultListModel listaPacientesHistoricos;
	private DefaultListModel listaColaDeEspera;

	/**
	 * Create the frame.
	 */
	public VentanaClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
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

		this.panelDatosAmbulancia = new JPanel();
		this.panelAmbulancia.add(this.panelDatosAmbulancia);
		this.panelDatosAmbulancia.setLayout(new BorderLayout(0, 0));

		this.panel_BtReparacion = new JPanel();
		this.panelDatosAmbulancia.add(this.panel_BtReparacion, BorderLayout.SOUTH);
		this.panel_BtReparacion.setLayout(new GridLayout(0, 2, 0, 0));

		this.btnLlamaAtencion = new JButton("Llama atencion");
		this.btnLlamaAtencion.setActionCommand("Llama atencion");
		this.panel_BtReparacion.add(this.btnLlamaAtencion);

		this.btnLlamaTranslado = new JButton("Llama Translado");
		this.btnLlamaTranslado.setActionCommand("Llama Translado");
		this.panel_BtReparacion.add(this.btnLlamaTranslado);

		this.btnSolReparacion = new JButton("Solicitar Reparacion");
		this.btnSolReparacion.setActionCommand("Solicitar Reparacion");
		this.panel_BtReparacion.add(this.btnSolReparacion);

		this.btnAgregarAsociado = new JButton("Agregar Asociado");
		this.btnAgregarAsociado.setActionCommand("Agregar Asociado");
		this.panel_BtReparacion.add(this.btnAgregarAsociado);

		this.panelCargaAsociado = new JPanel();
		this.panelDatosAmbulancia.add(this.panelCargaAsociado, BorderLayout.CENTER);
		this.panelCargaAsociado.setLayout(new GridLayout(4, 2, 0, 0));

		this.panelNombreApellidoAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelNombreApellidoAsociado);
		this.panelNombreApellidoAsociado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.lblNombreAsociado = new JLabel("Nombre y Apellido");
		this.panelNombreApellidoAsociado.add(this.lblNombreAsociado);

		this.panelCamposNombreApellidoAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelCamposNombreApellidoAsociado);
		this.panelCamposNombreApellidoAsociado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.textFieldNombreAsociado = new JTextField();
		this.panelCamposNombreApellidoAsociado.add(this.textFieldNombreAsociado);
		this.textFieldNombreAsociado.setColumns(10);

		this.textFieldApellidoAsociado = new JTextField();
		this.panelCamposNombreApellidoAsociado.add(this.textFieldApellidoAsociado);
		this.textFieldApellidoAsociado.setColumns(10);

		this.panelDomicilioAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelDomicilioAsociado);

		this.lblDomicilioAsociado = new JLabel("Domicilio");
		this.panelDomicilioAsociado.add(this.lblDomicilioAsociado);

		this.panelCampoDomicilioAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelCampoDomicilioAsociado);

		this.textFieldDomicilioCalleAsociado = new JTextField();
		this.panelCampoDomicilioAsociado.add(this.textFieldDomicilioCalleAsociado);
		this.textFieldDomicilioCalleAsociado.setColumns(10);

		this.textFieldDomicilioNumeroAsociado = new JTextField();
		this.textFieldDomicilioNumeroAsociado.setText("");
		this.panelCampoDomicilioAsociado.add(this.textFieldDomicilioNumeroAsociado);
		this.textFieldDomicilioNumeroAsociado.setColumns(10);

		this.panelTelefonoAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelTelefonoAsociado);

		this.lblTelefonoAsociado = new JLabel("Telefono");
		this.panelTelefonoAsociado.add(this.lblTelefonoAsociado);

		this.panelCampoTelefonoAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelCampoTelefonoAsociado);

		this.textFieldTelefonoAsociado = new JTextField();
		this.panelCampoTelefonoAsociado.add(this.textFieldTelefonoAsociado);
		this.textFieldTelefonoAsociado.setColumns(10);

		this.panelDniAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelDniAsociado);

		this.lblDNIAsociado = new JLabel("DNI");
		this.panelDniAsociado.add(this.lblDNIAsociado);

		this.panelCampoDNIAsociado = new JPanel();
		this.panelCargaAsociado.add(this.panelCampoDNIAsociado);

		this.textFieldDNIAsociado = new JTextField();
		this.panelCampoDNIAsociado.add(this.textFieldDNIAsociado);
		this.textFieldDNIAsociado.setColumns(10);

		this.panelListaPacAmbulancia = new JPanel();
		this.panelAmbulancia.add(this.panelListaPacAmbulancia);
		this.panelListaPacAmbulancia.setLayout(new BorderLayout(0, 0));

		this.scrollPaneListPac = new JScrollPane();
		this.panelListaPacAmbulancia.add(this.scrollPaneListPac, BorderLayout.CENTER);

		this.listPacien = new JList<Asociado>(listaAsociados);
		this.scrollPaneListPac.setViewportView(this.listPacien);

		this.panelEstadoAmbulancia = new JPanel();
		this.panelAmbulancia.add(this.panelEstadoAmbulancia);
		this.panelEstadoAmbulancia.setLayout(new BorderLayout(0, 0));

		this.textAreaAmbulancia = new JTextArea();
		this.panelEstadoAmbulancia.add(this.textAreaAmbulancia);

		this.scrollPaneMedicos = new JScrollPane();
		this.tabPanel.addTab("Medicos", null, this.scrollPaneMedicos, null);

		this.panelOperacionesConMedicos = new JPanel();
		this.panelOperacionesConMedicos.setBorder(UIManager.getBorder("OptionPane.border"));
		this.scrollPaneMedicos.setRowHeaderView(this.panelOperacionesConMedicos);
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

		this.panelMedicosEnMedicos = new JPanel();
		this.scrollPaneMedicos.setViewportView(this.panelMedicosEnMedicos);
		this.panelMedicosEnMedicos.setLayout(new BorderLayout(0, 0));

		this.panelListaMedicosEnMedicos = new JPanel();
		this.panelListaMedicosEnMedicos
				.setBorder(new TitledBorder(null, "Medicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelMedicosEnMedicos.add(this.panelListaMedicosEnMedicos);
		this.panelListaMedicosEnMedicos.setLayout(new GridLayout(0, 1, 0, 0));

		this.listMedicosEnMedicos = new JList<IMedico>(this.listaMedicos);
		this.panelListaMedicosEnMedicos.add(this.listMedicosEnMedicos);

		this.panelPacientes = new JPanel();
		this.tabPanel.addTab("Pacientes", null, this.panelPacientes, null);
		this.panelPacientes.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelListasPacientes = new JPanel();
		this.panelListasPacientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panelPacientes.add(this.panelListasPacientes);
		this.panelListasPacientes.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorListaPacientesHistoricos = new JPanel();
		this.panelContenedorListaPacientesHistoricos.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panelListasPacientes.add(this.panelContenedorListaPacientesHistoricos);
		this.panelContenedorListaPacientesHistoricos.setLayout(new BorderLayout(0, 0));

		this.lblTituloPacientesHistoricosEnPacientes = new JLabel("Registro de Pacientes");
		this.lblTituloPacientesHistoricosEnPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelContenedorListaPacientesHistoricos.add(this.lblTituloPacientesHistoricosEnPacientes,
				BorderLayout.NORTH);

		this.listPacientesHistoricosEnPacientes = new JList();
		this.panelContenedorListaPacientesHistoricos.add(this.listPacientesHistoricosEnPacientes, BorderLayout.CENTER);

		this.PanelContenedorListaPacientesEnAtencion = new JPanel();
		this.PanelContenedorListaPacientesEnAtencion.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panelListasPacientes.add(this.PanelContenedorListaPacientesEnAtencion);
		this.PanelContenedorListaPacientesEnAtencion.setLayout(new BorderLayout(0, 0));

		this.lblTituloPacientesEnAtencionEnPacientes = new JLabel("Pacientes en atencion");
		this.lblTituloPacientesEnAtencionEnPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		this.PanelContenedorListaPacientesEnAtencion.add(this.lblTituloPacientesEnAtencionEnPacientes,
				BorderLayout.NORTH);

		this.listPacientesEnAtencionEnPacientes = new JList();
		this.PanelContenedorListaPacientesEnAtencion.add(this.listPacientesEnAtencionEnPacientes, BorderLayout.CENTER);

		this.PanelContenedorListaDeEspera = new JPanel();
		this.PanelContenedorListaDeEspera.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panelListasPacientes.add(this.PanelContenedorListaDeEspera);
		this.PanelContenedorListaDeEspera.setLayout(new BorderLayout(0, 0));

		this.lblTituloPacientesEnEsperaEnPacientes = new JLabel("Pacientes en espera");
		this.lblTituloPacientesEnEsperaEnPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		this.PanelContenedorListaDeEspera.add(this.lblTituloPacientesEnEsperaEnPacientes, BorderLayout.NORTH);

		this.listPacientesEnEsperaEnPacientes = new JList();
		this.PanelContenedorListaDeEspera.add(this.listPacientesEnEsperaEnPacientes, BorderLayout.CENTER);

		this.PanelContenedorTiposDeEspera = new JPanel();
		this.panelListasPacientes.add(this.PanelContenedorTiposDeEspera);
		this.PanelContenedorTiposDeEspera.setLayout(new GridLayout(2, 0, 0, 0));

		this.panelSalaDeEsperaPrivada = new JPanel();
		this.PanelContenedorTiposDeEspera.add(this.panelSalaDeEsperaPrivada);
		this.panelSalaDeEsperaPrivada.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblCartelSalaPrivada = new JLabel("Sala de Espera privada:");
		this.lblCartelSalaPrivada.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelSalaDeEsperaPrivada.add(this.lblCartelSalaPrivada);

		this.lblPacienteSalaDeEsperaprivadaEnPacientes = new JLabel("");
		this.lblPacienteSalaDeEsperaprivadaEnPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelSalaDeEsperaPrivada.add(this.lblPacienteSalaDeEsperaprivadaEnPacientes);

		this.panel_1 = new JPanel();
		this.PanelContenedorTiposDeEspera.add(this.panel_1);

		this.btnAtiendeSiguiente = new JButton("Atender Siguiente");
		this.btnAtiendeSiguiente.setHorizontalTextPosition(SwingConstants.CENTER);
		this.panel_1.add(this.btnAtiendeSiguiente);

		this.panelAccionesPacientes = new JPanel();
		this.panelPacientes.add(this.panelAccionesPacientes);
		this.panelAccionesPacientes.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelNombrePaciente = new JPanel();
		this.panelAccionesPacientes.add(this.panelNombrePaciente);
		this.panelNombrePaciente.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblNombrePaciente = new JPanel();
		this.panelNombrePaciente.add(this.panelContenedorLblNombrePaciente);

		this.lblNombrePaciente = new JLabel("Nombre");
		this.panelContenedorLblNombrePaciente.add(this.lblNombrePaciente);

		this.panelContenedorTextFieldNombrePaciente = new JPanel();
		this.panelNombrePaciente.add(this.panelContenedorTextFieldNombrePaciente);

		this.textFieldNombrePaciente = new JTextField();
		this.panelContenedorTextFieldNombrePaciente.add(this.textFieldNombrePaciente);
		this.textFieldNombrePaciente.setColumns(10);
		this.textFieldNombrePaciente.addKeyListener(this);

		this.PanelApellidoPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.PanelApellidoPaciente);
		this.PanelApellidoPaciente.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblPaciente = new JPanel();
		this.PanelApellidoPaciente.add(this.panelContenedorLblPaciente);

		this.lblApellidoPaciente = new JLabel("Apellido");
		this.panelContenedorLblPaciente.add(this.lblApellidoPaciente);

		this.panelContenedorTextFieldApellidoPaciente = new JPanel();
		this.PanelApellidoPaciente.add(this.panelContenedorTextFieldApellidoPaciente);

		this.textFieldApellidoPaciente = new JTextField();
		this.panelContenedorTextFieldApellidoPaciente.add(this.textFieldApellidoPaciente);
		this.textFieldApellidoPaciente.setColumns(10);
		this.textFieldApellidoPaciente.addKeyListener(this);

		this.PanelDniPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.PanelDniPaciente);
		this.PanelDniPaciente.setLayout(new GridLayout(1, 0, 0, 0));

		this.panelContenedorLblDniPaciente = new JPanel();
		this.PanelDniPaciente.add(this.panelContenedorLblDniPaciente);

		this.lblDniPaciente = new JLabel("DNI");
		this.panelContenedorLblDniPaciente.add(this.lblDniPaciente);

		this.panelContenedorTextFieldDniPaciente = new JPanel();
		this.PanelDniPaciente.add(this.panelContenedorTextFieldDniPaciente);

		this.textFieldDniPaciente = new JTextField();
		this.panelContenedorTextFieldDniPaciente.add(this.textFieldDniPaciente);
		this.textFieldDniPaciente.setColumns(10);
		this.textFieldDniPaciente.addKeyListener(this);

		this.panelDireccionPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.panelDireccionPaciente);
		this.panelDireccionPaciente.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelContenedorLblDireccionPaciente = new JPanel();
		this.panelDireccionPaciente.add(this.panelContenedorLblDireccionPaciente);

		this.lblDireccionPaciente = new JLabel("Direccion");
		this.panelContenedorLblDireccionPaciente.add(this.lblDireccionPaciente);

		this.panelContenedorTextFieldCallePaciente = new JPanel();
		this.panelDireccionPaciente.add(this.panelContenedorTextFieldCallePaciente);

		this.textFieldCallePaciente = new JTextField();
		this.panelContenedorTextFieldCallePaciente.add(this.textFieldCallePaciente);
		this.textFieldCallePaciente.setColumns(10);

		this.panelContenedorTextFieldNumeroCallePaciente = new JPanel();
		this.panelDireccionPaciente.add(this.panelContenedorTextFieldNumeroCallePaciente);

		this.textFieldNumeroDeCallePaciente = new JTextField();
		this.panelContenedorTextFieldNumeroCallePaciente.add(this.textFieldNumeroDeCallePaciente);
		this.textFieldNumeroDeCallePaciente.setColumns(10);

		this.panelTelefonoPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.panelTelefonoPaciente);
		this.panelTelefonoPaciente.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblTelefonoPaciente = new JPanel();
		this.panelTelefonoPaciente.add(this.panelContenedorLblTelefonoPaciente);

		this.lblTelefonoPaciente = new JLabel("Telefono");
		this.panelContenedorLblTelefonoPaciente.add(this.lblTelefonoPaciente);

		this.panelContenedorTextFieldTelefonoPaciente = new JPanel();
		this.panelTelefonoPaciente.add(this.panelContenedorTextFieldTelefonoPaciente);

		this.textFieldTelefonoPaciente = new JTextField();
		this.panelContenedorTextFieldTelefonoPaciente.add(this.textFieldTelefonoPaciente);
		this.textFieldTelefonoPaciente.setColumns(10);

		this.panelCiudadPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.panelCiudadPaciente);
		this.panelCiudadPaciente.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblCiuadPaciente = new JPanel();
		this.panelCiudadPaciente.add(this.panelContenedorLblCiuadPaciente);

		this.lblCiudadPaciente = new JLabel("Ciudad");
		this.panelContenedorLblCiuadPaciente.add(this.lblCiudadPaciente);

		this.panelContenedorTextFieldCiudadPaciente = new JPanel();
		this.panelCiudadPaciente.add(this.panelContenedorTextFieldCiudadPaciente);

		this.textFieldCiudadPaciente = new JTextField();
		this.panelContenedorTextFieldCiudadPaciente.add(this.textFieldCiudadPaciente);
		this.textFieldCiudadPaciente.setColumns(10);

		this.panelNroHistoriaClinica = new JPanel();
		this.panelAccionesPacientes.add(this.panelNroHistoriaClinica);
		this.panelNroHistoriaClinica.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelContenedorLblNroDeHistoriaClinicaPaciente = new JPanel();
		this.panelNroHistoriaClinica.add(this.panelContenedorLblNroDeHistoriaClinicaPaciente);

		this.lblNroDeHistoriaClinicaPaciente = new JLabel("Numero de historia clinica");
		this.panelContenedorLblNroDeHistoriaClinicaPaciente.add(this.lblNroDeHistoriaClinicaPaciente);

		this.panelContenedorTextFieldNroDeHistoriaClinicaPaciente = new JPanel();
		this.panelNroHistoriaClinica.add(this.panelContenedorTextFieldNroDeHistoriaClinicaPaciente);

		this.textFieldNroDeHistoriaClinicaPaciente = new JTextField();
		this.panelContenedorTextFieldNroDeHistoriaClinicaPaciente.add(this.textFieldNroDeHistoriaClinicaPaciente);
		this.textFieldNroDeHistoriaClinicaPaciente.setColumns(10);
		this.textFieldNroDeHistoriaClinicaPaciente.addKeyListener(this);

		this.panelContenedorComboBoxRangoEtareo = new JPanel();
		this.panelNroHistoriaClinica.add(this.panelContenedorComboBoxRangoEtareo);

		this.comboBoxRangoEtareo = new JComboBox();
		this.comboBoxRangoEtareo.setModel(new DefaultComboBoxModel(new String[] { "Niño", "Joven", "Mayor" }));
		this.panelContenedorComboBoxRangoEtareo.add(this.comboBoxRangoEtareo);

		this.panelContenedorBtnAgregarPaciente = new JPanel();
		this.panelAccionesPacientes.add(this.panelContenedorBtnAgregarPaciente);

		this.btnAgregarPaciente = new JButton("Agregar Paciente");
		this.panelContenedorBtnAgregarPaciente.add(this.btnAgregarPaciente);
		this.btnAgregarPaciente.setEnabled(false);

		this.panelConfiguraciones = new JPanel();
		this.tabPanel.addTab("General", null, this.panelConfiguraciones, null);
		this.panelConfiguraciones.setLayout(new BorderLayout(0, 0));

		this.panelCondicionesActuales = new JPanel();
		this.panelCondicionesActuales.setBorder(new TitledBorder(null, "Datos Actuales de la clinica",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelConfiguraciones.add(this.panelCondicionesActuales, BorderLayout.NORTH);
		this.panelCondicionesActuales.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelDatosActualClinica = new JPanel();
		this.panelCondicionesActuales.add(this.panelDatosActualClinica);
		this.panelDatosActualClinica.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblNombreActualClinica = new JLabel("");
		this.panelDatosActualClinica.add(this.lblNombreActualClinica);

		this.lblTelefonoActualClinica = new JLabel("");
		this.panelDatosActualClinica.add(this.lblTelefonoActualClinica);

		this.lblDireccionActualClinica = new JLabel("");
		this.panelDatosActualClinica.add(this.lblDireccionActualClinica);

		this.lblCiudadActualDeLaClinica = new JLabel("");
		this.panelDatosActualClinica.add(this.lblCiudadActualDeLaClinica);

		this.panelCostosActualClinica = new JPanel();
		this.panelCondicionesActuales.add(this.panelCostosActualClinica);
		this.panelCostosActualClinica.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblSueldoBasicoActualMedicos = new JLabel("");
		this.panelCostosActualClinica.add(this.lblSueldoBasicoActualMedicos);

		this.lblCostoHabPrivadaActual = new JLabel("");
		this.panelCostosActualClinica.add(this.lblCostoHabPrivadaActual);

		this.lblCostoHabCompartidaActual = new JLabel("");
		this.panelCostosActualClinica.add(this.lblCostoHabCompartidaActual);

		this.lblCostoTerapiaIntensivaActual = new JLabel("");
		this.panelCostosActualClinica.add(this.lblCostoTerapiaIntensivaActual);

		this.panelActualizarConfiguraciones = new JPanel();
		this.panelActualizarConfiguraciones.setBorder(null);
		this.panelConfiguraciones.add(this.panelActualizarConfiguraciones, BorderLayout.CENTER);
		this.panelActualizarConfiguraciones.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelActualizarDatosClinica = new JPanel();
		this.panelActualizarDatosClinica.setBorder(
				new TitledBorder(null, "Datos de la Clinica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelActualizarConfiguraciones.add(this.panelActualizarDatosClinica);
		this.panelActualizarDatosClinica.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContenedorNombreDeLaClinica = new JPanel();
		this.panelActualizarDatosClinica.add(this.panelContenedorNombreDeLaClinica);
		this.panelContenedorNombreDeLaClinica.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblNombreDeLaClinica = new JPanel();
		this.panelContenedorNombreDeLaClinica.add(this.panelContenedorLblNombreDeLaClinica);

		this.lblNombreDeLaClinica = new JLabel("Nombre");
		this.panelContenedorLblNombreDeLaClinica.add(this.lblNombreDeLaClinica);

		this.panelContenedorTextFielNombreDeLaClinica = new JPanel();
		this.panelContenedorNombreDeLaClinica.add(this.panelContenedorTextFielNombreDeLaClinica);

		this.textFieldNombreClinica = new JTextField();
		this.textFieldNombreClinica.setColumns(10);
		this.panelContenedorTextFielNombreDeLaClinica.add(this.textFieldNombreClinica);

		this.panelContenedorTelefonoDeLaClinica = new JPanel();
		this.panelActualizarDatosClinica.add(this.panelContenedorTelefonoDeLaClinica);
		this.panelContenedorTelefonoDeLaClinica.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblTelefonoDeLaClinica = new JPanel();
		this.panelContenedorTelefonoDeLaClinica.add(this.panelContenedorLblTelefonoDeLaClinica);

		this.lblTelefonoDeLaClinica = new JLabel("Telefono");
		this.panelContenedorLblTelefonoDeLaClinica.add(this.lblTelefonoDeLaClinica);

		this.panelContenedorTextFielTelefonoDeLaClinica = new JPanel();
		this.panelContenedorTelefonoDeLaClinica.add(this.panelContenedorTextFielTelefonoDeLaClinica);

		this.textFieldTelefonoClinica = new JTextField();
		this.textFieldTelefonoClinica.setColumns(10);
		this.panelContenedorTextFielTelefonoDeLaClinica.add(this.textFieldTelefonoClinica);

		this.panelContenedorDireccionDeLaClinica = new JPanel();
		this.panelActualizarDatosClinica.add(this.panelContenedorDireccionDeLaClinica);
		this.panelContenedorDireccionDeLaClinica.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelContenedorLblDireccionDeLaClinica = new JPanel();
		this.panelContenedorDireccionDeLaClinica.add(this.panelContenedorLblDireccionDeLaClinica);

		this.lblDireccionDeLaClinica = new JLabel("Direccion");
		this.panelContenedorLblDireccionDeLaClinica.add(this.lblDireccionDeLaClinica);

		this.panelContenedorTextFielCalleDeLaClinica = new JPanel();
		this.panelContenedorDireccionDeLaClinica.add(this.panelContenedorTextFielCalleDeLaClinica);

		this.textFieldCalleClinica = new JTextField();
		this.textFieldCalleClinica.setColumns(10);
		this.panelContenedorTextFielCalleDeLaClinica.add(this.textFieldCalleClinica);

		this.panelContenedorTextFielNumeroDeCalleDeLaClinica = new JPanel();
		this.panelContenedorDireccionDeLaClinica.add(this.panelContenedorTextFielNumeroDeCalleDeLaClinica);

		this.textFieldNumeroDeCalleDeLaClinica = new JTextField();
		this.textFieldNumeroDeCalleDeLaClinica.setColumns(10);
		this.panelContenedorTextFielNumeroDeCalleDeLaClinica.add(this.textFieldNumeroDeCalleDeLaClinica);

		this.panelContenedorCiudadDeLaClinica = new JPanel();
		this.panelActualizarDatosClinica.add(this.panelContenedorCiudadDeLaClinica);
		this.panelContenedorCiudadDeLaClinica.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblNuevaCiudadDeLaClinica = new JPanel();
		this.panelContenedorCiudadDeLaClinica.add(this.panelContenedorLblNuevaCiudadDeLaClinica);

		this.lblNuevaCiudadDeLaClinica = new JLabel("Ciudad");
		this.panelContenedorLblNuevaCiudadDeLaClinica.add(this.lblNuevaCiudadDeLaClinica);

		this.panelContenedorTextFieldNuevaCiudadDeLaClinica = new JPanel();
		this.panelContenedorCiudadDeLaClinica.add(this.panelContenedorTextFieldNuevaCiudadDeLaClinica);

		this.textFieldNuevaCiudadDeLaClinica = new JTextField();
		this.panelContenedorTextFieldNuevaCiudadDeLaClinica.add(this.textFieldNuevaCiudadDeLaClinica);
		this.textFieldNuevaCiudadDeLaClinica.setColumns(10);

		this.panelContenedorBtnActualizarDatos = new JPanel();
		FlowLayout fl_panelContenedorBtnActualizarDatos = (FlowLayout) this.panelContenedorBtnActualizarDatos
				.getLayout();
		this.panelActualizarDatosClinica.add(this.panelContenedorBtnActualizarDatos);

		this.btnActualizarDatosClinica = new JButton("Actualizar datos");
		this.panelContenedorBtnActualizarDatos.add(this.btnActualizarDatosClinica);

		this.panelActualizarCostosClinica = new JPanel();
		this.panelActualizarCostosClinica.setBorder(
				new TitledBorder(null, "Valores de la Clinica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelActualizarConfiguraciones.add(this.panelActualizarCostosClinica);
		this.panelActualizarCostosClinica.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContenedorSueldoBasicoMedicos = new JPanel();
		this.panelActualizarCostosClinica.add(this.panelContenedorSueldoBasicoMedicos);
		this.panelContenedorSueldoBasicoMedicos.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblSueldoBasicoMedicos = new JPanel();
		this.panelContenedorSueldoBasicoMedicos.add(this.panelContenedorLblSueldoBasicoMedicos);

		this.lblSueldoBasicoMedicos = new JLabel("Sueldo Basico Medicos");
		this.panelContenedorLblSueldoBasicoMedicos.add(this.lblSueldoBasicoMedicos);

		this.panelContenedorTextFieldSueldoBasicoMedicos = new JPanel();
		this.panelContenedorSueldoBasicoMedicos.add(this.panelContenedorTextFieldSueldoBasicoMedicos);

		this.textFieldSueldoBasicoMedicos = new JTextField();
		this.panelContenedorTextFieldSueldoBasicoMedicos.add(this.textFieldSueldoBasicoMedicos);
		this.textFieldSueldoBasicoMedicos.setColumns(10);

		this.panelContenedorCostoPrivada = new JPanel();
		this.panelActualizarCostosClinica.add(this.panelContenedorCostoPrivada);
		this.panelContenedorCostoPrivada.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblCostoPrivada = new JPanel();
		this.panelContenedorCostoPrivada.add(this.panelContenedorLblCostoPrivada);

		this.lblCostoPrivada = new JLabel("Costo habitacion Privada");
		this.panelContenedorLblCostoPrivada.add(this.lblCostoPrivada);

		this.panelContenedorTextFieldCostoPrivada = new JPanel();
		this.panelContenedorCostoPrivada.add(this.panelContenedorTextFieldCostoPrivada);

		this.textFieldCostoPrivada = new JTextField();
		this.panelContenedorTextFieldCostoPrivada.add(this.textFieldCostoPrivada);
		this.textFieldCostoPrivada.setColumns(10);

		this.panelContenedorCompartida = new JPanel();
		this.panelActualizarCostosClinica.add(this.panelContenedorCompartida);
		this.panelContenedorCompartida.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblCostoCompartida = new JPanel();
		this.panelContenedorCompartida.add(this.panelContenedorLblCostoCompartida);

		this.lblCostoCompartida = new JLabel("Costo Habiatacion Compartida");
		this.panelContenedorLblCostoCompartida.add(this.lblCostoCompartida);

		this.panelContenedorTextFieldCostoCompartida = new JPanel();
		this.panelContenedorCompartida.add(this.panelContenedorTextFieldCostoCompartida);

		this.textFieldCostoCompartida = new JTextField();
		this.panelContenedorTextFieldCostoCompartida.add(this.textFieldCostoCompartida);
		this.textFieldCostoCompartida.setColumns(10);

		this.panelContenedorTerapia = new JPanel();
		this.panelActualizarCostosClinica.add(this.panelContenedorTerapia);
		this.panelContenedorTerapia.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorLblCostoTerapia = new JPanel();
		this.panelContenedorTerapia.add(this.panelContenedorLblCostoTerapia);

		this.lblCostoTerapia = new JLabel("Costo Terapia Intensiva");
		this.panelContenedorLblCostoTerapia.add(this.lblCostoTerapia);

		this.panelContenedorTextFieldCostoTerapia = new JPanel();
		this.panelContenedorTerapia.add(this.panelContenedorTextFieldCostoTerapia);

		this.textFieldCostoTerapia = new JTextField();
		this.panelContenedorTextFieldCostoTerapia.add(this.textFieldCostoTerapia);
		this.textFieldCostoTerapia.setColumns(10);

		this.panelActualizarMontos = new JPanel();
		this.panelActualizarCostosClinica.add(this.panelActualizarMontos);

		this.btnActualizarMontosClinica = new JButton("Actualizar valores");
		this.btnActualizarMontosClinica.setActionCommand("Actualizar valores");
		this.panelActualizarMontos.add(this.btnActualizarMontosClinica);

		this.panelPersistencia = new JPanel();
		this.panelConfiguraciones.add(this.panelPersistencia, BorderLayout.SOUTH);
		this.panelPersistencia.setLayout(new GridLayout(0, 2, 0, 0));

		this.panelContenedorBtnRestaurarClinica = new JPanel();
		this.panelPersistencia.add(this.panelContenedorBtnRestaurarClinica);

		this.btnRestaurarClinica = new JButton("Restaurar Clinica");
		this.panelContenedorBtnRestaurarClinica.add(this.btnRestaurarClinica);

		this.panelContenedorBtnAlmacenarClinica = new JPanel();
		this.panelPersistencia.add(this.panelContenedorBtnAlmacenarClinica);

		this.btnAlmacenarClinica = new JButton("Almacenar Clinica");
		this.panelContenedorBtnAlmacenarClinica.add(this.btnAlmacenarClinica);

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

		int dniPaciente, nroHitoriaClinica;
		try {
			dniPaciente = Integer.parseInt(this.textFieldDniPaciente.getText());
			nroHitoriaClinica = Integer.parseInt(this.textFieldNroDeHistoriaClinicaPaciente.getText());
			boolean nombreCorrecto, ApellidoCorrecto, dniCorrecto, nroHistoriClinicaCorrecto;

		} catch (NumberFormatException e1) {
			this.btnAgregarPaciente.setEnabled(false);
		}
	}

	@Override
	public void actualizaAsociados(Iterator<Asociado> historicos) {
		this.listaAsociados.clear();
		while (historicos.hasNext()) {
			this.listaAsociados.addElement(historicos.next());
		}
		this.repaint();

	}

	@Override
	public Asociado getAsociadoAmbulancia() {
		return this.listPacien.getSelectedValue();
	}

	@Override
	public void setActionListenerConfiguraciones(ActionListener listener) {
		this.btnActualizarDatosClinica.addActionListener(listener);
		this.btnActualizarMontosClinica.addActionListener(listener);
		this.btnAlmacenarClinica.addActionListener(listener);
		this.btnRestaurarClinica.addActionListener(listener);

	}

	@Override
	public void SetWindowListenerConfiguraciones(WindowListener listener) {
		this.addWindowListener(listener);

	}

	@Override
	public void actualizarCostosDeLaClinica(double habPrivada, double habCompartida, double terapia, double sueldo) {
		this.lblCostoHabCompartidaActual.setText("Costo de la habitacion compartida: " + habCompartida);
		this.lblCostoHabPrivadaActual.setText("Costo de la habitacion privada: " + habPrivada);
		this.lblCostoTerapiaIntensivaActual.setText("Costo de la terapia intensiva: " + terapia);
		this.lblSueldoBasicoActualMedicos.setText("Sueldo Basico de los medicos: " + sueldo);
		this.repaint();
	}

	@Override
	public void actualizarDatosDeLaClinia(String nombre, String telefono, String direccion, String ciudad) {
		this.lblNombreActualClinica.setText("Nombre: " + nombre);
		this.lblTelefonoActualClinica.setText("Telefono: " + telefono);
		this.lblDireccionActualClinica.setText("Direccion: " + direccion);
		this.lblCiudadActualDeLaClinica.setText("Ciudad: " + ciudad);
		this.repaint();

	}

	@Override
	public String getNuevoNombreClinica() {
		return this.textFieldNombreClinica.getText();
	}

	@Override
	public String getNuevoTelefonoClinica() {
		return this.textFieldTelefonoClinica.getText();
	}

	@Override
	public String getNuevaCalleClinica() {
		return this.textFieldCalleClinica.getText();
	}

	@Override
	public String getNuevoNumeroClinica() {
		return this.textFieldNumeroDeCalleDeLaClinica.getText();
	}

	@Override
	public String getCiudadClinica() {
		return this.textFieldNuevaCiudadDeLaClinica.getText();
	}

	@Override
	public String getNuevoCostoHabitacionPrivada() {
		return this.textFieldCostoPrivada.getText();
	}

	@Override
	public String getNuevoCostoHabitacionCompartida() {
		return this.textFieldCostoCompartida.getText();
	}

	@Override
	public String getNuevoCostoTerapiaIntensiva() {
		return this.textFieldCostoTerapia.getText();
	}

	@Override
	public String getNuevoSueldoBasicoMedicos() {
		return this.textFieldSueldoBasicoMedicos.getText();
	}

	@Override
	public void limpiarCamposConfiguracion() {
		this.textFieldNombreClinica.setText("");
		this.textFieldCalleClinica.setText("");
		this.textFieldTelefonoClinica.setText("");
		this.textFieldNumeroDeCalleDeLaClinica.setText("");
		this.textFieldNuevaCiudadDeLaClinica.setText("");

		this.textFieldCostoCompartida.setText("");
		this.textFieldCostoPrivada.setText("");
		this.textFieldCostoTerapia.setText("");

	}

	@Override
	public void actualizaEstadoAmbulancia(String estado) {
		this.textAreaAmbulancia.append(estado + "\n");
	}

	@Override
	public void setActionListener(ActionListener a) {
		this.btnSolReparacion.addActionListener(a);

		this.btnAgregarAsociado.addActionListener(a);
		this.btnLlamaAtencion.addActionListener(a);
		this.btnLlamaTranslado.addActionListener(a);

	}

	@Override
	public String getNombreAsociado() {
		return this.textFieldNombreAsociado.getText();
	}

	@Override
	public String getApellidoAsociado() {
		return this.textFieldApellidoAsociado.getText();
	}

	@Override
	public String getTelefonoAsociado() {
		return this.textFieldTelefonoAsociado.getText();
	}

	@Override
	public String getCalleDomicilioAsocidado() {
		return this.textFieldDomicilioCalleAsociado.getText();
	}

	@Override
	public String getNumeroDomicilioAsociado() {
		return this.textFieldDomicilioNumeroAsociado.getText();
	}

	@Override
	public String getDNIAsociado() {
		return this.textFieldDNIAsociado.getText();
	}

	@Override
	public void SetActionListenerPacientes(ActionListener listener) {

	}

	@Override
	public void actualizaListaEnAtencion(Iterator<Paciente> lista) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaColaDeEspera(Iterator<Paciente> lista) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaPacientesHistoricos(Iterator<Paciente> lista) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizaSalaPrivada(String paciente) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNombrePaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getApellidoPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDniPaciente() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNroDeHistoriaClinicaPaciente() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCallePaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNroDeCallePaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCiudadPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTelefonoPaciente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void LimpiarCamposPaciente() {
		// TODO Auto-generated method stub

	}
}
