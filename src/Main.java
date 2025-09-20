import repositorio.InMemoryRepository;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Crear repositorios en memoria para cada entidad
        InMemoryRepository<Imagen> repoImagenes = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> repoArtInsumo = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> repoArtManufacturado = new InMemoryRepository<>();
        InMemoryRepository<Categoria> repoCategorias = new InMemoryRepository<>();
        InMemoryRepository<UnidadMedida> repoUnidades = new InMemoryRepository<>();

        // Crear categorías y guardarlas en el repositorio
        repoCategorias.save(Categoria.builder().denominacion("Pizzas").build());
        repoCategorias.save(Categoria.builder().denominacion("Sandwich").build());
        repoCategorias.save(Categoria.builder().denominacion("Lomos").build());
        repoCategorias.save(Categoria.builder().denominacion("Insumos").build());

        // Crear unidades de medida y guardarlas en el repositorio
        UnidadMedida unidadKg = UnidadMedida.builder().denominacion("Kilogramos").build();
        repoUnidades.save(unidadKg);
        UnidadMedida unidadLitros = UnidadMedida.builder().denominacion("Litros").build();
        repoUnidades.save(unidadLitros);
        UnidadMedida unidadGramos = UnidadMedida.builder().denominacion("Gramos").build();
        repoUnidades.save(unidadGramos);

        // Crear artículos insumo y guardarlos en el repositorio
        ArticuloInsumo sal = ArticuloInsumo.builder()
                .denominacion("Sal")
                .unidad(unidadGramos)
                .esParaElaborar(true)
                .build();
        repoArtInsumo.save(sal);

        ArticuloInsumo aceite = ArticuloInsumo.builder()
                .denominacion("Aceite")
                .unidad(unidadLitros)
                .esParaElaborar(true)
                .build();
        repoArtInsumo.save(aceite);

        ArticuloInsumo carne = ArticuloInsumo.builder()
                .denominacion("Carne")
                .unidad(unidadKg)
                .esParaElaborar(true)
                .build();
        repoArtInsumo.save(carne);

        ArticuloInsumo harina = ArticuloInsumo.builder()
                .denominacion("Harina")
                .unidad(unidadKg)
                .esParaElaborar(true)
                .build();
        repoArtInsumo.save(harina);

        // Crear imágenes y guardarlas en el repositorio
        Imagen img1 = Imagen.builder().denominacion("imgHawainaPizza1").build();
        repoImagenes.save(img1);
        Imagen img2 = Imagen.builder().denominacion("imgHawainaPizza2").build();
        repoImagenes.save(img2);
        Imagen img3 = Imagen.builder().denominacion("imgHawainaPizza3").build();
        repoImagenes.save(img3);
        Imagen img4 = Imagen.builder().denominacion("imgLomoCompleto1").build();
        repoImagenes.save(img4);
        Imagen img5 = Imagen.builder().denominacion("imgLomoCompleto2").build();
        repoImagenes.save(img5);
        Imagen img6 = Imagen.builder().denominacion("imgLomoCompleto3").build();
        repoImagenes.save(img6);

        // Crear artículo manufacturado Pizza Hawaina y asignarle imágenes y detalles
        ArticuloManufacturado pizzaHawaina = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaina")
                .descripcion("Pizza con salsa, queso y ananá")
                .tiempoEstimadoMinutos(20)
                .preparacion("Hornear 20 min")
                .build();
        pizzaHawaina.añadirImagen(img1);
        pizzaHawaina.añadirImagen(img2);
        pizzaHawaina.añadirImagen(img3);
        pizzaHawaina.añadirDetalle(1, sal);
        pizzaHawaina.añadirDetalle(2, harina);
        pizzaHawaina.añadirDetalle(1, aceite);
        repoArtManufacturado.save(pizzaHawaina);

        // Crear artículo manufacturado Lomo Completo y asignarle imágenes y detalles
        ArticuloManufacturado lomoCompleto = ArticuloManufacturado.builder()
                .denominacion("Lomo Completo")
                .descripcion("Lomo con huevo, jamón, queso y papas")
                .tiempoEstimadoMinutos(25)
                .preparacion("Plancha y armado")
                .build();
        lomoCompleto.añadirImagen(img4);
        lomoCompleto.añadirImagen(img5);
        lomoCompleto.añadirImagen(img6);
        lomoCompleto.añadirDetalle(1, sal);
        lomoCompleto.añadirDetalle(1, aceite);
        lomoCompleto.añadirDetalle(1, carne);
        repoArtManufacturado.save(lomoCompleto);

        // Mostrar todas las categorías guardadas
        System.out.println("\nLas categorías disponibles son:");
        for (Categoria cat : repoCategorias.findAll()){
            System.out.println(cat.toString());
        }

        // Mostrar todos los artículos insumo guardados
        System.out.println("\nLos insumos disponibles son:");
        for (ArticuloInsumo insumo : repoArtInsumo.findAll()){
            System.out.println(insumo.toString());
        }

        // Mostrar todos los artículos manufacturados guardados
        System.out.println("\nLos artículos manufacturados disponibles son:");
        for (ArticuloManufacturado artManu : repoArtManufacturado.findAll()){
            System.out.println(artManu.toString());
        }

        // Buscar un artículo manufacturado por id (ejemplo id=1) y mostrarlo
        Long idBuscar = 1L;
        Optional<ArticuloManufacturado> artManuEncontrado = repoArtManufacturado.findById(idBuscar);
        artManuEncontrado.ifPresent(art -> System.out.println(
                "\nSe encontró el artículo buscado con los datos:\n" + art.toString()));

        // Crear nueva versión de Pizza Hawaina actualizada y reemplazarla en el repositorio
        ArticuloManufacturado nuevaPizzaHawaina = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaina (Act)")
                .descripcion("Pizza Hawaina con receta ajustada")
                .tiempoEstimadoMinutos(18)
                .preparacion("Hornear 18 min")
                .build();
        nuevaPizzaHawaina.añadirImagen(img1);
        nuevaPizzaHawaina.añadirImagen(img2);
        nuevaPizzaHawaina.añadirImagen(img3);
        nuevaPizzaHawaina.añadirDetalle(1, sal);
        nuevaPizzaHawaina.añadirDetalle(2, harina);
        nuevaPizzaHawaina.añadirDetalle(2, aceite);
        repoArtManufacturado.genericUpdate(artManuEncontrado.get().getId(), nuevaPizzaHawaina);

        // Volver a buscar Pizza Hawaina y mostrarla después de la actualización
        Optional<ArticuloManufacturado> artManuEncontrado2 = repoArtManufacturado.findById(idBuscar);
        artManuEncontrado2.ifPresent(art -> System.out.println(
                "\nDespués de actualizar, los datos son:\n" + art.toString()));

        // Eliminar Lomo Completo del repositorio (ejemplo id=2)
        Long idEliminar = 2L;
        repoArtManufacturado.genericDelete(idEliminar);

        // Mostrar todos los artículos manufacturados restantes luego de eliminar
        System.out.println("\nLos artículos manufacturados disponibles son:");
        for (ArticuloManufacturado artManu : repoArtManufacturado.findAll()){
            System.out.println(artManu.toString());
        }
    }
}
