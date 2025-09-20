import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "detalles")
public class ArticuloManufacturado extends Articulo{
    @Setter
    private String descripcion;
    @Setter
    private Integer tiempoEstimadoMinutos;
    @Setter
    private String preparacion;
    @Builder.Default
    private final Map<Long, ArticuloManufacturadoDetalle> detalles = new HashMap<>();
    @Builder.Default
    private final AtomicLong detalleSeq = new AtomicLong(0);

    public void añadirDetalle(Integer cantidad, ArticuloInsumo insumo) {
        if (cantidad == null){
            throw new NullPointerException("Cantidad no puede ser nula");
        }
        if (insumo == null){
            throw new NullPointerException("Insumo no puede ser nulo");
        }
        if (cantidad <= 0){
            throw new IllegalArgumentException("Cantidad no puede ser 0 o negativa");
        }

        Long nuevoId = detalleSeq.incrementAndGet();;
        detalles.put(nuevoId,
                ArticuloManufacturadoDetalle.builder()
                .id(nuevoId)
                .cantidad(cantidad)
                .insumo(insumo)
                .build());
    }
    public void removerDetalle(Long id){
        detalles.remove(id);
    }

    public ArticuloManufacturadoDetalle obtenerDetalle(Long id) {
        return detalles.get(id);
    }
}
