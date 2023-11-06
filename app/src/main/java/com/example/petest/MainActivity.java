package com.example.petest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petest.adapter.Adapter;
import com.example.petest.apiService.ApiService;
import com.example.petest.apiclient.APIClient;
import com.example.petest.model.Product;
import com.example.petest.model.Nsx;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ImageView icAdd;
    ImageView icDelete;
    ImageView icAddNsx;
    List<Product> productList = new ArrayList<>();
    List<Nsx> nsxList = new ArrayList<>();
    Adapter adapter;
    private ApiService apiService;
    public enum DialogType {
       CREATE,
        UPDATE
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icAdd = findViewById(R.id.ivAdd);
        icAdd.setOnClickListener(v -> showDialog(DialogType.CREATE, null));





        apiService = APIClient.getClient().create(ApiService.class);
        listView = findViewById(R.id.lv_Product);
        adapter = new Adapter(MainActivity.this, productList, nsxList, R.layout.row_product);
        adapter.setNsxList(nsxList);
        listView.setAdapter(adapter);

        loadPoductList();
        loadNsxList();



    }

    public void showDialog(DialogType dialogType, Product currentProduct) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.create_update_product);

        TextView tvHeading = dialog.findViewById(R.id.tvHeading);
        TextView negativeButton = dialog.findViewById(R.id.negativeButton);
        TextView positiveButton = dialog.findViewById(R.id.positiveButton);

        EditText etProductname = dialog.findViewById(R.id.edt_name);
        EditText etId = dialog.findViewById(R.id.edt_id);
        EditText etdate = dialog.findViewById(R.id.edt_date);
        EditText etgender = dialog.findViewById(R.id.edt_gender);
        EditText etAddress = dialog.findViewById(R.id.edt_address);
        EditText etisNganh = dialog.findViewById(R.id.edt_isnganh);
        Spinner spinnerNsx = dialog.findViewById(R.id.spinnerNhasx);
        EditText etDatesx = dialog.findViewById(R.id.edt_date);
//        RadioGroup rgQuicach = dialog.findViewById(R.id.rgProduct);
        Spinner spinnerQuicach = dialog.findViewById(R.id.spinnerQuicach);


        if (dialogType == DialogType.CREATE) {
            tvHeading.setText("Create Student");
            positiveButton.setText("Create");
        } else if (dialogType == DialogType.UPDATE) {
            tvHeading.setText("Update Student");
            positiveButton.setText("Update");
            etProductname.setText(currentProduct.getName());
            etDatesx.setText(currentProduct.getDatexs());
            etId.setText(currentProduct.getId());
            etgender.setText(currentProduct.getQuicach());
            etAddress.setText(currentProduct.getDatexs());
            etisNganh.setText(currentProduct.getIdNhsx());
        }


        ArrayAdapter<CharSequence> quicachAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.quicach_array, android.R.layout.simple_spinner_item);
        quicachAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuicach.setAdapter(quicachAdapter);

        ArrayAdapter<CharSequence> nhasxAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.nhasx_array, android.R.layout.simple_spinner_item);
        nhasxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNsx.setAdapter(nhasxAdapter);

        negativeButton.setOnClickListener(v -> dialog.dismiss());

        positiveButton.setOnClickListener(v -> {
            String name = etProductname.getText().toString().trim();
            String id = etId.getText().toString().trim();
            String gender = etgender.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String isNganh = etisNganh.getText().toString().trim();
            String selectedNsx =  spinnerNsx.getSelectedItem().toString();
            String date = etDatesx.getText().toString().trim();
            String quicach = spinnerQuicach.getSelectedItem().toString();

            if (name.isEmpty()) {
                etProductname.setError("Name is required");
                etProductname.requestFocus();
                return;
            }
            if (selectedNsx == null) {
                Toast.makeText(MainActivity.this, "Nsx is required", Toast.LENGTH_SHORT).show();
                return;
            }
            if (date.isEmpty()) {
                etDatesx.setError("Datesx is required");
                etDatesx.requestFocus();
                return;
            }

            Product newProduct = new Product(name, date, quicach, selectedNsx);

            if (dialogType == DialogType.CREATE) {
                createProduct(newProduct);
            } else {
                updateProduct(currentProduct.getId(), newProduct);
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    public void loadPoductList() {
        apiService.getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    adapter.setProductList(productList);
                    adapter.notifyDataSetChanged();
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }

    public void loadNsxList() {
        apiService.getPhongban().enqueue(new Callback<List<Nsx>>() {
            @Override
            public void onResponse(Call<List<Nsx>> call, Response<List<Nsx>> response) {
                if (response.isSuccessful()) {
                    nsxList = response.body();
                    adapter.setNsxList(nsxList);
                    adapter.notifyDataSetChanged();
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<List<Nsx>> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }


    public void createProduct(Product product) {
        apiService.addProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Student created successfully", Toast.LENGTH_SHORT).show();
                    loadPoductList();
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }

    public void updateProduct(String productId, Product product) {
        apiService.updateProduct(productId, product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                    loadPoductList();
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }

    public void deleteProduct(Product productToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete Student");
        builder.setMessage("Are you sure you want to delete " + productToDelete.getName() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            apiService.deleteProduct(productToDelete.getId()).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                        loadPoductList();
                    } else {
                        Toast.makeText(MainActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
                }
            });
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
    }



