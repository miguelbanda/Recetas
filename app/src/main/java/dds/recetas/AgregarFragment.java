package dds.recetas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class AgregarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((Main)getActivity()).hideFAB();
        LinearLayout fragmentAgregar = (LinearLayout) inflater.inflate(R.layout.fragment_agregar, container, false);
        return fragmentAgregar;
    }
}
