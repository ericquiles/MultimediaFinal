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
import com.vedruna.multimediafinal.constants.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para la eliminación de un coche.
 */
public class borrar extends Fragment {

    CRUD crudInterfaces;
    Button button;
    EditText idEditText;

    /**
     * Constructor por defecto de la clase borrar.
     */
    public borrar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        // Inicializar el EditText para el ID
        idEditText = view.findViewById(R.id.editTextID);

        // Configurar el botón de borrado con el clic
        setupDeleteButton(view);

        return view;
    }

    /**
     * Configura el botón de borrado para manejar clics.
     *
     * @param view Vista del fragmento.
     */
    private void setupDeleteButton(View view) {
        button = view.findViewById(R.id.buttonBorrarProducto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID ingresado por el usuario
                String idString = idEditText.getText().toString().trim();

                if (!idString.isEmpty()) {
                    int productId = Integer.parseInt(idString);
                    delete(productId);
                } else {
                    // Manejar el caso en el que el ID esté vacío
                    mostrarToast("El Id no puede estar vacío");
                }
            }
        });
    }

    /**
     * Llama al método de borrado a través de la interfaz CRUD.
     *
     * @param id Identificador único del coche a borrar.
     */
    private void delete(int id) {
        // Construir la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz CRUDInterface
        crudInterfaces = retrofit.create(CRUD.class);

        // Llamar al método de borrado con el ID del producto
        Call<Void> call = crudInterfaces.delete(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                mostrarToast("Coche eliminado");
                // Borrado exitoso, puedes realizar acciones adicionales si es necesario
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
