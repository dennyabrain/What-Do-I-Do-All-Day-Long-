package space.dennymades.whatdoido;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;

/**
 * Created by abrain on 7/10/16.
 */
public class NewItemDialogFragment extends DialogFragment {
    public interface NewItemDialogListener{
        public void onNewItemDialogPositiveClick(DialogFragment dialog);
        public void onNewItemDialogNegativeClick(DialogFragment dialog);
    }

    NewItemDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setView(R.layout.item_dialog)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("denny","clicked ok");
                        mListener.onNewItemDialogPositiveClick(NewItemDialogFragment.this);
                    }
                })
                .setNegativeButton("return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("denny","clicked cancel");
                        mListener.onNewItemDialogNegativeClick(NewItemDialogFragment.this);
                    }
                });
        return alertBuilder.create();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener=(NewItemDialogListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement NoticeDialogListener");
        }
    }
}
