package com.example.stylishjewelryboxadminphase.activities;

import android.app.ProgressDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.get_allcateby_Ids.GetAllCat;
import com.example.stylishjewelryboxadminphase.get_allcateby_Ids.GetAllCatResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateCategoryResponse;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCategoryActivity extends AppCompatActivity {
    WebServices webServices;
    Spinner spv_select_material, spv_select_category;
    CardView card_selec_category, card_details_form_newitem;
    Button btnUpdateCategory;
    private ProgressDialog progressDialog;
    EditText et_updatecat_name, et_updatecat_price;
    private String mediaPath;
    private int MY_REQ_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_category);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        initviews();

        getMaterialNames();

    }

    private void initviews() {
        progressDialog.setMessage("Updating...");
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spv_select_material = findViewById(R.id.spv_select_material_updatecats);
        spv_select_category = findViewById(R.id.spv_select_category_for_update);

        card_selec_category = findViewById(R.id.card_selec_category);
        card_details_form_newitem = findViewById(R.id.card_details_form_newitem);


        et_updatecat_name = findViewById(R.id.et_updatecat_name);
        et_updatecat_price = findViewById(R.id.et_updatecat_price);
        btnUpdateCategory = findViewById(R.id.btnUpdateCategory);

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
                        array1[0] = "Select Material";

                        String[] array2 = new String[listsize];

                        String[] array3 = new String[1];
                        array3[0] = "Select Material";

                        String[] array4 = new String[listsize];


                        for (int x = 0; x < meterialCatList.size(); x++) {
                            array2[x] = meterialCatList.get(x).getMcName();
                            array4[x] = meterialCatList.get(x).getMcId();

                        }

                        int length1 = array1.length; //slect
                        int length2 = array2.length; //name

                        int length3 = array3.length; //slect
                        int length4 = array4.length; //id

                        String[] new_array = new String[length2 + length1];
                        System.arraycopy(array1, 0, new_array, 0, length1);
                        System.arraycopy(array2, 0, new_array, length1, length2);


                        String[] new_arrayofIDS = new String[length3 + length4];
                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);


                        selectMaterial(new_array, new_arrayofIDS);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllMeterialCatResponse> call, Throwable t) {
                Toast.makeText(UpdateCategoryActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void selectMaterial(String[] array, String[] arrayofis) {
//        for (int x = 0; x < arrayofis.length; x++) {
//
//            Log.d("MYTAG", "selectMaterial: " + arrayofis[x]);
//        }
        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_material.setAdapter(arrayAdapter_slct_material);

        spv_select_material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayofis[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Material")) {

                        webServices.getAllCatsByID(ids).enqueue(new Callback<GetAllCatResponse>() {
                            @Override
                            public void onResponse(Call<GetAllCatResponse> call, Response<GetAllCatResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {


                                        List<GetAllCat> catList = response.body().getGetAllCat();
                                        card_details_form_newitem.setVisibility(View.GONE);
                                        card_selec_category.setVisibility(View.VISIBLE);

                                        int listsize = catList.size();

                                        String[] array1 = new String[1];
                                        array1[0] = "Select category";

                                        String[] array2 = new String[listsize];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select category";

                                        String[] array4 = new String[listsize];


                                        for (int x = 0; x < catList.size(); x++) {
                                            array2[x] = catList.get(x).getJcName();
                                            array4[x] = catList.get(x).getJcId();

                                        }

                                        int length1 = array1.length; //slect
                                        int length2 = array2.length; //name

                                        int length3 = array3.length; //slect
                                        int length4 = array4.length; //id

                                        String[] new_array = new String[length2 + length1];
                                        System.arraycopy(array1, 0, new_array, 0, length1);
                                        System.arraycopy(array2, 0, new_array, length1, length2);


                                        String[] new_arrayofIDS = new String[length3 + length4];
                                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);


                                        categoryNameIDs(new_array, new_arrayofIDS);

                                    } else {
                                        Log.d("MYTAG", " Status : false ");
                                        card_details_form_newitem.setVisibility(View.GONE);

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllCatResponse> call, Throwable t) {
                                Log.d("MYTAG", "OnFailure: " + t.getMessage());
                                card_details_form_newitem.setVisibility(View.GONE);

                            }
                        });


                    } else {
                        card_selec_category.setVisibility(View.GONE);
                        card_details_form_newitem.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_details_form_newitem.setVisibility(View.GONE);

            }
        });

    }

    private void categoryNameIDs(String[] new_array, String[] new_arrayofIDS) {


        card_selec_category.setVisibility(View.VISIBLE);
        ArrayAdapter<String> arrayAdapter_slct_Category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new_array);
        arrayAdapter_slct_Category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_category.setAdapter(arrayAdapter_slct_Category);

        spv_select_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = new_arrayofIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select category")) {
                        card_details_form_newitem.setVisibility(View.VISIBLE);
                        btnUpdateCategory.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                udateCategory(item, ids);

                            }
                        });


                    } else {
                        card_details_form_newitem.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void udateCategory(String item, String ids) {

        String name = et_updatecat_name.getText().toString().trim();
        String price = et_updatecat_price.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            et_updatecat_name.setError("Can't be Empty");
            et_updatecat_name.setFocusable(true);
        } else if (TextUtils.isEmpty(price)) {
            et_updatecat_price.setError("Can't be Empty");
            et_updatecat_price.setFocusable(true);
            et_updatecat_name.setError(null);

        } else {

            if (mediaPath != null) {
                Toast.makeText(this, name + price + ids, Toast.LENGTH_SHORT).show();

                uploadFile(price, name, ids);

            } else {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            }

        }

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

    private void uploadFile(String price, String category_name, String ID) {
        progressDialog.show();
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);


        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody bprice = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody cname = RequestBody.create(MediaType.parse("text/plain"), category_name);
        RequestBody bfkid = RequestBody.create(MediaType.parse("text/plain"), ID);
        webServices.updateCats(name, image, bfkid, cname, bprice).enqueue(new Callback<UpdateCategoryResponse>() {
            @Override
            public void onResponse(Call<UpdateCategoryResponse> call, Response<UpdateCategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(UpdateCategoryActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        et_updatecat_name.setText(null);
                        et_updatecat_price.setText(null);
                        mediaPath = null;
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateCategoryActivity.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateCategoryResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateCategoryActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void selectimg(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);

    }
}
