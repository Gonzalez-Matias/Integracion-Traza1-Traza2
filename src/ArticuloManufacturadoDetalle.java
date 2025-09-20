import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloManufacturadoDetalle {
    @NonNull
    private Integer cantidad;
    private Long id;
    @NonNull
    private ArticuloInsumo insumo;

}
