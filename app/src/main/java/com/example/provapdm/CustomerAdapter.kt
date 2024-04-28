package com.example.provapdm

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapter(private val context : Context, private val customers: MutableList<Customer>): RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemLista =
            LayoutInflater.from(context).inflate(R.layout.card_view_customer, parent, false)

        return CustomerViewHolder(itemLista)
    }

    override fun getItemCount(): Int = customers.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]

        holder.name.text = customer.name
        holder.cpf.text = customer.cpf
        holder.phone.text = customer.phone
        holder.email.text = customer.email

        if (customer.isActive == 1) {
            holder.isActive.text = "Ativo"
            holder.isActive.setTextColor(Color.GREEN)
        } else if (customer.isActive == 0) {
            holder.isActive.text = "Inativo"
            holder.isActive.setTextColor(Color.RED)
        }
    }

    /*
    private fun onButtonClickAlterar(position: Int, context: Context) {
        val cliente = clientes[position]

        val intent = Intent(context, AlterarDadosClienteActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nomeCliente", cliente.nome)
        bundle.putString("cpfCliente", cliente.cpf)
        bundle.putString("telefoneCliente", cliente.telefone)
        bundle.putString("emailCliente", cliente.email)
        intent.putExtras(bundle)
        if (context is Activity) {
            context.startActivity(intent)
        }

        notifyItemChanged(position)
    }

    private fun onButtonClickExcluir(position: Int) {
        val cliente = clientes[position]

        val clienteDAO = ClienteDAO(context)
        val desativou = clienteDAO.desativarCliente(cliente)

        if (desativou) {
            cliente.situacao = "INATIVO"
            clientes[position] = cliente
            notifyItemChanged(position)
            Toast.makeText(context, "Cliente desativado com sucesso!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Erro ao desativar o cliente.", Toast.LENGTH_LONG).show()
        }
    }*/

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cardTitleCustomer)
        val cpf: TextView = itemView.findViewById(R.id.cardValueCpfCustomer)
        val phone: TextView = itemView.findViewById(R.id.cardValuePhoneCustomer)
        val email: TextView = itemView.findViewById(R.id.cardValueEmailCustomer)
        val isActive: TextView = itemView.findViewById(R.id.cardValueActiveCustomer)

        /*
        init {
            buttonAlterar.setOnClickListener {
                onButtonClickAlterar(adapterPosition, context)
            }

            buttonExcluir.setOnClickListener{
                onButtonClickExcluir(adapterPosition)
            }

        }*/

    }
}