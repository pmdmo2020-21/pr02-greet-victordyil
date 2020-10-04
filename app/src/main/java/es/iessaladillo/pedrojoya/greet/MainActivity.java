package es.iessaladillo.pedrojoya.greet;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

import static android.widget.Toast.LENGTH_SHORT;
import static es.iessaladillo.pedrojoya.greet.R.string.avisoRelleno;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding binding;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViews();
    }

    private void setupViews() {
        setValue();
        binding.mrRB.setChecked(true);
        binding.swPremium.setOnCheckedChangeListener((buttonView, isChecked) -> checkPremium(isChecked));
        binding.BTNGreet.setOnClickListener(v -> saludar());
        binding.RGGender.setOnCheckedChangeListener((group, checkedId) -> changeImage(checkedId));
        binding.GenderImg.getBaseline();
    }

    private void saludar() {
       if (binding.nameET.length()>0&&binding.subnameET.length()>0){
           if (quantity != 10|| binding.swPremium.isChecked()) {
               if (binding.CBPolitely.isChecked()) {
                   switch (binding.RGGender.getCheckedRadioButtonId()) {
                       case R.id.mrRB:
                           binding.saludo.setText(String.format(getString(R.string.señorEducado), binding.nameET.getText(), binding.subnameET.getText()));
                           break;
                       case R.id.mrsRB:
                           binding.saludo.setText(String.format(getString(R.string.señoritaEducado), binding.nameET.getText(), binding.subnameET.getText()));

                           break;
                       case R.id.msRB:
                           binding.saludo.setText(String.format(getString(R.string.señoraEducado), binding.nameET.getText(), binding.subnameET.getText()));
                           break;
                   }
               } else {
                   switch (binding.RGGender.getCheckedRadioButtonId()) {
                       case R.id.mrRB:
                           binding.saludo.setText(String.format(getString(R.string.mrSaludo), binding.nameET.getText(), binding.subnameET.getText()));
                           break;
                       case R.id.mrsRB:
                           binding.saludo.setText(String.format(getString(R.string.señoritaSaludo), binding.nameET.getText(), binding.subnameET.getText()));


                           break;
                       case R.id.msRB:
                           binding.saludo.setText(String.format(getString(R.string.señoraSaludo), binding.nameET.getText(), binding.subnameET.getText()));

                           break;
                   }
               }
               quantity++;
               setValue();
           }
           else binding.saludo.setText(R.string.avisoPremium);
       }
       else {
           Toast toast = Toast.makeText(binding.getRoot().getContext(), avisoRelleno, LENGTH_SHORT);
           toast.show();

       }

    }

    private void changeImage(int checkedId) {
        switch (checkedId) {
            case R.id.mrRB:
                binding.GenderImg.setImageResource(R.drawable.ic_mr);
                break;
            case R.id.mrsRB:
                binding.GenderImg.setImageResource(R.drawable.ic_mrs);

                break;
            case R.id.msRB:
                binding.GenderImg.setImageResource(R.drawable.ic_ms);
                break;
        }
    }

    private void checkPremium(boolean isChecked) {
        if (isChecked) {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.contador.setVisibility(View.INVISIBLE);
            binding.saludo.setText("");

        } else {
            quantity=0;
            setValue();
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.contador.setVisibility(View.VISIBLE);

        }
    }
    private void setValue(){
        binding.progressBar.setProgress(quantity);
        binding.contador.setText(String.format(getString(R.string.number),quantity));
    }

}