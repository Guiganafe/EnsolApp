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
import com.example.ensolapp.R;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentVisitaTecnica_02 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private TextInputLayout edt_amperagem_disjuntor;
    private RadioGroup rg_padrao_entrada, rg_condicao_padrao_entrada, rg_local_instalacao;
    private ImageView foto_padrao_entrada;
    private RadioButton rb_checked;
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
        return inflater.inflate(R.layout.fragment_visita_tecnica_02, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        textWatcherController();
        radioGroupController(view);
        loadViewModelController();
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }
    }

    private void loadViewModelController() {
        String amperagem = visitaTecnicaViewModel.getAperagemDisjuntosEntrada().getValue();

        if (!TextUtils.isEmpty(amperagem)){
            edt_amperagem_disjuntor.getEditText().setText(amperagem);
        }

        if(visitaTecnicaViewModel.getPadraoEntradaPosition().getValue() != null){
            rg_padrao_entrada.check(rg_padrao_entrada.getChildAt(visitaTecnicaViewModel.getPadraoEntradaPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getCondicaoPadraoEntradaPositon().getValue() != null){
            rg_condicao_padrao_entrada.check(rg_condicao_padrao_entrada.getChildAt(visitaTecnicaViewModel.getCondicaoPadraoEntradaPositon().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getLocalInstalacaoModulosPosition().getValue() != null){
            rg_local_instalacao.check(rg_local_instalacao.getChildAt(visitaTecnicaViewModel.getLocalInstalacaoModulosPosition().getValue()).getId());
        }

        if(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue() != null){
            foto_padrao_entrada.setBackground(null);
            foto_padrao_entrada.setPadding(0, 0, 0, 0);
            foto_padrao_entrada.setImageBitmap(visitaTecnicaViewModel.getFotoPadraoEntrada().getValue());
        }
    }

    private void radioGroupController(View view) {
        rg_padrao_entrada.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setPadraoEntrada(textoRB);
            visitaTecnicaViewModel.setPadraoEntradaPosition(position);
        });

        rg_condicao_padrao_entrada.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setCondicaoPadraoEntrada(textoRB);
            visitaTecnicaViewModel.setCondicaoPadraoEntradaPositon(position);
        });

        rg_local_instalacao.setOnCheckedChangeListener((group, checkedId) -> {
            rb_checked = view.findViewById(checkedId);
            String textoRB = rb_checked.getText().toString();
            int position = group.indexOfChild(rb_checked);

            visitaTecnicaViewModel.setLocalInstalacaoModulos(textoRB);
            visitaTecnicaViewModel.setLocalInstalacaoModulosPosition(position);
        });
    }

    private void textWatcherController() {
        edt_amperagem_disjuntor.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                visitaTecnicaViewModel.setAperagemDisjuntosEntrada(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);

        foto_padrao_entrada.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE));
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(visitaTecnicaViewModel.getPadraoEntrada().getValue() == null){
            Toast.makeText(requireActivity(), "Selecione um padrão de entrada", Toast.LENGTH_SHORT).show();
            valido = false;
        } else if(visitaTecnicaViewModel.getLocalInstalacaoModulos().getValue() == null){
            Toast.makeText(requireActivity(), "Defina o local de instalação", Toast.LENGTH_SHORT).show();
            valido = false;
        }

        if(valido){
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentVisitaTecnica_02_to_fragmentVisitaTecnica_03);
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
                foto_padrao_entrada.setBackground(null);
                foto_padrao_entrada.setPadding(0, 0, 0, 0);
                foto_padrao_entrada.setImageBitmap(foto_comprimida);
                visitaTecnicaViewModel.setFotoPadraoEntrada(foto_comprimida);
                enviarDados();
                currentPhotoPath = "";
            }
        }
    }

    private Task<String> enviarDados() {
        final StorageReference padraoImageRef =
                storageRef.child("fotos/fotos_padrao/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = visitaTecnicaViewModel.getFotoPadraoEntrada().getValue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] data = baos.toByteArray();

        UploadTask uploadTask = padraoImageRef.putBytes(data);

        Task<Uri> urlTask = uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Continue with the task to get the download URL
            return padraoImageRef.getDownloadUrl();
        }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                visitaTecnicaViewModel.setFotoPadraoEntradaUrl(downloadUri.toString());
            }
        });
        return null;
    }


    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_vs_voltar_passo_1);
        btn_avancar = view.findViewById(R.id.btn_vs_avancar_passo_3);
        edt_amperagem_disjuntor = view.findViewById(R.id.edt_vt_amperagem_disjuntor);
        rg_padrao_entrada = view.findViewById(R.id.rg_padrao_entrada);
        rg_condicao_padrao_entrada = view.findViewById(R.id.rg_condicao_padrao_entrada);
        rg_local_instalacao = view.findViewById(R.id.rg_local_instalacao);
        foto_padrao_entrada = view.findViewById(R.id.foto_padrao_entrada);
    }
}