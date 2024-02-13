package com.devdroid.ecomadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.Object;
import java.util.UUID;

import com.devdroid.ecomadmin.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private  String id, title,  description,price;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            title = binding.title.getText().toString();
            description=binding.description.getText().toString();
            price=binding.price.getText().toString();
            addProduct();
            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
        binding.uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("products/"+id+".png");
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        FirebaseFirestore.getInstance().collection("products")
                                                .document(id)
                                                .update("image",uri.toString());
                                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
    }

    private void addProduct() {
        id = UUID.randomUUID().toString();
        ProductModel productModel = new ProductModel(id,title,description,price,null,true);
        FirebaseFirestore.getInstance().collection("products")
                .document(id)
                .set(productModel);
        Toast.makeText(MainActivity.this, "Product Added", Toast.LENGTH_SHORT).show();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            uri=data.getData();
            binding.image.setImageURI(uri);
        }
    }
}