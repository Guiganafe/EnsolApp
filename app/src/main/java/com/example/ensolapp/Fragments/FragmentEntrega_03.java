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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentEntrega_03 extends Fragment {

    private Button btn_voltar, btn_avancar;
    private ImageView foto_tensao_string_box, foto_string_box_instalada, foto_medida_tensao_conectado;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrega_03, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
        loadViewModelController();
    }

    private void loadViewModelController() {
        if(entregaViewModel.getFotoTensaoStringBox().getValue() != null){
            foto_tensao_string_box.setBackground(null);
            foto_tensao_string_box.setPadding(0, 0, 0, 0);
            foto_tensao_string_box.setImageBitmap(entregaViewModel.getFotoTensaoStringBox().getValue());
        }

        if(entregaViewModel.getFotoStringBoxInstalada().getValue() != null){
            foto_string_box_instalada.setBackground(null);
            foto_string_box_instalada.setPadding(0, 0, 0, 0);
            foto_string_box_instalada.setImageBitmap(entregaViewModel.getFotoStringBoxInstalada().getValue());
        }

        if(entregaViewModel.getFotoMedidaTensaoConectado().getValue() != null){
            foto_medida_tensao_conectado.setBackground(null);
            foto_medida_tensao_conectado.setPadding(0, 0, 0, 0);
            foto_medida_tensao_conectado.setImageBitmap(entregaViewModel.getFotoMedidaTensaoConectado().getValue());
        }
    }

    private void onClickController() {
        btn_voltar.setOnClickListener(v -> getActivity().onBackPressed());

        btn_avancar.setOnClickListener(this::validarDados);

        foto_tensao_string_box.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_1));
        foto_string_box_instalada.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_2));
        foto_medida_tensao_conectado.setOnClickListener(v -> cameraPermissao(CAMERA_REQUEST_CODE_FOTO_3));
    }

    private void validarDados(View view) {
        boolean valido = true;

        if(valido)
        {
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentEntrega_03_to_fragmentEntrega_04);
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
                foto_tensao_string_box.setBackground(null);
                foto_tensao_string_box.setPadding(0, 0, 0, 0);
                foto_tensao_string_box.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoTensaoStringBox(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_1();
            } else {
                Toast.makeText(requireActivity(), "Erro, tire a foto novamente!", Toast.LENGTH_SHORT).show();
            }
        }

        if(resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE_FOTO_2) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_string_box_instalada.setBackground(null);
                foto_string_box_instalada.setPadding(0, 0, 0, 0);
                foto_string_box_instalada.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoStringBoxInstalada(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_2();
            } else {
                Toast.makeText(requireActivity(), "Erro, tire a foto novamente!", Toast.LENGTH_SHORT).show();
            }
        }

        if(resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE_FOTO_3) {
                File fileCam = new File(currentPhotoPath);
                Bitmap foto = BitmapFactory.decodeFile(fileCam.getPath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 15, out);
                Bitmap foto_comprimida = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                foto_medida_tensao_conectado.setBackground(null);
                foto_medida_tensao_conectado.setPadding(0, 0, 0, 0);
                foto_medida_tensao_conectado.setImageBitmap(foto_comprimida);
                entregaViewModel.setFotoMedidaTensaoConectado(foto_comprimida);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(fileCam);
                mediaScanIntent.setData(contentUri);
                requireActivity().sendBroadcast(mediaScanIntent);
                currentPhotoPath = "";
                enviarDadosFoto_3();
            } else {
                Toast.makeText(requireActivity(), "Erro, tire a foto novamente!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Task<String> enviarDadosFoto_1() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_tensao_string_box/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoTensaoStringBox().getValue();

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
                entregaViewModel.setFotoTensaoStringBoxUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private Task<String> enviarDadosFoto_2() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_string_box_instalada/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoStringBoxInstalada().getValue();

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
                entregaViewModel.setFotoStringBoxInstaladaUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private Task<String> enviarDadosFoto_3() {
        final StorageReference ImageRef =
                storageRef.child("fotos/foto_medida_tensao_conectado/cliente_" + clienteViewModel.getNomeCliente().getValue() + ".jpg");
        Bitmap bitmap = entregaViewModel.getFotoMedidaTensaoConectado().getValue();

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
                entregaViewModel.setFotoMedidaTensaoConectadoUrl(downloadUri.toString());
            }
        });
        return null;
    }

    private void inicializarComponentes(View view) {
        btn_voltar = view.findViewById(R.id.btn_entrega_voltar_passo_2);
        btn_avancar = view.findViewById(R.id.btn_entrega_avancar_passo_4);
        foto_tensao_string_box = view.findViewById(R.id.foto_tensao_string_box);
        foto_string_box_instalada = view.findViewById(R.id.foto_string_box_instalada);
        foto_medida_tensao_conectado = view.findViewById(R.id.foto_medida_tensao_conectado);
    }
}
