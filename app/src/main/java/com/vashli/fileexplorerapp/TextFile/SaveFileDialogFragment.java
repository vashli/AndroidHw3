package com.vashli.fileexplorerapp.TextFile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.vashli.fileexplorerapp.R;

public class SaveFileDialogFragment extends DialogFragment {
    private EditText fileName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        builder.setView(inflater.inflate(R.layout.fragment_dialog_save, null))
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SaveFileDialogFragment.this.getDialog().cancel();
                    }
                });
        fileName = getActivity().findViewById(R.id.fragment_dialog_file_name);

        return builder.create();
    }

    private void addFileNameText(){

    }
}
