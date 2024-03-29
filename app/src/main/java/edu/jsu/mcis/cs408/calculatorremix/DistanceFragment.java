package edu.jsu.mcis.cs408.calculatorremix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.jsu.mcis.cs408.calculatorremix.databinding.DistanceFragmentBinding;

public class DistanceFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "DistanceFragment";
    private DistanceFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DistanceFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.convertButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText mText = binding.mText;
        EditText kText = binding.kText;

        boolean mempty = mText.getText().toString().isEmpty();
        boolean kempty = kText.getText().toString().isEmpty();

        if(!mempty) { //Miles given (Default) ; Convert: M->Km
            Log.i(TAG, "Convert Miles -> Kilometers");
            BigDecimal M = new BigDecimal(mText.getText().toString());
            BigDecimal conversion = M.multiply(new BigDecimal("1.609344")).stripTrailingZeros();
            kText.setText(conversion.toString());
        }
        else if(mempty && !kempty) { //Miles empty, Kilometers given ; Convert: M<-Km
            Log.i(TAG, "Convert Miles <- Kilometers");
            BigDecimal Km = new BigDecimal(kText.getText().toString());
            BigDecimal conversion = Km.divide(new BigDecimal("1.609344"), 6, RoundingMode.HALF_UP).stripTrailingZeros();
            //BigDecimal conversion = Km.multiply(new BigDecimal("0.62137")).stripTrailingZeros();
            mText.setText(conversion.toString());
        }
        else {
            Toast toast = Toast.makeText(binding.getRoot().getContext(), "Please enter at least one distance for conversion.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
