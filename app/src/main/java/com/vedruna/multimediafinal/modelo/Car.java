package com.vedruna.multimediafinal.modelo;

import androidx.annotation.NonNull;

/**
 * Clase que representa un objeto Car en la aplicación.
 */
public class Car {
    public int idCar;
    public String name;
    public int price;
    public String model;
    public String imageURL;

    /**
     * Constructor por defecto de la clase Car.
     */
    public Car() {
    }

    /**
     * Constructor de la clase Car con parámetros.
     *
     * @param idCar    Identificador único del coche.
     * @param name     Nombre del coche.
     * @param price    Precio del coche.
     * @param model    Modelo del coche.
     * @param imageURL URL de la imagen asociada al coche.
     */
    public Car(int idCar, String name, String price, String model, String imageURL) {
        this.idCar = idCar;
        this.name = name;
        this.price = Integer.parseInt(price);
        this.model = model;
        this.imageURL = imageURL;
    }

    /**
     * Obtiene el identificador único del coche.
     *
     * @return El identificador único del coche.
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * Establece el identificador único del coche.
     *
     * @param idCar El identificador único del coche.
     */
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    /**
     * Obtiene el nombre del coche.
     *
     * @return El nombre del coche.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del coche.
     *
     * @param name El nombre del coche.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del coche.
     *
     * @return El precio del coche.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Establece el precio del coche.
     *
     * @param price El precio del coche.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Obtiene el modelo del coche.
     *
     * @return El modelo del coche.
     */
    public String getModel() {
        return model;
    }

    /**
     * Establece el modelo del coche.
     *
     * @param model El modelo del coche.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Obtiene la URL de la imagen asociada al coche.
     *
     * @return La URL de la imagen asociada al coche.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Establece la URL de la imagen asociada al coche.
     *
     * @param imageURL La URL de la imagen asociada al coche.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Genera una representación en cadena del objeto Car.
     *
     * @return Una cadena que representa el objeto Car.
     */
    @NonNull
    @Override
    public String toString() {
        return "Id: " + getIdCar() + " / Nombre: " + getName() + " / Precio: " + getPrice() + " / Modelo: " + getModel();
    }
}
