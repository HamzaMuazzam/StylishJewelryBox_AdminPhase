package com.example.stylishjewelryboxadminphase.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewCategory;
import com.example.stylishjewelryboxadminphase.network.WebServices;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCategoryActivity extends AppCompatActivity {
    Spinner spv_addnew_cats;
    private ArrayAdapter<String> arrayAdapterOfAddCat;
    WebServices webServices;
    Button btn_insertNewCategory;
    LinearLayout linear_addcategory;
    String mediaPath;
    EditText et_addnewCategory, et_addnewCategory_Starting_price;
    private int MY_REQ_CODE = 1001;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading....");
        checkReadPhoneStatePermission();
        initviews();
    }

    private void initviews() {

        et_addnewCategory = findViewById(R.id.et_addnewCategory);
        et_addnewCategory_Starting_price = findViewById(R.id.et_addnewCategory_Starting_price);

        spv_addnew_cats = findViewById(R.id.spv_addnew_cats);
        btn_insertNewCategory = findViewById(R.id.btn_insertNewCategory);
        linear_addcategory = findViewById(R.id.linear_addcategory);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        getMaterialNames();


    }

    private void getMaterialNames() {
        webServices.getmterialname().enqueue(new Callback<GetAllMeterialCatResponse>() {
            @Override
            public void onResponse(Call<GetAllMeterialCatResponse> call, Response<GetAllMeterialCatResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getStatus()) {
                        List<GetAllMeterialCat> meterialCatList = response.body().getGetAllMeterialCat();
                        int listsize = meterialCatList.size();

                        String[] array1 = new String[1];
                        array1[0] = "Select Material Type";
                        String[] array2 = new String[listsize];


                        String[] array3 = new String[1];
                        array3[0] = "Select Material";
                        String[] array4 = new String[listsize];


                        for (int x = 0; x < meterialCatList.size(); x++) {
                            array2[x] = meterialCatList.get(x).getMcName();
                            array4[x] = meterialCatList.get(x).getMcId();

                        }


                        String[] locationarray1 = array2;
                        String[] locationarray2 = array1;
                        int length1 = locationarray1.length;
                        int length2 = locationarray2.length;
                        int length3 = array3.length; //slect
                        int length4 = array4.length; //id
                        String[] new_array = new String[length1 + length2];
                        System.arraycopy(locationarray2, 0, new_array, 0, length2);
                        System.arraycopy(locationarray1, 0, new_array, length2, length1);


                        String[] new_arrayofIDS = new String[length3 + length4];
                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);

                        spinner_addnewCategory(new_array, new_arrayofIDS);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllMeterialCatResponse> call, Throwable t) {
                Toast.makeText(NewCategoryActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void spinner_addnewCategory(String[] array, String[] arrayOfIDS) {

//        for (String item : array) {
//            Log.d("MYTAG", "spinner_addnewCategory: " + item);
//        }

        arrayAdapterOfAddCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapterOfAddCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_addnew_cats.setAdapter(arrayAdapterOfAddCat);

        spv_addnew_cats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ID = arrayOfIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#000000"));
                    if (!item.equalsIgnoreCase("Select Material Type")) {
                        linear_addcategory.setVisibility(View.VISIBLE);
                        Log.d("MYTAG", "onItemSelected: " + ID);
                        btn_insertNewCategory.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mediaPath != null) {
                                    String name = et_addnewCategory.getText().toString().trim();
                                    String price = et_addnewCategory_Starting_price.getText().toString().trim();
                                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)) {


                                        uploadFile(price, name, ID);


                                    } else {
                                        Toast.makeText(NewCategoryActivity.this, "Some Field is Empty", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(NewCategoryActivity.this, "Please Choose an Image", Toast.LENGTH_LONG).show();
                                }


/**
 * check uri and permission
 * then upload image and inser details
 * ***/

//                                webServices.insertNewCategory("Test Name", "909", "null",
//                                        ID).enqueue(new Callback<InsertNewCategory>() {
//                                    @Override
//                                    public void onResponse(Call<InsertNewCategory> call, Response<InsertNewCategory> response) {
//                                        if (response.isSuccessful() && response.body() != null) {
//                                            if (response.body().getStatus()) {
//
//                                                Toast.makeText(NewCategoryActivity.this, "Status " + response.body().getStatus()
//                                                        , Toast.LENGTH_LONG).show();
//
//                                            }
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<InsertNewCategory> call, Throwable t) {
//
//                                        Toast.makeText(NewCategoryActivity.this, "OnFailure " + t.getMessage()
//                                                , Toast.LENGTH_LONG).show();
//                                    }
//                                });
//

                            }
                        });

                    } else {
                        linear_addcategory.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void uploadFile(String price, String category_name, String ID) {

        // Map is used to multipart the file using okhttp3.RequestBody

        File file = new File(mediaPath);
        progressDialog.show();

        // Parsing any Media type file

        final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);


        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody bprice = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody cname = RequestBody.create(MediaType.parse("text/plain"), category_name);
        RequestBody bfkid = RequestBody.create(MediaType.parse("text/plain"), ID);

        webServices.insertNewCategory(name, image, bfkid, cname, bprice).enqueue(new Callback<InsertNewCategory>() {
            @Override
            public void onResponse(Call<InsertNewCategory> call, Response<InsertNewCategory> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(NewCategoryActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        et_addnewCategory.setText(null);
                        et_addnewCategory_Starting_price.setText(null);
                        mediaPath = null;
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(NewCategoryActivity.this, "False", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<InsertNewCategory> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewCategoryActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void selectCategoryImage(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You can't Run app without permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("This  permission needed to run this app properly on your device")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(NewCategoryActivity.this, new String[]{Manifest.
                                    permission.READ_EXTERNAL_STORAGE}, MY_REQ_CODE);
                        }
                    }).setCancelable(false)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.
                    permission.READ_EXTERNAL_STORAGE}, MY_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                mediaPath = getRealPathFromURI(selectedImage);
                Toast.makeText(this, "Path: \n" + mediaPath, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            Log.d("MYTAG", "onActivityResult: " + e.getMessage());
        }

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
