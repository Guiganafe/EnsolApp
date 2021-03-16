package com.example.ensolapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ensolapp.BuildConfig;
import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Cliente;
import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.VisitaTecnicaViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentVisitaTecnica_03 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private RadioGroup rg_material_estrutura, rg_condicao_telhado, rg_orientacao_telhado;
    private RadioButton rb_checked;
    private ImageView foto_orientacao_telhado;
    private VisitaTecnicaViewModel visitaTecnicaViewModel;
    private ClienteViewModel clienteViewModel;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visita_tecnica_03, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        radioGroupController(view);
        loadViewModelController();
    }

    private void loadViewModelController() {
        if(visitaTecnicaViewModel.getMaterialEstruturaTelhadoPosition().getValue() != null){
            rg_material_estrutura.check(rg_material_estrutura.getChildAt(visitaTecnicaViewModel.getMaterialEstruturaTelhadoPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getCondicaoTelhadoPosition().getValue() != null){
            rg_condicao_telhado.check(rg_condicao_telhado.getChildAt(visitaTecnicaViewModel.getCondicaoTelhadoPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getOrientacaoTelhadoPosition().getValue() != null){
            rg_orientacao_telhado.check(rg_orientacao_telhado.getChildAt(visitaTecnicaViewModel.getOrientacaoTelhadoPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue() != null){
            foto_orientacao_telhado.setBackground(null);
            foto_orientacao_telhado.setPadding(0, 0, 0, 0);
            foto_orientacao_telhado.setImageBitmap(visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue());
        }
    }

    private void radioGroupController(View view) {
        rg_material_estrutura.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setMaterialEstruturaTelhado(textoRB);
            visitaTecnicaViewModel.setMaterialEstruturaTelhadoPosition(position);
        });

        rg_condicao_telhado.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setCondicaoTelhado(textoRB);
            visitaTecnicaViewModel.setCondicaoTelhadoPosition(position);
        });

        rg_orientacao_telhado.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setOrientacaoTelhado(textoRB);
            visitaTecnicaViewModel.setOrientacaoTelhadoPosition(position);
        });
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);

        foto_orientacao_telhado.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE));
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(visitaTecnicaViewModel.getMaterialEstruturaTelhado().getValue() == null){
            Toast.makeText(requireActivity(), "Selecione o material da estrutura", Toast.LENGTH_SHORT).show();
            valido = false;
        } else if(visitaTecnicaViewModel.getOrientacaoTelhado().getValue() == null){
            Toast.makeText(requireActivity(), "Selecione a orientação do telhado", Toast.LENGTH_SHORT).show();
            valido = false;
        }

        if(valido)
        {
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentVisitaTecnica_03_to_fragmentVisitaTecnica_04);
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
                foto_orientacao_telhado.setBackground(null);
                foto_orientacao_telhado.setPadding(0, 0, 0, 0);
                foto_orientacao_telhado.setImageBitmap(foto_comprimida);
                visitaTecnicaViewModel.setFotoOrientacaoTelhado(foto_comprimida);
                enviarDados();
                currentPhotoPath = "";
            }
        }
    }

    private Task<String> enviarDados() {
        final StorageReference ImageRef =
                storageRef.child("fotos/fotos_orientacao_telhado/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = visitaTecnicaViewModel.getFotoOrientacaoTelhado().getValue();

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
                visitaTecnicaViewModel.setFotoOrientacaoTelhadoUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private void inicializarComponentes(View view) {
        btn_voltar = (Button) view.findViewById(R.id.btn_vs_voltar_passo_2);
        btn_avancar = (Button) view.findViewById(R.id.btn_vs_avancar_passo_4);
        rg_material_estrutura = view.findViewById(R.id.rg_material_estrutura);
        rg_condicao_telhado = view.findViewById(R.id.rg_condicao_telhado);
        rg_orientacao_telhado = view.findViewById(R.id.rg_orientacao_telhado);
        foto_orientacao_telhado = view.findViewById(R.id.foto_orientacao_telhado);
    }

}