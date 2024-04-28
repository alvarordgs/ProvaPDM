package com.example.provapdm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TamaleOrderAdapter(private val context : Context, private val tamaleOrders: MutableList<TamaleOrder>): RecyclerView.Adapter<TamaleOrderAdapter.TamaleOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TamaleOrderViewHolder {
        val itemList =
            LayoutInflater.from(context).inflate(R.layout.card_view_tamale_order, parent, false)

        return TamaleOrderViewHolder(itemList)
    }

    override fun getItemCount(): Int = tamaleOrders.size

    override fun onBindViewHolder(holder: TamaleOrderViewHolder, position: Int) {
        val tamaleOrder = tamaleOrders[position]

        holder.tamaleOrderId.text = tamaleOrder.id.toString()
        holder.fillingType.text = tamaleOrder.fillingType
        holder.cheeseWeight.text = tamaleOrder.cheeseWeight.toString()
        holder.customerCPF.text = tamaleOrder.customerCPf
    }


    private fun onButtonClickEditTamaleOrder(position: Int, context: Context) {
        val tamaleOrder = tamaleOrders[position]

        val intent = Intent(context, TamaleOrderEditActivity::class.java)
        val bundle = Bundle()
        bundle.putString("tamaleOrderId", tamaleOrder.id.toString())
        bundle.putString("tamaleOrderFillingType", tamaleOrder.fillingType)
        bundle.putString("tamaleOrderCheeseWeight", tamaleOrder.cheeseWeight.toString())
        bundle.putString("tamaleOrderCustomerCPF", tamaleOrder.customerCPf)
        intent.putExtras(bundle)

        if (context is Activity) {
            context.startActivity(intent)
        }

        notifyItemChanged(position)
    }

    private fun onButtonClickDeleteTamaleOrder(position: Int, context: Context) {
        val tamaleOrder = tamaleOrders[position]

        val tamaleOrderDAO = TamaleOrderDAO(context)
        val wasDeleted = tamaleOrder.id?.let { tamaleOrderDAO.delete(it) }

        if(wasDeleted != null && wasDeleted) {
            notifyItemChanged(position)
            Toast.makeText(context, "Pedido excluído com sucesso", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Não foi possível excluir o pedido!", Toast.LENGTH_SHORT).show()
        }
    }

    inner class TamaleOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tamaleOrderId: TextView = itemView.findViewById(R.id.cardTitleTamaleOrder)
        val fillingType: TextView = itemView.findViewById(R.id.cardValueFillingTypeTamaleOrder)
        val cheeseWeight: TextView = itemView.findViewById(R.id.cardValueCheeseWeightTamaleOrder)
        val customerCPF: TextView = itemView.findViewById(R.id.cardValueCustomerCPFTamaleOrder)
        val editButton: Button = itemView.findViewById(R.id.cardButtonEditTamaleOrder)
        val deleteButton: Button = itemView.findViewById(R.id.cardButtonDeleteTamaleOrder)


        init {
            editButton.setOnClickListener {
                onButtonClickEditTamaleOrder(adapterPosition, context)
            }

            deleteButton.setOnClickListener {
                onButtonClickDeleteTamaleOrder(adapterPosition, context)
            }
        }

    }
}