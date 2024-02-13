package com.vedruna.multimediafinal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.vedruna.multimediafinal.CarAdp.CarAdp;
import com.vedruna.multimediafinal.interfaz.CRUD;
import com.vedruna.multimediafinal.modelo.Car;
import com.vedruna.multimediafinal.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para mostrar la lista de coches al usuario.
 */
public class inicio extends Fragment {

    List<Car> cars;
    CRUD crudInterface;
    ListView listView;

    /**
     * Constructor por defecto de la clase inicio.
     */
    public inicio() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        listView = view.findViewById(R.id.lista);
        getAll();
        return view;
    }

    /**
     * Método para obtener la lista de coches desde la API.
     */
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        crudInterface = retrofit.create(CRUD.class);
        Call<List<Car>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Reponse error: " , response.message());
                    return;
                }
                cars = response.body();
                CarAdp carAdapter = new CarAdp(requireContext(), cars);
                listView.setAdapter(carAdapter);
                cars.forEach(c -> Log.i("Coches: ", c.toString()));
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Log.e("throw error: " , t.getMessage());
            }
        });
    }
}
