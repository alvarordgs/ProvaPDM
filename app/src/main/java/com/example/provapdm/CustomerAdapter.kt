package com.example.provapdm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


    private fun onButtonClickEditCustomer(position: Int, context: Context) {
        val customer = customers[position]

        val intent = Intent(context, CustomerEditActivity::class.java)
        val bundle = Bundle()
        bundle.putString("customerName", customer.name)
        bundle.putString("customerCPF", customer.cpf)
        bundle.putString("customerPhone", customer.phone)
        bundle.putString("customerEmail", customer.email)
        bundle.putInt("customerIsActive", customer.isActive)
        intent.putExtras(bundle)

        if (context is Activity) {
            context.startActivity(intent)
        }

        notifyItemChanged(position)
    }

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cardTitleCustomer)
        val cpf: TextView = itemView.findViewById(R.id.cardValueCpfCustomer)
        val phone: TextView = itemView.findViewById(R.id.cardValuePhoneCustomer)
        val email: TextView = itemView.findViewById(R.id.cardValueEmailCustomer)
        val isActive: TextView = itemView.findViewById(R.id.cardValueActiveCustomer)
        val editButton: Button = itemView.findViewById(R.id.cardButtonEditCustomer)


        init {
            editButton.setOnClickListener {
                onButtonClickEditCustomer(adapterPosition, context)
            }
        }

    }
}