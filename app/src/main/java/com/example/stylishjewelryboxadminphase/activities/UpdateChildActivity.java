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
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllSubCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllSubCatsResponse;
import com.example.stylishjewelryboxadminphase.get_allcateby_Ids.GetAllCat;
import com.example.stylishjewelryboxadminphase.get_allcateby_Ids.GetAllCatResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateResponse;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateChildActivity extends AppCompatActivity {
    private String TAG = "MYTAG";
    private Spinner spv_select_material, spv_select_category, spv_select_childtoUpdatecategory;
    private CardView card_selec_category, card_select_childtoUpdatecategory, card_updateChildCategry;
    private EditText et_updatechildename, et_updatechildeprice;
    private WebServices webServices;
    private Button btn_updatechildCategory;
    private String mediaPath;
    private int MY_REQ_CODE = 1001;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_child);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");

        initviews();
        getMaterialNames();
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spv_select_material = findViewById(R.id.spv_select_material);
        spv_select_category = findViewById(R.id.spv_select_category);
        card_selec_category = findViewById(R.id.card_selec_category);
        card_select_childtoUpdatecategory = findViewById(R.id.card_select_childtoUpdatecategory);
        spv_select_childtoUpdatecategory = findViewById(R.id.spv_select_childtoUpdatecategory);
        card_updateChildCategry = findViewById(R.id.card_updateChildCategry);

        btn_updatechildCategory = findViewById(R.id.btn_updatechildCategory);

        et_updatechildename = findViewById(R.id.et_updatechildename);
        et_updatechildeprice = findViewById(R.id.et_updatechildeprice);

//        btn_updatechildCategory = findViewById(R.id.btn_updatechildCategory);
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
                Toast.makeText(UpdateChildActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

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
                        card_updateChildCategry.setVisibility(View.GONE);
                        card_select_childtoUpdatecategory.setVisibility(View.GONE);

                        webServices.getAllCatsByID(ids).enqueue(new Callback<GetAllCatResponse>() {
                            @Override
                            public void onResponse(Call<GetAllCatResponse> call, Response<GetAllCatResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        card_selec_category.setVisibility(View.VISIBLE);


                                        List<GetAllCat> catList = response.body().getGetAllCat();


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


                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllCatResponse> call, Throwable t) {
                                Log.d("MYTAG", "OnFailure: " + t.getMessage());


                            }
                        });


                    } else {
                        card_selec_category.setVisibility(View.GONE);
                        card_select_childtoUpdatecategory.setVisibility(View.GONE);

                        card_updateChildCategry.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_updateChildCategry.setVisibility(View.GONE);


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
                        card_select_childtoUpdatecategory.setVisibility(View.VISIBLE);
                        card_updateChildCategry.setVisibility(View.GONE);

                        Log.d(TAG, "onItemSelected: " + ids);
                        webServices.getAllSubCatBYID(ids).enqueue(new Callback<GetAllSubCatsResponse>() {
                            @Override
                            public void onResponse(Call<GetAllSubCatsResponse> call, Response<GetAllSubCatsResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        List<GetAllSubCat> list = response.body().getGetAllSubCats();

                                        int listsize = list.size();
                                        String[] array1 = new String[1];
                                        array1[0] = "Select Child Category";

                                        String[] array2 = new String[listsize];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select Child Category";

                                        String[] array4 = new String[listsize];


                                        for (int x = 0; x < list.size(); x++) {
                                            array2[x] = list.get(x).getJscName();
                                            array4[x] = list.get(x).getJscId();

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

                                        childCateforySpinner(new_array, new_arrayofIDS);


                                    } else {

                                        Toast.makeText(UpdateChildActivity.this, "Nothing Found: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<GetAllSubCatsResponse> call, Throwable t) {
                                Toast.makeText(UpdateChildActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    } else {
                        card_select_childtoUpdatecategory.setVisibility(View.GONE);
                        card_updateChildCategry.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_updateChildCategry.setVisibility(View.GONE);

            }
        });
    }

    private void childCateforySpinner(String[] arrayname, String[] arrayofIDS) {
        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayname);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_childtoUpdatecategory.setAdapter(arrayAdapter_slct_material);

        spv_select_childtoUpdatecategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayofIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Child Category")) {
                        card_updateChildCategry.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onItemSelected: " + ids);


                        btn_updatechildCategory.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String price = et_updatechildeprice.getText().toString().trim();
                                String name = et_updatechildename.getText().toString().trim();
                                if (TextUtils.isEmpty(name)) {
                                    et_updatechildename.setError("Can't be empty");
                                    et_updatechildename.setFocusable(true);

                                } else if (TextUtils.isEmpty(price)) {
                                    et_updatechildeprice.setError("Can't be empty");
                                    et_updatechildeprice.setFocusable(true);
                                } else {

                                    if (mediaPath != null) {


                                        uploadFile(price, name, ids);
                                    } else {
                                        Toast.makeText(UpdateChildActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                                    }

                                }


                            }
                        });


                    } else {
                        card_updateChildCategry.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


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
        webServices.updateChildCats(name, image, bfkid, cname, bprice).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(UpdateChildActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        mediaPath = null;
                        et_updatechildename.setText(null);
                        et_updatechildeprice.setText(null);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateChildActivity.this, "" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateChildActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void selectimgs(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);


    }
}
