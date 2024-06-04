package empresaDeServicios;

class ServicioDePintura extends Servicio {

	protected int metrosCuadrados;
	protected double precioPorMetroCuadrado;

	public ServicioDePintura(int cliente, int especialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado) {
		super(cliente, especialista, direccion);

		if (metrosCuadrados <= 0) {
			throw new RuntimeException("El precio por hora debe ser mayor a 0m2.");
		}
		if (precioPorMetroCuadrado <= 0) {
			throw new RuntimeException("Las horas de trabajo deben ser mayores a $0.");
		}

		this.metrosCuadrados = metrosCuadrados;
		this.precioPorMetroCuadrado = precioPorMetroCuadrado;
		this.tipoDeServicio = "Pintura";
	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		costo = materiales + (metrosCuadrados * precioPorMetroCuadrado);

		return costo;
	}

	@Override
	public String toString() {

		return "Servicio N°: " + idServicio + "\n" + "Cliente: " + cliente + "\n" + "Especialista: " + especialista
				+ "\n" + "Direccion: " + direccion + "\n" + "Costo: $" + costo;
	}
}
