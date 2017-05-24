package phuong.example.com.androidproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class KitchenAppliancesFragment extends Fragment {

  ListView listDevices;
  FloatingActionButton btnAddDevice;
  DatabaseHandler dbHelper;
  DeviceAdapter mAdapter = null;
  ProgressBar progressbar;

  public KitchenAppliancesFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_kitchen_appliances, container, false);
    dbHelper = new DatabaseHandler(getContext());
    //the default kitchen appliances list
    listDevices = (ListView) view.findViewById(R.id.listDevices);
    //button to add device
    btnAddDevice = (FloatingActionButton) view.findViewById(R.id.btnAddDevice);
    //progress bar
    progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
    progressbar.setVisibility(View.VISIBLE);
    //list of items
    List<Device> mDefaultList = new ArrayList<>();
    if (dbHelper.getAllDevices().size() == 0) {
      dbHelper.addDevice(new Device(0, getContext().getResources().getString(R.string.microwave),
              getContext().getResources().getString(R.string.microwave),  getContext().getResources().getString(R.string.microwaveSETTING)));

      dbHelper.addDevice(new Device(0, getContext().getResources().getString(R.string.fridge),
          getContext().getResources().getString(R.string.fridge),  getContext().getResources().getString(R.string.fridgeSETTING)));

      dbHelper.addDevice(new Device(0,getContext().getResources().getString(R.string.light) ,
              getContext().getResources().getString(R.string.light),  getContext().getResources().getString(R.string.lightSETTING)));


      mDefaultList = dbHelper.getAllDevices();
    } else {
      mDefaultList = dbHelper.getAllDevices();
    }

    progressbar.setVisibility(View.INVISIBLE);
    mAdapter = new DeviceAdapter(getContext(), mDefaultList);
    listDevices.setAdapter(mAdapter);
//button event handling
    btnAddDevice.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AddNewDeviceDialog();
      }
    });
    //OnItemClick  to get the click of List items
    listDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        DeviceAdapter adapter = (DeviceAdapter) adapterView.getAdapter();
        Device selDevice = adapter.getItem(position);
        String sDevice = selDevice.getType();
        //shamaedit
        // ListView Clicked item value
        //String value= (String) listDevices.getItemAtPosition(position);
        Toast.makeText(getContext(),  "User Clicked:"+sDevice , Toast.LENGTH_LONG)
                .show();
        //shamaedit
        if (sDevice.equals(getContext().getResources().getString(R.string.microwave))) {
          startActivity(new Intent(view.getContext(), MicrowaveActivity.class));
        } else if (sDevice.equals(getContext().getResources().getString(R.string.fridge))) {
          startActivity(new Intent(view.getContext(), FridgeActivity.class));
        } else if (sDevice.equals(getContext().getResources().getString(R.string.light))) {
          startActivity(new Intent(view.getContext(), KitchenMainLightActivity.class));
        } else {
          Snackbar.make(getView(), "This device is not supported.", Snackbar.LENGTH_LONG).show();
        }
      }
    });

    return view;

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    listDevices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        DeviceAdapter adapter = (DeviceAdapter) parent.getAdapter();
        final Device selDevice = adapter.getItem(position);
        Snackbar.make(getView(), "Are you sure want to delete ?", Snackbar.LENGTH_LONG)
            .setAction("YES", new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                dbHelper.deleteDevice(selDevice);
                mAdapter.notifyDataSetChanged();
              }
            });

        return true;
      }
    });

  }

  class DeviceAdapter extends BaseAdapter {

    Context context;
    List<Device> data;
    LayoutInflater inflater;

    public DeviceAdapter(Context applicationContext, List<Device> buildings) {
      this.context = applicationContext;
      this.data = buildings;
      inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
      return data.size();
    }

    @Override
    public Device getItem(int position) {
      return data.get(position);
    }

    public List<Device> getData() {
      return data;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      View view = convertView;
      if (convertView == null) view = inflater.inflate(R.layout.row_main, null);

      TextView info_text = (TextView) view.findViewById(R.id.txtDeviceName);
      info_text.setText(data.get(position).getName());
      return view;
    }

    public void addDevice(Device fridge) {
      data.add(fridge);
      notifyDataSetChanged();
    }
  }

  private void AddNewDeviceDialog() {
    final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
    View view_Filter = layoutInflater.inflate(R.layout.add_device, null);
    final EditText text_deviceName = (EditText) view_Filter.findViewById(R.id.editDeviceName);
    final RadioGroup deviceGroup = (RadioGroup) view_Filter.findViewById(R.id.deviceGroup);
    final AlertDialog.Builder d = new AlertDialog.Builder(getContext());
    d.setTitle("Add New Device");
    d.setView(view_Filter, 20, 0, 20, 0);
    d.setCancelable(true);
    d.setPositiveButton(android.R.string.ok, null); //Set to null. We override the onclick
    d.setNegativeButton(android.R.string.cancel, null);
    final AlertDialog mdialog = d.create();
    mdialog.setOnShowListener(new DialogInterface.OnShowListener() {

      @Override
      public void onShow(DialogInterface dialog) {

        Button posb = mdialog.getButton(AlertDialog.BUTTON_POSITIVE);
        posb.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View view) {
            if (text_deviceName != null && TextUtils.isEmpty(
                text_deviceName.getText().toString())) {
              Toast.makeText(getContext(), "Device name should not be null", Toast.LENGTH_LONG)
                  .show();
            } else {
              mdialog.dismiss();
              progressbar.setVisibility(View.VISIBLE);
              int selectedId = deviceGroup.getCheckedRadioButtonId();

              // find the radiobutton by returned id
              RadioButton radioDevice = (RadioButton) deviceGroup.findViewById(selectedId);
              dbHelper.addDevice(new Device(0, text_deviceName.getText().toString(),
                  radioDevice.getText().toString(), "10c"));
              mAdapter.addDevice(new Device(0, text_deviceName.getText().toString(),
                  radioDevice.getText().toString(), "10c"));
              progressbar.setVisibility(View.INVISIBLE);
            }
          }
        });
        Button negb = mdialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negb.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mdialog.dismiss();
          }
        });
      }
    });
    mdialog.show();
  }
}
