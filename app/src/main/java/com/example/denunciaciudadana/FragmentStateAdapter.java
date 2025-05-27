import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.denunciaciudadana.InicioFragment;
import com.example.denunciaciudadana.PerfilFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new InicioFragment();
            case 1:
                return new DenunciarFragment();
            case 2:
                return new VerDenunciasFragment();
            case 3:
                return new PerfilFragment();
            default:
                return new InicioFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}