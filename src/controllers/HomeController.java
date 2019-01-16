package controllers;

import java.io.*;
import javafx.fxml.FXML;
import models.Restaurant;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.nio.charset.StandardCharsets;

import static controllers.DashboardController.dbc;

public class HomeController {

    @FXML
    public void saveRestaurant() {
        try {
            this.save();
        } catch(IOException e) {
            System.out.println("Unable to save to disk");
        }
    }

    @FXML
    public void loadRestaurant() {
        try {
            this.load();
        } catch(IOException e) {
            System.out.println("Unable to read from disk");
        }
    }

    @FXML
    public void resetRestaurant() {
        String message = "Are you sure you want to reset the restaurant?\n"
                + "This cannot be undone.";
        dbc.displayAlertBox("Reset restaurant", message, null, "restaurant", null, null);
    }


    /**
     * Write the restaurant object and data to a json file
     *
     * References:
     * https://github.com/google/gson/blob/master/UserGuide.md
     * https://stackoverflow.com/questions/29319434/how-to-save-data-with-gson-in-a-json-file
     *
     * @throws IOException throws an IOException if the data cannot be saved to file
     */
    private void save() throws IOException {
        System.out.println("Saving to file...");
        // Set the type of the object, in this case Restaurant
        Type type = new TypeToken<Restaurant>() { }.getType();
        OutputStream outputStream = new FileOutputStream("restaurant.json");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        gson.toJson(DashboardController.restaurant, type, bufferedWriter);
        bufferedWriter.close();
        System.out.println("Save successful");
    }

    /**
     * Read the restaurant object from a json file and load into
     * application
     *
     * References:
     * https://github.com/google/gson/blob/master/UserGuide.md
     * https://stackoverflow.com/questions/29319434/how-to-save-data-with-gson-in-a-json-file
     *
     * @throws IOException throws an IOException if the data cannot be read from file
     */
    private void load() throws IOException {
        System.out.println("Loading from file...");
        // Set the type of the object, in this case Restaurant
        Type type = new TypeToken<Restaurant>() { }.getType();
        InputStream inputStream = new FileInputStream("restaurant.json");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        DashboardController.restaurant = gson.fromJson(streamReader, type);
        streamReader.close();
        System.out.println("Load successful");
    }

}
