package com.whiterabbit.windlocator.mainactivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whiterabbit.windlocator.R;

/**
 * Created by fedepaol on 10/02/16.
 */
public class ProgressDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dlg =
        new ProgressDialog(getActivity());
        dlg.setMessage(getActivity().getString(R.string.main_progress));
        dlg.setIndeterminate(true);
        dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dlg;
    }


}