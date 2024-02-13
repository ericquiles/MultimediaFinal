package com.vedruna.multimediafinal.CarAdp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.multimediafinal.R; // Asegúrate de cambiar el paquete R al correspondiente
import com.vedruna.multimediafinal.modelo.Car; // Asegúrate de cambiar el nombre del modelo Player a Car o como se llame en tu API

import java.util.List;

/**
 * Adaptador personalizado para la visualización de la lista de coches.
 */
public class CarAdp extends BaseAdapter {
    private List<Car> carList;
    private Context context;

    /**
     * Constructor de la clase CarAdp.
     *
     * @param context  Contexto de la aplicación.
     * @param carList  Lista de objetos Car que se mostrarán en la lista.
     */
    public CarAdp(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carList.get(position).getIdCar(); // Asegúrate de tener un método getIdCar() en tu modelo Car
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.car_list_item, parent, false); // Asegúrate de tener el archivo correcto (car_list_item.xml)

            viewHolder = new ViewHolder();
            viewHolder.idLabel = convertView.findViewById(R.id.idLabel);
            viewHolder.idText = convertView.findViewById(R.id.idText);
            viewHolder.nameLabel = convertView.findViewById(R.id.nameLabel);
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.priceLabel = convertView.findViewById(R.id.priceLabel);
            viewHolder.priceText = convertView.findViewById(R.id.priceText);
            viewHolder.modelLabel = convertView.findViewById(R.id.modelLabel);
            viewHolder.modelText = convertView.findViewById(R.id.modelText);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el coche actual
        Car car = (Car) getItem(position);

        // Asignar los valores del coche a las vistas
        viewHolder.idLabel.setText("Id: ");
        viewHolder.idText.setText(String.valueOf(car.getIdCar()));
        viewHolder.nameLabel.setText("Nombre: ");
        viewHolder.nameText.setText(car.getName());
        viewHolder.priceLabel.setText("Precio: ");
        viewHolder.priceText.setText(String.valueOf(car.getPrice()));
        viewHolder.modelLabel.setText("Modelo: ");
        viewHolder.modelText.setText(car.getModel());
        Picasso.get().load(car.getImageURL()).into(viewHolder.imageView);

        return convertView;
    }

    /**
     * Clase interna que representa la vista de cada elemento en la lista.
     */
    static class ViewHolder {
        TextView idLabel;
        TextView idText;
        TextView nameLabel;
        TextView nameText;
        TextView priceLabel;
        TextView priceText;
        TextView modelLabel;
        TextView modelText;
        ImageView imageView;
    }
}
