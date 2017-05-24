package phuong.example.com.androidproject;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RadioControl extends Activity implements OnItemClickListener {
    private ArrayList<String> Array;
    private EditText edit;
    private ListView view;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_control);

        Array = new ArrayList<String>();
        view = (ListView) findViewById(R.id.listView9);
        edit = (EditText) findViewById(R.id.itemfield);

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Array.add(edit.getText().toString());
                    // updateAdapter();
                    adapter.notifyDataSetChanged(); // Just refresh the adapter
                    edit.setText("");

            }
        });
        view.setOnItemClickListener(this);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, Array);
        view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        AlertDialog.Builder alert=new AlertDialog.Builder(RadioControl.this);
        alert.setTitle("Delete ");
        alert.setMessage("Do you want to Delete the selected Station");
        final int positionToRemove = position;
        alert.setNegativeButton("Cancel",null);
        alert.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Array.remove(positionToRemove);
                adapter.notifyDataSetChanged();
            }});
        alert.show();

    }
}
