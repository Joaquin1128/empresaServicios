package empresaDeServicios;

class ServicioDeElectricidad extends Servicio {

	private double precioPorHora;
	private int horasTrabajadas;

	public ServicioDeElectricidad(int cliente, int especialista, String direccion, double precioPorHora,
			int horasTrabajadas) {

		super(cliente, especialista, direccion);

		if (precioPorHora <= 0) {
			throw new RuntimeException("El precio por hora debe ser mayor a $0.");
		}
		if (horasTrabajadas <= 0) {
			throw new RuntimeException("Las horas de trabajo deben ser mayores a 0hs.");
		}

		this.horasTrabajadas = horasTrabajadas;
		this.precioPorHora = precioPorHora;
		this.tipoDeServicio = "Electricidad";
	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales + (precioPorHora * horasTrabajadas);
		return costo;
	}

	@Override
	public String toString() {

		return "Servicio N°: " + idServicio + "\n" + "Cliente: " + cliente + "\n" + "Especialista: " + especialista
				+ "\n" + "Direccion: " + direccion + "\n" + "Costo: $" + costo;
	}
}
