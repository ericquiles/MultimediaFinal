package com.vedruna.multimediafinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
 * Fragmento para insertar un nuevo coche en la base de datos.
 */
public class insertar extends Fragment {

    EditText nameText;
    EditText idText;
    EditText precioText;
    EditText modeloText;
    EditText editTextUrlImagen;
    Button button;
    CRUD crudInterfaces;

    /**
     * Constructor por defecto de la clase insertar.
     */
    public insertar() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_insertar, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextId);
        nameText = rootView.findViewById(R.id.editTextNombre);
        precioText = rootView.findViewById(R.id.editTextPrecio);
        modeloText = rootView.findViewById(R.id.editTextModelo);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonCrearCoche);

        // Puedes agregar un listener al botón si deseas manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String nombre = nameText.getText().toString();
                String precio = precioText.getText().toString();
                String modelo = modeloText.getText().toString();
                String urlImagen = editTextUrlImagen.getText().toString();

                // Verificar si alguno de los campos está vacío
                if (id.isEmpty() || nombre.isEmpty() || precio.isEmpty() || modelo.isEmpty() || urlImagen.isEmpty()) {
                    // Mostrar Toast indicando que todos los campos son obligatorios
                    mostrarToast("Todos los campos son obligatorios");
                } else {
                    Car car = new Car(Integer.parseInt(id), nombre, precio, modelo, urlImagen);
                    create(car);
                }
            }
        });

        return rootView;
    }

    /**
     * Método para crear un nuevo coche en la base de datos.
     *
     * @param car Objeto Car que representa el nuevo coche.
     */
    private void create(Car car) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        crudInterfaces = retrofit.create(CRUD.class);
        Call<Car> call = crudInterfaces.create(car);

        call.enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                Car car = response.body();
                mostrarToast("Coche añadido correctamente: " + car.getName());
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
            }
        });
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
