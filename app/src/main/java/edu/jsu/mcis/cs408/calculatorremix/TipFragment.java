package edu.jsu.mcis.cs408.calculatorremix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;

import edu.jsu.mcis.cs408.calculatorremix.databinding.TipFragmentBinding;

public class TipFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "TipFragment";
    private TipFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TipFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.calculateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String label = getResources().getString(R.string.total_per_person);
        NumberFormat format = NumberFormat.getCurrencyInstance();

        EditText billInput = binding.billInput;
        EditText tipInput = binding.tipInput;
        EditText peopleInput = binding.peopleInput;
        TextView t = binding.resultOutput;

        //Logs the inputted values
        Log.d(TAG, "bill input: " + billInput.getText().toString());
        Log.d(TAG, "tip input: " + tipInput.getText().toString());
        Log.d(TAG, "people input: " + peopleInput.getText().toString());

        boolean billempty = billInput.getText().toString().isEmpty();
        boolean tipempty = tipInput.getText().toString().isEmpty();
        boolean peopleempty = peopleInput.getText().toString().isEmpty();

        //Logs if input values were empty
        Log.d(TAG, "bill empty: " + billempty);
        Log.d(TAG, "tip empty: " + tipempty);
        Log.d(TAG, "people empty: " + peopleempty);

        if(!billempty && !tipempty && !peopleempty){ //only runs if ALL values are not null/empty
            float bill = Float.parseFloat(billInput.getText().toString());
            float tip = 1 + ((float) Integer.parseInt(tipInput.getText().toString()) / 100);
            int people = Integer.parseInt(peopleInput.getText().toString());

            float total = bill * tip;
            float result = total / people;

            String output = label + " " + format.format(result);
            t.setText(output);
        }
        else {
            Toast toast = Toast.makeText(binding.getRoot().getContext(), "Please enter all values.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
