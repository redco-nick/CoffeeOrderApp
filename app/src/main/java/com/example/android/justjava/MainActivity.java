package com.example.android.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static android.os.Build.VERSION_CODES.N;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckbox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox ChocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText text=(EditText)findViewById(R.id.name_view);
        String name =text.getText().toString();
        boolean haswhippedcream=WhippedCreamCheckbox.isChecked();
        boolean haschocolate = ChocolateCheckbox.isChecked();
        int price = calculatePrice(haswhippedcream,haschocolate);
        String OrderSummary = createOrderSummary(price,haswhippedcream,haschocolate,name);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+ name);
            intent.putExtra(Intent.EXTRA_TEXT,OrderSummary );
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        displayMessage(OrderSummary);
    }

    private int calculatePrice(boolean whippedcream,boolean chocolate) {
        int basePrice=5;
        if(whippedcream){
            basePrice+=1;
        }
        if(chocolate){
            basePrice+=2;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(int price,boolean addwhipcream,boolean addchocolate,String addname) {

        String priceMessage= "Name:"+addname+"\n";
        priceMessage+="has whipped cream ? "+ addwhipcream;
        priceMessage+="\nhas chocolate ?"+addchocolate;
        priceMessage+="\nQuantity: " + quantity;
        priceMessage=priceMessage +  "\nTotal:$" + price + "\n";
        priceMessage+= getString(R.string.thanks);
        return priceMessage;
    }


    public void increment(View view) {
        if(quantity==100){
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;}
        quantity = quantity + 1;

        displayQuantity(quantity);
    }

    public void decrement(View view) {
      if(quantity==1){
          Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
          return;}
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        orderSummaryTextView.setText(message);
    }


}
