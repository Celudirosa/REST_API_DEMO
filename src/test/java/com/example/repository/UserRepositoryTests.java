/**
* Segun el enfoque: Una prueba unitaria se divide en tres partes
*
* 1. Arrange: Setting up the data that is required for this test case
* 2. Act: Calling a method or Unit that is being tested.
* 3. Assert: Verify that the expected result is right or wrong.
*
* Segun el enfoque BDD (Behaviour Driven Development). 'Given-When-Then' como lenguaje comun con BDD
* 
* Para definir los casos BDD para una historia de usuario se deben definir bajo el patrón "Given-When-Then"
* , que se define como sigue:
*
* 1. given (dado) : Se especifica el escenario, las precondiciones.
* 2. when (cuando) : Las condiciones de las acciones que se van a ejecutar.
* 3. then (entonces) : El resultado esperado, las validaciones a realizar.
*
* Un ejemplo practico seria:
*
* Given: Dado que el usuario no ha introducido ningun dato en el formulario.
* When: Cuando se hace click en el boton de enviar.
* Then: Se deben de mostrar los mensajes de validación apropiados.
*
* "Role-Feature-Reason" como lenguaje común con BDD
*
* Este patrón se utiliza en BDD para ayudar a la creación de historias de usuarios. Este se define como:
*
* As a "Como" : Se especifica el tipo de usuario.
* I want "Deseo" : Las necesidades que tiene.
* So that "Para que" : Las caracteristicas para cumplir el objetivo.
*
* Un ejemplo práctico de historia de usuario sería: Como cliente interesado, deseo ponerme en contacto mediante formulario, 
* para que atiendan mis necesidades. 
*
* Parece que BDD y TDD son la misma cosa, pero la principal diferencia entre ambas esta en el alcance. TDD es una practica de desarrollo 
* (se enfoca en como escribir el codigo y como deberia trabajar ese codigo) mientras que BDD es una metodologia de equipo (Se enfoca
* en porque debes escribir ese codigo y como se deberia de comportar ese codigo)
*
* En TDD el desarrollador escribe los tests mientras que en BDD el usuario final (o PO o analista) en conjunto con los testers escriben
* los tests (y los Devs solo generan el codigo necesario para ejecutar dichos tests)
*
* Tambien existe ATDD (Acceptance Test Driven Development), que es mas cercana a BDD ya que no es una practica,
* sino una metodologia de trabajo, pero la diferencia esta nuevamente en el alcance, a diferencia de BDD, ATDD se extiende aun 
* mas en profundizar la búsqueda de que lo que se esta haciendo no solo se hace de forma correcta, sino que tambien 
* es lo correcto a hacer.
*
*/

package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.security.entities.OurUser;
import com.example.security.entities.Role;
import com.example.security.repository.OurUserRepository;

@DataJpaTest // Esta anotacion es para probar las entidades y repositorios, donde estan las anotaciones @Entities y @Repository, 
// por defecto va a intentar usar una bbdd que no tenemos, llamada H2noseque y para que podamos usar MySQL tenemos que poner la siguiente anotacion
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private OurUserRepository ourUserRepository;

    // creamos fuera un user0
    private OurUser ourUser0;

    @BeforeEach
    void setUp() {
        ourUser0 = OurUser.builder()
            .email("user0@gmail.com")
            .password("123456")
            .role(Role.USER)
            .build();
    }

    // Test para agregar un user
    @DisplayName("Test para agregar un user")
    @Test
    public void testAddUser() {
        // given
        OurUser ourUser1 = OurUser.builder()
            .email("celia@mola.com")
            .password("1234")
            .role(Role.ADMIN)
            .build();
        // when
        @SuppressWarnings("null")
        OurUser ourUserSave = ourUserRepository.save(ourUser1);
        // then
        assertThat(ourUserSave).isNotNull();
        assertThat(ourUserSave.getId()).isGreaterThan(0);
    }

    @SuppressWarnings("null")
    @DisplayName("Test para sacar todos los usuarios")
    @Test
    public void testFindAllUsers() {
        // given
        OurUser ourUser2 = OurUser.builder()
            .email("andrea@mola.com")
            .password("1234")
            .role(Role.ADMIN)
            .build();

        ourUserRepository.save(ourUser0);
        ourUserRepository.save(ourUser2);
        // when
        List<OurUser> users = ourUserRepository.findAll();
        // then
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Test para encontrar un user por el id")
    public void testFindById() {
        // given
        ourUserRepository.save(ourUser0);
        // when
        OurUser foundUser = ourUserRepository.findById(ourUser0.getId()).get();
        // then
        assertThat(foundUser.getId()).isNotEqualTo(0);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Test para actualizar un user")
    public void testUpdateUser() {
        // given
        ourUserRepository.save(ourUser0);
        // when
        OurUser saveUser = ourUserRepository.findById(ourUser0.getId()).get();

        saveUser.setEmail("celia@celia.com");
        saveUser.setPassword("1111");
        saveUser.setRole(Role.ADMIN);

        OurUser userUpdate = ourUserRepository.save(saveUser);
        // then
        assertThat(userUpdate.getEmail()).isEqualTo("celia@celia.com");
        assertThat(userUpdate.getPassword()).isEqualTo("1111");
        assertThat(userUpdate.getRole()).isEqualTo(Role.ADMIN);
    }

}
