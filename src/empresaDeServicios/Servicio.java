package empresaDeServicios;

public abstract class Servicio {

	private static int Idglobal = 1; // El Id de los servicios incrementa en 1 cada vez que se crea un nuevo servicio

	protected int idServicio;
	protected int cliente;
	protected int especialista;
	protected boolean estado;
	protected String direccion;
	protected double costo;
	protected String tipoDeServicio;

	public Servicio(int cliente, int especialista, String direccion) {
		this.idServicio = Idglobal; // Se asigna el Idglobal actual
		this.cliente = cliente;
		this.direccion = direccion;
		this.estado = false; // false indica que el servicio todavia no finalizo
		this.costo = 0; // El costo se inicia en 0
		this.especialista = especialista;
		Idglobal++; // Se aumenta el Idglobal
	}

	public int getIdServicio() {
		return idServicio;
	}

	public int getEspecialista() {
		return especialista;
	}

	public int getCliente() {
		return cliente;
	}

	public boolean getEstado() {
		return estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public double getCosto() {
		return costo;
	}

	public String getTipoDeServicio() {
		return this.tipoDeServicio;
	}

	public void cambiarEspecialista(int especialista) { // Actualiza la variable de instancia especialista por la pasada
														// como parametro de entrada
		this.especialista = especialista;
	}

	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales;
		return costo;

	}

	protected void finalizar() {
		if (estado == true) { // Se cambia estado a true para indicar que el servicio finalizo
			throw new RuntimeException("No se puede finalizar un servicio ya finalizado."); // Si estado ya es igual a
																							// true significa que el
																							// servicio ya finalizo
		}
		estado = true;
	}

	@Override
	public String toString() {

		return "Servicio N°: " + idServicio + "\n" + "Cliente: " + cliente + "\n" + "Especialista: " + especialista
				+ "\n" + "Direccion: " + direccion + "\n" + "Costo: $" + costo;
	}
}
