package utilities;

import com.jfoenix.controls.JFXTextField;

public class Validation {

    /**
     * Validation helper to help dry up the code.
     * The method takes in a JFoenix text fields and adds the listeners for the required
     * listeners
     *
     * Reference:
     * http://www.jfoenix.com/documentation.html#InputFields
     *
     * @param field the field to which the listener is being added
     */
    public static void addValidationListener(JFXTextField field) {
        // Using a lambda function to monitor for changes to the value,
        // if the value has changed, validate the input
        field.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue) field.validate();
        });
    }

}
