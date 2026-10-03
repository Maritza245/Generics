import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Carrito<T extends Producto> implements Iterable<T> {

    private ArrayList<T> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(T producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public T productoMayorPrecio() {
        if (productos.isEmpty()) {
            return null;
        }
        T mayor = productos.get(0);
        for (T producto : productos) {
            if (producto.getPrecio() > mayor.getPrecio()) {
                mayor = producto;
            }
        }
        return mayor;
    }

    public double calcularTotal() {
        double total = 0;
        for (T producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    // ---------- Iterador propio ----------

    @Override
    public Iterator<T> iterator() {
        return new IteradorSobrePromedio();
    }

    // Solo entrega los productos cuyo precio es mayor al promedio del carrito
    private class IteradorSobrePromedio implements Iterator<T> {

        private final double promedio;
        private int posicion;   // índice del próximo producto que voy a revisar

        public IteradorSobrePromedio() {
            if (productos.isEmpty()) {
                promedio = 0;
            } else {
                promedio = calcularTotal() / productos.size();
            }
            posicion = 0;
            saltarHastaSiguienteValido();
        }

        // Avanza 'posicion' hasta un producto que supere el promedio (o hasta el final)
        private void saltarHastaSiguienteValido() {
            while (posicion < productos.size()
                    && productos.get(posicion).getPrecio() <= promedio) {
                posicion++;
            }
        }

        @Override
        public boolean hasNext() {
            return posicion < productos.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No quedan productos sobre el promedio");
            }
            T resultado = productos.get(posicion);
            posicion++;
            saltarHastaSiguienteValido();
            return resultado;
        }
    }
}