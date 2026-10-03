public class Main {

    public static void main(String[] args) {

        Carrito<Producto> carrito = new Carrito<>();

        carrito.agregarProducto(new Producto("Computador", 2500000));
        carrito.agregarProducto(new Producto("Mouse", 50000));
        carrito.agregarProducto(new Producto("Teclado", 120000));
        carrito.agregarProducto(new Producto("Audifonos", 80000));
        carrito.agregarProducto(new Producto("Monitor", 900000));
        carrito.agregarProducto(new Producto("Cable HDMI", 15000));

        Producto mayor = carrito.productoMayorPrecio();
        System.out.println("\nProducto de mayor precio:");
        System.out.println(mayor);

        System.out.println("\nPrecio total del carrito:");
        System.out.println("$" + carrito.calcularTotal());

        System.out.println("\nProductos por encima del promedio:");
        for (Producto p : carrito) {
            System.out.println(p);
        }
    }
}