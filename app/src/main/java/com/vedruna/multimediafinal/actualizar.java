package com.vedruna.multimediafinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.multimediafinal.interfaz.CRUD;
import com.vedruna.multimediafinal.modelo.Car;
import com.vedruna.multimediafinal.constants.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para la actualización de información de un coche.
 */
public class actualizar extends Fragment {

    EditText nameText;
    EditText precioText;
    EditText modeloText;
    EditText editTextUrlImagen;
    Button button;
    EditText idText;  // Nuevo EditText para el ID
    private Retrofit retrofit;
    CRUD crudInterfaces;

    /**
     * Constructor por defecto de la clase actualizar.
     */
    public actualizar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_actualizar, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextID);  // Nuevo EditText para el ID
        nameText = rootView.findViewById(R.id.editTextNombre);
        precioText = rootView.findViewById(R.id.editTextPrecio);
        modeloText = rootView.findViewById(R.id.editTextModelo);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarCoche);

        // Puedes agregar un listener al botón si deseas manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }

    /**
     * Método que se llama al hacer clic en el botón de actualizar.
     * Realiza la validación de los campos y llama al método de actualización.
     */
    private void actualizar() {
        String id = idText.getText().toString().trim();
        String nombre = nameText.getText().toString().trim();
        String precio = precioText.getText().toString().trim();
        String modelo = modeloText.getText().toString().trim();
        String imagen = editTextUrlImagen.getText().toString().trim();

        // Verificar si alguno de los campos está vacío
        if (id.isEmpty() || nombre.isEmpty() || precio.isEmpty() || modelo.isEmpty() || imagen.isEmpty()) {
            // Mostrar Toast indicando que todos los campos son obligatorios
            mostrarToast("Todos los campos son obligatorios");
        } else {
            // Crear un coche
            Car car = new Car(Integer.parseInt(id), nombre, precio, modelo, imagen);

            crudInterfaces = retrofit.create(CRUD.class);

            // Llamar al método actualizar
            Call<Car> call = crudInterfaces.actualizar(Integer.parseInt(id), car);

            call.enqueue(new Callback<Car>() {
                @Override
                public void onResponse(Call<Car> call, Response<Car> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Response err ", response.message());
                        return;
                    }
                    Car carUpdated = response.body();
                    mostrarToast("Coche actualizado: " + carUpdated.getName());
                }

                @Override
                public void onFailure(Call<Car> call, Throwable t) {
                    Log.e("Throw err:", t.getMessage());
                    mostrarToast("Error al actualizar el coche");
                }
            });
        }
    }

    /**
     * Método para mostrar un Toast.
     *
     * @param mensaje Mensaje a mostrar en el Toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
