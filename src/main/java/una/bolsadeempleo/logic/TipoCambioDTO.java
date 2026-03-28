package una.bolsadeempleo.logic;

public class TipoCambioDTO {
    private Valor venta;
    private Valor compra;

    public TipoCambioDTO(Valor venta, Valor compra) {
        this.venta = venta;
        this.compra = compra;
    }

    public TipoCambioDTO() {
    }


    public Valor getVenta() {
        return venta;
    }

    public void setVenta(Valor venta) {
        this.venta = venta;
    }

    public Valor getCompra() {
        return compra;
    }

    public void setCompra(Valor compra) {
        this.compra = compra;
    }
}
