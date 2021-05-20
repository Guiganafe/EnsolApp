package com.example.ensolapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ensolapp.BuildConfig;
import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDFEntrega;
import com.example.ensolapp.Utils.GerarPDFOrcamento;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrcamentoActivity extends AppCompatActivity {

    private Button btn_cancelar, btn_finalizar;
    private TextInputLayout data, nome, contato, potencia_desejada, localizacao, observacao;
    private ImageView foto_conta = null;
    private String fotoUrl;
    private DatePickerDialog datePickerDialog;
    private int dia, mes, ano;
    private Calendar calendar;
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 103;
    private String currentPhotoPath;
    Bitmap fotoGaleria = null;
    Bitmap foto_comprimida = null;
    Orcamento orcamento = new Orcamento();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);
        storageRef = FirebaseStorage.getInstance().getReference();
        inicializarComponentes();
        onClickController();
        timeController();
    }

    private void timeController() {
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);

        data.getEditText().setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String dataDia = String.format(Locale.getDefault(),"%02d/%02d/%04d", dayOfMonth, month+1, year);
                data.getEditText().setText(dataDia);

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date dataDate = null;
                try {
                    dataDate = formato.parse(dataDia);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar dataDoDia = Calendar.getInstance(Locale.getDefault());
                dataDate.setHours(dataDoDia.getTime().getHours());
                dataDate.setMinutes(dataDoDia.getTime().getMinutes());
                orcamento.setDataSolicitacao(dataDate);

            },ano, mes, dia);
            datePickerDialog.show();
        });
    }

    private void onClickController() {
        btn_cancelar.setOnClickListener(v -> onBackPressed());
        btn_finalizar.setOnClickListener(this::validarDados);
        foto_conta.setOnClickListener(this::selectImage);
    }

    private void selectImage(View view) {
        final CharSequence[] options = { "Tirar foto", "Selecionar da galeria","Cancelar" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar foto");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Tirar foto"))
                {
                    cameraPermissao();
                }
                else if (options[item].equals("Selecionar da galeria"))
                {
                    Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galeria, GALLERY_REQUEST_CODE);
                }
                else if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraPermissao() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_conta.setBackground(null);
                foto_conta.setPadding(0, 0, 0, 0);
                foto_conta.setImageBitmap(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_camera();
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                Uri contentUri = data.getData();
                foto_conta.setBackground(null);
                foto_conta.setPadding(0, 0, 0, 0);
                foto_conta.setImageURI(contentUri);
                try {
                    fotoGaleria = MediaStore.Images.Media.getBitmap(getContentResolver(), contentUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                enviarDadosFoto_galeria();
            }
        }
    }

    private Task<String> enviarDadosFoto_galeria() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_conta_energia/cliente_" + nome.getEditText().getText().toString() + ".jpg");
        Bitmap bitmap = fotoGaleria;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 15, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ImageRef.putBytes(data);

        Task<Uri> urlTask = uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Continue with the task to get the download URL
            return ImageRef.getDownloadUrl();
        }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                fotoUrl = downloadUri.toString();
            }
        });
        return null;
    }

    private Task<String> enviarDadosFoto_camera() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_conta_energia/cliente_" + nome.getEditText().getText().toString() + ".jpg");
        Bitmap bitmap = foto_comprimida;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 15, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ImageRef.putBytes(data);

        Task<Uri> urlTask = uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Continue with the task to get the download URL
            return ImageRef.getDownloadUrl();
        }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                fotoUrl = downloadUri.toString();
            }
        });
        return null;
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(TextUtils.isEmpty(data.getEditText().getText().toString())){
            data.setError("Insira a data");
            valido = false;
        } else if(TextUtils.isEmpty(nome.getEditText().getText().toString())){
            nome.setError("Insira um nome");
            valido = false;
        } else if(TextUtils.isEmpty(contato.getEditText().getText().toString())){
            contato.setError("Insira um contato válido");
            valido = false;
        } else if(TextUtils.isEmpty(potencia_desejada.getEditText().getText().toString())){
            potencia_desejada.setError("Informe a potência desejada");
            valido = false;
        } else if(TextUtils.isEmpty(localizacao.getEditText().getText().toString())){
            localizacao.setError("Insira a localizção");
            valido = false;
        } else if(TextUtils.isEmpty(observacao.getEditText().getText().toString())) {
            observacao.setError("Insira uma observação");
            valido = false;
        } else if(foto_conta == null){
            Toast.makeText(this, "Insira a foto da conta", Toast.LENGTH_SHORT).show();
            valido = false;
        }

        if (valido){
            salvarForm();
        }

    }

    private void salvarForm() {
        orcamento.setNomeCliente(nome.getEditText().getText().toString());
        orcamento.setContato(contato.getEditText().getText().toString());
        orcamento.setPotenciaDesejada(potencia_desejada.getEditText().getText().toString());
        orcamento.setLocalizacao(localizacao.getEditText().getText().toString());
        if(!TextUtils.isEmpty(observacao.getEditText().getText())){
            orcamento.setObservacao(observacao.getEditText().getText().toString());
        }
        orcamento.setFotoContaUrl(fotoUrl);
        orcamento.setTecnicoId(FirebaseService.getFirebaseUser().getUid());

        Bitmap fotoFinal = null;
        if(foto_comprimida != null){
            fotoFinal = foto_comprimida;
        } else {
            fotoFinal = fotoGaleria;
        }

        baixarPermissao(orcamento, fotoFinal);

        db.collection("orcamentos")
                .add(orcamento.toMap())
                .addOnSuccessListener(documentReference -> {

                })
                .addOnFailureListener(e -> {

                });

        finish();

    }

    private void baixarPermissao(Orcamento orcamento, Bitmap fotoFinal) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        } else {
            try{
                GerarPDFOrcamento.gerarPDF(this, orcamento, fotoFinal);
            }catch (FileNotFoundException fe){
                fe.printStackTrace();
            }
        }
    }

    private void inicializarComponentes() {
        calendar = Calendar.getInstance();
        btn_cancelar = findViewById(R.id.btn_orcamento_cancelar);
        btn_finalizar = findViewById(R.id.btn_orcamento_finalizar);
        data = findViewById(R.id.edt_orcamento_data);
        nome = findViewById(R.id.edt_orcamento_nome_cliente);
        contato = findViewById(R.id.edt_orcamento_contato);
        potencia_desejada = findViewById(R.id.edt_orcamento_potencia_desejada);
        localizacao = findViewById(R.id.edt_orcamento_localizacao);
        observacao = findViewById(R.id.edt_orcamento_observacao);
        foto_conta = findViewById(R.id.foto_conta_energia);
    }
}