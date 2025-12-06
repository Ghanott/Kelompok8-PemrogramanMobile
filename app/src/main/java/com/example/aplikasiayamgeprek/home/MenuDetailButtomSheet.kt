package com.example.aplikasiayamgeprek.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.aplikasiayamgeprek.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.aplikasiayamgeprek.home.ChartManager
import com.example.aplikasiayamgeprek.home.ChartModel
import com.google.android.material.bottomnavigation.BottomNavigationView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuDetailButtomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuDetailButtomSheet : BottomSheetDialogFragment() {
    private var quantity = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_menu_detail_buttom_sheet, container, false)

        val imgFood = view.findViewById<ImageView>(R.id.imgFoodDetail)
        val tvName = view.findViewById<TextView>(R.id.tvNameDetail)
        val tvPrice = view.findViewById<TextView>(R.id.tvPriceDetail)
        val tvDeskripsiProduk = view.findViewById<TextView>(R.id.tvDeskripsiProduct)
        val btnAddToCart = view.findViewById<Button>(R.id.btnAddToCart)
        val tvQty = view.findViewById<TextView>(R.id.tvQty)
        val btnMinus = view.findViewById<TextView>(R.id.btnMinus)
        val btnPlus = view.findViewById<TextView>(R.id.btnPlus)


        val menu = arguments?.getSerializable(MENU) as? MenuModel ?: return view


        imgFood.setImageResource(menu.image)
        tvName.text = menu.name
        tvDeskripsiProduk.text = menu.description

        val basePrice = parsePrice(menu.price)
        updatePrice(tvPrice, basePrice)

        tvQty.text = quantity.toString()

        btnPlus.setOnClickListener (){
            quantity++
            updatePrice(tvPrice, basePrice)
            tvQty.text = quantity.toString()

        }
        btnMinus.setOnClickListener () {
            if (quantity > 1) {
                quantity--
                tvQty.text = quantity.toString()
                updatePrice(tvPrice, basePrice)
            }
        }
        btnAddToCart.setOnClickListener {

            // Ambil total harga dalam angka
            val basePrice = parsePrice(menu.price)
            val totalPrice = basePrice * quantity

            // Buat item keranjang
            val item = ChartModel(
                name = menu.name,
                desc = menu.description,
                price = basePrice,
                image = menu.image,
                qty = quantity
            )

            // Masukkan ke keranjang
            ChartManager.addToChart(item)

            dismiss()  // Tutup bottom sheet

            val buttomNav = requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)
            buttomNav.selectedItemId = R.id.chartFragment
        }

        return view
    }

    private fun updatePrice(tv: TextView, basePrice: Int) {
        val total = basePrice * quantity
        tv.text = formatRupiah(total)
    }


    private fun parsePrice(priceText: String): Int {
        val digits = priceText.filter { it.isDigit() }
        return digits.toIntOrNull() ?: 0
    }


    private fun formatRupiah(amount: Int): String {
        return "Rp. %,d".format(java.util.Locale("in", "ID"), amount)
            .replace(',', '.')        // 20,000 -> 20.000
    }

    companion object {
        private const val MENU = "MENU"

        fun newInstance(menu: MenuModel): MenuDetailButtomSheet {
            return MenuDetailButtomSheet().apply {
                arguments = Bundle().apply {
                    putSerializable(MENU, menu)
                }
            }
        }
    }

}