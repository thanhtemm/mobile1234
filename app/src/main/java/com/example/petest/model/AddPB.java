//package com.example.petest.model;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.petest.R;
//import com.example.petest.apiService.ApiService;
//
//public class AddPB extends AppCompatActivity {
//    private EditText edtNamePB;
//    private Button btnAddPB;
//    private ApiService apiService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_phongban);
//
//        // Ánh xạ các thành phần trong layout
//        edtNamePB = findViewById(R.id.edt_namePB);
//        btnAddPB = findViewById(R.id.btn_addpb);
//
//        // Khởi tạo ApiService
//        apiService = RetrofitClient.getRetrofit().create(ApiService.class);
//
//        // Đặt sự kiện click cho nút "btnAddPB"
//        btnAddPB.setOnClickListener(v -> {
//            String namePB = edtNamePB.getText().toString().trim();
//
//            // Kiểm tra và xử lý logic khi thêm phòng ban
//            if (!namePB.isEmpty()) {
//                // Tạo một đối tượng Phongban mới với thông tin đã nhập
//                Phongban phongban = new Phongban();
//                phongban.setNamePB(namePB);
//
//                // Gọi phương thức addPhongban trên ApiService
//                addPhongban(phongban);
//            } else {
//                Toast.makeText(AddPhongbanActivity.this, "Please enter the Phongban name", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void addPhongban(Phongban phongban) {
//        apiService.addPhongban(phongban).enqueue(new Callback<Phongban>() {
//            @Override
//            public void onResponse(Call<Phongban> call, Response<Phongban> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(AddPhongbanActivity.this, "Phongban added successfully", Toast.LENGTH_SHORT).show();
//                    // Đóng Activity AddPhongbanActivity và trở về MainActivity
//                    finish();
//                } else {
//                    Toast.makeText(AddPhongbanActivity.this, "Failed to add Phongban", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Phongban> call, Throwable t) {
//                Toast.makeText(AddPhongbanActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
