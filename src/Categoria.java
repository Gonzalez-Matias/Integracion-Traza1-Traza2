import lombok.*;

import java.util.HashSet;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "articulos")
public class Categoria {
    private String denominacion;
    private Long id;
    @Builder.Default
    private final HashSet<Articulo> articulos = new HashSet<>();

    public boolean añadirArticulo(Articulo articulo) {
        return articulos.add(articulo);
    }

    public boolean removerArticulo(Articulo articulo) {
        return articulos.remove(articulo);
    }
}
