package com.android.yugi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;



public class Deck extends Activity 
{
	String invataque = "";
	String invdefesa = "";
	int im1 = 0; // identificar se mão está vazia
	int sacarM1 = 0;
	int im2 = 0;
	int im3 = 0;
	// int ip1 = 0;//espaço na mão
	int iModoAtaque = 0;
	int iModoDefesa = 0;
	int monstroNoCampo = 0;
	int iSeminvocarMais = 0;
	int ib1 = 0;// limite do ataque
	int ib2 = 0;
	int x = 0;// ordem de cartas sorteadas cpu
	int icartabaixo = 0;
	int iturno = 0;
	int ivida1 = 4000;
	int ivida2 = 4000;
	int isacar = 0;
	int posicao = 0;

	// estancia a classe
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		// chama a tela campo.xml
		setContentView(R.layout.campo);

		// cria um objeto ImageView sacar relacionado com ImageView deck2 do
		// campo.xml
		final ImageView sacar = (ImageView) this.findViewById(R.id.deck2);
		final Button bpasso = (Button) this.findViewById(R.id.passa);

		final ImageView p1 = (ImageView) this.findViewById(R.id.jogador1);

		final ImageView p2 = (ImageView) this.findViewById(R.id.jogador2);
		final ImageView zoom1 = (ImageView) this.findViewById(R.id.zoom1);
		final FrameLayout zoomcarta = (FrameLayout) this.findViewById(R.id.cartazoom);
		final ImageView cartadefesa = (ImageView) this.findViewById(R.id.defesa);

		// cria objetos ImageView do local aonde ficam as cartas na mão
		final ImageView m1 = (ImageView) this.findViewById(R.id.mao1);
		final ImageView m2 = (ImageView) this.findViewById(R.id.mao2);
		final ImageView m3 = (ImageView) this.findViewById(R.id.mao3);

		// TextView do ataque
		final TextView atk1 = (TextView) this.findViewById(R.id.ataque1);
		final TextView atk2 = (TextView) this.findViewById(R.id.ataque2);
		final TextView atk3 = (TextView) this.findViewById(R.id.ataque3);
		final TextView bAtk1 = (TextView) this.findViewById(R.id.bAtaque1);
		final TextView bAtk2 = (TextView) this.findViewById(R.id.bAtaque2);

		// TextView da defesa
		final TextView def1 = (TextView) this.findViewById(R.id.defesa1);
		final TextView def2 = (TextView) this.findViewById(R.id.defesa2);
		final TextView def3 = (TextView) this.findViewById(R.id.defesa3);
		final TextView bDef1 = (TextView) this.findViewById(R.id.bDefesa1);
		final TextView bDef2 = (TextView) this.findViewById(R.id.bDefesa2);

		// TextView da vida
		final TextView vida1 = (TextView) this.findViewById(R.id.lp1);
		final TextView vida2 = (TextView) this.findViewById(R.id.lp2);

		// crio 3 vetores já com atribuições: baralho, ataque e defesa
		final int[] baralho = { R.drawable.ckuriboh, R.drawable.cbranco,
				R.drawable.cobelisco, R.drawable.cslife, R.drawable.ckuriboh,
				R.drawable.cbranco, R.drawable.cobelisco, R.drawable.cslife };
		final String[] atk = { "50", "100", "200", "300", "50", "100", "200",
				"300" };
		final String[] def = { "300", "200", "100", "50", "300", "200", "100",
				"50" };

