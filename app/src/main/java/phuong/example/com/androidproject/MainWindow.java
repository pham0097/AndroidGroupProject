package phuong.example.com.androidproject;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainWindow extends Fragment {


    final String[] items = {"Lamp 1", "Lamp 2", "Lamp 3", "Television", "WindowBlinds"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lamp1, container, false);
        ListView listView = (ListView) view.findViewById(R.id.lamp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getActivity(), "Lamp 1", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PopupLamp1.class);
                    startActivity(intent);

                } else if (position == 1) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    DimmableLamp2 fl = new DimmableLamp2();
                    ft.replace(R.id.main2, fl);
                    ft.addToBackStack("");
                    ft.commit();

                    Toast.makeText(getActivity(), "Lamp 2", Toast.LENGTH_SHORT).show();

                } else if (position == 2) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Lamp3 fl = new Lamp3();
                    ft.replace(R.id.main2, fl);
                    ft.addToBackStack("");
                    ft.commit();
                    Toast.makeText(getActivity(), "Lamp 3", Toast.LENGTH_SHORT).show();
            }else if (position == 3){
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Television fl = new Television();
                    ft.replace(R.id.main2, fl);
                    ft.addToBackStack("");
                    ft.commit();
                    Toast.makeText(getActivity(), "Television", Toast.LENGTH_SHORT).show();


                } else if (position == 4) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    WindowBlind fl = new WindowBlind();
                    ft.replace(R.id.main2, fl);
                    ft.addToBackStack("");
                    ft.commit();
                    Toast.makeText(getActivity(), "WindowBlinds", Toast.LENGTH_SHORT).show();
                }
            }


        });
        return view;

    }
}



