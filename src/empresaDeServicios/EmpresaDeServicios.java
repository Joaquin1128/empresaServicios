package empresaDeServicios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class EmpresaDeServicios {

	private HashMap<Integer, Especialista> especialistas;
	private HashMap<Integer, Servicio> servicios;
	private HashMap<Integer, Cliente> clientes;
	private HashMap<String, Integer> tipoDeServicios;
	private HashMap<String, Double> facturacion;

	public EmpresaDeServicios() {

		this.especialistas = new HashMap<>();
		this.servicios = new HashMap<>();
		this.clientes = new HashMap<>();
		this.facturacion = new HashMap<>();
		this.tipoDeServicios = new HashMap<>();

		agregarServicios();

	}

	public void registrarEspecialista(int nroEspecialista, String nombre, String telefono, String especialidad) {

		Especialista especialista = new Especialista(nroEspecialista, nombre, telefono, especialidad);

		if (especialistas.containsKey(nroEspecialista)) {
			throw new RuntimeException("El especialista ya está registrado.");
		}
		if (!tipoDeServicios.containsKey(especialidad)) {
			throw new RuntimeException("No es una especialidad valida");
		}

		especialistas.put(nroEspecialista, especialista);
	}

	public void registrarCliente(int dni, String nombre, String telefono) {

		Cliente cliente = new Cliente(dni, nombre, telefono);

		if (clientes.containsKey(dni)) {
			throw new RuntimeException("El cliente ya está registrado.");
		}

		clientes.put(dni, cliente);
	}

	public int solicitarServicioElectricidad(int dni, int nroEspecialista, String direccion, double precioPorHora,
			int horasTrabajadas) {

		validarDatos(dni, nroEspecialista);
		if (especialistas.get(nroEspecialista).getEspecializacion() != "Electricidad") {
			throw new RuntimeException("El especialista indicado no es electricista.");
		}

		ServicioDeElectricidad servicioElectricidad = new ServicioDeElectricidad(dni, nroEspecialista, direccion,
				precioPorHora, horasTrabajadas);
		servicios.put(servicioElectricidad.getIdServicio(), servicioElectricidad);

		return servicioElectricidad.getIdServicio();
	}

	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado) {

		validarDatos(dni, nroEspecialista);
		if (especialistas.get(nroEspecialista).getEspecializacion() != "Pintura") {
			throw new RuntimeException("El especialista indicado no es pintor.");
		}

		ServicioDePintura servicioPintura = new ServicioDePintura(dni, nroEspecialista, direccion, metrosCuadrados,
				precioPorMetroCuadrado);
		servicios.put(servicioPintura.getIdServicio(), servicioPintura);

		return servicioPintura.getIdServicio();
	}

	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado, int piso, double seguro, double alqAndamios) {

		validarDatos(dni, nroEspecialista);
		if (especialistas.get(nroEspecialista).getEspecializacion() != "PinturaEnAltura") {
			throw new RuntimeException("El especialista indicado no es pintor de altura.");
		}

		PinturaEnAltura servicioPinturaEnAltura = new PinturaEnAltura(dni, nroEspecialista, direccion, metrosCuadrados,
				precioPorMetroCuadrado, piso, seguro, alqAndamios);
		servicios.put(servicioPinturaEnAltura.getIdServicio(), servicioPinturaEnAltura);

		return servicioPinturaEnAltura.getIdServicio();
	}

	public int solicitarServicioGasistaInstalacion(int dni, int nroEspecialista, String direccion, int cantArtefactos,
			double precioPorArtefacto) {

		validarDatos(dni, nroEspecialista);
		if (especialistas.get(nroEspecialista).getEspecializacion() != "GasistaInstalacion") {
			throw new RuntimeException("El especialista indicado no es gasista instalador.");
		}

		Instalacion servicioInstalacion = new Instalacion(dni, nroEspecialista, direccion, cantArtefactos,
				precioPorArtefacto);
		servicios.put(servicioInstalacion.getIdServicio(), servicioInstalacion);

		return servicioInstalacion.getIdServicio();
	}

	public int solicitarServicioGasistaRevision(int dni, int nroEspecialista, String direccion, int cantArtefactos,
			double precioPorArtefacto) {

		validarDatos(dni, nroEspecialista);
		if (especialistas.get(nroEspecialista).getEspecializacion() != "GasistaRevision") {
			throw new RuntimeException("El especialista indicado no es gasista de revision.");
		}

		Revision servicioRevision = new Revision(dni, nroEspecialista, direccion, cantArtefactos, precioPorArtefacto);
		servicios.put(servicioRevision.getIdServicio(), servicioRevision);

		return servicioRevision.getIdServicio();
	}

	public double finalizarServicio(int codServicio, double costoMateriales) {

		if (!this.servicios.containsKey(codServicio)) {
			throw new RuntimeException("No se puede finalizar un servicio inexistente");
		}

		else {

			Servicio s = this.servicios.get(codServicio);

			if (s.getEstado() == true) {
				throw new RuntimeException("No se puede finalizar un servicio ya finalizado.");
			}

			this.facturacion.put(s.getTipoDeServicio(),
					facturacion.get(s.getTipoDeServicio()) + s.facturar(costoMateriales));

			return s.getCosto();
		}
	}

	public Map<String, Integer> cantidadDeServiciosRealizadosPorTipo() {

		Iterator<Integer> iterator = servicios.keySet().iterator();
		while (iterator.hasNext()) {
			Integer Id = iterator.next();
			Servicio servicio = servicios.get(Id);
			if (servicio.getEstado()) {
				String tipoDeServicio = servicio.getTipoDeServicio();
				tipoDeServicios.put(tipoDeServicio, tipoDeServicios.getOrDefault(tipoDeServicio, 0) + 1);
			}
		}

		return tipoDeServicios;
	}

	public double facturacionTotalPorTipo(String tipoServicio) {

		if (this.tipoDeServicios.containsKey(tipoServicio)) {

			return facturacion.get(tipoServicio);
		}

		else {
			throw new RuntimeException("El tipo de servicio no es valido");
		}

	}

	public double facturacionTotal() {

		return facturacion.get("PinturaEnAltura") + facturacion.get("Electricidad") + facturacion.get("Pintura")
				+ facturacion.get("GasistaInstalacion") + facturacion.get("GasistaRevision");

	}

	public void cambiarResponsable(int codServicio, int nroEspecialista) {

		if (!servicios.containsKey(codServicio)) {
			throw new RuntimeException("El servicio no está registrado.");
		}
		if (!especialistas.containsKey(nroEspecialista)) {
			throw new RuntimeException("El especialista no está registrado.");
		}

		Servicio s = this.servicios.get(codServicio);

		if (especialistas.get(nroEspecialista).getEspecializacion() != s.getTipoDeServicio()) {
			throw new RuntimeException("El responsable indicado no se especializa en el tipo de servicio deseado.");
		} else {
			s.cambiarEspecialista(nroEspecialista);
		}
	}

	private void agregarServicios() {

		this.tipoDeServicios.put("PinturaEnAltura", 0);
		this.tipoDeServicios.put("Electricidad", 0);
		this.tipoDeServicios.put("Pintura", 0);
		this.tipoDeServicios.put("GasistaInstalacion", 0);
		this.tipoDeServicios.put("GasistaRevision", 0);

		this.facturacion.put("PinturaEnAltura", 0.0);
		this.facturacion.put("Electricidad", 0.0);
		this.facturacion.put("Pintura", 0.0);
		this.facturacion.put("GasistaInstalacion", 0.0);
		this.facturacion.put("GasistaRevision", 0.0);

	}

	private void validarDatos(int dni, int nroEspecialista) {

		if (!clientes.containsKey(dni)) {
			throw new RuntimeException("El cliente no está registrado.");
		}
		if (!especialistas.containsKey(nroEspecialista)) {
			throw new RuntimeException("El especialista no está registrado.");
		}

	}

	public String listadoServiciosAtendidosPorEspecialista(int especialista) {

		StringBuilder listadoServicios = new StringBuilder();
		for (Integer numServicio : servicios.keySet()) {
			Servicio servicio = servicios.get(numServicio);
			if (servicio.getEspecialista() == especialista) {
				listadoServicios.append(" + [ ").append(numServicio).append(" - ").append(servicio.getTipoDeServicio())
						.append(" ] ").append(servicio.getDireccion()).append("\n");
			}
		}
		return listadoServicios.toString();
	}

	@Override
	public String toString() {

		return " ***Empresa De Servicios*** \n\n" + "Cantidad de servicios finalizados por tipo: \n\n"
				+ cantidadDeServiciosRealizadosPorTipo() + "\n\n" + "Facturacion total: $" + facturacionTotal();
	}

	public static void main(String[] args) {
		/*
		 * La empresa dispone de los siguientes tipos de Servicios: - Pintura -
		 * PinturaEnAltura - Electricidad - GasistaInstalacion - GasistaRevision
		 */

		// Inicializa la empresa.
		EmpresaDeServicios empresa = new EmpresaDeServicios();

		// Registro especialistas.
		empresa.registrarEspecialista(1001, "Electricista1", "1144556677", "Electricidad");
		empresa.registrarEspecialista(20, "Gasista", "1144556688", "GasistaInstalacion");
		empresa.registrarEspecialista(33, "Pintor1", "1144556699", "PinturaEnAltura");
		empresa.registrarEspecialista(452, "Electricista2", "1144557700", "Electricidad");
		empresa.registrarEspecialista(34, "Pintor2", "1144557733", "PinturaEnAltura");

		// Registro clientes.
		empresa.registrarCliente(30449448, "Cliente1", "1146651100");
		empresa.registrarCliente(37223451, "Cliente2", "1146651111");

		// Solicitud servicios.
		int cli1_elec1 = empresa.solicitarServicioElectricidad(30449448, 1001, "calle falsa 123", 700, 20);
		int cli1_pintor1 = empresa.solicitarServicioPintura(30449448, 33, "calle falsa 123", 24, 4500, 2, 18000, 5000);
		int cli2_elec2 = empresa.solicitarServicioElectricidad(37223451, 452, "otra Calle 321", 650, 2);
		int cli2_gasista = empresa.solicitarServicioGasistaInstalacion(37223451, 20, "bulevar 333", 3, 3200);
		int cli1_elec2 = empresa.solicitarServicioElectricidad(30449448, 452, "calle falsa 139", 650, 10);

		// Finalizar servicios.
		empresa.finalizarServicio(cli1_elec1, 30000);
		empresa.finalizarServicio(cli2_elec2, 10000);
		empresa.finalizarServicio(cli1_elec2, 5000);
		empresa.finalizarServicio(cli2_gasista, 1000);
		empresa.cambiarResponsable(cli1_pintor1, 34);

		// Reportes.
		System.out.println(empresa.cantidadDeServiciosRealizadosPorTipo());
		System.out.println(empresa.facturacionTotalPorTipo("Electricidad"));
		System.out.println(empresa.facturacionTotal());
		System.out.println(empresa.listadoServiciosAtendidosPorEspecialista(452));

		/*
		 * El toString de empresa debe mostrar información útil, relevante y ordenada
		 * para que se pueda entender el estado actual del sistema.
		 */
		System.out.println(empresa);

	}
}
