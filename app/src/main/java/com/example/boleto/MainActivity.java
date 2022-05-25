package com.example.boleto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumeroBoleto;
    private EditText txtNombreCliente;
    private EditText txtEdad;
    private EditText txtFecha;
    private Spinner spnDestinos;
    private Spinner spnTipoBoleto;
    private EditText txtPrecio;

    private Button btnCalular;
    private Button btnLimpiar;
    private Button btnCerrar;

    private TextView lblNumeroBoleto2;
    private TextView lblNombreCliente2;
    private TextView lblEdad2;
    private TextView lblFecha2;
    private TextView lblDestinos2;
    private TextView lblTipoBoleto2;
    private TextView lblPrecio2;
    private TextView lblSubTotal;
    private TextView lblTotal;
    private TextView lblDescuento;
    private TextView lblImpuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNumeroBoleto2 = (TextView) findViewById(R.id.lblNumeroBoleto2);
        lblNombreCliente2 = (TextView) findViewById(R.id.lblNombreCliente2);
        lblEdad2 = (TextView) findViewById(R.id.lblEdad2);
        lblFecha2 = (TextView) findViewById(R.id.lblFecha2);
        lblDestinos2 = (TextView) findViewById(R.id.lblDestinos2);
        lblTipoBoleto2 = (TextView) findViewById(R.id.lblTipoBoleto2);
        lblPrecio2 = (TextView) findViewById(R.id.lblPrecio2);

        lblSubTotal = (TextView) findViewById(R.id.lblSubTotal);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
        lblDescuento = (TextView) findViewById(R.id.lblDescuento);
        lblImpuesto = (TextView) findViewById(R.id.lblImpuesto);

        txtNumeroBoleto = (EditText) findViewById(R.id.txtNumeroBoleto);
        txtNombreCliente = (EditText) findViewById(R.id.txtNombreCliente);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        spnDestinos = (Spinner) findViewById(R.id.spnDestinos);
        spnTipoBoleto = (Spinner) findViewById(R.id.spnTipoBoleto);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);

        btnCalular = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);

        final String [] destinos = {""};
        final int [] tipoBoleto = {0};

        Boleto boleto = new Boleto();

        btnCalular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double subTotal;
                double descuento;
                double impuesto;
                double total;

                if(txtNumeroBoleto.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Dato faltante: Numero de Boleto",Toast.LENGTH_SHORT).show();
                }
                else if(txtNombreCliente.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Dato faltante: Nombre del cliente",Toast.LENGTH_SHORT).show();
                }
                else if(txtEdad.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Dato faltante: Edad",Toast.LENGTH_SHORT).show();
                }
                else if(txtFecha.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Dato faltante: Fecha", Toast.LENGTH_SHORT).show();
                }
                else if(txtPrecio.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Dato faltante: Precio del boleto",Toast.LENGTH_SHORT).show();
                }
                else{
                    String numeroBoleto = txtNumeroBoleto.getText().toString();
                    boleto.setNumeroBoleto(Integer.parseInt(numeroBoleto));

                    String nombreCliente = txtNombreCliente.getText().toString();
                    boleto.setNombreCliente(nombreCliente);

                    String edad = txtEdad.getText().toString();

                    String fecha = txtFecha.getText().toString();
                    boleto.setFecha(fecha);

                    boleto.setDestino(destinos[0]);

                    boleto.setTipoBoleto(tipoBoleto[0]);

                    String precio = txtPrecio.getText().toString();
                    boleto.setPrecio(Double.parseDouble(precio));

                    subTotal = boleto.obtenerSubtotal();

                    descuento = boleto.obtenerDescuento(Integer.parseInt(edad));

                    impuesto = boleto.obtenerImpuesto();

                    total = boleto.obtenerTotal();

                    //Mostrar datos
                    lblNumeroBoleto2.setText("# " + boleto.getNumeroBoleto());
                    lblNombreCliente2.setText("Nombre: " + boleto.getNombreCliente());
                    lblEdad2.setText("Edad " + edad);
                    lblFecha2.setText("Fecha: "+ boleto.getFecha());
                    lblDestinos2.setText("Destino: " + boleto.getDestino());
                    lblTipoBoleto2.setText("Tipo Boleto: "+ boleto.getTipoBoleto());
                    lblPrecio2.setText("Precio: " + boleto.getPrecio());
                    lblSubTotal.setText("Subtotal: "+subTotal);
                    lblImpuesto.setText("Impuesto: " + impuesto);
                    lblDescuento.setText("Descuento: " + descuento);
                    lblTotal.setText("Total: " + total);
                }
            }
        });

        ArrayAdapter<String> Adpt1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.destinos));
        spnDestinos.setAdapter(Adpt1);
        spnDestinos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                destinos[0] = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> Adpt2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.tipoBoleto));
        spnTipoBoleto.setAdapter(Adpt2);
        spnTipoBoleto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tipo = adapterView.getItemAtPosition(i).toString();
                if (tipo.matches("Sencillo")) {
                    tipoBoleto [0] = 1;
                } else if (tipo.matches("Doble")){
                    tipoBoleto [0] = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNumeroBoleto.setText("");
                txtNombreCliente.setText("");
                txtEdad.setText("");
                txtFecha.setText("");
                txtPrecio.setText("");

                lblNumeroBoleto2.setText("");
                lblNombreCliente2.setText("");
                lblEdad2.setText("");
                lblFecha2.setText("");
                lblDestinos2.setText("");
                lblTipoBoleto2.setText("");
                lblPrecio2.setText("");
                lblSubTotal.setText("");
                lblImpuesto.setText("");
                lblDescuento.setText("");
                lblTotal.setText("");
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmar = new AlertDialog.Builder(MainActivity.this);
                confirmar.setTitle("¿Cerrar APP?");
                confirmar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                confirmar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirmar.show();
            }
        });
    }
}