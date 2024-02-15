package com.example.helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Presentacion;
import com.example.entities.Producto;
import com.example.services.PresentacionService;
import com.example.services.ProductoService;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(ProductoService productoService, PresentacionService presentacionService) {

        return datos -> {

            // presentaciones
            presentacionService.save(Presentacion.builder()
                .name("Unidad")
                .build()
            );

            presentacionService.save(Presentacion.builder()
                .name("Docena")
                .build()
            );

            // productos
            productoService.save(Producto.builder()
                .name("Tijeras")
                .description("Con punta redonda")
                .stock(10)
                .price(3.75)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Sobres")
                .description("De color rosa")
                .stock(36)
                .price(0.75)
                .presentacion(presentacionService.findById(2))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Bolígrafo")
                .description("Disponibles en varios colores")
                .stock(143)
                .price(1.25)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Post-it")
                .description("Packete de varios colores")
                .stock(32)
                .price(8)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Carpesano")
                .description("De color blanco. No, no tenemos de otro color")
                .stock(14)
                .price(8)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Recambio hojas blancas")
                .description("Sin líneas ni cuadraos, cari")
                .stock(9)
                .price(3)
                .presentacion(presentacionService.findById(2))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Lápices HB")
                .description("De dureza media")
                .stock(6)
                .price(2)
                .presentacion(presentacionService.findById(2))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Subrayador")
                .description("Disponibles en diferentes colores")
                .stock(27)
                .price(1.5)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Goma de miga de pan")
                .description("Moldeable. Especial para dibujo")
                .stock(8)
                .price(4)
                .presentacion(presentacionService.findById(1))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Goma")
                .description("Goma de borrar Milán")
                .stock(64)
                .price(0.75)
                .presentacion(presentacionService.findById(2))
                .build()
            );


            productoService.save(Producto.builder()
                .name("Estuche chachipiruli")
                .description("Con forma de animalitos")
                .stock(13)
                .price(17.5)
                .presentacion(presentacionService.findById(1))
                .build()
            );

        };

    }

}
