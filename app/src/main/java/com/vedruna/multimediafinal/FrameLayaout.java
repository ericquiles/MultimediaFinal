package com.vedruna.multimediafinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Actividad principal que contiene la interfaz de usuario con navegación inferior.
 */
public class FrameLayaout extends AppCompatActivity {
    FirebaseAuth salirApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer el diseño de la actividad
        setContentView(R.layout.frame_layaout);

        // Inicializar FirebaseAuth
        salirApp = FirebaseAuth.getInstance();

        // Obtener la referencia a BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.inicio);

        // Obtener el fragmento de navegación
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Configurar el oyente de selección para BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_inicio) {
                navController.navigate(R.id.inicio);
            } else if (item.getItemId() == R.id.navigation_add) {
                navController.navigate(R.id.anadir);
            } else if (item.getItemId() == R.id.navigation_actualizar) {
                navController.navigate(R.id.actualizar);
            } else if (item.getItemId() == R.id.navigation_borrar) {
                navController.navigate(R.id.borrar);
            } else if (item.getItemId() == R.id.navigation_salir) {
                showLogoutConfirmationDialog();
            }
            return true;
        });
    }

    /**
     * Método para realizar la salida de la aplicación.
     */
    private void logOut() {
        salirApp.signOut();
        goLogin();
    }

    /**
     * Método para navegar a la pantalla de inicio de sesión.
     */
    private void goLogin() {
        Intent intent = new Intent(FrameLayaout.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Método para mostrar un cuadro de diálogo de confirmación de cierre de sesión.
     */
    private void showLogoutConfirmationDialog() {
        // Construir el diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Quieres Salir?");

        // Establecer el texto y el color del botón positivo ("Sí")
        builder.setPositiveButton("Si", (dialog, which) -> logOut());

        // Establecer el texto y el color del botón negativo ("Cancelar")
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        // Obtener el color principal (cambia R.color.white por el identificador de tu color principal)
        int colorMain = getResources().getColor(R.color.white);

        // Crear el diálogo y mostrarlo
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Obtener los botones del diálogo
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // Establecer el color del texto de los botones
                positiveButton.setTextColor(colorMain);
                negativeButton.setTextColor(colorMain);
            }
        });
        dialog.show();
    }
}
