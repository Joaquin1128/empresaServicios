package empresaDeServicios;

public class Revision extends ServicioGasista {

	public Revision(int cliente, int especialista, String domicilio, int artefactos, double precioPorArtefacto) {
		super(cliente, especialista, domicilio, artefactos, precioPorArtefacto);
		this.tipoDeServicio = "GasistaRevision";

	}

	@Override
	public double facturar(double materiales) {
		finalizar(); // finalizar() cambia el estado del servicio a true para indicar que esta
						// finalizado
		aplicarDescuento(); // De ser aplicable, se aplica el descuento sobre el precioPorArtefacto
		costo = materiales + (artefactos * precioPorArtefacto);

		return costo;
	}

	public void aplicarDescuento() {
		if (artefactos > 5 && artefactos <= 10) { // Si los artefactos son mayores a 5 se aplica el descuento del 10%
			this.precioPorArtefacto = this.precioPorArtefacto - (this.precioPorArtefacto * 0.1);// Descuento del 10%
		} else if (artefactos > 10) { // Si los artefactos son mayores a 10 se aplica el descuento del 15%
			this.precioPorArtefacto = this.precioPorArtefacto - (this.precioPorArtefacto * 0.15);// Descuento del 15%
		}
	}
}
