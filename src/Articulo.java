import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
abstract class Articulo {
    protected Long id;
    protected String denominacion;
    protected Double precioVenta;
    protected UnidadMedida unidad;
    @Builder.Default
    protected final HashSet<Imagen> imagenes = new HashSet<>();

    public void añadirImagen(Imagen nuevaImagen){
        imagenes.add(nuevaImagen);
    }
    public void removerImagen(Imagen imagenARemover){
        imagenes.remove(imagenARemover);
    }
}
