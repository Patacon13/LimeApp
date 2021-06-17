package app;

import android.os.Bundle;

import com.example.limeapp.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.limeapp.databinding.ActivityLibreMeshBinding;

public class LibreMesh extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLibreMeshBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLibreMeshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebView navegador;
        navegador = (WebView) findViewById(R.id.navegadorLibreMesh);
        navegador.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        navegador.loadUrl("http://www.google.com");
    }
}