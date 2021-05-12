package com.example.androidnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.androidnews.db.DAOArticle;
import com.example.androidnews.db.DBController;
import com.example.androidnews.models.ArticleDB;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class AddArticleActivity extends AppCompatActivity
        implements Validator.ValidationListener{

    @NotEmpty
    EditText titulo;
    @NotEmpty
    EditText autor;
    @NotEmpty
    EditText contenido;

    @NotEmpty
    EditText fechaPub;

    ImageView imagenv;

    Date fecha;
    byte[] imagen;
    Validator validator;
    private int PICK_IMAGE_REQUEST = 15;
    private int dia,mes,ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        validator= new Validator(this);
        validator.setValidationListener(this);

        titulo=this.findViewById(R.id.etTitulo);
        autor=this.findViewById(R.id.etAutor);
        contenido=this.findViewById(R.id.etContenido);
        fechaPub=this.findViewById(R.id.etFechaPublicacion);
        imagenv=this.findViewById(R.id.ivImagen);

    }
    public void Newmas(){
        DBController db= Room.databaseBuilder(this, DBController.class,"DBNews" ).allowMainThreadQueries().build();
        DAOArticle dao=db.getTablaArticle();
        Toast.makeText(this,String.valueOf( imagen.length), Toast.LENGTH_LONG).show();
        ArticleDB article=new ArticleDB((long) 0, autor.getText().toString(),titulo.getText().toString(),contenido.getText().toString(),fecha,imagen);
        dao.insert(article);
        finish();
    }

    @Override
    public void onValidationSucceeded() {
        Newmas();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void add(View view){
        validator.validate();
    }

    public void fin(View view) {
        this.finish();
    }

    public void select(View view ){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void  mostrarcalendario(View view){
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fechaPub.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                fecha= new Date(year,monthOfYear,dayOfMonth);
            }
        },ano,mes,dia);
        datePickerDialog.show();
    }
    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            File f=new File(uri.getPath()) ;
            imagen= readFileToByteArray(f);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imagenv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}