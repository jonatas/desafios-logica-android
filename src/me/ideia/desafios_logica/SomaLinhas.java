package me.ideia.desafios_logica;

import java.util.ArrayList;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SomaLinhas extends Activity {

	private TextWatcher watcher;
	final int[] buttons = { R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7, R.id.editText8, R.id.editText9 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soma_linhas);
		watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				verificarLogica();

			}

			private void verificarLogica() {
				ArrayList<Integer> valores = new ArrayList<Integer>();
				TextView gameStatus = (TextView)findViewById(R.id.gameStatus);
				for (int i = 0; i < buttons.length; i++) {
					try {
						EditText input = (EditText)findViewById(buttons[i]);
						int valor = Integer.parseInt(input.getText().toString());
						if (!valores.contains(valor)) {
							valores.add(valor);
						} else {
							gameStatus.setText("Repetiu o " + valor);
							input.findFocus();
							return;
						}
						// System.out.println("Botao: " + i + " = " +
						// valores.get(i));
					} catch (NumberFormatException nf) {
						System.out.println("Botao: " + i + " nao informado");
					}
				}
				if (valores.size() == buttons.length) {
					int[][] somasQueFecha10 = { { 0, 1 }, { 2, 3, 5 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
					for (int[] posicoes : somasQueFecha10) {
						int valoresDasPosicoes = 0;
						for (int posicao : posicoes) {
							valoresDasPosicoes += valores.get(posicao);
						}
						if (valoresDasPosicoes != 10) {
							gameStatus.setText("As posi›es " + posicoes + " n‹o somam " + valoresDasPosicoes + " != 10");
							return;
						}
					}
					gameStatus.setText("ParabŽns! voc passou de fase!");
				} else {
					gameStatus.setText("Ainda faltam " + (buttons.length - valores.size()) + " bot›es...");
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		};
		for (int button : buttons) {
			((EditText)findViewById(button)).addTextChangedListener(watcher);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_soma_linhas, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.ver_resposta) {
			int[] respostaCerta = { 9, 1, 5, 2, 8, 3, 7, 4, 6 };
			for (int i = 0; i < buttons.length; i++) {
				((EditText)findViewById(buttons[i])).setText("" + respostaCerta[i]);
			}
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
