package persistencia;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ifcig.agendaandroid.R;
import com.example.ifcig.agendaandroid.TelaEditarActivity;

public class UsuarioAdapter extends BaseAdapter {
	private Context context;
	private List<ContatoDTO> list;
	
	public UsuarioAdapter(Context context, List<ContatoDTO> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0).getId();
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		final int auxPosition = position;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_contatos, null);
		
		TextView nome = (TextView) layout.findViewById(R.id.nome);
		nome.setText(list.get(position).getNome());

		TextView email = (TextView) layout.findViewById(R.id.email);
		email.setText(list.get(position).getEmail());

		TextView celular = (TextView) layout.findViewById(R.id.celular);
		celular.setText(list.get(position).getCelular());
		
		
		ImageButton editarBt = (ImageButton) layout.findViewById(R.id.imageButtonEditar);
		editarBt.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, TelaEditarActivity.class);
				intent.putExtra("id", list.get(auxPosition).getId());
				intent.putExtra("nome", list.get(auxPosition).getNome());
				intent.putExtra("email", list.get(auxPosition).getEmail());
				intent.putExtra("celular", list.get(auxPosition).getCelular());

				context.startActivity(intent);
			}
		});
		
		ImageButton deletarBt = (ImageButton) layout.findViewById(R.id.imageButtonExcluir);
		deletarBt.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				DataBase bd = new DataBase(context);
				bd.deletar(list.get(auxPosition));
				
				layout.setVisibility(View.GONE);
			}
		});
		
		return layout;
	}

}
