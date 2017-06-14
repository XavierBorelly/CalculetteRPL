package com.example.admin.calculetterpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView zoneEcriture;

    private TextView pile1;
    private TextView pile2;
    private TextView pile3;
    private TextView pile4;

    private List<String> pile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoneEcriture = (TextView) findViewById(R.id.zoneEcriture);

        pile1 = (TextView) findViewById(R.id.pile1);
        pile2 = (TextView) findViewById(R.id.pile2);
        pile3 = (TextView) findViewById(R.id.pile3);
        pile4 = (TextView) findViewById(R.id.pile4);

        pile = new ArrayList<>();

    }

    public void on0(View view) {
        String value = zoneEcriture.getText() + "0";
        zoneEcriture.setText(value);
    }

    public void on1(View view) {
        String value = zoneEcriture.getText() + "1";
        zoneEcriture.setText(value);
    }

    public void on2(View view) {
        String value = zoneEcriture.getText() + "2";
        zoneEcriture.setText(value);
    }

    public void on3(View view) {
        String value = zoneEcriture.getText() + "3";
        zoneEcriture.setText(value);
    }

    public void on4(View view) {
        String value = zoneEcriture.getText() + "4";
        zoneEcriture.setText(value);
    }

    public void on5(View view) {
        String value = zoneEcriture.getText() + "5";
        zoneEcriture.setText(value);
    }

    public void on6(View view) {
        String value = zoneEcriture.getText() + "6";
        zoneEcriture.setText(value);
    }

    public void on7(View view) {
        String value = zoneEcriture.getText() + "7";
        zoneEcriture.setText(value);
    }

    public void on8(View view) {
        String value = zoneEcriture.getText() + "8";
        zoneEcriture.setText(value);
    }

    public void on9(View view) {
        String value = zoneEcriture.getText() + "9";
        zoneEcriture.setText(value);
    }

    public void onVirgule(View view) {
        if(!zoneEcriture.getText().toString().contains("."))
        {
            if(!zoneEcriture.getText().toString().isEmpty()) {
                String value = zoneEcriture.getText() + ".";
                zoneEcriture.setText(value);
            }
        }
    }

    public void onNegate(View view) {
        if(!zoneEcriture.getText().toString().contains("-")) {
            String value = "-" + zoneEcriture.getText();
            zoneEcriture.setText(value);
        }
        else{
            String value = zoneEcriture.getText().subSequence(1, zoneEcriture.getText().length()).toString();
            zoneEcriture.setText(value);
        }

    }

    public void onMoins(View view) {
        calcul(view, "-");
    }

    public void onPlus(View view) {
        calcul(view, "+");
    }

    public void onMultiplier(View view) {
        calcul(view, "*");
    }

    public void onDiviser(View view) {

        onEnter(view);
        if(pile.size() > 1) {
            BigDecimal newValue = new BigDecimal(pile.get(0));

            if (newValue.compareTo(new BigDecimal("0")) != 0) {
                calcul(view, "/");
            }
        }
    }


    public void calcul(View view, String caractère)
    {

        onEnter(view);

        if(pile.size() >= 2){
            BigDecimal lastValue = new BigDecimal(pile.get(1));
            BigDecimal newValue = new BigDecimal(pile.get(0));

            BigDecimal value;

            String resulta = "";

            switch (caractère){
                case "+" : value = lastValue.add(newValue); resulta = value + "" ; break;
                case "-" : value = lastValue.subtract(newValue); resulta = value + "" ; break;
                case "/" : value = lastValue.divide(newValue, MathContext.DECIMAL32); resulta = value + "" ; break;
                case "*" : value = lastValue.multiply(newValue); resulta = value + "" ; break;
            }
            pile.remove(0);
            pile.set(0, resulta);
            EcriturePile();
        }
    }


    public void onPop(View view) {
        onEnter(view);
        if(pile.size() >= 1){
            pile.remove(0);
        }
        EcriturePile();
    }

    public void onClear(View view) {
        pile.clear();
        pile4.setText("");
        pile3.setText("");
        pile2.setText("");
        pile1.setText("");
        zoneEcriture.setText("");
    }

    public void onSwap(View view) {
        String saveText = pile.get(0);
        pile.set(0, pile.get(1));
        pile.set(1, saveText);
        EcriturePile();
    }

    public void onEnter(View view) {

        if(!zoneEcriture.getText().toString().isEmpty()){
            if(!zoneEcriture.getText().toString().equals("-")){
                pile.add(0, zoneEcriture.getText().toString());
                zoneEcriture.setText("");
                EcriturePile();
            }
        }

    }

    private void EcriturePile() {
        pile4.setText("");
        pile3.setText("");
        pile2.setText("");
        pile1.setText("");

        int taillePile = pile.size();

        if(taillePile >= 4){
            taillePile = 4;
        }
        switch (taillePile){
            case 4: pile4.setText(pile.get(3));
            case 3: pile3.setText(pile.get(2));
            case 2: pile2.setText(pile.get(1));
            case 1: pile1.setText(pile.get(0));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String superString = savedInstanceState.getString("sauv", "");
        String[] splitString = superString.split("S");
        pile.addAll(Arrays.asList(splitString));

        EcriturePile();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String superString = "";

        for (String string : pile){
            if(superString.isEmpty())
            {
                superString = string;
            }
            else{
                superString = superString + "S" + string;
            }
        }

        outState.putString("sauv", superString);
    }
}
