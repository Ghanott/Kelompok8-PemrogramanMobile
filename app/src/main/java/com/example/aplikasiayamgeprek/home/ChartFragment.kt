package com.example.aplikasiayamgeprek.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiayamgeprek.R
import com.example.aplikasiayamgeprek.adapter.ChartAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {
    private lateinit var adapter: ChartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
//        val title = view.findViewById<TextView>(R.id.textViewChart)
//        title.text = "ini adalah halaman chart"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvCart = view.findViewById<RecyclerView>(R.id.rvCart)
        val txtTotalPrice = view.findViewById<TextView>(R.id.txtTotalPrice)
        val txtTotalItems = view.findViewById<TextView>(R.id.txtTotalItems)

        adapter = ChartAdapter(ChartManager.getCartItems()) {
            updateTotal(txtTotalPrice, txtTotalItems)
        }

        rvCart.layoutManager = LinearLayoutManager(requireContext())
        rvCart.adapter = adapter

        updateTotal(txtTotalPrice, txtTotalItems)
    }

    private fun updateTotal(price: TextView, items: TextView) {
        price.text = "Rp.${ChartManager.getTotalPrice()}"
        items.text = ChartManager.getTotalItems().toString()
    }
}