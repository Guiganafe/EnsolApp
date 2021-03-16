package com.example.ensolapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ensolapp.BuildConfig;
import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Cliente;
import com.example.ensolapp.Models.Fotos;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.Utils.GerarPDF;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentVisitaTecnica_04 extends Fragment {

    private Button btn_voltar, btn_avancar_passo_5;
    private TextInputLayout edt_largura_telhado, edt_comprimento_telhado, edt_altura_telhado,
            edt_acesso_escada, edt_acesso_andaime, edt_obs_finais;
    private ImageView foto_acesso_telhado;
    private ClienteViewModel clienteViewModel;
    private VisitaTecnicaViewModel visitaTecnicaViewModel;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageRef;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private String currentPhotoPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visitaTecnicaViewModel = new ViewModelProvider(requireActivity()).get(VisitaTecnicaViewModel.class);
        clienteViewModel = new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        textWatcherController();
        loadViewModelController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_04, container, false);
    }


    private void loadViewModelController() {
        String largura_telhado = visitaTecnicaViewModel.getLarguraTelhado().getValue(),
                comprimento_telhado = visitaTecnicaViewModel.getComprimentoTelhado().getValue(),
                altura_telhado = visitaTecnicaViewModel.getAlturaTelhado().getValue(),
                acesso_escada = visitaTecnicaViewModel.getAcessoEscada().getValue(),
                acesso_andaime = visitaTecnicaViewModel.getAcessoAndaime().getValue(),
                obs_finais = visitaTecnicaViewModel.getObsFinais().getValue();

        if (!TextUtils.isEmpty(largura_telhado)){
            edt_largura_telhado.getEditText().setText(largura_telhado);
        }

        if (!TextUtils.isEmpty(comprimento_telhado)){
            edt_comprimento_telhado.getEditText().setText(comprimento_telhado);
        }

        if (!TextUtils.isEmpty(altura_telhado)){
            edt_altura_telhado.getEditText().setText(altura_telhado);
        }

        if (!TextUtils.isEmpty(acesso_escada)){
            edt_acesso_escada.getEditText().setText(acesso_escada);
        }

        if (!TextUtils.isEmpty(acesso_andaime)){
            edt_acesso_andaime.getEditText().setText(acesso_andaime);
        }

        if (!TextUtils.isEmpty(obs_finais)){
            edt_obs_finais.getEditText().setText(obs_finais);
        }

        if(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue() != null){
            foto_acesso_telhado.setBackground(null);
            foto_acesso_telhado.setPadding(0, 0, 0, 0);
            foto_acesso_telhado.setImageBitmap(visitaTecnicaViewModel.getFotoAcessoTelhado().getValue());
        }
    }

    private void textWatcherController() {
        edt_largura_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setLarguraTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_comprimento_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setComprimentoTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_altura_telhado.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAlturaTelhado(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_acesso_escada.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAcessoEscada(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_acesso_andaime.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAcessoAndaime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_obs_finais.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setObsFinais(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar_passo_5.setOnClickListener(this::validarDados);

        foto_acesso_telhado.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE));
    }

    private void validarDados(View v) {
        boolean valido = true;

        if(visitaTecnicaViewModel.getAcessoEscada().getValue() == null){
            edt_acesso_escada.setError("Insira um valor válido");
            valido = false;
        }

        if(visitaTecnicaViewModel.getAcessoAndaime().getValue() == null){
            edt_acesso_andaime.setError("Insira um valor válido");
            valido = false;
        }

        if(valido){
            edt_acesso_escada.setError(null);
            edt_acesso_andaime.setError(null);
            Navigation.findNavController(v).navigate(R.id.action_fragmentVisitaTecnica_04_to_fragmentVisitaTecnica_05);
        }
    }

    private void cameraPermissao(int REQUEST_CODE) {
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            if(REQUEST_CODE == 102) {
                dispatchTakePictureIntent();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(requireActivity(),
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
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_acesso_telhado.setBackground(null);
                foto_acesso_telhado.setPadding(0, 0, 0, 0);
                foto_acesso_telhado.setImageBitmap(foto_comprimida);
                visitaTecnicaViewModel.setFotoAcessoTelhado(foto_comprimida);
                enviarDados();
                currentPhotoPath = "";
            }
        }
    }

    private Task<String> enviarDados() {
        final StorageReference ImageRef =
                storageRef.child("fotos/fotos_acesso_telhado/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = visitaTecnicaViewModel.getFotoAcessoTelhado().getValue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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
                visitaTecnicaViewModel.setFotoAcessoTelhadoUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_vs_voltar_passo_3);
        btn_avancar_passo_5 = view.findViewById(R.id.btn_vs_avancar_passo_5);
        edt_largura_telhado = view.findViewById(R.id.edt_vt_largura_telhado);
        edt_comprimento_telhado = view.findViewById(R.id.edt_vt_comprimento_telhado);
        edt_altura_telhado = view.findViewById(R.id.edt_vt_altura_telhado);
        edt_acesso_escada = view.findViewById(R.id.edt_vt_acesso_escada);
        edt_acesso_andaime = view.findViewById(R.id.edt_vt_acesso_andaime);
        edt_obs_finais = view.findViewById(R.id.edt_vt_obs_finais);
        foto_acesso_telhado = view.findViewById(R.id.foto_acesso_telhado);
    }
}