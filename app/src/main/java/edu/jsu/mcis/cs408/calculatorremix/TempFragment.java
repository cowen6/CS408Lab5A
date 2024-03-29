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

import edu.jsu.mcis.cs408.calculatorremix.databinding.TempFragmentBinding;

public class TempFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "TempFragment";
    private TempFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TempFragmentBinding.inflate(getLayoutInflater(), container, false);
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

        EditText fText = binding.fText;
        EditText cText = binding.cText;

        boolean fempty = fText.getText().toString().isEmpty();
        boolean cempty = cText.getText().toString().isEmpty();

        if(!fempty) { //F given (Default) ; Convert: F->C
            Log.i(TAG, "Convert F -> C");
            float F = Float.parseFloat(fText.getText().toString());
            Log.d(TAG, "F: " + F);
            Float conversion = ((F - 32) * 5)/9;
            cText.setText(conversion.toString());
        }
        else if(fempty && !cempty) { //F empty, C given ; Convert: F<-C
            Log.i(TAG, "Convert F <- C");
            float C = Float.parseFloat(cText.getText().toString());
            Float conversion = (C * (float)1.8) + 32;
            fText.setText(conversion.toString());
        }
        else {
            Toast toast = Toast.makeText(binding.getRoot().getContext(), "Please enter at least one temperature for conversion.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
