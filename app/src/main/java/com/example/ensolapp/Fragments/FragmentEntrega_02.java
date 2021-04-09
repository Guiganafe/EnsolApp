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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ensolapp.BuildConfig;
import com.example.ensolapp.R;
import com.example.ensolapp.ViewModels.ClienteViewModel;
import com.example.ensolapp.ViewModels.EntregaViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentEntrega_02 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private ImageView foto_estrutura_fixacao_telhado, foto_modulos_telhado, foto_string_box_aberta;
    private ClienteViewModel clienteViewModel;
    private EntregaViewModel entregaViewModel;
    private StorageReference storageRef;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE_FOTO_1 = 102;
    public static final int CAMERA_REQUEST_CODE_FOTO_2 = 103;
    public static final int CAMERA_REQUEST_CODE_FOTO_3 = 104;
    private String currentPhotoPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clienteViewModel =  new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        entregaViewModel =  new ViewModelProvider(requireActivity()).get(EntregaViewModel.class);
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_02, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        loadViewModelController();
    }

    private void loadViewModelController() {
        if(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue() != null){
            foto_estrutura_fixacao_telhado.setBackground(null);
            foto_estrutura_fixacao_telhado.setPadding(0, 0, 0, 0);
            foto_estrutura_fixacao_telhado.setImageBitmap(entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue());
        }

        if(entregaViewModel.getFotoModulosTelhado().getValue() != null){
            foto_modulos_telhado.setBackground(null);
            foto_modulos_telhado.setPadding(0, 0, 0, 0);
            foto_modulos_telhado.setImageBitmap(entregaViewModel.getFotoModulosTelhado().getValue());
        }

        if(entregaViewModel.getFotoStringBoxAberta().getValue() != null){
            foto_string_box_aberta.setBackground(null);
            foto_string_box_aberta.setPadding(0, 0, 0, 0);
            foto_string_box_aberta.setImageBitmap(entregaViewModel.getFotoStringBoxAberta().getValue());
        }
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);

        foto_estrutura_fixacao_telhado.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_1));
        foto_modulos_telhado.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_2));
        foto_string_box_aberta.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_3));
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(valido)
        {
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentEntrega_02_to_fragmentEntrega_03);
        }
    }

    private void cameraPermissao(int REQUEST_CODE) {
        if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            if(REQUEST_CODE == 102){
                dispatchTakePictureIntentFoto_1();
            } else if(REQUEST_CODE == 103) {
                dispatchTakePictureIntentFoto_2();
            } else if(REQUEST_CODE == 104){
                dispatchTakePictureIntentFoto_3();
            }
        }
    }

    private void dispatchTakePictureIntentFoto_1() {
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
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE_FOTO_1);
            }
        }
    }

    private void dispatchTakePictureIntentFoto_2() {
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
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE_FOTO_2);
            }
        }
    }

    private void dispatchTakePictureIntentFoto_3() {
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
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE_FOTO_3);
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
        if(resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE_FOTO_1) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_estrutura_fixacao_telhado.setBackground(null);
                foto_estrutura_fixacao_telhado.setPadding(0, 0, 0, 0);
                foto_estrutura_fixacao_telhado.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoEstruturaFixacaoTelhado(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_1();
            }
        }

        if(resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE_FOTO_2) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_modulos_telhado.setBackground(null);
                foto_modulos_telhado.setPadding(0, 0, 0, 0);
                foto_modulos_telhado.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoModulosTelhado(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_2();
            }
        }

        if(resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE_FOTO_3) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_string_box_aberta.setBackground(null);
                foto_string_box_aberta.setPadding(0, 0, 0, 0);
                foto_string_box_aberta.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoStringBoxAberta(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_3();
            }
        }
    }

    private Task<String> enviarDadosFoto_1() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_estrutura_fixacao_telhado/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoEstruturaFixacaoTelhado().getValue();

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
                entregaViewModel.setFotoEstruturaFixacaoTelhadoUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private Task<String> enviarDadosFoto_2() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_modulos_telhado/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoModulosTelhado().getValue();

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
                entregaViewModel.setFotoModulosTelhadoUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private Task<String> enviarDadosFoto_3() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_string_box_aberta/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoStringBoxAberta().getValue();

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
                entregaViewModel.setFotoStringBoxAbertaUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_entrega_voltar_passo_1);
        btn_avancar = view.findViewById(R.id.btn_entrega_avancar_passo_3);
        foto_estrutura_fixacao_telhado = view.findViewById(R.id.foto_estrutura_fixacao_telhado);
        foto_modulos_telhado = view.findViewById(R.id.foto_modulos_telhado);
        foto_string_box_aberta = view.findViewById(R.id.foto_string_box_aberta);
    }
}