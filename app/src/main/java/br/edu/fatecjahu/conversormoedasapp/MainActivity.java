package br.edu.fatecjahu.conversormoedasapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.json.JSONObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText valorReal;
    private Spinner spinnerMoedas;
    private Button botaoConverter;
    private TextView resultado;
    private EditText valorMoeda; // Conversão Moeda -> BRL
    private Spinner spinnerMoedas2;
    private Button botaoConverter2;
    private TextView resultado2;
    private OkHttpClient client;
    private ExecutorService executor;
    private Handler mainHandler;
    private String[] moedas = {"USD (Dólar)", "EUR (Euro)", "GBP (Libra)"};
    private String[] codigosMoedas = {"USD", "EUR", "GBP"};
    private String urlApi = "https://api.exchangerate-api.com/v4/latest/BRL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarComponentes();
        configurarExecutorEHandler();
        configurarSpinner();
        configurarSpinnerInverso();
        configurarBotao();
        configurarBotaoInverso();
    }
    private void inicializarComponentes() {
        valorReal = findViewById(R.id.edtValorReal);
        spinnerMoedas = findViewById(R.id.spinnerMoedas);
        botaoConverter = findViewById(R.id.btnConverter);
        resultado = findViewById(R.id.tvResultado);
        valorMoeda = findViewById(R.id.edtValorMoeda);
        spinnerMoedas2 = findViewById(R.id.spinnerMoedas2);
        botaoConverter2 = findViewById(R.id.btnConverter2);
        resultado2 = findViewById(R.id.tvResultado2);
        client = new OkHttpClient();
    }
    private void configurarExecutorEHandler() {
        executor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
    }
    private void configurarSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoedas.setAdapter(adapter);
    }

    private void configurarSpinnerInverso() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoedas2.setAdapter(adapter);
    }

    private void configurarBotao() {
        botaoConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorStr = valorReal.getText().toString().trim();
                if (!valorStr.isEmpty()) {
                    double valor = Double.parseDouble(valorStr);
                    int posicao = spinnerMoedas.getSelectedItemPosition();
                    String codigoMoeda = codigosMoedas[posicao];
                    executarConversao(valor, codigoMoeda);
                } else {
                    resultado.setText(R.string.texto_msg_digite_um_valor_valido_em_reais);
                }
            }
        });
    }

    private void configurarBotaoInverso() {
        botaoConverter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorStr = valorMoeda.getText().toString().trim();
                if (!valorStr.isEmpty()) {
                    double valor = Double.parseDouble(valorStr);
                    int posicao = spinnerMoedas2.getSelectedItemPosition();
                    String codigoMoeda = codigosMoedas[posicao];
                    executarConversaoInversa(valor, codigoMoeda);
                } else {
                    resultado2.setText(R.string.texto_msg_digite_um_valor_valido_em_moeda);
                }
            }
        });
    }
    private void executarConversao(double valorReal, String moedaDestino) {
        // Executa na thread de background
        executor.execute(() -> {
            String resultadoConversao = fazerConversaoEmBackground(valorReal, moedaDestino);
            // Posta resultado na thread principal (UI)
            mainHandler.post(() -> {
                resultado.setText(resultadoConversao);
            });
        });
    }
    private String fazerConversaoEmBackground(double valorReal, String moedaDestino) {
        try {
            Request request = new Request.Builder().url(urlApi).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONObject rates = jsonObject.getJSONObject("rates");
                double taxa = rates.getDouble(moedaDestino);
                double resultadoConversao = valorReal * taxa;
                @SuppressLint("DefaultLocale")
                String resultadoFinal = String.format("R$ %.2f = %.2f %s\n(Taxa: 1 BRL = %.4f %s)", valorReal, resultadoConversao, moedaDestino, taxa, moedaDestino);
                return resultadoFinal;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return
                getString(R.string.texto_msg_erro_ao_obter_taxa_de_cambio_verifique_sua_conexao);
    }

    private void executarConversaoInversa(double valorMoeda, String moedaOrigem) {
        // Executa na thread de background
        executor.execute(() -> {
            String resultadoConversao = fazerConversaoInversaEmBackground(valorMoeda, moedaOrigem);
            // Posta resultado na thread principal (UI)
            mainHandler.post(() -> {
                resultado2.setText(resultadoConversao);
            });
        });
    }

    private String fazerConversaoInversaEmBackground(double valorMoeda, String moedaOrigem) {
        try {
            Request request = new Request.Builder().url(urlApi).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONObject rates = jsonObject.getJSONObject("rates");

                // Taxa da API é: 1 BRL = X Moeda (Ex: 1 BRL = 0.20 USD)
                double taxaBRLParaMoeda = rates.getDouble(moedaOrigem);

                // Para converter de Moeda -> BRL, a taxa é o inverso: 1 Moeda = (1/X) BRL
                double taxaMoedaParaBRL = 1.0 / taxaBRLParaMoeda;

                double resultadoConversao = valorMoeda * taxaMoedaParaBRL;

                @SuppressLint("DefaultLocale")
                String resultadoFinal = String.format("%.2f %s = R$ %.2f\n(Taxa: 1 %s = R$ %.4f)", valorMoeda, moedaOrigem, resultadoConversao, moedaOrigem, taxaMoedaParaBRL);
                return resultadoFinal;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getString(R.string.texto_msg_erro_ao_obter_taxa_de_cambio_verifique_sua_conexao);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
