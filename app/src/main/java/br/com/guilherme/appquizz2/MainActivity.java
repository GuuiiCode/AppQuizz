package br.com.guilherme.appquizz2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Creating by Guilherme Alves in 10-08-2019
 **/

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView txt_pergunta;
    private RadioGroup radioGroup;
    private RadioButton opcaoA;
    private RadioButton opcaoB;
    private RadioButton opcaoC;
    private RadioButton opcaoD;
    private RadioButton opcaoE;
    private Button btnOk;

    String[] pergunta = {
            "1. Quantos planetas Terra cabem dentro no Sol?",
            "2. Em que lugar vivem mais cangurus do que pessoas?",
            "3. Quantos olhos a maior parte das aranhas têm?",
            "4. Qual das alternativas contém apenas nomes de rios?",
            "5. Quanto mede uma girafa?",
            "6. Qual a ciência que estuda a atmosfera da Terra e a climatologia?",
            "7. Quanto tempo garrafas de plástico e jornal, respectivamente, demoram para se decompor?",
            "8. Em que ordem aparecem as cores do arco-íris sempre?",
            "9. Nesses pares, ambos são mamíferos:",
            "10. Qual das alternativas contém apenas animais cujos esqueletos são externos?"
    };

    String[] respostaA = {
            "a) Um milhão",
            "a) Indonésia",
            "a) Dois",
            "a) São Francisco, Douro, Antártico",
            "a) 4 metros",
            "a) Astronomia",
            "a) 1000 anos e 1 ano",
            "a) Amarelo, laranja, vermelho, azul, anil (ou índigo), verde e violeta",
            "a) Morcegos e galinhas",
            "a) Caracois, caranguejos e lagostas"
    };

    String[] respostaB = {
            "b) Cem",
            "b) Nova Zelândia",
            "b) Quatro",
            "b) Nilo, Amazonas, Mississípi",
            "b) 2 metros",
            "b) Metereologia",
            "b) 450 anos e 6 semanas",
            "b) Amarelo, violeta, laranja, verde, vermelho, anil (ou índigo) e azul",
            "b) Baleia azul e golfinhos",
            "b) Besouros, peixes e formigas"
    };

    String[] respostaC = {
            "c) Seiscentos",
            "c) Austrália",
            "c) Quatro pares",
            "c) Cáspio, Vermelho, Reno",
            "c) Entre 5 e 6 metros",
            "c) Dispersão atmosférica",
            "c) 100 anos e 10 semanas",
            "c) Vermelho, laranja, violeta, anil (ou índigo), azul, verde e amarelo",
            "c) Girafas e tartarugas",
            "c) Caracois, lulas e aranhas"
    };

    String[] respostaD = {
            "d) Cento e cinquenta",
            "d) Papua-Nova Guiné",
            "d) Seis",
            "d) Tocantins, Bering, Ganges",
            "d) 2,5 metros",
            "d) Meteorologia",
            "d) 1 milhão de anos e 1 semana",
            "d) Vermelho, laranja, amarelo, branco, verde, azul e violeta",
            "d) Porcos e pinguins",
            "d) Borboletas, caranguejos e peixes"
    };

    String[] respostaE = {
            "e) Dois milhões",
            "e) África do Sul",
            "e) Um",
            "e) Danúbio, Jordão, Morto",
            "e) Entre 4,8 e 5,5 metros",
            "e) Horologia",
            "e) 500 anos e 5 meses",
            "e) Vermelho, laranja, amarelo, verde, azul, anil (ou índigo) e violeta",
            "e) Macacos e sapos",
            "e) Lagostas, polvos e escorpiões"
    };

    int[] listaResposta = new int[pergunta.length];
    int[] listaGabarito = {1, 3, 3, 2, 5, 4, 2, 5, 2, 1,};
    int respotasCorretas = 0;
    int numeroPergunta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camposDeInicializacao();
        atualizaPerguntas(btnOk);

        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.radBtn1:
                Log.i("s", "Opcao 1");
                listaResposta[numeroPergunta - 1] = 1;
                break;
            case R.id.radBtn2:
                Log.i("s", "Opcao 2");
                listaResposta[numeroPergunta - 1] = 2;
                break;
            case R.id.radBtn3:
                Log.i("s", "Opcao 3");
                listaResposta[numeroPergunta - 1] = 3;
                break;
            case R.id.radBtn4:
                Log.i("s", "Opcao 4");
                listaResposta[numeroPergunta - 1] = 4;
                break;
            case R.id.radBtn5:
                Log.i("s", "Opcao 5");
                listaResposta[numeroPergunta - 1] = 5;
        }
        componentesHabilitados();
    }

    public void atualizaPerguntas(View v) {

        if (numeroPergunta == pergunta.length) {
            reset();
            confereResultado();
        } else {
            txt_pergunta.setText(pergunta[numeroPergunta]);
            opcaoA.setText(respostaA[numeroPergunta]);
            opcaoB.setText(respostaB[numeroPergunta]);
            opcaoC.setText(respostaC[numeroPergunta]);
            opcaoD.setText(respostaD[numeroPergunta]);
            opcaoE.setText(respostaE[numeroPergunta]);

            numeroPergunta++;
            radioGroup.clearCheck();
            btnOk.setText("ok");
            btnOk.setEnabled(false);
        }
    }

    private void reset() {
        respotasCorretas = 0;
        numeroPergunta = 0;
    }

    public void confereResultado() {
        int cont = 0;
        for (int num : listaResposta) {
            System.out.println(num);

            if (num == listaGabarito[cont]) {
                System.out.println("Resposta Correta!");
                respotasCorretas++;
            } else {
                System.out.println("Resposta Errada!");
            }
            cont++;
        }
        alertaAcertos();
        btnOk.setText("jogar novamente");
    }

    public void alertaAcertos() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        if (respotasCorretas == 0) {
            alertDialog.setTitle("Tente novamente!");
            alertDialog.setMessage("Você acertou " + respotasCorretas + "/" + pergunta.length + " respostas");
        } else {
            alertDialog.setTitle("Parabéns!");
            alertDialog.setMessage("Você acertou " + respotasCorretas + "/" + pergunta.length + " respostas");
        }
        alertDialog.show();
        componentesDesabilitados();
    }

    private void componentesHabilitados() {
        btnOk.setEnabled(true);
        opcaoA.setEnabled(true);
        opcaoB.setEnabled(true);
        opcaoC.setEnabled(true);
        opcaoD.setEnabled(true);
        opcaoE.setEnabled(true);
    }

    private void componentesDesabilitados() {
        opcaoA.setEnabled(false);
        opcaoB.setEnabled(false);
        opcaoC.setEnabled(false);
        opcaoD.setEnabled(false);
        opcaoE.setEnabled(false);
    }

    private void camposDeInicializacao() {
        txt_pergunta = findViewById(R.id.txt_pergunta);
        radioGroup = findViewById(R.id.radGroup);
        opcaoA = findViewById(R.id.radBtn1);
        opcaoB = findViewById(R.id.radBtn2);
        opcaoC = findViewById(R.id.radBtn3);
        opcaoD = findViewById(R.id.radBtn4);
        opcaoE = findViewById(R.id.radBtn5);
        btnOk = findViewById(R.id.btn_ok);

        btnOk.setEnabled(false);
    }
}