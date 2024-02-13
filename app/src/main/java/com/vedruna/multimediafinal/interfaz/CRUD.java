package com.vedruna.multimediafinal.interfaz;

import com.vedruna.multimediafinal.modelo.Car; // Asegúrate de cambiar el nombre del modelo Player a Car o como se llame en tu API

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Interfaz que define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad Car.
 * Asegúrate de cambiar el nombre del modelo Player a Car o como se llame en tu API.
 */
public interface CRUD {

    /**
     * Obtiene la lista de todos los coches.
     *
     * @return Una instancia de Call que representa la solicitud de la lista de coches.
     */
    @GET("car")
    Call<List<Car>> getAll(); // Cambiado a "car" en lugar de "player"

    /**
     * Crea un nuevo coche.
     *
     * @param car El objeto Car que se va a crear.
     * @return Una instancia de Call que representa la solicitud de creación del coche.
     */
    @POST("car")
    Call<Car> create(@Body Car car); // Cambiado a "car" en lugar de "player"

    /**
     * Actualiza la información de un coche existente.
     *
     * @param id  El identificador único del coche a actualizar.
     * @param car El objeto Car con la información actualizada.
     * @return Una instancia de Call que representa la solicitud de actualización del coche.
     */
    @PUT("car/{id}")
    Call<Car> actualizar(@Path("id") int id, @Body Car car); // Cambiado a "car" en lugar de "player"

    /**
     * Elimina un coche por su identificador único.
     *
     * @param id El identificador único del coche a eliminar.
     * @return Una instancia de Call que representa la solicitud de eliminación del coche.
     */
    @DELETE("car/{id}")
    Call<Void> delete(@Path("id") int id); // Cambiado a "car" en lugar de "player"
}