		// Cria uma lista de números com ArrayList + Collections + shuffle para
		// sorteiar números de 0 a 3 sem repetir
		final List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			numeros.add(i);
		}
		Collections.shuffle(numeros);

		// ////////////////////////////////////////////////////////////////////cpu//////////////////////////////////////////////////////////////////

		// cria objetos ImageView do local aonde ficam as cartas na mão
		final ImageView cpum1 = (ImageView) this.findViewById(R.id.cpumao1);
		final ImageView cpum2 = (ImageView) this.findViewById(R.id.cpumao2);
		final ImageView cpum3 = (ImageView) this.findViewById(R.id.cpumao3);

		// Strings para ataque e defesa

		String cpuM1Atk = "";
		String cpuM2Atk = "";
		String cpuM3Atk = "";
		String cpuM1Def = "";
		String cpuM2Def = "";
		String cpuM3Def = "";

		// crio 3 vetores já com atribuições: baralho, ataque e defesa
		final int[] cpubaralho = { R.drawable.ckuriboh, R.drawable.cbranco,
				R.drawable.cobelisco, R.drawable.cslife, R.drawable.ckuriboh,
				R.drawable.cbranco, R.drawable.cobelisco, R.drawable.cslife };
		final String[] cpuatk = { "50", "100", "200", "300", "50", "100",
				"200", "300" };
		final String[] cpudef = { "300", "200", "100", "50", "300", "200",
				"100", "50" };

		// Cria uma lista de números com ArrayList + Collections + shuffle para
		// sorteiar números de 0 a 3 sem repetir
		final List<Integer> cpunumeros = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			cpunumeros.add(i);
		}
		Collections.shuffle(cpunumeros);

		// //////////////////////////////////////////////////////////saque///////////////////////////////////////////////////////////

		if (isacar == 0)// ////////////////////isacar1
		{
			// usa o objeto sacar para ter uma ação se for clicado
			sacar.setOnClickListener(new View.OnClickListener() 
			{
				int z = 0;

				@Override
				public void onClick(View v) 
				{
					if (sacarM1 == 0) {
						m1.setVisibility(View.VISIBLE);
						// atribui uma carta, valor de ataque e valor de defesa
						// a primeira carta da mão
						atk1.setText(atk[numeros.get(z)]);
						def1.setText(def[numeros.get(z)]);
						m1.setImageResource(baralho[numeros.get(z)]);
						z++;

						sacarM1 = 1;
					} 
					else if (im2 == 0) 
					{
						m2.setVisibility(View.VISIBLE);
						// atribui uma carta, valor de ataque e valor de defesa
						// a segunda carta da mão
						atk2.setText(atk[numeros.get(z)]);
						def2.setText(def[numeros.get(z)]);
						m2.setImageResource(baralho[numeros.get(z)]);
						z++;
						im2++;
					} 
					else if (im3 == 0)
					{
						m3.setVisibility(View.VISIBLE);
						// atribui uma carta, valor de ataque e valor de defesa
						// a terceira carta da mão
						atk3.setText(atk[numeros.get(z)]);
						def3.setText(def[numeros.get(z)]);
						m3.setImageResource(baralho[numeros.get(z)]);
						z++;
						im3++;
					}
					isacar = 1;// /////////////////////?
				}

			});

		}

		// /////////////////////////////////////////////////////////////////////m1//////////////////////////////////////////////////////////////
		// ação da primeira carta da mão for clicada
		m1.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				if (sacarM1 == 1) {
					// chama o metódo showPopup
					showPopup(Deck.this);
				}
			}

			private void showPopup(final Activity context) 
			{
				// cria um objeto relacionado a tela "opcao"
				LinearLayout viewOpcao = (LinearLayout) context
						.findViewById(R.id.opcao);
				// cria um objeto do tipo LayoutInflater
				LayoutInflater layoutInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// cria um objeto do tipo View e atribui os valores do
				// layoutInflater e viewOpcao
				View layout = layoutInflater.inflate(R.layout.opcao, viewOpcao);

				// cria um objeto do tipo PopupWindow e defina suas propriedades
				final PopupWindow popup = new PopupWindow(context);
				popup.setContentView(layout);
				popup.setWidth(240);
				popup.setHeight(108);
				popup.setFocusable(true);

				// defina o posicionamento do popup na tela
				popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

				// cria um objeto do tipo botão
				Button ataque = (Button) layout.findViewById(R.id.ataque);
				// ação do primeiro botão do popup se for clicado
				ataque.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						if (sacarM1 == 1) 
						{
							// cria um objeto do tipo Drawable e atribui com
							// valor da primeira carta da mão
							Drawable copiaCarta = m1.getDrawable();
							// copia a carta do objeto copiaCarta e joga no
							// campo de batalha
							p1.setImageDrawable(copiaCarta);
							// remove a carta da mão
							m1.setVisibility(View.GONE);
							
							Matrix matrix = new Matrix();
							matrix.postRotate(0, 82,80);
							matrix.postScale((float) 0.3, (float) 0.3);
							p1.setScaleType(ScaleType.MATRIX);
							p1.setImageMatrix(matrix);

							p1.setVisibility(View.VISIBLE);
														
							sacarM1 = 0;
							iModoAtaque = 1;
							posicao = 0;
							icartabaixo = 0;

							// cria um objeto do tipo CharSequence e atribui o
							// valor do ataque na primeira carta da mão
							CharSequence copiatexto = (CharSequence) atk1
									.getText();
							// joga o valor do copiatexto para o bAtk1 no campo
							// de batalha
							bAtk1.setText(copiatexto);
							// Remove o valor do ataque na primeira carta da mão
							atk1.setText(null);

							// cria um objeto do tipo CharSequence e atribui o
							// valor da defesa na primeira carta da mão
							CharSequence copiatexto2 = (CharSequence) def1
									.getText();
							// joga o valor do copiatexto2 para o bDef1 no campo
							// de batalha
							bDef1.setText(copiatexto2);
							// Remove o valor da defesa na primeira carta da mão
							def1.setText(null);
						}

						// fecha a popup
						popup.dismiss();
					}
				});
				// cria um objeto do tipo botão
				Button defesa = (Button) layout.findViewById(R.id.defesa);
				// ação do segundo botão do popup se for clicado
				defesa.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						 p1.setVisibility(View.GONE);
						 p1.setVisibility(View.VISIBLE);
						Drawable copiacarta = m1.getDrawable();
						cartadefesa.setImageDrawable(copiacarta);
						// cartadefesa.setVisibility(View.GONE);

						CharSequence copiatexto = (CharSequence) atk1.getText();
						invataque = (String) copiatexto;

						CharSequence copiatexto2 = (CharSequence) def1
								.getText();
						invdefesa = (String) copiatexto2;

						icartabaixo = 1;

						// atribui uma imagem a p1
						p1.setImageResource(R.drawable.cverso);
						
						Matrix matrix = new Matrix();
						matrix.postRotate(0, 82,80);
						matrix.postScale((float) 0.3, (float) 0.3);
						p1.setScaleType(ScaleType.MATRIX);
						p1.setImageMatrix(matrix);

						// remove a carta da mão
						m1.setVisibility(View.GONE);
						// ip1++;

						// remove os valores do atk1 e def1
						atk1.setText(null);
						def1.setText(null);

						// fecha a popup
						popup.dismiss();

						sacarM1 = 0;
						posicao = 1;
					}
				});
				// cria um objeto do tipo botão
				Button zoom = (Button) layout.findViewById(R.id.lupa);
				// ação do terceiro botão do popup se for clicado
				zoom.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						// atribui o valor de visibilidade para zoomcarta
						zoomcarta.setVisibility(View.VISIBLE);
						// cria um objeto do tipo Drawable e atribui o valor da
						// primeira carta da mão
						Drawable copiaCarta = m1.getDrawable();
						// atribui o valor do copiaCarta para zoom1
						zoom1.setImageDrawable(copiaCarta);
						// fecha a popup
						popup.dismiss();

						// ação da imagem se for clicada
						zoom1.setOnClickListener(new View.OnClickListener() 
						{
							@Override
							public void onClick(View v) 
							{
								// remove a carta da tela
								zoomcarta.setVisibility(View.GONE);
							}
						});
					}
				});
			}
		});

		
		// /////////////////////////////////////////////atacar///////////////////////////////////////////////////////////////////////

		// campo de invocaçao p1
		p1.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				showPopup2(Deck.this);
			}

			// menu de batalha
			private void showPopup2(final Activity context) 
			{
				LinearLayout viewOpcao = (LinearLayout) context
						.findViewById(R.id.batalhaopcao);
				LayoutInflater layoutInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = layoutInflater.inflate(R.layout.batalhaopcao,
						viewOpcao);

				final PopupWindow popup2 = new PopupWindow(context);
				popup2.setContentView(layout);
				popup2.setWidth(240);
				popup2.setHeight(108);
				popup2.setFocusable(true);

				popup2.showAtLocation(layout, Gravity.CENTER, 0, 0);

				Button batalhar = (Button) layout.findViewById(R.id.batalhar);
				// /////ataque
				batalhar.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						if (ib1 == 0) 
						{
							iSeminvocarMais = 1;
							ib1 = 1;
							String forca1 = bAtk1.getText().toString();
							int Forca1 = Integer.parseInt(forca1);

							String forca2 = bAtk2.getText().toString();
							int Forca2;
							if (forca2.matches("")) 
							{
								Forca2 = 0;
							} else 
							{
								Forca2 = Integer.parseInt(forca2);
							}

							int danototal = Forca1 - Forca2;

							if (Forca1 > Forca2) 
							{
								String Vida2 = vida2.getText().toString();
								int life = Integer.parseInt(Vida2);

								int total = life - danototal;

								vida2.setText("" + total);

								bAtk2.setText(null);
								bDef2.setText(null);
								p2.setVisibility(View.GONE);
							} 
							else if (Forca2 > Forca1) 
							{
								String Vida1 = vida1.getText().toString();
								int life = Integer.parseInt(Vida1);

								int total = life - (danototal * (-1));

								vida1.setText("" + total);

								bAtk1.setText(null);
								bDef1.setText(null);
								p1.setVisibility(View.GONE);
							} 
							else 
							{
								bAtk1.setText(null);
								bAtk2.setText(null);
								bDef1.setText(null);
								bDef2.setText(null);
								p1.setVisibility(View.GONE);
								p2.setVisibility(View.GONE);
							}
							// iturno--;
							popup2.dismiss();
						}
						popup2.dismiss();
					}
				});
				// cria um objeto do tipo botão
				Button zoom2 = (Button) layout.findViewById(R.id.lupa2);
				// ação do terceiro botão do popup se for clicado
				zoom2.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						if (icartabaixo == 0) 
						{
							// atribui o valor de visibilidade para zoomcarta
							zoomcarta.setVisibility(View.VISIBLE);
							// cria um objeto do tipo Drawable e atribui o valor
							// da primeira carta da mão
							Drawable copiaCarta = p1.getDrawable();
							// atribui o valor do copiaCarta para zoom1
							zoom1.setImageDrawable(copiaCarta);
							// fecha a popup
							popup2.dismiss();
						} 
						else 
						{
							zoomcarta.setVisibility(View.VISIBLE);
							Drawable copiaCarta = cartadefesa.getDrawable();
							zoom1.setImageDrawable(copiaCarta);
							popup2.dismiss();
						}

						// ação da imagem se for clicada
						zoom1.setOnClickListener(new View.OnClickListener() 
						{
							@Override
							public void onClick(View v) 
							{
								// remove a carta da tela
								zoomcarta.setVisibility(View.GONE);
							}
						});
					}
				});
				// virar posicão
				Button flip = (Button) layout.findViewById(R.id.mudarposicao);

				flip.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						if (icartabaixo == 1) 
						{
							p1.setVisibility(View.VISIBLE);

							Drawable copiacarta = cartadefesa.getDrawable();
							p1.setImageDrawable(copiacarta);
							//p1.setVisibility(View.VISIBLE);

							bAtk1.setText(invataque);
							bDef1.setText(invdefesa);

							icartabaixo = 0;
							posicao = 0;
							// p1.setVisibility(View.GONE);
						}
						// if(ip1 == 0)
						// {
						else if((icartabaixo == 0)&&(posicao == 0))
						{
							/*Matrix matrix = new Matrix();
							p1.setVisibility(View.VISIBLE);
							matrix.postScale((float) 0.3, (float) 0.3);
							p1.setScaleType(ScaleType.MATRIX);
							p1.setImageMatrix(matrix); */
							 
								/*p1.setVisibility(View.VISIBLE);
								Drawable copiacarta = cartadefesa.getDrawable();
								
								p1.setImageDrawable(copiacarta);*/
								
								Matrix matrix = new Matrix();
								matrix.postRotate(270, 82,80);
								matrix.postScale((float) 0.3, (float) 0.3);
								p1.setScaleType(ScaleType.MATRIX);
								p1.setImageMatrix(matrix);
								
						
								posicao = 1;
								iModoDefesa = 1;
								// vida1.setText(""+count);
								// popup2.dismiss();

							} else if (posicao == 1){
								p1.setVisibility(View.VISIBLE);
								Matrix matrix = new Matrix();
								//p1.setVisibility(View.VISIBLE);
								matrix.postScale((float) 0.3, (float) 0.3);
								matrix.postRotate(0);
								p1.setScaleType(ScaleType.MATRIX);
								p1.setImageMatrix(matrix);

								posicao = 0;
								iModoDefesa = 0;

								// vida1.setTex5t(""+count);
								// popup2.dismiss();
								// ip1++;
							}

						

						popup2.dismiss();
					}
				});

			}
		});

		// //////////////////////////////////////////////////////////////////cpu/////////////////////////////////////
		bpasso.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				passaVez();
				// iturno++;

				if (ib1 == 1) {
					ib1--;
				}
			}

			public void passaVez() {
				p2.setVisibility(View.VISIBLE);
				p2.setImageResource(cpubaralho[cpunumeros.get(x)]);
				bAtk2.setText(cpuatk[cpunumeros.get(x)]);
				bDef2.setText(cpudef[cpunumeros.get(x)]);
				x++;

				if (icartabaixo == 0) {
					if (iModoDefesa == 0) {

						String forca1 = bAtk2.getText().toString();
						int Forca1 = Integer.parseInt(forca1);

						int Forca2;
						String forca2 = bAtk1.getText().toString();

						if (forca2.matches("")) {
							Forca2 = 0;
						} else {
							Forca2 = Integer.parseInt(forca2);
						}

						int danototal = Forca1 - Forca2;

						// ////////////////////////////dano do ataque cpu
						if (Forca1 > Forca2) {
							String Vida1 = vida1.getText().toString();
							int life = Integer.parseInt(Vida1);

							int total = life - danototal;

							vida1.setText("" + total);

							bAtk1.setText(null);
							bDef1.setText(null);
							p1.setVisibility(View.GONE);

							// ib2++;
						} else if (Forca2 > Forca1) {
							String Vida2 = vida2.getText().toString();
							int life = Integer.parseInt(Vida2);

							int total = life - (danototal * (-1));

							vida2.setText("" + total);

							bAtk2.setText(null);
							bDef2.setText(null);
							p2.setVisibility(View.GONE);

							iSeminvocarMais = 0;

							// ib2++;
						} else {
							bAtk1.setText(null);
							bAtk2.setText(null);
							bDef1.setText(null);
							bDef2.setText(null);
							p1.setVisibility(View.GONE);
							p2.setVisibility(View.GONE);

							// ib2++;
						}
					} else {
						String forca1 = bAtk2.getText().toString();
						int Forca1 = Integer.parseInt(forca1);

						int Forca2;
						String forca2 = bDef1.getText().toString();

						if (forca2.matches("")) {
							Forca2 = 0;
						} else {
							Forca2 = Integer.parseInt(forca2);
						}

						int danototal = Forca1 - Forca2;

						// ////////////////////////////dano do ataque cpu
						if (Forca1 > Forca2) {

							p1.setVisibility(View.GONE);
							bAtk1.setText(null);
							bDef1.setText(null);

							// ib2++;
						} else if (Forca2 > Forca1) {
							String Vida2 = vida2.getText().toString();
							int life = Integer.parseInt(Vida2);

							int total = life - (danototal * (-1));

							vida2.setText("" + total);

							posicao = 1;

							// ib2++;
						} else {
							bAtk1.setText(null);
							bAtk2.setText(null);
							bDef1.setText(null);
							bDef2.setText(null);
							p1.setVisibility(View.GONE);
							p2.setVisibility(View.GONE);

							// ib2++;
						}
					}

				} else// carta de face para baixo icartabaixo ==1
				{

					Drawable copiacarta = cartadefesa.getDrawable();
					p1.setImageDrawable(copiacarta);
					// p1.setVisibility(View.VISIBLE);

					bAtk1.setText(invataque);
					bDef1.setText(invdefesa);

					
					
					Matrix matrix = new Matrix();
					matrix.postRotate(270, 82,80);
					matrix.postScale((float) 0.3, (float) 0.3);
					p1.setScaleType(ScaleType.MATRIX);
					p1.setImageMatrix(matrix);

					String forca1 = bAtk2.getText().toString();
					int Forca1 = Integer.parseInt(forca1);

					int Forca2;
					String forca2 = bDef1.getText().toString();

					if (forca2.matches("")) {
						Forca2 = 0;
					} else {
						Forca2 = Integer.parseInt(forca2);
					}

					int danototal = Forca1 - Forca2;

					// ////////////////////////////dano do ataque cpu
					if (Forca1 > Forca2) {

						p1.setVisibility(View.GONE);
						bAtk1.setText(null);
						bDef1.setText(null);

						// ib2++;
					} else if (Forca2 > Forca1) {
						String Vida2 = vida2.getText().toString();
						int life = Integer.parseInt(Vida2);

						int total = life - (danototal * (-1));

						vida2.setText("" + total);

						p1.setImageDrawable(copiacarta);
						p1.setVisibility(View.VISIBLE);

						posicao = 1;

						// ib2++;
					}

					icartabaixo = 0;
				}

				isacar = 0;

				// /cpudef1.setText(def[numeros.get(z)]);
				/*
				 * if(icpum1 == 0) { //cpum1.setVisibility(View.VISIBLE);
				 * //atribui uma carta, valor de ataque e valor de defesa a
				 * primeira carta da mão
				 * ///cpuatk1.setText(atk[numeros.get(z)]);
				 * ///cpudef1.setText(def[numeros.get(z)]);
				 * cpum1.setImageResource(cpubaralho[numeros.get(x)]); x++;
				 * icpum1++; } if (icpum2 == 0) {
				 * //cpum2.setVisibility(View.VISIBLE); //atribui uma carta,
				 * valor de ataque e valor de defesa a segunda carta da mão
				 * ///cpuatk2.setText(atk[numeros.get(z)]);
				 * ///cpudef2.setText(def[numeros.get(z)]);
				 * cpum2.setImageResource(cpubaralho[numeros.get(x)]); x++;
				 * icpum2++; } if(icpum3 == 0) {
				 * //cpum3.setVisibility(View.VISIBLE); //atribui uma carta,
				 * valor de ataque e valor de defesa a terceira carta da mão
				 * ///cpuatk3.setText(atk[numeros.get(z)]);
				 * ///cpudef3.setText(def[numeros.get(z)]);
				 * cpum3.setImageResource(cpubaralho[numeros.get(x)]); x++;
				 * icpum3++; }
				 */
			}

		});

	}
}
// @android:style/Theme.NoTitleBar.Fullscreen"

/*

p1.setVisibility(View.VISIBLE);
Drawable copiacarta = cartadefesa.getDrawable();

p1.setImageDrawable(copiacarta);

Matrix matrix = new Matrix();
matrix.postRotate(270, p1.getWidth(),p1.getHeight());
matrix.postScale((float) 0.3, (float) 0.3);
p1.setScaleType(ScaleType.MATRIX);
p1.setImageMatrix(matrix);

*/
